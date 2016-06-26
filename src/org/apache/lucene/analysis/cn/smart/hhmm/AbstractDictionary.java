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

package org.apache.lucene.analysis.cn.smart.hhmm;

import java.io.UnsupportedEncodingException;

public abstract class AbstractDictionary {
  /**
   * ��һ������Ϊ����������ǰ����15��������15*94���ַ�
   */
  public static final int GB2312_FIRST_CHAR = 1410;

  /**
   * GB2312�ַ�����01~87���ַ����ſ�����Ч����8178��
   */
  public static final int GB2312_CHAR_NUM = 87 * 94;

  /**
   * �ʿ��ļ�����¼��6768�����ֵĴ�Ƶͳ��
   */
  public static final int CHAR_NUM_IN_FILE = 6768;

  // =====================================================
  // code +0 +1 +2 +3 +4 +5 +6 +7 +8 +9 +A +B +C +D +E +F
  // B0A0 �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��
  // B0B0 �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��
  // B0C0 �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��
  // B0D0 �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��
  // B0E0 �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��
  // B0F0 �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��
  // =====================================================
  //
  // GB2312 �ַ�������λ�ֲ���
  // ���� ���� �ַ����
  // 01 94 һ�����
  // 02 72 ˳�����
  // 03 94 ������ĸ
  // 04 83 ���ļ���
  // 05 86 Katakana
  // 06 48 ϣ����ĸ
  // 07 66 ������ĸ
  // 08 63 ����ƴ������
  // 09 76 ͼ�η���
  // 10-15 ������
  // 16-55 3755 һ�����֣���ƴ��Ϊ��
  // 56-87 3008 �������֣��Աʻ�Ϊ��
  // 88-94 ������
  // ======================================================

  /**
   * GB2312 ����¼�� 7445 ���ַ������м򻯺��� 6763 ������ĸ�ͷ��� 682 ����
   * 
   * GB2312 ������¼���ַ���Ϊ 94 ���������Ϊ 01 ���� 94 ����ÿ������¼ 94 ���ַ������Ϊ 01 λ�� 94
   * λ��01Ϊ��ʼ��0xA1��94λ����0xFE��GB2312 ��ÿһ���ַ���������Ψһ��Ӧ�����ź�λ����ȷ�������磺���֡����������Ϊ 16 �� 01
   * λ��
   */
  /**
   * @param ccid
   * @return
   */
  public String getCCByGB2312Id(int ccid) {
    if (ccid < 0 || ccid > WordDictionary.GB2312_CHAR_NUM)
      return "";
    int cc1 = ccid / 94 + 161;
    int cc2 = ccid % 94 + 161;
    byte[] buffer = new byte[2];
    buffer[0] = (byte) cc1;
    buffer[1] = (byte) cc2;
    try {
      String cchar = new String(buffer, "GB2312");
      return cchar;
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

  /**
   * ���������Unicode�ַ�����ȡ����GB2312�������ascii���룬
   * 
   * @param ch �����GB2312�����ַ�����ASCII�ַ�(128��)
   * @return ch��GB2312�е�λ�ã�-1��ʾ���ַ�����ʶ
   */
  public short getGB2312Id(char ch) {
    try {
      byte[] buffer = Character.toString(ch).getBytes("GB2312");
      if (buffer.length != 2) {
        // ���������bufferӦ���������ֽڣ�����˵��ch������GB2312���룬�ʷ���'?'����ʱ˵������ʶ���ַ�
        return -1;
      }
      int b0 = (int) (buffer[0] & 0x0FF) - 161; // �����A1��ʼ����˼�ȥ0xA1=161
      int b1 = (int) (buffer[1] & 0x0FF) - 161; // ��һ���ַ������һ���ַ�û�к��֣����ÿ����ֻ��16*6-2=94������
      return (short) (b0 * 94 + b1);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return -1;
  }

  /**
   * �Ľ���32λFNV hash�㷨�������������еĵ�һhash����.��һ�͵ڶ�hash�����������ϼ���hash�� ʹ����ȷֲ���
   * ���ܱ�����hash����ܶ����µĳ�ʱ����������
   * 
   * @param c ��hash��Unicode�ַ�
   * @return c�Ĺ�ϣֵ
   * @see Utility.hash2()
   */
  public long hash1(char c) {
    final long p = 1099511628211L;
    long hash = 0xcbf29ce484222325L;
    hash = (hash ^ (c & 0x00FF)) * p;
    hash = (hash ^ (c >> 8)) * p;
    hash += hash << 13;
    hash ^= hash >> 7;
    hash += hash << 3;
    hash ^= hash >> 17;
    hash += hash << 5;
    return hash;
  }

  /**
   * @see Utility.hash1(char[])
   * @param carray
   * @return
   */
  public long hash1(char carray[]) {
    final long p = 1099511628211L;
    long hash = 0xcbf29ce484222325L;
    for (char d : carray) {
      hash = (hash ^ (d & 0x00FF)) * p;
      hash = (hash ^ (d >> 8)) * p;
    }

    // hash += hash << 13;
    // hash ^= hash >> 7;
    // hash += hash << 3;
    // hash ^= hash >> 17;
    // hash += hash << 5;
    return hash;
  }

  /**
   * djb2��ϣ�㷨�������������еĵڶ�hash����
   * 
   * djb2 hash algorithm��this algorithm (k=33) was first reported by dan
   * bernstein many years ago in comp.lang.c. another version of this algorithm
   * (now favored by bernstein) uses xor: hash(i) = hash(i - 1) * 33 ^ str[i];
   * the magic of number 33 (why it works better than many other constants,
   * prime or not) has never been adequately explained.
   * 
   * @param c
   * @return
   */
  public int hash2(char c) {
    int hash = 5381;

    /* hash 33 + c */
    hash = ((hash << 5) + hash) + c & 0x00FF;
    hash = ((hash << 5) + hash) + c >> 8;

    return hash;
  }

  /**
   * @see Utility.hash2(char[])
   * @param carray
   * @return
   */
  public int hash2(char carray[]) {
    int hash = 5381;

    /* hash 33 + c */
    for (char d : carray) {
      hash = ((hash << 5) + hash) + d & 0x00FF;
      hash = ((hash << 5) + hash) + d >> 8;
    }

    return hash;
  }
}
