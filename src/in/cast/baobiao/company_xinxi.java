package in.cast.baobiao;

import java.util.List;

public class company_xinxi {
      
	public void xinxi(String Text, List<String> xinxi_thing){
		 
		 String xinxi[]={"股票代码","公司的中文简称","公司的中文名称","公司的法定代表人","注册地址","电话"};
		 String[] Text_kongge=Text.split(" ");
		 
		 for(int i=0;i<xinxi.length;i++){
			 
			 
			 for(int j=0;j<Text_kongge.length;j++){
				 
				 if(i==2||i==3||i==5){
					 if(Text_kongge[j].indexOf(xinxi[i])!=-1){
						 xinxi_thing.add(xinxi[i]+":"+Text_kongge[j+1]);
					 }
				 }
				 
				 
				 if(i==0){
					 if(Text_kongge[j].indexOf(xinxi[i])!=-1&&Text_kongge[j].equals(xinxi[i])){
						 Boolean isNumber=new dongshihuibaogao().NUMBER(Text_kongge[j+1]); 
						 if(isNumber){
							 xinxi_thing.add(xinxi[i]+":"+Text_kongge[j+1]);
							 break;
						 }else{
							 xinxi_thing.add(" ");
						 }
					 }
				 }
				 
				 if(i==1){
					 if(Text_kongge[j].indexOf(xinxi[i])!=-1&&Text_kongge[j].equals(xinxi[i])){
						 xinxi_thing.add(xinxi[i]+":"+Text_kongge[j+1]);
						 if(xinxi_thing.get(0).equals(" ")){
							 for(int k=0;k<Text_kongge.length;k++){
								 if(Text_kongge[k].indexOf(Text_kongge[j+1])!=-1&&Text_kongge[k].equals(Text_kongge[j+1])){
									 Boolean isNumber=new dongshihuibaogao().NUMBER(Text_kongge[k+1]); 
									 if(isNumber){
										 xinxi_thing.set(0, xinxi[0]+":"+Text_kongge[k+1]);
										 break;
									 }
								 }
							 }
						 }
						 break;
					 }
				 }
				 
				 if(i==4){
					 if(Text_kongge[j].indexOf(xinxi[i])!=-1){
						 String dizhi="";
						 for(int k=j+1;k<Text_kongge.length;k++){
							 if(Text_kongge[k].indexOf("邮政编码")==-1){
								 dizhi=dizhi+Text_kongge[k];
							 }else{break;}
						 }
						 xinxi_thing.add(xinxi[i]+":"+dizhi);
						 break;
					 }
				 }
				 
			 }
			 
		 }
	}
}
