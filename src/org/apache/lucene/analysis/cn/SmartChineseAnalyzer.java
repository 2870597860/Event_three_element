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

package org.apache.lucene.analysis.cn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.PorterStemFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SentenceTokenizer;
import org.apache.lucene.analysis.cn.smart.WordSegmenter;
import org.apache.lucene.analysis.cn.smart.WordTokenizer;

/**
 * 
 * SmartChineseAnalyzer ��һ���������ķִ�ģ�飬 �ܹ����ø��ʶԺ�����ӽ��������з֣�
 * ����ǶӢ��tokenizer������Ч������Ӣ�Ļ�ϵ��ı����ݡ�
 * 
 * ����ԭ�������Ȼ���Դ��������������Ʒ�ģ��(HMM)�� ���ô������Ͽ��ѵ����ͳ�ƺ���ʻ�Ĵ�Ƶ����ת���ʣ�
 * �Ӷ�������Щͳ�ƽ��������������Ӽ�������Ȼ(likelihood)���з֡�
 * 
 * ��Ϊ���ִܷ���Ҫ�ʵ�������ʻ��ͳ��ֵ��SmartChineseAnalyzer��������Ҫָ���ʵ�λ�ã����ָ���ʵ�λ����ο�
 * org.apache.lucene.analysis.cn.smart.AnalyzerProfile
 * 
 * SmartChineseAnalyzer���㷨�����Ͽ�ʵ�������ictclas1.0��Ŀ(http://www.ictclas.org)��
 * ���дʵ��ѻ�ȡwww.ictclas.org��apache license v2(APLv2)����Ȩ������ѭAPLv2�������£���ӭ�û�ʹ�á�
 * �ڴ˸�лwww.ictclas.org�Լ�ictclas�ִ�����Ĺ�����Ա����˽���ף�
 * 
 * @see org.apache.lucene.analysis.cn.smart.AnalyzerProfile
 * 
 */
public class SmartChineseAnalyzer extends Analyzer {

  private Set<String> stopWords = null;

  private WordSegmenter wordSegment;

  public SmartChineseAnalyzer() {
    this(false);
  }

  /**
   * SmartChineseAnalyzer�ڲ�����Ĭ��ֹͣ�ʿ⣬��Ҫ�Ǳ����š������ϣ������г��ֱ����ţ�
   * ���Խ�useDefaultStopWords��Ϊtrue�� useDefaultStopWordsΪfalseʱ��ʹ���κ�ֹͣ��
   * 
   * @param useDefaultStopWords
   */
  public SmartChineseAnalyzer(boolean useDefaultStopWords) {
    if (useDefaultStopWords) {
      stopWords = loadStopWords(this.getClass().getResourceAsStream(
          "stopwords.txt"));
    }
    wordSegment = new WordSegmenter();
  }

  /**
   * ʹ���Զ���Ķ���ʹ�����õ�ֹͣ�ʿ⣬ֹͣ�ʿ���ʹ��SmartChineseAnalyzer.loadStopWords(InputStream)����
   * 
   * @param stopWords
   * @see SmartChineseAnalyzer.loadStopWords(InputStream)
   */
  public SmartChineseAnalyzer(Set<String> stopWords) {
    this.stopWords = stopWords;
    wordSegment = new WordSegmenter();
  }

  public TokenStream tokenStream(String fieldName, Reader reader) {
    TokenStream result = new SentenceTokenizer(reader);
    result = new WordTokenizer(result, wordSegment);
    // result = new LowerCaseFilter(result);
    // ������ҪLowerCaseFilter����ΪSegTokenFilter�Ѿ�������Ӣ���ַ�ת����Сд
    // stem̫�ϸ���, This is not bug, this feature:)
    result = new PorterStemFilter(result);
    if (stopWords != null) {
      result = new StopFilter(result, stopWords, false);
    }
    return result;
  }

  /**
   * ��ͣ�ô��ļ��м���ͣ�ôʣ� ͣ�ô��ļ�����ͨUTF-8������ı��ļ��� ÿһ����һ��ͣ�ôʣ�ע�����á�//���� ͣ�ô��а������ı����ţ� ���Ŀո�
   * �Լ�ʹ����̫�߶����������岻��Ĵʡ�
   * 
   * @param input ͣ�ô��ļ�
   * @return ͣ�ô���ɵ�HashSet
   */
  public static Set<String> loadStopWords(InputStream input) {
    String line;
    Set<String> stopWords = new HashSet<String>();
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(input,
          "UTF-8"));
      while ((line = br.readLine()) != null) {
        if (line.indexOf("//") != -1) {
          line = line.substring(0, line.indexOf("//"));
        }
        line = line.trim();
        if (line.length() != 0)
          stopWords.add(line.toLowerCase());
      }
      br.close();
    } catch (IOException e) {
      System.err.println("WARNING: cannot open stop words list!");
    }
    return stopWords;
  }

}
