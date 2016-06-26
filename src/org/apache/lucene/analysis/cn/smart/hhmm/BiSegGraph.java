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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.cn.smart.Utility;

public class BiSegGraph {

  private Map<Integer, List<SegTokenPair>> tokenPairListTable = new HashMap<Integer, List<SegTokenPair>>();

  private List<SegToken> segTokenList;

  private static BigramDictionary bigramDict = BigramDictionary.getInstance();

  public BiSegGraph(SegGraph segGraph) {
    segTokenList = segGraph.makeIndex();
    generateBiSegGraph(segGraph);
  }

  /**
   * ����������֮��Ķ���ͼ�������������һ��MultiTokenPairMap��
   * 
   * @param segGraph ���е�Token�б�
   * @param smooth ƽ��ϵ��
   * @param biDict ����ʵ�
   * @return
   * 
   * @see MultiTokenPairMap
   */
  private void generateBiSegGraph(SegGraph segGraph) {
    double smooth = 0.1;
    int wordPairFreq = 0;
    int maxStart = segGraph.getMaxStart();
    double oneWordFreq, weight, tinyDouble = 1.0 / Utility.MAX_FREQUENCE;

    int next;
    char[] idBuffer;
    // ΪsegGraph�е�ÿ��Ԫ�ظ���һ���±�
    segTokenList = segGraph.makeIndex();
    // ��ΪstartToken��"ʼ##ʼ"������ʼλ����-1���keyΪ-1ʱ����ȡ��startToken
    int key = -1;
    List<SegToken> nextTokens = null;
    while (key < maxStart) {
      if (segGraph.isStartExist(key)) {

        List<SegToken> tokenList = segGraph.getStartList(key);

        // Ϊĳһ��key��Ӧ������Token������һ��
        for (SegToken t1 : tokenList) {
          oneWordFreq = t1.weight;
          next = t1.endOffset;
          nextTokens = null;
          // �ҵ���һ����Ӧ��Token�����硰���⺣��������ǰToken�ǡ����⡱�� ��һ��Token�����ǡ��������ߡ�������
          // ����Ҳ�����һ��Token����˵������ĩβ������ѭ����
          while (next <= maxStart) {
            // ��ΪendToken����ʼλ����sentenceLen����˵���sentenceLen�ǿ����ҵ�endToken
            if (segGraph.isStartExist(next)) {
              nextTokens = segGraph.getStartList(next);
              break;
            }
            next++;
          }
          if (nextTokens == null) {
            break;
          }
          for (SegToken t2 : nextTokens) {
            idBuffer = new char[t1.charArray.length + t2.charArray.length + 1];
            System.arraycopy(t1.charArray, 0, idBuffer, 0, t1.charArray.length);
            idBuffer[t1.charArray.length] = BigramDictionary.WORD_SEGMENT_CHAR;
            System.arraycopy(t2.charArray, 0, idBuffer,
                t1.charArray.length + 1, t2.charArray.length);

            // Two linked Words frequency
            wordPairFreq = bigramDict.getFrequency(idBuffer);

            // Smoothing

            // -log{a*P(Ci-1)+(1-a)P(Ci|Ci-1)} Note 0<a<1
            weight = -Math
                .log(smooth
                    * (1.0 + oneWordFreq)
                    / (Utility.MAX_FREQUENCE + 0.0)
                    + (1.0 - smooth)
                    * ((1.0 - tinyDouble) * wordPairFreq / (1.0 + oneWordFreq) + tinyDouble));

            SegTokenPair tokenPair = new SegTokenPair(idBuffer, t1.index,
                t2.index, weight);
            this.addSegTokenPair(tokenPair);
          }
        }
      }
      key++;
    }

  }

  /**
   * �鿴SegTokenPair�Ľ���λ��Ϊto(SegTokenPair.toΪto)�Ƿ����SegTokenPair��
   * ���û����˵��to��û��SegTokenPair���߻�û�����
   * 
   * @param to SegTokenPair.to
   * @return
   */
  public boolean isToExist(int to) {
    return tokenPairListTable.get(to) != null;
  }

  /**
   * ȡ��SegTokenPair.toΪto������SegTokenPair�����û���򷵻�null
   * 
   * @param to
   * @return ������ͬSegTokenPair.to��SegTokenPair������
   */
  public List<SegTokenPair> getToList(int to) {
    return tokenPairListTable.get(to);
  }

  /**
   * ��BiSegGraph������һ��SegTokenPair����ЩSegTokenPair������ͬSegTokenPair.
   * to����ͬһ��ArrayList��
   * 
   * @param tokenPair
   */
  public void addSegTokenPair(SegTokenPair tokenPair) {
    int to = tokenPair.to;
    if (!isToExist(to)) {
      ArrayList<SegTokenPair> newlist = new ArrayList<SegTokenPair>();
      newlist.add(tokenPair);
      tokenPairListTable.put(to, newlist);
    } else {
      tokenPairListTable.get(to).add(tokenPair);
    }
  }

  /**
   * @return TokenPair��������Ҳ����Map�в�ͬ�кŵ�TokenPair������
   */
  public int getToCount() {
    return tokenPairListTable.size();
  }

  /**
   * ��veterbi�㷨�������㵽�յ�����·��
   * 
   * @return
   */
  public List<SegToken> getShortPath() {
    int current;
    int nodeCount = getToCount();
    List<PathNode> path = new ArrayList<PathNode>();
    PathNode zeroPath = new PathNode();
    zeroPath.weight = 0;
    zeroPath.preNode = 0;
    path.add(zeroPath);
    for (current = 1; current <= nodeCount; current++) {
      double weight;
      List<SegTokenPair> edges = getToList(current);

      double minWeight = Double.MAX_VALUE;
      SegTokenPair minEdge = null;
      for (SegTokenPair edge : edges) {
        weight = edge.weight;
        PathNode preNode = path.get(edge.from);
        if (preNode.weight + weight < minWeight) {
          minWeight = preNode.weight + weight;
          minEdge = edge;
        }
      }
      PathNode newNode = new PathNode();
      newNode.weight = minWeight;
      newNode.preNode = minEdge.from;
      path.add(newNode);
    }

    // ��������nodePaths�м������㵽�յ����ʵ·��
    int preNode, lastNode;
    lastNode = path.size() - 1;
    current = lastNode;
    List<Integer> rpath = new ArrayList<Integer>();
    List<SegToken> resultPath = new ArrayList<SegToken>();

    rpath.add(current);
    while (current != 0) {
      preNode = path.get(current).preNode;
      rpath.add(preNode);
      current = preNode;
    }
    for (int j = rpath.size() - 1; j >= 0; j--) {
      resultPath.add(segTokenList.get(rpath.get(j)));
    }
    return resultPath;

  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    Collection<List<SegTokenPair>> values = tokenPairListTable.values();
    for (List<SegTokenPair> segList : values) {
      for (SegTokenPair pair : segList) {
        sb.append(pair + "\n");
      }
    }
    return sb.toString();
  }

}
