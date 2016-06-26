package in.cast.juzizuhe;

import java.util.ArrayList;
import java.util.List;

public class juzi_zuhe {
	
	public void juzizuhe(String weibiaoji,String biaojiqian ,List<String> list_time,List<String> list_district, List<String> thing, List<String> time, List<String> district){
		
		//System.out.println("zuhe:::"+weibiaoji);
		
		//时间段
		List<String> time_tiao=new ArrayList<String>();
		if(!list_time.isEmpty()){
			
			for(int i=0;i<list_time.size();i++){
				weibiaoji=weibiaoji.replace(list_time.get(i).split(",")[2], "");
				
				if(time_tiao.isEmpty()){
					time_tiao.add(list_time.get(i));
				}else{
					int juli=Integer.parseInt(list_time.get(i).split(",")[0])-Integer.parseInt(time_tiao.get(time_tiao.size()-1).split(",")[1]);
					if(juli<=4){
						String qian_weizhi=time_tiao.get(time_tiao.size()-1).split(",")[0];
						String qian_ci=time_tiao.get(time_tiao.size()-1).split(",")[2];
						String hou_weizhi=list_time.get(i).split(",")[1];
						String hou_ci=list_time.get(i).split(",")[2];
						time_tiao.set(time_tiao.size()-1,qian_weizhi+","+hou_weizhi+","+qian_ci+hou_ci);
					}else{
						time_tiao.add(list_time.get(i));
					}
				}
		    }
			
		}
		
		//事件段
		List<String> thing_tiao=new ArrayList<String>();
        String shijianduan="";
        if(!time_tiao.isEmpty()){
        	if(time_tiao.size()==1){
        		thing_tiao.add(weibiaoji);
        	}
        	if(time_tiao.size()>=2){

        			String  juzi_shenyu=weibiaoji;
        			for(int i=1;i<time_tiao.size();i++){
        				int jiexian_count=Integer.parseInt(time_tiao.get(i).split(",")[0])-1;
        				String[] kong_fenkai=biaojiqian.split(" ");
        				String jiexian_ci=kong_fenkai[jiexian_count];
        				
        				String[] thingduan=juzi_shenyu.split(jiexian_ci);
        				thing_tiao.add(thingduan[0]+jiexian_ci);
        				juzi_shenyu="";
        				for(int j=1;j<thingduan.length;j++){
        					juzi_shenyu=juzi_shenyu+thingduan[j];
        				}
        				
        				if(i==(time_tiao.size()-1)){
        					thing_tiao.add(juzi_shenyu);
        				}
        			}


        	}
          
        }else{
        	time_tiao.add("");
        	thing_tiao.add(weibiaoji);
        }
        
        //去除时间条中的位置
        for(int i=0;i<time_tiao.size();i++){
        	if(!time_tiao.get(i).equals("")){
        		time_tiao.set(i, time_tiao.get(i).split(",")[2]);
        	}
        	
        }
        
        //去除地点——开始
        List<String> district_tiao=new ArrayList<String>();
        
    	for(int i=0;i<thing_tiao.size();i++){
    		if(!list_district.isEmpty()){
	        	district_tiao.add("");
	        	new juzi_zuhe().select_district(i,thing_tiao,list_district,district_tiao);
    		}else{district_tiao.add("");}
        }
        //去除地点——结束
    	
    	//加入mian中的事件、地点、时间
        for(int i=0;i<thing_tiao.size();i++){
        	//System.out.println();
            //System.out.println("时间："+time_tiao.get(i));
            //System.out.println("地点："+district_tiao.get(i));
            //System.out.println("句子："+thing_tiao.get(i));
            thing.add(thing_tiao.get(i));
            time.add(time_tiao.get(i));
            district.add(district_tiao.get(i));
        }
        
        
        
      
	}

	private void select_district(int thing_tiao_count, List<String> thing_tiao,List<String> list_district, List<String> district_tiao) {
		// TODO Auto-generated method stub
		String district="";
		for(int i=0;i<list_district.size();i++){
			if(thing_tiao.get(thing_tiao_count).indexOf(list_district.get(i).split(",")[1])>=0){
				thing_tiao.set(thing_tiao_count, thing_tiao.get(thing_tiao_count).replace(list_district.get(i).split(",")[1], ""));
				district_tiao.set(thing_tiao_count, district_tiao.get(thing_tiao_count)+list_district.get(i).split(",")[1]+","+list_district.get(i).split(",")[2]+","+list_district.get(i).split(",")[3]);
			}
		}
	}


}
