package in.cast.character;

import java.io.*;

public class write_txt {
	
	public void Write(String wenzhang){
		String write_path="C:\\Users\\dutengfei\\Desktop\\报表\\报表词典.txt";
		
		File file=new File(write_path);
		
		try {
		       if(!file.exists()){file.createNewFile();}
			   
		       FileWriter fw=new FileWriter(file);
		       BufferedWriter bw=new BufferedWriter(fw);
		       
		       bw.write(wenzhang);
		       //bw.newLine();  //换行
		       bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}
	
	
	public static void main(String[] args) {
		String wenzhang="ghgrei\r\nohgi\r\noigke";  //txt换行
		new write_txt().Write(wenzhang);
	}
	
}
