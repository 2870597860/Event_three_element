package in.cast.element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class EVENT_element_district {
	public void district(String juzi, List<String> district){
		
		String path = "C:\\Users\\dtf\\Desktop\\µÿµ„.txt";
		//System.out.println(juzi);
		String didian="";
		try {
				File file = new File(path);
				//StringBuilder sb = new StringBuilder();
				String s ="";
				BufferedReader br;
				br = new BufferedReader(new FileReader(file));
				
				while( (s = br.readLine()) != null) 
				{
					//String[] tempArr = s.split(",");
					
					while((juzi.indexOf(s)) != -1) {				
							
						//int x=juzi.indexOf(tempArr[0]);
						//System.out.println("StopŒª÷√"+x); 
						juzi = juzi.replaceFirst(s, ""); 
						didian=didian+" "+s;
					}
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		district.add(didian);
		
	}

}
