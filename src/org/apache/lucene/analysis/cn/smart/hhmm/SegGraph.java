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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SegGraph {

  /**
   * ��һ��ArrayList��¼startOffset��ͬ��Token�����startOffset����Token��key
   */
  private Map<Integer, List<SegToken>> tokenListTable = new HashMap<Integer, List<SegToken>>();

  private int maxStart = -1;

  /**
   * �鿴startOffsetΪs��Token�Ƿ���ڣ����û����˵��s��û��Token���߻�û�����
   * 
   * @param s startOffset
   * @return
   */
  public boolean isStartExist(int s) {
    return tokenListTable.get(s) != null;
  }

  /**
   * ȡ��startOffsetΪs������Tokens�����û���򷵻�null
   * 
   * @param s
   * @return ������ͬstartOffset��Token������
   */
  public List<SegToken> getStartList(int s) {
    return tokenListTable.get(s);
  }

  public int getMaxStart() {
    return maxStart;
  }

  /**
   * ΪSegGraph�е�����Tokens����һ��ͳһ��index��index��0��ʼ��
   * ����startOffset������˳��������ͬstartOffset��Tokens���շ����Ⱥ�˳������
   */
  public List<SegToken> makeIndex() {
    List<SegToken> result = new ArrayList<SegToken>();
    int s = -1, count = 0, size = tokenListTable.size();
    List<SegToken> tokenList;
    short index = 0;
    while (count < size) {
      if (isStartExist(s)) {
        tokenList = tokenListTable.get(s);
        for (SegToken st : tokenList) {
          st.index = index;
          result.add(st);
          index++;
        }
        count++;
      }
      s++;
    }
    return result;
  }

  /**
   * ��Map������һ��Token����ЩToken������ͬstartOffset����ͬһ���б��У�
   * 
   * @param token
   */
  public void addToken(SegToken token) {
    int s = token.startOffset;
    if (!isStartExist(s)) {
      ArrayList<SegToken> newlist = new ArrayList<SegToken>();
      newlist.add(token);
      tokenListTable.put(s, newlist);
    } else {
      tokenListTable.get(s).add(token);
    }
    if (s > maxStart)
      maxStart = s;
  }

  /**
   * ��ȡSegGraph�в�ͬ��ʼ��Start��λ��Token��ĸ�����ÿ����ʼλ�ÿ����ж��Token�����λ������Token������һ��
   * 
   * @return
   */
  public int getStartCount() {
    return tokenListTable.size();
  }

  /**
   * ��Map�д洢������Token������ʼλ�ô�С����ķ�ʽ���һ���б�
   * 
   * @return
   */
  public List<SegToken> toTokenList() {
    List<SegToken> result = new ArrayList<SegToken>();
    int s = -1, count = 0, size = tokenListTable.size();
    List<SegToken> tokenList;

    while (count < size) {
      if (isStartExist(s)) {
        tokenList = tokenListTable.get(s);
        for (SegToken st : tokenList)
          result.add(st);
        count++;
      }
      s++;
    }
    return result;
  }

  public String toString() {
    List<SegToken> tokenList = this.toTokenList();
    StringBuffer sb = new StringBuffer();
    for (SegToken t : tokenList) {
      sb.append(t + "\n");
    }
    return sb.toString();
  }
}
