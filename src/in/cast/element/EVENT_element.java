package in.cast.element;

import in.cast.juzizuhe.juzi_zuhe;
import in.fenju.fenju;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.SmartChineseAnalyzer;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class EVENT_element {

	/**
	 * @param district 
	 * @param time 
	 * @param thing 
	 * @param args
	 */
	public String element(List<String> fenju, List<String> time, List<String> district) {
		// TODO Auto-generated method stub
        List<String> fenju_end=new ArrayList<String>();
        
        for(int i=0;i<fenju.size();i++){
        	//System.out.println(fenju.get(i));
        	
        	try {
        		//分句进行分词
				new EVENT_element().sampleMethod(fenju.get(i),fenju_end);
				//System.out.println();
				//System.out.println(fenju_end.get(i));
				String biaojiqian=fenju_end.get(i);
				//分句进行标记
	        	new EVENT_element().biaoji(fenju_end,i);
	        	//System.out.println(fenju_end.get(i));
	        	
	        	
	        	
	        	
	        	//时间提取List
	        	//List<String> List_time=new ArrayList<String>();
	        	//List<String> List_time_jieci=new ArrayList<String>();
	        	new EVENT_element_time().time(fenju_end.get(i),time);
	        	
	        	
	        	/*******
	        	System.out.println();
	        	if(!List_time.isEmpty()){
	        		System.out.println("时间词：");
	        		for(int j=0;j<List_time.size();j++){
	        		   System.out.print(List_time.get(j)+"#");
	        	    }
	        	}
	        	
	        	System.out.println();
	        	if(!List_time_jieci.isEmpty()){
	        		System.out.print("介词：");
	        		for(int j=0;j<List_time_jieci.size();j++){
	        		    System.out.print("介词："+List_time_jieci.get(j)+"#");
	        	    }
	        	}
	        	******/
	        	
	        	
	        	//地点提取
	        	List<String> List_district=new ArrayList<String>();
	        	new EVENT_element_district().district(fenju.get(i),district);
	        	
	        	System.out.println("<<<"+fenju.get(i));
	        	System.out.println(">>>"+fenju_end.get(i));
	        	System.out.println(time.get(i));
	        	System.out.println(district.get(i));
	        	/******
	        	System.out.println();
	        	if(!List_district.isEmpty()){
	        		System.out.println("地点词：");
	        		for(int j=0;j<List_district.size();j++){
	        		   System.out.print(List_district.get(j)+"#");
	        	    }
	        	}
	        	*******/
	        	
	        	//for(int j=0;j<juzi_biaoji_into_List.size();j++){
	        	//	System.out.print(juzi_biaoji_into_List.get(j)+"#");
	        	//}
	        	
	        	
	        	//进行句子组合
	        	//new juzi_zuhe().juzizuhe(fenju.get(i),biaojiqian,List_time,List_district,thing,time,district);
	        	
	        	
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
			//String elements=new EVENT_element_time().element(fenju.get(i));
			//Three_elements=Three_elements+
        }
        
        String wenzhang = "";
        //分句
		
        
        //分词
        
        //标记
        
        //规则
        
        
        return wenzhang;
	}
	




	private void sampleMethod(String juzi,List<String> fenju_end) throws UnsupportedEncodingException,FileNotFoundException, IOException {
		    Token nt = new Token();
		    Analyzer ca = new SmartChineseAnalyzer(true);
		    Reader sentence = new StringReader(juzi);
		    TokenStream ts = ca.tokenStream("sentence", sentence);

		    //System.out.println("start: " + (new Date()));
		    long before = System.currentTimeMillis();
		    nt = ts.next(nt);
		    String fenju_zhong="";
		    while (nt != null) {
			      //System.out.print(nt.term()+" ");
			      //if(ww%10==0){System.out.println();}
		    	  fenju_zhong=fenju_zhong+" "+nt.term();
			      nt = ts.next(nt);
		    }
		    if(fenju_zhong.indexOf(" ")==0){fenju_zhong=fenju_zhong.replaceFirst(" ","");}
		    fenju_end.add(fenju_zhong);
	}
	
	
	  private void biaoji(List<String> fenju_end, int i) {
		// TODO Auto-generated method stub
		String model = "C:\\Users\\dtf\\Desktop\\models\\chinese-distsim.tagger";
		MaxentTagger tagger = new MaxentTagger(model);
		String biaoji=null;
		List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new BufferedReader(new StringReader(fenju_end.get(i))));
		for (List<HasWord> sentence : sentences) {
			ArrayList<edu.stanford.nlp.ling.TaggedWord> tSentence = tagger.tagSentence(sentence);
			biaoji=Sentence.listToString(tSentence, false);
			//System.out.println(biaoji);
		}
		fenju_end.set(i, biaoji);
		//System.out.println();
	  }
	  
	  

}
