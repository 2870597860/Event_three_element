package in.cast.getXML;

import in.cast.HttpPost.HttpPost;

import java.util.List;

public class observer_xml {

	public String observer(List<String> xinxi_thing,
			List<String> kuaijiANDcaiwu_thing,
			List<String> dongshihuibaogao_thing, List<String> xiaoshou_thing,
			List<String> gongying_thing, String fenlei,String biaoji, String shijian_last) {
		// TODO Auto-generated method stub
		String output_get=new observer_xml().output(xinxi_thing,kuaijiANDcaiwu_thing,dongshihuibaogao_thing,xiaoshou_thing,gongying_thing,biaoji);
		
		return new observer_xml().observerXML(xinxi_thing,fenlei,output_get,biaoji,shijian_last);
		
	}	
    	//String output=new observer_xml().getOutput(xinxi_thing);
		
		


	




	private String output(List<String> xinxi_thing,
			List<String> kuaijiANDcaiwu_thing,
			List<String> dongshihuibaogao_thing, List<String> xiaoshou_thing,
			List<String> gongying_thing, String biaoji) {
		// TODO Auto-generated method stub
		String observedProperty="";
		String DataRecord="";
		String values="";
		
		for(int i=0;i<kuaijiANDcaiwu_thing.size();i++){
			
			//System.out.println("!!!!"+kuaijiANDcaiwu_thing.get(i));
			String Text[]=kuaijiANDcaiwu_thing.get(i).split(":");
			//System.out.println("!!!!"+Text[0]);
			//System.out.println("!!!!"+Text[1]);
			if(Text[1].equals(" ")){continue;}
			
			observedProperty=observedProperty+"<swe:component xlink:href=\"urn:ogc:def:property:OGC:1.0.30:"+biaoji+Text[0]+"\"/>";
			//System.out.println("***"+observedProperty);
			DataRecord=DataRecord+"<swe:field name=\""+biaoji+Text[0]+"\">" +
					"<swe:Quantity definition=\"urn:ogc:def:property:OGC:1.0:"+biaoji+Text[0]+"\">" +
					"<swe:uom code=\"¡æ\"/>" +
							"</swe:Quantity>" +
							"</swe:field>";
			Text[1]=Text[1].replaceAll(",", "");
			Text[1]=Text[1].replaceAll(":", "");
			if(values.equals("")){
				values=Text[1];
			}else{values=values+","+Text[1];}
			
		}
		
		for(int i=0;i<dongshihuibaogao_thing.size()-2;i++){
			
			String Text[]=dongshihuibaogao_thing.get(i).split(":");
			if(Text[1].equals(" ")){continue;}
			observedProperty=observedProperty+"<swe:component xlink:href=\"urn:ogc:def:property:OGC:1.0.30:"+biaoji+Text[0]+"\"/>";
			DataRecord=DataRecord+"<swe:field name=\""+biaoji+Text[0]+"\">" +
					"<swe:Quantity definition=\"urn:ogc:def:property:OGC:1.0:"+biaoji+Text[0]+"\">" +
					"<swe:uom code=\"¡æ\"/>" +
							"</swe:Quantity>" +
							"</swe:field>";
			Text[1]=Text[1].replaceAll(",", "");
			Text[1]=Text[1].replaceAll(":", "");
			values=values+","+Text[1];
		}
		
		/***
		String Text[]=dongshihuibaogao_thing.get(dongshihuibaogao_thing.size()-2).split(":");
		observedProperty=observedProperty+"<swe:component xlink:href=\"urn:ogc:def:property:OGC:1.0:"+Text[0]+"\"/>";
		DataRecord=DataRecord+"<swe:field name=\""+Text[0]+"\">" +
				"<swe:Quantity definition=\"urn:ogc:def:property:OGC:1.0:"+Text[0]+"\">" +
						"</swe:Quantity>" +
						"</swe:field>";
		Text[1]=Text[1].replaceAll(",", "");
		Text[1]=Text[1].replaceAll(":", "");
		values=values+","+Text[1];
		
		
		String Text1[]=dongshihuibaogao_thing.get(dongshihuibaogao_thing.size()-1).split(":");
		observedProperty=observedProperty+"<swe:component xlink:href=\"urn:ogc:def:property:OGC:1.0:"+Text1[0]+"\"/>";
		DataRecord=DataRecord+"<swe:field name=\""+Text1[0]+"\">" +
				"<swe:Quantity definition=\"urn:ogc:def:property:OGC:1.0:"+Text1[0]+"\">" +
						"</swe:Quantity>" +
						"</swe:field>";
		Text1[1]=Text1[1].replaceAll(",", "");
		Text1[1]=Text1[1].replaceAll(":", "");
		values=values+","+Text1[1];
		
		***/
		
		for(int i=0;i<xiaoshou_thing.size();i++){
			
			String Text_xiaoshou=xiaoshou_thing.get(i).replaceAll(",", "");
			if(i==0){
				values=values+","+Text_xiaoshou;
			}else{
				values=values+"/"+Text_xiaoshou;
			}
			
			if(i==xiaoshou_thing.size()-1){
				observedProperty=observedProperty+"<swe:component xlink:href=\"urn:ogc:def:property:OGC:1.0.30:"+biaoji+"outforcompany\"/>";
				DataRecord=DataRecord+"<swe:field name=\""+biaoji+"outforcompany\">" +
						"<swe:Quantity definition=\"urn:ogc:def:property:OGC:1.0:"+biaoji+"outforcompany\">" +
								"</swe:Quantity>" +
								"</swe:field>";
				
				
			}
			
		}
		for(int i=0;i<gongying_thing.size();i++){
			
			String Text_xiaoshou=xiaoshou_thing.get(0).replaceAll(",", "");
			if(i==0){
				values=values+","+Text_xiaoshou;
			}else{
				values=values+"/"+Text_xiaoshou;
			}
			if(i==xiaoshou_thing.size()-1){
				observedProperty=observedProperty+"<swe:component xlink:href=\"urn:ogc:def:property:OGC:1.0.30:"+biaoji+"inforcompany\"/>";
				DataRecord=DataRecord+"<swe:field name=\""+biaoji+"inforcompany\">" +
						"<swe:Quantity definition=\"urn:ogc:def:property:OGC:1.0:"+biaoji+"inforcompany\">" +
								"</swe:Quantity>" +
								"</swe:field>";
			}
		}
		
		//System.out.println("!!!!"+observedProperty);
		return observedProperty+"#####"+DataRecord+"#####"+values;
	}









	public String observerXML(List<String> xinxi_thing,String fenlei,String output_get, String biaoji, String shijian_last) {
		// TODO Auto-generated method stub
		
		String[] output=output_get.split("#####");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<InsertObservation xmlns=\"http://www.opengis.net/sos/1.0\" xmlns:ows=\"http://www.opengis.net/ows/1.1\" xmlns:ogc=\"http://www.opengis.net/ogc\" xmlns:om=\"http://www.opengis.net/om/1.0\" xmlns:sos=\"http://www.opengis.net/sos/1.0\" xmlns:sa=\"http://www.opengis.net/sampling/1.0\" xmlns:gml=\"http://www.opengis.net/gml\" xmlns:swe=\"http://www.opengis.net/swe/1.0.1\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.opengis.net/sos/1.0 http://schemas.opengis.net/sos/1.0.0/sosInsert.xsd http://www.opengis.net/sampling/1.0 http://schemas.opengis.net/sampling/1.0.0/sampling.xsd http://www.opengis.net/om/1.0 http://schemas.opengis.net/om/1.0.0/extensions/observationSpecialization_override.xsd\" service=\"SOS\" version=\"1.0.0\">");
		sb.append("   <AssignedSensorId>"+biaoji+xinxi_thing.get(0)+"</AssignedSensorId>");
		sb.append("   <om:Observation>");
		sb.append("   <om:samplingTime><gml:TimeInstant><gml:timePosition>"+shijian_last+"+08:00</gml:timePosition></gml:TimeInstant></om:samplingTime>");
		sb.append("   <om:procedure xlink:href=\""+biaoji+xinxi_thing.get(0)+"\"/>");
		
		sb.append("   <om:observedProperty>");
		sb.append("      <swe:CompositePhenomenon gml:id=\""+biaoji+xinxi_thing.get(0)+"\" dimension=\"1\">");
		sb.append("      	<gml:name>"+fenlei+"</gml:name>");
		sb.append("      	<swe:component xlink:href=\"http://www.opengis.net/def/uom/ISO-8601/0/Gregorian\"/>");
		sb.append("      	"+output[0]+"");
		sb.append("      </swe:CompositePhenomenon>");
		sb.append("   </om:observedProperty>");
		
		sb.append("   <om:featureOfInterest>");
		sb.append("      <sa:SamplingPoint gml:id=\""+biaoji+xinxi_thing.get(0)+"\">");
		sb.append("          <gml:name>"+fenlei+"</gml:name>");
		sb.append("          <sa:sampledFeature xlink:href=\"\"/>");
		sb.append("          <sa:position><gml:Point srsName=\"urn:ogc:def:crs:EPSG::4326\"><gml:pos>35.535296 114.369321</gml:pos></gml:Point></sa:position>");
		sb.append("      </sa:SamplingPoint>");
		sb.append("   </om:featureOfInterest>");
		
		
		sb.append("   <om:result>");
		sb.append("      <swe:DataArray>");
		sb.append("          <swe:elementCount><swe:Count><swe:value>1</swe:value></swe:Count></swe:elementCount>");
		sb.append("          <swe:elementType name=\"Components\">");
		sb.append("              <swe:DataRecord>");
		sb.append("                  <swe:field name=\"Time\"><swe:Time definition=\"http://www.opengis.net/def/uom/ISO-8601/0/Gregorian\"/></swe:field>");
		sb.append("                  "+output[1]+"");
		sb.append("              </swe:DataRecord>");
		sb.append("          </swe:elementType>");
		
		sb.append("         <swe:encoding><swe:TextBlock decimalSeparator=\".\" tokenSeparator=\",\" blockSeparator=\";\"/></swe:encoding>");
		
		sb.append("         <swe:values>");
		sb.append(""+shijian_last+"+08:00,"+output[2]+";");
		sb.append("         </swe:values>");
		sb.append("      </swe:DataArray>");
		sb.append("   </om:result>");
		sb.append("  </om:Observation>");
		sb.append("</InsertObservation>");
		
		
		
		return sb.toString();
	}









	public String observer_many_TXT(List<String> xinxi_thing,
			String dongshihuibaogaoName, String dongshihuibaogaoTXT,
			String fenlei, String biaoji, String shijian_last) {
		// TODO Auto-generated method stub
		return null;
		
		
		
		
	}









	

	
}
