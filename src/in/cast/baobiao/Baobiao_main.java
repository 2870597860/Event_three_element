package in.cast.baobiao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.cast.EVENT_main;
import in.cast.observer.observer_stock;
import in.cast.register.register_stock;

public class Baobiao_main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String wenzhang_path = "C:\\Users\\dtf\\Desktop\\wenzhangpingjia.txt";
		//��ȡ����
		String wenzhang=new Baobiao_main().get_wenzhang(wenzhang_path);
		System.out.println("��ȡ�������");
		wenzhang = wenzhang.replaceAll(" +"," ");
		System.out.println("ȥ���ո����");
		//System.out.println(wenzhang);
		String[] biaoji={"��Ҫ��ʾ��Ŀ¼������","��˾���","������ݺͲ���ָ��ժҪ","���»ᱨ��","��Ҫ����","�ɷݱ䶯���ɶ����",
				"���ȹ�������","���¡����¡��߼�������Ա��Ա�����","��˾����","�ڲ�����","���񱨸�","�����ļ�Ŀ¼"};
		
		//��ȡ���µı����¼�������Ϊ12��
		List<String> biaoji_thing=new ArrayList<String>();
		new Baobiao_main().get_thing(wenzhang,biaoji_thing);
        //��ȡ��˾����Ϣ�����繫˾�����Ʊ����
		List<String> xinxi_thing=new ArrayList<String>();
		new company_xinxi().xinxi(biaoji_thing.get(1),xinxi_thing);
		System.out.println();
		System.out.println("��˾��Ϣ_______________________________________");
		for(int i=0;i<xinxi_thing.size();i++){
			System.out.println(xinxi_thing.get(i));
		}
		//�Ի�����ݺͲ���ָ��ժҪ�������ȡ
		List<String> kuaijiANDcaiwu_thing=new ArrayList<String>();
		new kuaijiANDcaiwu().kuaijiANDcaiwu_jiexi(biaoji_thing.get(2),kuaijiANDcaiwu_thing);
		System.out.println();
		System.out.println("������ݺͲ���ָ��ժҪ______________________________");
		for(int i=0;i<kuaijiANDcaiwu_thing.size();i++){
			System.out.println(kuaijiANDcaiwu_thing.get(i));
		}
		
		//���»ᱨ��
		List<String> dongshihuibaogao_thing=new ArrayList<String>();
		List<String> xiaoshou_thing=new ArrayList<String>();
		List<String> gongying_thing=new ArrayList<String>();
		new dongshihuibaogao().dongshihuibaogao_jiexi(biaoji_thing.get(3),dongshihuibaogao_thing,xiaoshou_thing,gongying_thing);
		System.out.println("���»ᱨ��_______________________________________");
		for(int i=0;i<dongshihuibaogao_thing.size()-2;i++){
			System.out.println(dongshihuibaogao_thing.get(i));
		}
		System.out.println();
		System.out.println();
		System.out.println("���»���ڹ�˾�������ھ�Ӫ��������������__________________");
		String taolunfenxi=dongshihuibaogao_thing.get(dongshihuibaogao_thing.size()-2);
		new Baobiao_main().dayin(taolunfenxi);
		//System.out.println(taolunfenxi);
		System.out.println();
		System.out.println();
		System.out.println("���ľ�����________________________________________");
		String hexinjingzhenli=dongshihuibaogao_thing.get(dongshihuibaogao_thing.size()-1);
		new Baobiao_main().dayin(hexinjingzhenli);
		//System.out.println(hexinjingzhenli);
		System.out.println();
		
		
		
		
		
				
		System.out.println();
		System.out.println("��Ҫ���ۿͻ������___________________________________");
		for(int i=0;i<xiaoshou_thing.size();i++){
			if(i==gongying_thing.size()-1){
				System.out.println("�ܼ�:"+xiaoshou_thing.get(i));
			}else{System.out.println(xiaoshou_thing.get(i));}
		}
		System.out.println();
		System.out.println("��Ҫ��Ӧ�����______________________________________");
		for(int i=0;i<gongying_thing.size();i++){
			if(i==gongying_thing.size()-1){
				System.out.println("�ܼ�:"+gongying_thing.get(i));
			}else{System.out.println(gongying_thing.get(i));}
			
		}
/***
		for(int i=0;i<biaoji_thing.size();i++){
			
			if(i<7){
				System.out.println(biaoji[i]+"~~~~:"+biaoji_thing.get(i));
			}
			
			System.out.println();
		}

**/     
		String fenlei="energy";
		new zhuanhuan().zhuanhuan_ALL(xinxi_thing,kuaijiANDcaiwu_thing,dongshihuibaogao_thing,xiaoshou_thing,gongying_thing);
		//�Թ�Ʊ�����걨ע����۲�   �� RE_OB_sensorΪ0ʱ����ע�ᣬ��RE_OB_sensorΪ1ʱ���й۲�
		//String url="http://202.121.197.111:84/stock_sos/sos";
		String url="http://localhost:8080/52nSOSv3.5.0/sos";
		String biaoji_stock="sensor";
		int RE_OB_sensor=1;
        if(RE_OB_sensor==2){
        	new register_stock().register(xinxi_thing,fenlei,url,biaoji_stock);
        }
        if(RE_OB_sensor==1){
        	new observer_stock().observer(url,xinxi_thing,kuaijiANDcaiwu_thing,dongshihuibaogao_thing,xiaoshou_thing,gongying_thing,fenlei,biaoji_stock);
        }
		
	}
	
	
	private void dayin(String TXT) {
		// TODO Auto-generated method stub
		for(int i=0;i<TXT.length();i++){
			System.out.print(TXT.charAt(i));
			if(i%100==0){System.out.println();}
		}
	}


	private void get_thing(String wenzhang, List<String> biaoji_thing) {
		// TODO Auto-generated method stub
		String[] biaojiXX={"�� ��Ҫ��ʾ��Ŀ¼������","�� ��˾���","�� ������ݺͲ���ָ��ժҪ","�� ���»ᱨ��","�� ��Ҫ����","�� �ɷݱ䶯���ɶ����",
				"�� ���ȹ�������","�� ���¡����¡��߼�������Ա��Ա�����","�� ��˾����","�� �ڲ�����","�� ���񱨸�","�� �����ļ�Ŀ¼"};
		
		List<Integer> biaoji_position=new ArrayList<Integer>();
		
		for(int i=0;i<biaojiXX.length;i++){
			
			int Thefrist=wenzhang.indexOf(biaojiXX[i]);
			//System.out.println(biaojiXX[i]+"the first position :"+Thefrist);
			int Thesecond=wenzhang.indexOf(biaojiXX[i],(Thefrist+biaojiXX[i].length()));
			//System.out.println(i+"   "+biaojiXX[i]+"the second position :"+Thesecond);
			biaoji_position.add(Thesecond);
		}
		
		for(int i=0;i<biaoji_position.size();i++){
			//System.out.println(biaojiXX[i]+"the second position :"+biaoji_position.get(i));
			
			if((biaoji_position.get(i))==-1){
				biaoji_thing.add("");
				continue;
			}
			
			
			int juli=0;
			int start=0;
			int end=0;
			if(i==0){
				juli=biaoji_position.get(i);
				start=0;
				end=biaoji_position.get(i)-4;
			}
			if((i<(biaoji_position.size()-1))&&(i>0)){
				int next=0;
				if(biaoji_position.get(i+1)==-1){
					next=i+2;
				}else{next=i+1;}
				//System.out.println("next:"+next);
				
				juli=(biaoji_position.get(next)-biaoji_position.get(i));
				start=biaoji_position.get(i)+biaojiXX[i].length();
				end=biaoji_position.get(next)-4;
			}
			if(i==(biaoji_position.size()-1)){
				juli=wenzhang.length()-biaoji_position.get(i);
				start=biaoji_position.get(i)+biaojiXX[i].length();
				end=wenzhang.length();
			}
			
			
			
			char buf[]=new char[juli];
			
			wenzhang.getChars(start,end,buf,0);
			String thing=String.valueOf(buf);			
			//System.out.println(thing);			
			//System.out.println();
			biaoji_thing.add(thing);
		}
		
		
	}


	//������µĺ���
	public String get_wenzhang(String path) {
		
		String wenzhang="";
		try {
			File file = new File(path);
			//StringBuilder sb = new StringBuilder();
			String s ="";
			BufferedReader br;
			br = new BufferedReader(new FileReader(file));
			while( (s = br.readLine()) != null) 
			{
				wenzhang=wenzhang+s;					
			}
			
	      } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
	      }
		
		
		
		return wenzhang;
		// TODO Auto-generated method stub
		
	}

}
