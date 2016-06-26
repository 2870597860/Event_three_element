package in.cast.character;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class tingdunci {
	public String Select_tingdunci(String wenzhang){
		
		String path = "C:\\Users\\dutengfei\\Desktop\\Word\\中文停用词库.txt";
		//System.out.println(text);
		try {
				File file = new File(path);
				//StringBuilder sb = new StringBuilder();
				String s ="";
				BufferedReader br;
				br = new BufferedReader(new FileReader(file));
				while( (s = br.readLine()) != null) 
				{
					
					while((wenzhang.indexOf(s)) != -1) {				
							
						int x=wenzhang.indexOf(s);
						//System.out.println("Stop位置"+x); 
						wenzhang = wenzhang.replaceFirst(s, ""); 
					}
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wenzhang;

	}
}
