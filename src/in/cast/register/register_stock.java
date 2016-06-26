package in.cast.register;


import in.cast.HttpPost.HttpPost;
import in.cast.getXML.register_xml;

import java.util.List;

public class register_stock {
     
	public void register(List<String> xinxi_thing, String fenlei, String url, String biaoji){
		
		
		
		String register_xml=new register_xml().register(xinxi_thing,fenlei,biaoji);
		//System.out.println();
		//System.out.println();
		System.out.println(register_xml);
		//System.out.println();
		HttpPost httpPost=new HttpPost();
        String result=httpPost.Post(url,register_xml);
		
        System.out.println(result);
	}

	

}
