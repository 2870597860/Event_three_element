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

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.cn.smart.hhmm.HHMMSegmenter;
import org.apache.lucene.analysis.cn.smart.hhmm.SegToken;
import org.apache.lucene.analysis.cn.smart.hhmm.SegTokenFilter;

public class WordSegmenter {

  private HHMMSegmenter hhmmSegmenter = new HHMMSegmenter();

  private SegTokenFilter tokenFilter = new SegTokenFilter();

  /**
   * ����HHMMSegment���򽫵�ǰ��sentence Token�ִʣ����طִʽ����������Token List��
   * 
   * @param sentenceToken ���ӵ�Token
   * @param shortPathCount HHMM�㷨�ִ�����Ҫ���Ż�ǰ�����·��������һ��Խ��ִʽ��Խ��ȷ�����Ǽ������Ҳ�ϸߡ�
   * @return �ִʽ����Token List
   */
  public List<Token> segmentSentence(Token sentenceToken, int shortPathCount) {
    String sentence = sentenceToken.term();

    List<SegToken> segTokenList = hhmmSegmenter.process(sentence);

    List<Token> result = new ArrayList<Token>();

    // i��1��rawTokens.length-2��Ҳ����˵����ʼ##ʼ������ĩ##ĩ������RawTokenȥ��
    for (int i = 1; i < segTokenList.size() - 1; i++) {
      result.add(convertSegToken(segTokenList.get(i), sentence, sentenceToken
          .startOffset(), "word"));
    }
    return result;

  }

  /**
   * 
   * ��RawToken����ת����������Ҫ��Token���ͣ� ��Ϊ������ҪRawToken��ԭ���е����ݣ� ���ת��ʱ��Ҫָ��ԭ���ӡ�
   * 
   * @param rt
   * @param sentence ת����Ҫ�ľ�������
   * @param sentenceStartOffset sentence�������еĳ�ʼλ��
   * @param type token���ͣ�Ĭ��Ӧ����word
   * @return
   */
  public Token convertSegToken(SegToken st, String sentence,
      int sentenceStartOffset, String type) {
    Token result;
    switch (st.wordType) {
      case STRING:
      case NUMBER:
      case FULLWIDTH_NUMBER:
      case FULLWIDTH_STRING:
        st.charArray = sentence.substring(st.startOffset, st.endOffset)
            .toCharArray();
        break;
      default:
        break;
    }

    st = tokenFilter.filter(st);

    result = new Token(st.charArray, 0, st.charArray.length, st.startOffset
        + sentenceStartOffset, st.endOffset + sentenceStartOffset);
    return result;
  }
}
