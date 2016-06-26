package in.cast.choose;

import in.cast.character.EVENT_chatacter;
import in.cast.character.tingdunci;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

public class EVENT_choose {

	/**
	 * @param args
	 */
	public String event_choose(String wenzhang_Select_tingdun) {
		// TODO Auto-generated method stub
		
	    //�������´����LIst
	    List<String> wenzhang_ciyu=new ArrayList<String>();
	    //��������Ƶ����List
	    List<Integer> wenzhang_pinshu=new ArrayList<Integer>();
	    //�������´���Ȩ��
	    List<Double> wenzhang_quanzhong=new ArrayList<Double>();
		
		
		//********   ֮���Ϊ����
		
		
		//���зִ�
		try {
			new EVENT_choose().fenci(wenzhang_Select_tingdun,wenzhang_ciyu,wenzhang_pinshu);
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
		System.out.println();
		System.out.println("�������");
	    
		
		double quanzhong=0;
		double quanbu_zhong=0;
		double quanbu=0;
		
		//Ȩ�ؼ���
		    //Ȩ���еķ�ĸ����
		for(int i=0;i<wenzhang_ciyu.size();i++){
			  
			  quanbu_zhong=quanbu_zhong+(wenzhang_pinshu.get(i)*wenzhang_pinshu.get(i));
		  }
		  quanbu=Math.sqrt(quanbu_zhong);
		    //Ȩ�صļ���List
		for(int i=0;i<wenzhang_ciyu.size();i++){
			wenzhang_quanzhong.add(wenzhang_pinshu.get(i)/quanbu); 
		}
		
		
		//�����ı����ࡪ����ʼ��������������������������������������������������������������������
		   //��ȡ������γɾ���
		List<Double> baobiao=new ArrayList<Double>();
		List<Double> jingzheng=new ArrayList<Double>();
		List<Double> gupiao=new ArrayList<Double>();
		String path_baobiao= "C:\\Users\\dutengfei\\Desktop\\����\\����ʵ�.txt";
		String path_jingzheng= "C:\\Users\\dutengfei\\Desktop\\����\\�����ʵ�.txt";
		String path_gupiao= "C:\\Users\\dutengfei\\Desktop\\��Ʊ\\��Ʊ�ʵ�.txt";
		
		
		
		for(int i=0;i<3;i++){
			if(i==0){new EVENT_choose().duibi_juzhen(wenzhang_ciyu,baobiao,path_baobiao);}
			if(i==1){new EVENT_choose().duibi_juzhen(wenzhang_ciyu,jingzheng,path_jingzheng);}
			if(i==2){new EVENT_choose().duibi_juzhen(wenzhang_ciyu,gupiao,path_gupiao);}			
		}
		//System.out.println("�����Աȣ�");
		//for(int i=0;i<wenzhang_ciyu.size();i++){
		//	System.out.println(wenzhang_ciyu.get(i)+":"+wenzhang_quanzhong.get(i)+"\t"+baobiao.get(i)+"\t"+jingzheng.get(i)+"\t"+gupiao.get(i));
		//}
		
		String xiangsi=new EVENT_choose().xiangsi(wenzhang_quanzhong,baobiao,jingzheng,gupiao);
		
		//�����Լ���
		
		
		//�����ı����ࡪ��������������������������������������������������������������������������
		
		//*********/
		
		return xiangsi;
	}
	
	
	

	


    //����ȫ���ִ�
	public void fenci(String wenzhang, List<String> wenzhang_ciyu,
			List<Integer> wenzhang_pinshu) throws UnsupportedEncodingException,FileNotFoundException, IOException {
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
	      }else{
	    	  
	    	  for(int i=0;i<wenzhang_ciyu.size();i++){
	    	   	   
	        	  if(wenzhang_ciyu.get(i).equals(nt.term())){
	        		  wenzhang_pinshu.set(i,(wenzhang_pinshu.get(i)+1));
	        		  break;
	        	  }else{
	        		  if((i==(wenzhang_ciyu.size()-1))&&(!(wenzhang_ciyu.get(i).equals(nt.term())))){		 
	        		      wenzhang_ciyu.add(nt.term());
	        		      wenzhang_pinshu.add(1);
	        	      }
	        	  }
	        	
	        	 
	        	 
	           } 
	      }
	      
	      
	      nt = ts.next(nt);
	      
	    }
	    ts.close();
	    //System.out.println("��ε�������ɣ�����");
	    //long now = System.currentTimeMillis();
	    //System.out.println("time: " + (now - before) / 1000.0 + " s");
	    //for(int i=0;i<wenzhang_ciyu.size();i++){
	    	//if(i%10==0){System.out.println();}
			//  System.out.print(wenzhang_ciyu.get(i)+":"+wenzhang_pinshu.get(i)+","+wenzhang_pianshu.get(i)+"\t");
	    //}
	    //���±�ǹ�0�������±����1Ϊ�Ѽ��룬0Ϊδ����
	  
	  }
	
	//��ȡ����
	private void duibi_juzhen(List<String> wenzhang_ciyu, List<Double> dui_juzhen,String path) {
		// TODO Auto-generated method stub
		for(int i=0;i<wenzhang_ciyu.size();i++){
			dui_juzhen.add(0.00);
			
			
            try {
				File file = new File(path);
				//StringBuilder sb = new StringBuilder();
				String s ="";
				BufferedReader br;
				br = new BufferedReader(new FileReader(file));
				while( (s = br.readLine()) != null) 
				{
					String[] tempArr = s.split(",");
					double score = Double.valueOf(tempArr[1]).doubleValue();

					if(tempArr[0].equals(wenzhang_ciyu.get(i))&&tempArr[0].length()==wenzhang_ciyu.get(i).length()){
						dui_juzhen.set(i, score);
					    break;
					}

				}
				

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			
		}
	}

	

    //�����Լ���
	private String xiangsi(List<Double> wenzhang_quanzhong,List<Double> baobiao, List<Double> jingzheng, List<Double> gupiao) {
		// TODO Auto-generated method stub
        double juzhen_X=0;
		
		double juzhen_Y_baobiao=0;
		double juzhen_Y_jingzheng=0;
		double juzhen_Y_gupiao=0;
		
		double juzhen_XY_baobiao=0;
		double juzhen_XY_jingzheng=0;
		double juzhen_XY_gupiao=0;
		
		for(int i=0;i<wenzhang_quanzhong.size();i++){
			juzhen_X=juzhen_X+(wenzhang_quanzhong.get(i)*wenzhang_quanzhong.get(i));
			
			juzhen_Y_baobiao=juzhen_Y_baobiao+(baobiao.get(i)*baobiao.get(i));
			juzhen_Y_jingzheng=juzhen_Y_jingzheng+(jingzheng.get(i)*jingzheng.get(i));
			juzhen_Y_gupiao=juzhen_Y_gupiao+(gupiao.get(i)*gupiao.get(i));
			
			juzhen_XY_baobiao=juzhen_XY_baobiao+(wenzhang_quanzhong.get(i)*baobiao.get(i));
			juzhen_XY_jingzheng=juzhen_XY_jingzheng+(wenzhang_quanzhong.get(i)*jingzheng.get(i));
			juzhen_XY_gupiao=juzhen_XY_gupiao+(wenzhang_quanzhong.get(i)*gupiao.get(i));
		}
		juzhen_X=Math.sqrt(juzhen_X);
		
		juzhen_Y_baobiao=Math.sqrt(juzhen_Y_baobiao);
		juzhen_Y_jingzheng=Math.sqrt(juzhen_Y_jingzheng);
		juzhen_Y_gupiao=Math.sqrt(juzhen_Y_gupiao);
		
		
		//������
		double sim_baobiao=0;
		double sim_jingzheng=0;
		double sim_gupiao=0;
		
		sim_baobiao=juzhen_XY_baobiao/(juzhen_X*juzhen_Y_baobiao);
		sim_jingzheng=juzhen_XY_jingzheng/(juzhen_X*juzhen_Y_jingzheng);
		sim_gupiao=juzhen_XY_gupiao/(juzhen_X*juzhen_Y_gupiao);
		
		return sim_baobiao+";"+sim_jingzheng+";"+sim_gupiao;
	}


}
