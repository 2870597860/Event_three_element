package in.cast;

import in.cast.character.tingdunci;
import in.cast.choose.EVENT_choose;
import in.cast.element.EVENT_element;
import in.fenju.fenju;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EVENT_main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String wenzhang_path = "C:\\Users\\dtf\\Desktop\\���ľ������ı�\\1.txt";
		//��ȡ����
		String wenzhang=new EVENT_main().get_wenzhang(wenzhang_path);
		System.out.println("��ȡ�������");
		//ȥ���ո�
		wenzhang = wenzhang.replaceFirst(" ","");
		System.out.println("ȥ���ո����");
		//ȥ��һЩͣ�ٴ�
		//tingdunci Select_tingdun=new tingdunci();
		//String wenzhang_Select_tingdun=Select_tingdun.Select_tingdunci(wenzhang);
		//System.out.println("ȥ��ͣ�ٴ����");
		//���·־�
		List<String> fenju=new ArrayList<String>();
        new fenju(wenzhang,fenju);
        

		//��ȡ�ı�ʱ�䣬�ص�
		List<String> time=new ArrayList<String>();
		List<String> district=new ArrayList<String>();
		new EVENT_element().element(fenju,time,district);
		
		/**
		for(int i=0;i<thing.size();i++){
			System.out.println();
        	System.out.println(fenju.get(i));
        	System.out.println("�¼���"+thing.get(i));
            System.out.println("ʱ�䣺"+time.get(i));
            System.out.println("�ص㣺"+district.get(i));
            System.out.println();
        }
        **/
     
	}
	
	
    //������µĺ���
	private String get_wenzhang(String path) {
		
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
