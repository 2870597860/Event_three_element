/**
 * Copyright 2009 www.imdict.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.lucene.analysis.cn.smart;

public class Utility {

  public static final char[] STRING_CHAR_ARRAY = new String("δ##��")
      .toCharArray();

  public static final char[] NUMBER_CHAR_ARRAY = new String("δ##��")
      .toCharArray();

  public static final char[] START_CHAR_ARRAY = new String("ʼ##ʼ")
      .toCharArray();

  public static final char[] END_CHAR_ARRAY = new String("ĩ##ĩ").toCharArray();

  public static final char[] COMMON_DELIMITER = new char[] { ',' };

  /**
   * ��Ҫ�����ķ��ţ������Ʊ�����س������еȵȡ�
   */
  public static final String SPACES = " ��\t\r\n";

  public static final int MAX_FREQUENCE = 2079997 + 80000;

  /**
   * �Ƚ�������������Ĵ�С, �ֱ�������һ��λ�ÿ�ʼ����Ƚ�, ����������Ҷ�����ĩβʱ, �������, ����δ����ĩβ�Ĵ��ڵ���ĩβ��;
   * ��δ����ĩβʱ��һλ�����, ��λ����ֵ����������С��
   * 
   * @param larray
   * @param lstartIndex larray����ʼλ��
   * @param rarray
   * @param rstartIndex rarray����ʼλ��
   * @return 0��ʾ��ȣ�1��ʾlarray > rarray, -1��ʾlarray < rarray
   */
  public static int compareArray(char[] larray, int lstartIndex, char[] rarray,
      int rstartIndex) {

    if (larray == null) {
      if (rarray == null || rstartIndex >= rarray.length)
        return 0;
      else
        return -1;
    } else {
      // larray != null
      if (rarray == null) {
        if (lstartIndex >= larray.length)
          return 0;
        else
          return 1;
      }
    }

    int li = lstartIndex, ri = rstartIndex;
    while (li < larray.length && ri < rarray.length && larray[li] == rarray[ri]) {
      li++;
      ri++;
    }
    if (li == larray.length) {
      if (ri == rarray.length) {
        // ����һֱ��ȵ�ĩβ����˷�����ȣ�Ҳ���ǽ��0
        return 0;
      } else {
        // ��ʱ������ri>rarray.length���ֻ��ri<rarray.length
        // ��ʾlarray�Ѿ�������rarrayû�н��������larray < rarray������-1
        return -1;
      }
    } else {
      // ��ʱ������li>larray.length���ֻ��li < larray.length����ʾliû�е���larrayĩβ
      if (ri == rarray.length) {
        // larrayû�н���������rarray�Ѿ����������larray > rarray
        return 1;
      } else {
        // ��ʱ������ri>rarray.length���ֻ��ri < rarray.length
        // ��ʾlarray��rarray��û�н�������˰���һ�����Ĵ�С�ж�
        if (larray[li] > rarray[ri])
          return 1;
        else
          return -1;
      }
    }
  }

  /**
   * ����ǰ׺���ж������ַ�����Ĵ�С����ǰ��Ϊ���ߵ�ǰ׺ʱ����ʾ��ȣ�����Ϊǰ׺ʱ��������ͨ�ַ�����ʽ�Ƚ�
   * 
   * @param shortArray
   * @param shortIndex
   * @param longArray
   * @param longIndex
   * @return
   */
  public static int compareArrayByPrefix(char[] shortArray, int shortIndex,
      char[] longArray, int longIndex) {

    // �����������������ǰ׺��������index
    if (shortArray == null)
      return 0;
    else if (longArray == null)
      return (shortIndex < shortArray.length) ? 1 : 0;

    int si = shortIndex, li = longIndex;
    while (si < shortArray.length && li < longArray.length
        && shortArray[si] == longArray[li]) {
      si++;
      li++;
    }
    if (si == shortArray.length) {
      // shortArray �� longArray��prefix
      return 0;
    } else {
      // ��ʱ������si>shortArray.length���ֻ��si <
      // shortArray.length����ʾsiû�е���shortArrayĩβ

      // shortArrayû�н���������longArray�Ѿ����������shortArray > longArray
      if (li == longArray.length)
        return 1;
      else
        // ��ʱ������li>longArray.length���ֻ��li < longArray.length
        // ��ʾshortArray��longArray��û�н�������˰���һ�����Ĵ�С�ж�
        return (shortArray[si] > longArray[li]) ? 1 : -1;
    }
  }

  public static CharType getCharType(char ch) {
    // �����Ǻ���
    if (ch >= 0x4E00 && ch <= 0x9FA5)
      return CharType.HANZI;
    if ((ch >= 0x0041 && ch <= 0x005A) || (ch >= 0x0061 && ch <= 0x007A))
      return CharType.LETTER;
    if (ch >= 0x0030 && ch <= 0x0039)
      return CharType.DIGIT;
    if (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n' || ch == '��')
      return CharType.SPACE_LIKE;
    // ��ǰ��������Ķ��Ǳ�������
    if ((ch >= 0x0021 && ch <= 0x00BB) || (ch >= 0x2010 && ch <= 0x2642)
        || (ch >= 0x3001 && ch <= 0x301E))
      return CharType.DELIMITER;

    // ȫ���ַ�����
    if ((ch >= 0xFF21 && ch <= 0xFF3A) || (ch >= 0xFF41 && ch <= 0xFF5A))
      return CharType.FULLWIDTH_LETTER;
    if (ch >= 0xFF10 && ch <= 0xFF19)
      return CharType.FULLWIDTH_DIGIT;
    if (ch >= 0xFE30 && ch <= 0xFF63)
      return CharType.DELIMITER;
    return CharType.OTHER;

  }
}
