package in.fenju;

import java.util.ArrayList;
import java.util.List;

public class fenju {


	public fenju(String text, List<String> fenju) {
		// TODO Auto-generated constructor stub
		if((text.indexOf("¡£"))!= -1){
			String[] fenju1 = text.split("¡£");
			for(int i = 0 ; i < fenju1.length ; i++) {
				//System.out.println(fenju1[i]);
				
				if((fenju1[i].indexOf("£¡"))!= -1){
					String[] fenju2 = fenju1[i].split("£¡");	
					for(int  j= 0 ; j < fenju2.length ; j++) {
						//System.out.println(fenju2[j]);
						
						if((fenju2[j].indexOf("£¿"))!= -1){
							String[] fenju3=fenju2[j].split("£¿");	
							for(int  k= 0 ; k < fenju3.length ; k++) {
								if(!(fenju3[k].equals(""))){
									if(!(fenju3[k].equals("")||fenju3[k].equals(" "))){
										fenju.add(fenju3[k]);
									}
									
								}
								
								//System.out.println(fenju[k]);
							}
						}else{
							if(!(fenju2[j].equals("")||fenju2[j].equals(" "))){
								fenju.add(fenju2[j]);
							}
							
							
						}
					}
				}else {
					if((fenju1[i].indexOf("£¿"))!= -1){
						String[] fenju2=fenju1[i].split("£¿");	
						for(int  j= 0 ; j < fenju2.length ; j++) {
							if(!(fenju2[j].equals("")||fenju2[j].equals(" "))){
								fenju.add(fenju2[j]);
							}
							
							//System.out.println(fenju[k]);
						}
					}else{
						if(!(fenju1[i].equals("")||fenju1[i].equals(" "))){
							fenju.add(fenju1[i]);
						}
						
						
					}
				}
			}
			
			
			
		}else {
			if((text.indexOf("£¡"))!= -1){
				String[] fenju1 = text.split("£¡");	
				for(int  j= 0 ; j < fenju1.length ; j++) {
					//System.out.println(fenju1[j]);
					
					if((fenju1[j].indexOf("£¿"))!= -1){
						String[] fenju2=fenju1[j].split("£¿");	
						for(int  k= 0 ; k < fenju2.length ; k++) {
							if(!(fenju2[k].equals("")||fenju2[k].equals(" "))){
								fenju.add(fenju2[k]);
							}
							
							//System.out.println(fenju[k]);
						}
					}else{
						if(!(fenju1[j].equals("")||fenju1[j].equals(" "))){
							fenju.add(fenju1[j]);
						}
						
						
					}
				}
			}else {
				if((text.indexOf("£¿"))!= -1){
					String[] fenju1=text.split("£¿");	
					for(int  j= 0 ; j < fenju1.length ; j++) {
						if(!(fenju1[j].equals("")||fenju1[j].equals(" "))){
							fenju.add(fenju1[j]);
						}
						
						//System.out.println(fenju[k]);
					}
				}else{
					if(!(text.equals("")||text.equals(" "))){
						fenju.add(text);
					}
					
					
				}
			}	
		}
		
		
	}

	
    
}
