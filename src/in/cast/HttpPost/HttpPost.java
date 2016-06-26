package in.cast.HttpPost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpPost {

	public String Post(String urlStr,String getXmlInforesult) {
		//���ص�xml�ĵ�
				String rusult="";
				//��ʼpost����
				
				try {
		            URL url = new URL(urlStr);
		            URLConnection con = url.openConnection();
		            con.setDoInput(true);
		        	con.setDoOutput(true);
		            OutputStreamWriter out = new OutputStreamWriter(con
		                    .getOutputStream());    
		            //String xmlInfo = getXmlInfo();
		            //System.out.println("urlStr=" + urlStr);
		            //System.out.println("xmlInfo=" + getXmlInforesult);
		            out.write(getXmlInforesult);
		            out.flush();
		            out.close();
		            BufferedReader br = new BufferedReader(new InputStreamReader(con
		                    .getInputStream(),"UTF-8"));
		            
		            String line = "";
		            for (line = br.readLine(); line != null; line = br.readLine()) {
		                //System.out.println(line);
		            	rusult=rusult+line;
		            }
		        } catch (MalformedURLException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				//����post����
				
				// TODO Auto-generated method stub
				return rusult;
			}
}
