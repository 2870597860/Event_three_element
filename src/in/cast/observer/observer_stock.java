package in.cast.observer;

import in.cast.HttpPost.HttpPost;
import in.cast.getXML.Txt_xml;
import in.cast.getXML.observer_xml;
import in.cast.getXML.register_xml;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class observer_stock {
      
	public void observer(String url,List<String> xinxi_thing, List<String> kuaijiANDcaiwu_thing, List<String> dongshihuibaogao_thing, List<String> xiaoshou_thing, List<String> gongying_thing, String fenlei, String biaoji){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String shijian=df.format(new Date());
		//System.out.println(shijian);// new Date()为获取当前系统时间
		String shijian_last=shijian+".000";
		String shijian_zhong[]=shijian_last.split(" ");
		shijian_last=shijian_zhong[0]+"T"+shijian_zhong[1];
		
		
		String observer_xml=new observer_xml().observer(xinxi_thing,kuaijiANDcaiwu_thing,dongshihuibaogao_thing,xiaoshou_thing,gongying_thing,fenlei,biaoji,shijian_last);
		//System.out.println();
		//System.out.println();
		//System.out.println(observer_xml);
		//System.out.println();
		
		//String result=new observer_stock().putXML(url,observer_xml);
		//System.out.println(result);
		
        //董事会报告观测插入
        String dongshihuibaogaoName=dongshihuibaogao_thing.get(dongshihuibaogao_thing.size()-2).split(":")[0];
        String dongshihuibaogaoTXT=dongshihuibaogao_thing.get(dongshihuibaogao_thing.size()-2).split(":")[1];
        //String observer_dongshihuibaogao_xml=new observer_xml().observer_many_TXT(xinxi_thing,dongshihuibaogaoName,dongshihuibaogaoTXT,fenlei,biaoji,shijian_last);
        new observer_stock().many_TXT(url,xinxi_thing,dongshihuibaogaoName,dongshihuibaogaoTXT,fenlei,biaoji,shijian_last);
        
        
        
       //核心竞争力
        String hexinjingzhengliName=dongshihuibaogao_thing.get(dongshihuibaogao_thing.size()-1).split(":")[0];
        String hexinjingzhengliTXT=dongshihuibaogao_thing.get(dongshihuibaogao_thing.size()-1).split(":")[1];
        //String observer_dongshihuibaogao_xml=new observer_xml().observer_many_TXT(xinxi_thing,dongshihuibaogaoName,dongshihuibaogaoTXT,fenlei,biaoji,shijian_last);
        new observer_stock().many_TXT(url,xinxi_thing,hexinjingzhengliName,hexinjingzhengliTXT,fenlei,biaoji,shijian_last);
        
        
        //System.out.println(result);
	}

	public String putXML(String url, String observer_xml) {
		// TODO Auto-generated method stub
		HttpPost httpPost=new HttpPost();
        String result=httpPost.Post(url,observer_xml);
		return result;
	}

	public void many_TXT(String url,List<String> xinxi_thing,
			String Name, String TXT,
			String fenlei, String biaoji, String shijian_last) {
		// TODO Auto-generated method stub
		TXT=TXT.replaceAll(",", "");
		TXT=TXT.replaceAll(":", "");
		
		
		int dijige=0;
		
		String TXT_now="";
		for(int i=0;i<TXT.length();i++){
			TXT_now=TXT_now+TXT.charAt(i);
			if(i==0){continue;}
			if(i==TXT.length()-1){
				dijige++;
				shijian_last=new observer_stock().shijian_jia(dijige);
				String xml=new Txt_xml().getXML(xinxi_thing,Name,TXT_now,fenlei,biaoji,shijian_last);
				String result=new observer_stock().putXML(url,xml);
				//System.out.println("xml:::"+xml);
				System.out.println("结果:::"+result); 
			}else{
				if(i%1000==0){
					dijige++;
					shijian_last=new observer_stock().shijian_jia(dijige);
					String xml=new Txt_xml().getXML(xinxi_thing,Name,TXT_now,fenlei,biaoji,shijian_last);
					String result=new observer_stock().putXML(url,xml);
					TXT_now="";
					//System.out.println("xml:::"+xml);
					System.out.println("结果:::"+result); 
				}
			}
			
			
		}
		
		/***
		int chushu=TXT.length()/2000;
		int yushu=TXT.length()%2000;
		int zhuchegeshu=0;
		if(yushu!=0){zhuchegeshu=zhuchegeshu+chushu+1;}
		
		//System.out.println("多少个:::"+zhuchegeshu);
		if(yushu==1){
			String xml=new Txt_xml().getXML(xinxi_thing,Name,TXT,fenlei,biaoji,shijian_last);
			String result=new observer_stock().putXML(url,xml);
			System.out.println("xml:::"+xml);
			System.out.println("000xml:::"+result); 
			System.out.println("000:::"+TXT);
		}else{
			for(int i=0;i<zhuchegeshu;i++){
				
				shijian_last=new observer_stock().shijian_jia(i);
				//System.out.println("时间:::"+shijian_last);
				String TXT_now="";
				if(i==zhuchegeshu-1){
					char buf[]=new char[2001];
					TXT.getChars((i*2000),(TXT.length()),buf,0);
					TXT_now=String.valueOf(buf);
					TXT_now=TXT_now.replaceAll(" ", "");
					String xml=new Txt_xml().getXML(xinxi_thing,Name,TXT_now,fenlei,biaoji,shijian_last);
					String result=new observer_stock().putXML(url,xml);
					//System.out.println("xml:::"+xml);
					System.out.println(i+":::"+result); 
				}else{
					char buf[]=new char[2001];
					TXT.getChars((i*2000),((i+1)*2000),buf,0);
					TXT_now=String.valueOf(buf);
					String xml=new Txt_xml().getXML(xinxi_thing,Name,TXT_now,fenlei,biaoji,shijian_last);
					String result=new observer_stock().putXML(url,xml);
					//System.out.println("xml:::"+xml);
					System.out.println(i+":::"+result); 
				}
				//System.out.println("当前的TXT:::"+TXT_now); 
		    }
		}
		***/
		
		
	}

	public String shijian_jia(int i) {
		// TODO Auto-generated method stub
		
		
			
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String shijian=df.format(new Date());
			//System.out.println(shijian);// new Date()为获取当前系统时间
			String shijianfenge[]=shijian.split(":");
			
			String miaoyuhaomiao=shijianfenge[2];
			//System.out.println("秒:::"+miaoyuhaomiao); 
			
			int miaomiao=Integer.parseInt(miaoyuhaomiao);
			int miao=miaomiao+i+1;
			//System.out.println("秒秒:::"+miao);
			String miao_last="";
			if(miao>=60){miao=0;}
			
			if(miao<10){miao_last="0"+String.valueOf(miao);}else{miao_last=String.valueOf(miao);}
			String shijian_zhong=shijianfenge[0]+":"+shijianfenge[1]+":"+(miao_last)+".000";
			String shijianXX[]=shijian_zhong.split(" ");
			String shijian_last=shijianXX[0]+"T"+shijianXX[1];
			//System.out.println(shijian_last);
		
		return shijian_last;
	}
}
