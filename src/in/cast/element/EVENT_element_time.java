package in.cast.element;

import java.util.ArrayList;
import java.util.List;

public class EVENT_element_time {
	public void time(String fenju_end, List<String> list_time){
		
		String[] time_biaoji={"����","��","��","��","��","ʱ","��","��","�·�","���","��"};
		String[] time_zhunque={"����һ","���ڶ�","������","������","������","������","������","������",
				              "����","��̧ͷ","Ԫ����","���ս�","��ʳ��","������","�����","��Ϧ��","�����","������","����","��Ϧ",
				              "����","����","����","��Ѯ","����","����","����","����",
				              "��һ","�ܶ�","����","����","����","����","����","����",
				              "��ǰ","����","���","��ǰ","����","����","��ǰ","����","�º�","��ǰ","����","�պ�",
				              "�糿","����","����","Ϧ��","����","����","�賿","����","����",
				              "Ŀǰ",
					          };
		String[] time_jieci_qian={"ǰ","��","����","��ǰ","��","��","ʱ"};
		String[] time_jieci_hou={"��"};
		//��������
		List<String> count=new ArrayList<String>();
		List<Integer> count_weizhi=new ArrayList<Integer>();
		//System.out.print(">>>>"+fenju_end);
		String[] kongge_fenkai = fenju_end.split(" ");
		
		//�����еĶ���
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
    	for(int i=0;i<kongge_fenkai.length;i++){   //i����ڼ�����
    		String[] huoquciyi=kongge_fenkai[i].split("/");
    		if(huoquciyi[1].equals("CD")){
    			count_weizhi.add(i);
    			count.add(huoquciyi[0]);
    			continue;
    		}
    		if(huoquciyi[1].equals("VV")||huoquciyi[1].equals("VA")){
    			continue;
    		}
    		
    		//���Ƿ��С��������յ�
    		for(int j=0;j<time_biaoji.length;j++){  //j����time_biaoji�ĵڼ�����
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
    		
    		
    		//���Ƿ��С���׼ȷ��ʱ����Ϣ
    		for(int j=0;j<time_zhunque.length;j++){  //j����time_biaoji�ĵڼ�����
    			if(time_zhunque[j].equals(huoquciyi[0])){
    				list_time.add(i+","+i+","+time_zhunque[j]);
    			}
    		}
    		
    		**/
    		/**
    		//���Ƿ��С������ǰ
    		for(int j=0;j<time_jieci_qian.length;j++){  //j����time_biaoji�ĵڼ�����
    			if(time_jieci_qian[j].equals(huoquciyi[0])){
    				if((i-1)>0){
    					list_time.add((i-1)+","+kongge_fenkai[i-1].split("/")[0]+time_jieci_qian[j]);
    				}
    			}
    		}
    		
    		//���Ƿ��С�����ʺ�
    		for(int j=0;j<time_jieci_hou.length;j++){  //j����time_biaoji�ĵڼ�����
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
