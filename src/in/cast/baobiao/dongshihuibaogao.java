package in.cast.baobiao;

import java.util.ArrayList;
import java.util.List;

public class dongshihuibaogao {
	
    public void dongshihuibaogao_jiexi(String Text, List<String> dongshihuibaogao_thing, List<String> xiaoshou_thing, List<String> gongying_thing){
		
    	
    	//�ʽ�Ļ�ȡ
    	new dongshihuibaogao().zijin_huoqu(Text,dongshihuibaogao_thing);
    	
		//System.out.println(Text);
    	//�ֽ��   ���»���ڹ�˾�������ھ�Ӫ��������������     ��     ���ľ���������
    	String XXX1="���»���ڹ�˾�������ھ�Ӫ��������������,����";
		String XXX2="��Ӫҵ�����";
		
		String XXX3="���ľ���������";
		String XXX4="Ͷ��״������";
    	String jingyingqingkuang=new dongshihuibaogao().jiequ(Text,XXX1,XXX2);
		//String jingyingqingkuang=new dongshihuibaogao().jiequ(Text,XXX1,XXX1_1,XXX2);
    	String hexinjingzhengli=new dongshihuibaogao().jiequ(Text,XXX3,XXX4);
    	
    	dongshihuibaogao_thing.add(jingyingqingkuang);
    	dongshihuibaogao_thing.add(hexinjingzhengli);
    	
    	//�Թ�Ӧ�����۵Ļ�ȡ
    	String xiaoshou_start="�ͻ�����,��˾ǰ 5 ��ͻ�����,��˾ǰ5��ͻ�����,��Ҫ���ۿͻ������";
    	String xioashou_end="�ɱ�";
    	String gongying_start="��Ӧ������,��˾ǰ 5 ����Ӧ������,��˾ǰ5����Ӧ������,��Ҫ��Ӧ�����";
    	String gongying_end="����";
        String xiaoshou=new dongshihuibaogao().jiequ(Text,xiaoshou_start,xioashou_end);
        String gongying=new dongshihuibaogao().jiequ(Text,gongying_start,gongying_end);
        
        
		//�������빩Ӧ�Ľ���
		if(xiaoshou.indexOf("�ͻ�����")!=-1){
			new dongshihuibaogao().xiaoshou_gongying_jiexi(xiaoshou,xiaoshou_thing);
		}else{
			xiaoshou_thing.add(xiaoshou);
		}
        if(gongying.indexOf("��Ӧ������")!=-1){
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
			int biaoji_baifenhao=2; //1������% 0����ֻ��Ǯ  2������ͨ������
			//System.out.println("///////////"+Text_kongge[i]);
			if((Text_kongge[i].indexOf("��")!=-1)||(Text_kongge[i].indexOf("%")!=-1)){
				if(Text_kongge[i].indexOf(".")!=-1){
					biaoji_baifenhao=1;
				}
			}else{
				if(Text_kongge[i].indexOf(".")!=-1){
					biaoji_baifenhao=0;
				}
			}
			//System.out.println("////////���"+biaoji_baifenhao+"????"+Text_kongge[i]);
			
			if(biaoji_baifenhao==0){
				String company_Name="";
				if((Text_kongge[i-1].indexOf("��˾")!=-1)||(Text_kongge[i-1].indexOf("����")!=-1)){
					company_Name=Text_kongge[i-1];
				}else{if(i>2){
					
					if((Text_kongge[i-2].indexOf("��˾")!=-1)||(Text_kongge[i-2].indexOf("����")!=-1)){
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
    	
    	String[] dongshihuibaogao_biaoji={"Ӫҵ�ɱ�","Ӫҵ����","���۷���","�������","�������","����˰����","Ӫҵ����","������","�з�֧��",
    			
    			"��Ӫ��ֽ�����С��","��Ӫ��ֽ�����С��","Ͷ�ʻ�ֽ�����С��","��Ӫ��������ֽ���������","Ͷ�ʻ�������ֽ���������",
    			"���ʻ�ֽ�����С��","���ʻ�ֽ�����С��","���ʻ�������ֽ���������","�ֽ��ֽ�ȼ��ﾻ���Ӷ�",
    			
    			"�����ʽ�","Ӧ��Ʊ��","Ӧ���˿�","���","���ڹ�ȨͶ��","�̶��ʲ�","�ڽ�����","���ڽ��","���ڽ��","���������ʲ�",
    			"���ڴ�̯����","�����������ʲ�","Ӧ���˿�","Ԥ���˿�","Ӧ����Ϣ","Ӧ������","Ӧ��ծȯ","������������ծ","�ʱ�����","ר���","ӯ�๫��","�����ɶ�Ȩ��",
    			
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
