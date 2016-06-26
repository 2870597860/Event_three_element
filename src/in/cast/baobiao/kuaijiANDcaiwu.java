package in.cast.baobiao;

import java.util.ArrayList;
import java.util.List;

public class kuaijiANDcaiwu {
      
	public void kuaijiANDcaiwu_jiexi(String Text, List<String> kuaijiANDcaiwu_thing){
		
		//System.out.println(Text);
		
		String[] Text_kongge=Text.split(" ");
		
		String[] kuaijiANDcaiwu_REALLY_biaoji={"营业收入","归属于上市公司股东的净利润","归属于上市公司股东的扣除非经常性损益的净利润","经营活动产生的现金流量净额",
				"基本每股收益","稀释每股收益","归属于上市公司股东的净利润","总资产"};
		String[] kuaijiANDcaiwu_biaoji={"营业收入","归属于上市公司","归属于上市公司","经营活动产生","基本每股","稀释每股","归属于上市公司","总资产"};
		                               //0        1           2           3          4       5        6            7     
		List<Integer> weizhi=new ArrayList<Integer>();
		
		/**
		for(int i=0;i<Text_kongge.length;i++){
			System.out.print(i+"^^"+Text_kongge[i]+"**");
			if(i%15==0){System.out.println();}
		}
		**/
		System.out.println();
		for(int i=0;i<kuaijiANDcaiwu_biaoji.length;i++){
			for(int j=0;j<Text_kongge.length;j++){	
				if(Text_kongge[j].indexOf(kuaijiANDcaiwu_biaoji[i])!=-1){
					weizhi.add(j);
					Text_kongge[j]="";
					//System.out.println(i+"       "+kuaijiANDcaiwu_biaoji[i]+"  位置："+j);
					break;
				}
			}

		}
        
		//System.out.println();
		for(int i=0;i<weizhi.size();i++){
			int XX=weizhi.get(i)+1;
			for(int j=XX;j<Text_kongge.length;j++){
				
				Boolean isNumber=new kuaijiANDcaiwu().NUMBER(Text_kongge[j]);
				if(isNumber){
					//System.out.println(i+"       "+kuaijiANDcaiwu_REALLY_biaoji[i]+"  多少："+Text_kongge[j]);
					kuaijiANDcaiwu_thing.add(kuaijiANDcaiwu_REALLY_biaoji[i]+":"+Text_kongge[j]);
					break;
				}
				
			}
		}

		

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
