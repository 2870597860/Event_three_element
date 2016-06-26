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

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;

public class WordTokenizer extends Tokenizer {

  /**
   * �ִ�������WordTokenizer��ʼ��ʱ���ء�
   */
  private WordSegmenter wordSegmenter;

  private TokenStream in;

  private Iterator<Token> tokenIter;

  private List<Token> tokenBuffer;

  private Token sentenceToken = new Token();

  /**
   * �������SentenceTokenizer����һ����㡣��SentenceTokenizer�ľ��Ӷ�����
   * ����HHMMSegment�����򽫾��ӷִʣ�Ȼ�󽫷ִʽ�����ء�
   * 
   * @param in ���ӵ�Token
   * @param smooth ƽ������
   * @param dataPath װ�غ����ֵ�������ֵ��Ŀ¼
   * @see init()
   */
  public WordTokenizer(TokenStream in, WordSegmenter wordSegmenter) {
    this.in = in;
    this.wordSegmenter = wordSegmenter;
  }

  public Token next() throws IOException {
    if (tokenIter != null && tokenIter.hasNext())
      return tokenIter.next();
    else {
      if (processNextSentence()) {
        return tokenIter.next();
      } else
        return null;
    }
  }

  /**
   * ����ǰ�ľ��ӷִʲ��������ʱ����Ҫ��ȡ��һ������Token�� ���������������һ���SentenceTokenizerȥ������һ�����ӣ� ������ִʣ�
   * ���ִʽ�������Token����tokenBuffer��
   * 
   * @return ��ȡ��������һ�����ӳɹ�������û�гɹ���˵���ļ�������ϣ�����û��Token��
   * @throws IOException
   */
  private boolean processNextSentence() throws IOException {
    sentenceToken = in.next(sentenceToken);
    if (sentenceToken == null)
      return false;
    tokenBuffer = wordSegmenter.segmentSentence(sentenceToken, 1);
    tokenIter = tokenBuffer.iterator();
    return tokenBuffer != null && tokenIter.hasNext();
  }

  public void close() throws IOException {
    in.close();
  }

}
