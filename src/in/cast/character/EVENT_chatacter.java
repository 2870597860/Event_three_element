package in.cast.character;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.SmartChineseAnalyzer;

public class EVENT_chatacter{

  public static void main(String[] args) throws IOException {
	//String wenzhang="我从小就不由自主地认为自己长大以后一定得成为一个象我父亲一样的画家, 可能是父母潜移默化的影响。其实我根本不知道作为画家意味着什么，我是否喜欢，最重要的是否适合我，我是否有这个才华。其实人到中年的我还是不确定我最喜欢什么，最想做的是什么？";
	  String wenzhang="";
	  
	  String path ;
	  
	  //保存文章词语的LIst
	  List<String> wenzhang_ciyu=new ArrayList<String>();
	  //保存文章频数的List
	  List<Integer> wenzhang_pinshu=new ArrayList<Integer>();
	  //保存文章词语在几篇文章中出现
	  List<Integer> wenzhang_pianshu=new ArrayList<Integer>();
	  //保存文章词语在同一篇文章出现
	  List<Integer> wenzhang_tongyi=new ArrayList<Integer>();
	  //保存文章词语权重
	  List<Double> wenzhang_quanzhong=new ArrayList<Double>();
	  //文章数目
	  //wenzhang_ciyu.add("xxxxxxxx");
	  //wenzhang_pinshu.add(11111);
	  //wenzhang_pianshu.add(1111);
	  //wenzhang_tongyi.add(11111);
	  
	  int wenzhang_count=11;
	  
	  for(int i=0;i<wenzhang_count;i++){
		  path = "C:\\Users\\dutengfei\\Desktop\\报表\\"+i+".txt";
		  System.out.println(path);
		  try {
				File file = new File(path);
				//StringBuilder sb = new StringBuilder();
				String s ="";
				BufferedReader br;
				br = new BufferedReader(new FileReader(file));
				while( (s = br.readLine()) != null) 
				{
					wenzhang=wenzhang+s;					
				}
				//System.out.println(wenzhang);
				//去掉空格
				wenzhang = wenzhang.replaceFirst(" ","");
				System.out.println(i+"去掉空格完成");
				//去掉一些停顿词
				tingdunci Select_tingdun=new tingdunci();
				wenzhang=Select_tingdun.Select_tingdunci(wenzhang);
				System.out.println();
				System.out.println(i+"去掉停顿词完成");
				//进行分词
				new EVENT_chatacter().sampleMethod(wenzhang,wenzhang_ciyu,wenzhang_pinshu,wenzhang_pianshu,wenzhang_tongyi);
				System.out.println();
				System.out.println(i+"加入完成");
		      } catch (IOException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
		      }
	  }
	  
	  
	  String long4 = "";
	  String long3 = "";
	  String long2 = "";
	  String long1 = "";
	  
	  double quanzhong = 0;
	  
	  double quanbu=0;
	  double quanbu_zhong = 0;
	  
	  
	  for(int i=0;i<wenzhang_ciyu.size();i++){
		  
		  if(wenzhang_pianshu.get(i)==wenzhang_count){
			  quanzhong=wenzhang_pinshu.get(i)*1.000000;
		  }else{
			  if(wenzhang_pianshu.get(i)==1){
				  quanzhong=wenzhang_pinshu.get(i)*(Math.log(wenzhang_pianshu.get(i)+0.01)/Math.log(wenzhang_count));
			  }else{
				  quanzhong=wenzhang_pinshu.get(i)*(Math.log(wenzhang_pianshu.get(i))/Math.log(wenzhang_count));
			  }
			  
		  }
		  
		  wenzhang_quanzhong.add(quanzhong);
		  quanbu_zhong=quanbu_zhong+(quanzhong*quanzhong);
	  }
	  quanbu=Math.sqrt(quanbu_zhong);
	  
	  
	  
	  
	  for(int i=0;i<wenzhang_ciyu.size();i++){


		  //if(quanzhong<1){break;}
		  quanzhong=wenzhang_quanzhong.get(i)/quanbu;
		  DecimalFormat df = new DecimalFormat("0.00000000");
		  String quanzhong_last=df.format(quanzhong);
		  
		  if(i%5==0){System.out.println();}
		  System.out.print(wenzhang_ciyu.get(i)+":"+wenzhang_pinshu.get(i)+","+wenzhang_pianshu.get(i)+",权重："+quanzhong_last+"\t");

		  if(wenzhang_ciyu.get(i).length()==4){
			  long4=long4+"\r\n"+wenzhang_ciyu.get(i)+","+quanzhong_last;
		  }
		  if(wenzhang_ciyu.get(i).length()==3){
			  long3=long3+"\r\n"+wenzhang_ciyu.get(i)+","+quanzhong_last;
		  }
		  if(wenzhang_ciyu.get(i).length()==2){
			  long2=long2+"\r\n"+wenzhang_ciyu.get(i)+","+quanzhong_last;
		  }
		  if(wenzhang_ciyu.get(i).length()==1){
			  long1=long1+"\r\n"+wenzhang_ciyu.get(i)+","+quanzhong_last;
		  }
	  }
	  wenzhang=long4+"\r\n"+long3+"\r\n"+long2;
	  
	  new write_txt().Write(wenzhang);
	  
    
  }

  private void sampleMethod(String wenzhang,List<String> wenzhang_ciyu,List<Integer> wenzhang_pinshu,List<Integer> wenzhang_pianshu,List<Integer> wenzhang_tongyi) throws UnsupportedEncodingException,FileNotFoundException, IOException {
    Token nt = new Token();
    Analyzer ca = new SmartChineseAnalyzer(true);
    Reader sentence = new StringReader(wenzhang);
    TokenStream ts = ca.tokenStream("sentence", sentence);

    //System.out.println("start: " + (new Date()));
    long before = System.currentTimeMillis();
    nt = ts.next(nt);
    int ww=1;
    while (nt != null) {
     
      //System.out.print(nt.term()+" ");
      //if(ww%10==0){System.out.println();}
      if(wenzhang_ciyu.isEmpty()){
    	  wenzhang_ciyu.add(nt.term());
	      wenzhang_pinshu.add(1);
	      wenzhang_pianshu.add(1);
	      wenzhang_tongyi.add(1); 
      }else{
    	  
    	  for(int i=0;i<wenzhang_ciyu.size();i++){
    	   	   
        	  if(wenzhang_ciyu.get(i).equals(nt.term())){
        		  wenzhang_pinshu.set(i,(wenzhang_pinshu.get(i)+1));
        		  if(wenzhang_tongyi.get(i)==0){
        			  wenzhang_pianshu.set(i,wenzhang_pianshu.get(i)+1);
        			  wenzhang_tongyi.set(i,1);
        		  }
        		  break;
        	  }else{
        		  if((i==(wenzhang_ciyu.size()-1))&&(!(wenzhang_ciyu.get(i).equals(nt.term())))){		 
        		      wenzhang_ciyu.add(nt.term());
        		      wenzhang_pinshu.add(1);
        		      wenzhang_pianshu.add(1);
        		      wenzhang_tongyi.add(1);   		 
        	      }
        	  }
        	
        	 
        	 
           } 
      }
      
      
      nt = ts.next(nt);
      
    }
    ts.close();
    //System.out.println("这次的文章完成！！！");
    //long now = System.currentTimeMillis();
    //System.out.println("time: " + (now - before) / 1000.0 + " s");
    //for(int i=0;i<wenzhang_ciyu.size();i++){
    	//if(i%10==0){System.out.println();}
		//  System.out.print(wenzhang_ciyu.get(i)+":"+wenzhang_pinshu.get(i)+","+wenzhang_pianshu.get(i)+"\t");
    //}
    //文章标记归0，即文章标记中1为已加入，0为未加入
    for(int i=0;i<wenzhang_tongyi.size();i++){
    	wenzhang_tongyi.set(i,0);
    }
  }


}
