package in.cast.baobiao;

import java.util.ArrayList;
import java.util.List;

public class dongshihuibaogao {
	
    public void dongshihuibaogao_jiexi(String Text, List<String> dongshihuibaogao_thing, List<String> xiaoshou_thing, List<String> gongying_thing){
		
    	
    	//资金的获取
    	new dongshihuibaogao().zijin_huoqu(Text,dongshihuibaogao_thing);
    	
		//System.out.println(Text);
    	//分解出   董事会关于公司报告期内经营情况的讨论与分析     与     核心竞争力分析
    	String XXX1="董事会关于公司报告期内经营情况的讨论与分析,概述";
		String XXX2="主营业务分析";
		
		String XXX3="核心竞争力分析";
		String XXX4="投资状况分析";
    	String jingyingqingkuang=new dongshihuibaogao().jiequ(Text,XXX1,XXX2);
		//String jingyingqingkuang=new dongshihuibaogao().jiequ(Text,XXX1,XXX1_1,XXX2);
    	String hexinjingzhengli=new dongshihuibaogao().jiequ(Text,XXX3,XXX4);
    	
    	dongshihuibaogao_thing.add(jingyingqingkuang);
    	dongshihuibaogao_thing.add(hexinjingzhengli);
    	
    	//对供应与销售的获取
    	String xiaoshou_start="客户名称,公司前 5 大客户资料,公司前5大客户资料,主要销售客户的情况";
    	String xioashou_end="成本";
    	String gongying_start="供应商名称,公司前 5 名供应商资料,公司前5名供应商资料,主要供应商情况";
    	String gongying_end="费用";
        String xiaoshou=new dongshihuibaogao().jiequ(Text,xiaoshou_start,xioashou_end);
        String gongying=new dongshihuibaogao().jiequ(Text,gongying_start,gongying_end);
        
        
		//对销售与供应的解析
		if(xiaoshou.indexOf("客户名称")!=-1){
			new dongshihuibaogao().xiaoshou_gongying_jiexi(xiaoshou,xiaoshou_thing);
		}else{
			xiaoshou_thing.add(xiaoshou);
		}
        if(gongying.indexOf("供应商名称")!=-1){
        	new dongshihuibaogao().xiaoshou_gongying_jiexi(gongying,gongying_thing);
		}else{
			gongying_thing.add(gongying);
		}
		

	}
    
    
	


	private void xiaoshou_gongying_jiexi(String jiexi_txt, List<String> jiexi_thing) {
		// TODO Auto-generated method stub
		String[] Text_kongge=jiexi_txt.split(" ");
		String jiexichuan="";
		for(int i=0;i<Text_kongge.length;i++){
			int biaoji_baifenhao=2; //1代表有% 0代表只是钱  2代表普通的文字
			//System.out.println("///////////"+Text_kongge[i]);
			if((Text_kongge[i].indexOf("％")!=-1)||(Text_kongge[i].indexOf("%")!=-1)){
				if(Text_kongge[i].indexOf(".")!=-1){
					biaoji_baifenhao=1;
				}
			}else{
				if(Text_kongge[i].indexOf(".")!=-1){
					biaoji_baifenhao=0;
				}
			}
			//System.out.println("////////标记"+biaoji_baifenhao+"????"+Text_kongge[i]);
			
			if(biaoji_baifenhao==0){
				String company_Name="";
				if((Text_kongge[i-1].indexOf("公司")!=-1)||(Text_kongge[i-1].indexOf("集团")!=-1)){
					company_Name=Text_kongge[i-1];
				}else{if(i>2){
					
					if((Text_kongge[i-2].indexOf("公司")!=-1)||(Text_kongge[i-2].indexOf("集团")!=-1)){
						company_Name=Text_kongge[i-2];
					}else{
						for(int j=i-1;j>0;j--){
							Boolean isLetter=new dongshihuibaogao().LETTER(Text_kongge[j]);
							if(isLetter){
								company_Name=company_Name+" "+Text_kongge[j];
							}else{break;}
				        }
					}
				}
					
				}
				//System.out.println(company_Name+"___"+Text_kongge[i]);
				jiexichuan=jiexichuan+"/"+company_Name+":"+Text_kongge[i];
			}
			//System.out.println("___"+Text_kongge[i]);
			if(biaoji_baifenhao==1){
				jiexichuan=jiexichuan+":"+Text_kongge[i];
			}
			
		}
		
		String fenkai[]=jiexichuan.split("/");
		for(int i=0;i<fenkai.length;i++){
			jiexi_thing.add(fenkai[i]);
		}
		
	}


	private String jiequ(String text, String XXX1, String XXX1_1, String XXX2) {
		// TODO Auto-generated method stub
		
		int Thefrist=text.indexOf(XXX1);
		if(Thefrist==-1){
			Thefrist=text.indexOf(XXX1_1);
		}
		//System.out.println(biaojiXX[i]+"the first position :"+Thefrist);
		int Thesecond=text.indexOf(XXX2,(Thefrist+XXX1.length()));
        int juli=Thesecond-Thefrist;
		char buf[]=new char[juli];
		
		text.getChars(Thefrist,Thesecond,buf,0);
		String thing=String.valueOf(buf);
		return thing;
	}


	

	private String jiequ(String text, String XXX1, String XXX2) {
		// TODO Auto-generated method stub
		int Thefrist=0;
		if(XXX1.indexOf(",")!=-1){
			String Frist[]=XXX1.split(",");
			for(int i=0;i<Frist.length;i++){
				int biaoji_cunzai=text.indexOf(Frist[i]);
				if(biaoji_cunzai!=-1){
					Thefrist=biaoji_cunzai;
					break;
				}
			}
		}else{
			Thefrist=text.indexOf(XXX1);
		}

		//System.out.println(biaojiXX[i]+"the first position :"+Thefrist);
		int Thesecond=text.indexOf(XXX2,(Thefrist+XXX1.length()));
        int juli=Thesecond-Thefrist;
		char buf[]=new char[juli];
		
		text.getChars(Thefrist,Thesecond,buf,0);
		String thing=String.valueOf(buf);
		return thing;
	}




	private void zijin_huoqu(String Text, List<String> dongshihuibaogao_thing) {
		// TODO Auto-generated method stub
        String[] Text_kongge=Text.split(" ");
    	
    	String[] dongshihuibaogao_biaoji={"营业成本","营业费用","销售费用","管理费用","财务费用","所得税费用","营业利润","净利润","研发支出",
    			
    			"经营活动现金流入小计","经营活动现金流出小计","投资活动现金流入小计","经营活动产生的现金流量净额","投资活动产生的现金流量净额",
    			"筹资活动现金流入小计","筹资活动现金流出小计","筹资活动产生的现金流量净额","现金及现金等价物净增加额",
    			
    			"货币资金","应收票据","应收账款","存货","长期股权投资","固定资产","在建工程","长期借款","短期借款","其他流动资产",
    			"长期待摊费用","其他非流动资产","应付账款","预收账款","应付利息","应付股利","应付债券","其他非流动负债","资本公积","专项储备","盈余公积","少数股东权益",
    			
    	};
    	
    	//List<Integer> weizhi=new ArrayList<Integer>();
    	List<String> dongshihuibaogao_thing_zhong=new ArrayList<String>();
    	for(int i=0;i<dongshihuibaogao_biaoji.length;i++){
    		int biaoji=0;
			for(int j=0;j<Text_kongge.length;j++){	
				if(Text_kongge[j].indexOf(dongshihuibaogao_biaoji[i])!=-1){
					if(Text_kongge[j].equals(dongshihuibaogao_biaoji[i])){
						biaoji=1;
						Boolean isNumber1=new dongshihuibaogao().NUMBER(Text_kongge[j+1]);
						Boolean isNumber2=new dongshihuibaogao().NUMBER(Text_kongge[j+2]);
						
						if(isNumber1){
							dongshihuibaogao_thing_zhong.add(Text_kongge[j+1]);
							break;
						}else{
							if(isNumber2){
								dongshihuibaogao_thing_zhong.add(Text_kongge[j+2]);
								break;
							}else{
								dongshihuibaogao_thing_zhong.add(" ");
								break;
							}
						}
					}
					/**
					else{
						for(int k=1;k<=6;k++){
							Boolean isNumber=new dongshihuibaogao().NUMBER(Text_kongge[j+k]);
							if(isNumber){
								dongshihuibaogao_thing_zhong.add(Text_kongge[j+k]);
							}
						}
					}
					***/

				}
				
				
			}
			if(biaoji==0){
				dongshihuibaogao_thing_zhong.add(" ");
			}
			dongshihuibaogao_thing.add(dongshihuibaogao_biaoji[i]+":"+dongshihuibaogao_thing_zhong.get(i));
			
		}
	}


	public Boolean LETTER(String duanyu) {
		boolean isLetter=false;	
		for(int i=0;i<duanyu.length();i++){
			if(Character.isLetter(duanyu.charAt(i))){
				isLetter=true;
			}
		}
		return isLetter;
		// TODO Auto-generated method stub
		
	}
	
	public Boolean NUMBER(String duanyu) {
		boolean isNumber=false;	
		for(int i=0;i<duanyu.length();i++){
			if(Character.isDigit(duanyu.charAt(i))){
				isNumber=true;
			}
		}
		return isNumber;
		// TODO Auto-generated method stub
		
	}
}
