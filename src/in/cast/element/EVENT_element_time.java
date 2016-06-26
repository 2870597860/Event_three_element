package in.cast.element;

import java.util.ArrayList;
import java.util.List;

public class EVENT_element_time {
	public void time(String fenju_end, List<String> list_time){
		
		String[] time_biaoji={"世纪","年","月","日","天","时","分","秒","月份","年份","点"};
		String[] time_zhunque={"星期一","星期二","星期三","星期四","星期五","星期六","星期天","星期日",
				              "春节","龙抬头","元宵节","社日节","寒食节","清明节","端午节","七夕节","中秋节","重阳节","冬至","除夕",
				              "整天","整日","整周","整旬","整月","整季","整年","近期",
				              "周一","周二","周三","周四","周五","周六","周日","周七",
				              "年前","年中","年后","季前","季中","季后","月前","月中","月后","日前","日中","日后",
				              "早晨","中午","下午","夕阳","傍晚","晚上","凌晨","早上","黎明",
				              "目前",
					          };
		String[] time_jieci_qian={"前","后","以来","以前","到","差","时"};
		String[] time_jieci_hou={"从"};
		//保存数字
		List<String> count=new ArrayList<String>();
		List<Integer> count_weizhi=new ArrayList<Integer>();
		//System.out.print(">>>>"+fenju_end);
		String[] kongge_fenkai = fenju_end.split(" ");
		
		//句子中的短语
		String time_string="";
		
		for(int i=0;i<time_biaoji.length;i++){
			for(int j=0;j<kongge_fenkai.length;j++){
				String[] huoquciyi=kongge_fenkai[j].split("/");
				if(huoquciyi[0].equals(time_biaoji[i])){
					if(j!=0){
						String[] qian=kongge_fenkai[j-1].split("/");
						if(qian[1].equals("CD")||qian[1].equals("NT")){
							time_string=time_string+" "+qian[0]+time_biaoji[i];
						}
					}
				}
			}
		}
		
		for(int i=0;i<time_zhunque.length;i++){
			for(int j=0;j<kongge_fenkai.length;j++){
				String[] huoquciyi=kongge_fenkai[j].split("/");
				if(huoquciyi[0].equals(time_zhunque[i])){
					time_string=time_string+" "+time_zhunque[i];
				}
			}
		}
		
		list_time.add(time_string);
		/**
    	for(int i=0;i<kongge_fenkai.length;i++){   //i代表第几个词
    		String[] huoquciyi=kongge_fenkai[i].split("/");
    		if(huoquciyi[1].equals("CD")){
    			count_weizhi.add(i);
    			count.add(huoquciyi[0]);
    			continue;
    		}
    		if(huoquciyi[1].equals("VV")||huoquciyi[1].equals("VA")){
    			continue;
    		}
    		
    		//看是否有——年月日等
    		for(int j=0;j<time_biaoji.length;j++){  //j代表time_biaoji的第几个词
    			if(time_biaoji[j].equals(huoquciyi[0])){
    				
    				
    				if(!count_weizhi.isEmpty()){
    					int juli=1000;
    					int biaoji=1000;
    					for(int k=0;k<count_weizhi.size();k++){
    						
    						int juli_zhongjian=i-count_weizhi.get(k);
    						if(juli_zhongjian<juli&&juli_zhongjian>0){
    							juli=juli_zhongjian;
    							biaoji=k;
    						}
    					}
    					if((!(juli==1000&&biaoji==1000))&&((i-count_weizhi.get(biaoji))==1)){
    						list_time.add(count_weizhi.get(biaoji)+","+(count_weizhi.get(biaoji)+1)+","+count.get(biaoji)+time_biaoji[j]);
    					}else{list_time.add(i+","+i+","+time_biaoji[j]);}
    					
    					
    				}else{
    					list_time.add(i+","+i+","+time_biaoji[j]);
    				}
  				
    				
    				
    			}
    		}
    		
    		
    		//看是否有——准确的时间信息
    		for(int j=0;j<time_zhunque.length;j++){  //j代表time_biaoji的第几个词
    			if(time_zhunque[j].equals(huoquciyi[0])){
    				list_time.add(i+","+i+","+time_zhunque[j]);
    			}
    		}
    		
    		**/
    		/**
    		//看是否有——介词前
    		for(int j=0;j<time_jieci_qian.length;j++){  //j代表time_biaoji的第几个词
    			if(time_jieci_qian[j].equals(huoquciyi[0])){
    				if((i-1)>0){
    					list_time.add((i-1)+","+kongge_fenkai[i-1].split("/")[0]+time_jieci_qian[j]);
    				}
    			}
    		}
    		
    		//看是否有——介词后
    		for(int j=0;j<time_jieci_hou.length;j++){  //j代表time_biaoji的第几个词
    			if(time_jieci_hou[j].equals(huoquciyi[0])){
    				if((i+1)<kongge_fenkai.length){
    						list_time.add(i+","+time_jieci_hou[j]+kongge_fenkai[i+1].split("/")[0]);
    				}
    			}
    		}
    		
    		
    	}
    	***/
    	
    	
	}

}
