package in.cast.baobiao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="this is a demo of the getChars method.";
		char buf[]=new char[4];
		s.getChars(10,14,buf,0);
		System.out.println(buf);
		
		String dd="30.000";
		System.out.println("前面："+dd.split(".")[0]);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String shijian=df.format(new Date());
		System.out.println(shijian);// new Date()为获取当前系统时间
		
		String shijianfenge[]=shijian.split(":");
		for(int i=0;i<3;i++){
			int miao=Integer.parseInt(shijianfenge[2])+i+1;
			String miao_last="";
			if(miao>=60){miao=0;}
			if(miao<10){miao_last="0"+String.valueOf(miao);}else{miao_last=String.valueOf(miao);}
			String shijian_zhong=shijianfenge[0]+":"+shijianfenge[1]+":"+(miao_last)+".000";
			String shijianXX[]=shijian_zhong.split(" ");
			String shijian_last=shijianXX[0]+"T"+shijianXX[1];
			System.out.println(shijian_last);
		}
	}

}
