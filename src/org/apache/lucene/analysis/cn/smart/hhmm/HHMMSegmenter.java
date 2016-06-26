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

import java.util.List;

import org.apache.lucene.analysis.cn.smart.CharType;
import org.apache.lucene.analysis.cn.smart.Utility;
import org.apache.lucene.analysis.cn.smart.WordType;

public class HHMMSegmenter {

  private static WordDictionary wordDict = WordDictionary.getInstance();

  /**
   * Ѱ��sentence�����п��ܵ�Token������������������Token��"ʼ##ʼ",
   * "ĩ##ĩ"��"ʼ##ʼ"Token����ʼλ����-1,"ĩ##ĩ"Token����ʼλ���Ǿ��ӵĳ���
   * 
   * @param sentence ����ľ��ӣ�������"ʼ##ʼ","ĩ##ĩ"��
   * @param coreDict �����ֵ�
   * @return ���п��ܵ�Token
   * @see MultiTokenMap
   */
  private SegGraph createSegGraph(String sentence) {
    int i = 0, j;
    int length = sentence.length();
    int foundIndex;
    CharType[] charTypeArray = getCharTypes(sentence);
    StringBuffer wordBuf = new StringBuffer();
    SegToken token;
    int frequency = 0; // word�ĳ��ִ���
    boolean hasFullWidth;
    WordType wordType;
    char[] charArray;

    SegGraph segGraph = new SegGraph();
    while (i < length) {
      hasFullWidth = false;
      switch (charTypeArray[i]) {
        case SPACE_LIKE:
          i++;
          break;
        case HANZI:
          j = i + 1;
          wordBuf.delete(0, wordBuf.length());
          // ���ܵ��������ܲ��ܹ��ɴʣ������������ִ浽segGraph��ȥ���������ɷִ�ͼ����
          wordBuf.append(sentence.charAt(i));
          charArray = new char[] { sentence.charAt(i) };
          frequency = wordDict.getFrequency(charArray);
          token = new SegToken(charArray, i, j, WordType.CHINESE_WORD,
              frequency);
          segGraph.addToken(token);

          foundIndex = wordDict.getPrefixMatch(charArray);
          while (j <= length && foundIndex != -1) {
            if (wordDict.isEqual(charArray, foundIndex) && charArray.length > 1) {
              // ��������Ҫ�ҵĴʣ� Ҳ����˵�ҵ��˴�i��j��һ���ɴ�SegToken�����Ҳ��ǵ��ִ�
              frequency = wordDict.getFrequency(charArray);
              token = new SegToken(charArray, i, j, WordType.CHINESE_WORD,
                  frequency);
              segGraph.addToken(token);
            }

            while (j < length && charTypeArray[j] == CharType.SPACE_LIKE)
              j++;

            if (j < length && charTypeArray[j] == CharType.HANZI) {
              wordBuf.append(sentence.charAt(j));
              charArray = new char[wordBuf.length()];
              wordBuf.getChars(0, charArray.length, charArray, 0);
              // idArray��Ϊǰ׺�Ѿ��ҵ���(foundWordIndex!=-1),
              // ��˼ӳ������idArrayֻ���ܳ�����foundWordIndex�Ժ�,
              // �ʴ�foundWordIndex֮��ʼ����
              foundIndex = wordDict.getPrefixMatch(charArray, foundIndex);
              j++;
            } else {
              break;
            }
          }
          i++;
          break;
        case FULLWIDTH_LETTER:
          hasFullWidth = true;
        case LETTER:
          j = i + 1;
          while (j < length
              && (charTypeArray[j] == CharType.LETTER || charTypeArray[j] == CharType.FULLWIDTH_LETTER)) {
            if (charTypeArray[j] == CharType.FULLWIDTH_LETTER)
              hasFullWidth = true;
            j++;
          }
          // �ҵ��˴�i��j��һ��Token������ΪLETTER���ַ���
          charArray = Utility.STRING_CHAR_ARRAY;
          frequency = wordDict.getFrequency(charArray);
          wordType = hasFullWidth ? WordType.FULLWIDTH_STRING : WordType.STRING;
          token = new SegToken(charArray, i, j, wordType, frequency);
          segGraph.addToken(token);
          i = j;
          break;
        case FULLWIDTH_DIGIT:
          hasFullWidth = true;
        case DIGIT:
          j = i + 1;
          while (j < length
              && (charTypeArray[j] == CharType.DIGIT || charTypeArray[j] == CharType.FULLWIDTH_DIGIT)) {
            if (charTypeArray[j] == CharType.FULLWIDTH_DIGIT)
              hasFullWidth = true;
            j++;
          }
          // �ҵ��˴�i��j��һ��Token������ΪNUMBER���ַ���
          charArray = Utility.NUMBER_CHAR_ARRAY;
          frequency = wordDict.getFrequency(charArray);
          wordType = hasFullWidth ? WordType.FULLWIDTH_NUMBER : WordType.NUMBER;
          token = new SegToken(charArray, i, j, wordType, frequency);
          segGraph.addToken(token);
          i = j;
          break;
        case DELIMITER:
          j = i + 1;
          // �����ŵ�weight���ò��ˣ�ѡ������Ƶ�ʼ���
          frequency = Utility.MAX_FREQUENCE;
          charArray = new char[] { sentence.charAt(i) };
          token = new SegToken(charArray, i, j, WordType.DELIMITER, frequency);
          segGraph.addToken(token);
          i = j;
          break;
        default:
          j = i + 1;
          // �Ѳ���ʶ���ַ�����δ֪������������GB2312����֮����ַ���ÿ���ַ�����һ��
          charArray = Utility.STRING_CHAR_ARRAY;
          frequency = wordDict.getFrequency(charArray);
          token = new SegToken(charArray, i, j, WordType.STRING, frequency);
          segGraph.addToken(token);
          i = j;
          break;
      }
    }

    // ΪsegGraph����������Token�� "ʼ##ʼ","ĩ##ĩ"
    charArray = Utility.START_CHAR_ARRAY;
    frequency = wordDict.getFrequency(charArray);
    token = new SegToken(charArray, -1, 0, WordType.SENTENCE_BEGIN, frequency);
    segGraph.addToken(token);

    // "ĩ##ĩ"
    charArray = Utility.END_CHAR_ARRAY;
    frequency = wordDict.getFrequency(charArray);
    token = new SegToken(charArray, length, length + 1, WordType.SENTENCE_END,
        frequency);
    segGraph.addToken(token);

    return segGraph;
  }

  /**
   * Ϊsentence�е�ÿ���ַ�ȷ��Ψһ���ַ�����
   * 
   * @see Utility.charType(char)
   * @param sentence �������ɾ���
   * @return ���ص��ַ��������飬�������Ϊnull������Ҳ��null
   */
  private static CharType[] getCharTypes(String sentence) {
    int length = sentence.length();
    CharType[] charTypeArray = new CharType[length];
    // ���ɶ�Ӧ�������ֵ��ַ���������
    for (int i = 0; i < length; i++) {
      charTypeArray[i] = Utility.getCharType(sentence.charAt(i));
    }

    return charTypeArray;
  }

  public List<SegToken> process(String sentence) {
    SegGraph segGraph = createSegGraph(sentence);
    BiSegGraph biSegGraph = new BiSegGraph(segGraph);
    List<SegToken> shortPath = biSegGraph.getShortPath();
    return shortPath;
  }
}
