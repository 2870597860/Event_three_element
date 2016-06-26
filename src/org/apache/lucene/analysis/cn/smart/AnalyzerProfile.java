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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ��Ĭ������£�SmartChineseAnalyzer�����дʵ�⡢Ĭ��ֹͣ�ʿ⣬�Ѿ�������װ���û�����ֱ��ʹ�á�
 * 
 * ��������£��û���Ҫʹ��ָ���Ĵʵ���ֹͣ�ʿ⣬��ʱ��Ҫɾ��org.apache.lucene.analysis.cn.smart. hhmm�µ�
 * coredict.mem �� bigramdict.mem�� Ȼ��ʹ��AnalyzerProfile��ָ���ʵ��Ŀ¼��
 * 
 * AnalyzerProfile ����Ѱ�Ҵ�ŷִʴʿ����� ��ͣ�ô����ݵ�Ŀ¼�� ��Ŀ¼��Ӧ���� bigramdict.dct, coredict.dct,
 * stopwords_utf8.txt, ���ҹ����������£�
 * 
 * <ol>
 * <li>��ȡϵͳ����ʱ������-Danalysis.data.dir=/path/to/analysis-data�����û�У�������һ��</li>
 * <li>ִ������ĵ�ǰĿ¼���Ƿ����analysis-dataĿ¼</li>
 * <li>ִ�������lib/Ŀ¼���Ƿ����analysis-dataĿ¼</li>
 * <li>ִ������ĵ�ǰĿ¼���Ƿ����analysis.properties�ļ�</li>
 * <li>ִ�������lib/Ŀ¼���Ƿ����analysis.properties�ļ�</li>
 * </ol>
 * 
 * ����analysis.properties�ļ�analysis.data.dirָ��analysis-dataĿ¼����λ��.
 * analysis.properties�ļ�������ʾ����
 * 
 * <pre>
 * analysis.data.dir=D:/path/to/analysis-data/
 * </pre>
 * 
 * ���Ҳ���analysis-dataĿ¼ʱ��ANALYSIS_DATA_DIR����Ϊ""�������ʹ��ǰ�������ڳ�������ʽָ��dataĿ¼�����磺
 * 
 * <pre>
 * AnalyzerProfile.ANALYSIS_DATA_DIR = &quot;/path/to/analysis-data&quot;;
 * </pre>
 * 
 */
public class AnalyzerProfile {

  public static String ANALYSIS_DATA_DIR = "";

  static {
    init();
  }

  private static void init() {
    String dirName = "analysis-data";
    String propName = "analysis.properties";

    // ��ȡϵͳ���ã�������ʱ���������-Danalysis.data.dir=/path/to/analysis-data
    ANALYSIS_DATA_DIR = System.getProperty("analysis.data.dir", "");
    if (ANALYSIS_DATA_DIR.length() != 0)
      return;

    File[] cadidateFiles = new File[] { new File("./" + dirName),
        new File("./lib/" + dirName), new File("./" + propName),
        new File("./lib/" + propName) };
    for (File file : cadidateFiles) {
      if (file.exists()) {
        if (file.isDirectory()) {
          ANALYSIS_DATA_DIR = file.getAbsolutePath();
        } else if (file.isFile() && getAnalysisDataDir(file).length() != 0) {
          ANALYSIS_DATA_DIR = getAnalysisDataDir(file);
        }
        break;
      }
    }

    if (ANALYSIS_DATA_DIR.length() == 0) {
      // ��ʾ�û�δ�ҵ��ʵ��ļ���
      System.err
          .println("WARNING: Can not found lexical dictionary directory!");
      System.err
          .println("WARNING: This will cause unpredictable exceptions in your application!");
      System.err
          .println("WARNING: Please refer to the manual to download the dictionaries.");
    }

  }

  private static String getAnalysisDataDir(File propFile) {
    Properties prop = new Properties();
    try {
      FileInputStream input = new FileInputStream(propFile);
      prop.load(input);
      String dir = prop.getProperty("analysis.data.dir", "");
      input.close();
      return dir;
    } catch (IOException e) {
    }
    return "";
  }

}
