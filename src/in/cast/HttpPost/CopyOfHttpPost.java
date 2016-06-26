package in.cast.HttpPost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CopyOfHttpPost {

	public String Post(String urlStr,String getXmlInforesult) {
		//返回的xml文档
		String rusult="";
		//开始post发送
		
		try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");

            OutputStreamWriter out = new OutputStreamWriter(con
                    .getOutputStream());    
            //String xmlInfo = getXmlInfo();
            //System.out.println("urlStr=" + urlStr);
            //System.out.println("xmlInfo=" + getXmlInforesult);
            out.write(new String(getXmlInforesult.getBytes("utf-8")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con
                    .getInputStream()));
            
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
		//结束post发送
		
		// TODO Auto-generated method stub
		return rusult;
	}
    
}
