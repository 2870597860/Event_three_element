package in.cast.baobiao;

import java.util.ArrayList;
import java.util.List;

public class kuaijiANDcaiwu {
      
	public void kuaijiANDcaiwu_jiexi(String Text, List<String> kuaijiANDcaiwu_thing){
		
		//System.out.println(Text);
		
		String[] Text_kongge=Text.split(" ");
		
		String[] kuaijiANDcaiwu_REALLY_biaoji={"Ӫҵ����","���������й�˾�ɶ��ľ�����","���������й�˾�ɶ��Ŀ۳��Ǿ���������ľ�����","��Ӫ��������ֽ���������",
				"����ÿ������","ϡ��ÿ������","���������й�˾�ɶ��ľ�����","���ʲ�"};
		String[] kuaijiANDcaiwu_biaoji={"Ӫҵ����","���������й�˾","���������й�˾","��Ӫ�����","����ÿ��","ϡ��ÿ��","���������й�˾","���ʲ�"};
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
					//System.out.println(i+"       "+kuaijiANDcaiwu_biaoji[i]+"  λ�ã�"+j);
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
					//System.out.println(i+"       "+kuaijiANDcaiwu_REALLY_biaoji[i]+"  ���٣�"+Text_kongge[j]);
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
