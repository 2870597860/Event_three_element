package in.cast.getXML;

import java.util.List;

public class register_xml {
      
	public String register(List<String> xinxi_thing, String fenlei,String biaoji){
		
		String output=new register_xml().getOutput(xinxi_thing,biaoji);
		return new register_xml().registerXML(xinxi_thing,fenlei,output,biaoji);
		
	}
	


	



	public String registerXML(List<String> xinxi_thing, String fenlei, String output,String biaoji){


		//sb.append("<wsa:To>"+sendsesurl+"</wsa:To>");
    	//sb.append("<SOAP-ENV:Envelope xmlns:ses=\"http://www.opengis.net/ses/0.0\" xmlns:wsa=\"http://www.w3.org/2005/08/addressing\" xmlns:essf=\"http://www.opengis.net/es-sf/0.0\" xmlns:wsn-b=\"http://docs.oasis-open.org/wsn/b-2\" xmlns:wsn-br=\"http://docs.oasis-open.org/wsn/br-2\"  xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:SOAP-ENV=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+"\n");
    	sb.append("<RegisterSensor service=\"SOS\" version=\"1.0.0\" xmlns=\"http://www.opengis.net/sos/1.0\" xmlns:swe=\"http://www.opengis.net/swe/1.0.1\" xmlns:ows=\"http://www.opengeospatial.net/ows\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:gml=\"http://www.opengis.net/gml\" xmlns:ogc=\"http://www.opengis.net/ogc\" xmlns:om=\"http://www.opengis.net/om/1.0\" xmlns:sml=\"http://www.opengis.net/sensorML/1.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.opengis.net/sos/1.0 http://schemas.opengis.net/sos/1.0.0/sosRegisterSensor.xsd http://www.opengis.net/om/1.0 http://schemas.opengis.net/om/1.0.0/extensions/observationSpecialization_override.xsd\">"+"\n");
    	sb.append("   <SensorDescription>"+"\n");
    	sb.append("      <sml:SensorML version=\"1.0.1\">"+"\n");
    	sb.append("         <sml:member>"+"\n");
    	sb.append("            <sml:System xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"+"\n");
    	sb.append("               <gml:description></gml:description>"+"\n");
    	sb.append("               <gml:name>"+fenlei+"</gml:name>"+"\n");
    	sb.append("               <sml:identification>"+"\n");
    	sb.append("                  <sml:IdentifierList>"+"\n");
    	sb.append("                     <sml:identifier name=\"标识码\">"+"\n");
    	sb.append("                        <sml:Term definition=\"urn:ogc:def:identifier:OGC:uniqueID\">"+"\n");
    	sb.append("                           <sml:value>"+biaoji+xinxi_thing.get(0)+"</sml:value>"+"\n");
    	sb.append("                        </sml:Term>"+"\n");
    	sb.append("                     </sml:identifier>"+"\n");
    	sb.append("                     <sml:identifier name=\"全称\">"+"\n");
    	sb.append("                        <sml:Term definition=\"urn:ogc:def:identifier:OGC:longName\">"+"\n");
    	sb.append("                           <sml:value>"+xinxi_thing.get(2)+"</sml:value>"+"\n");
    	sb.append("                        </sml:Term>"+"\n");
    	sb.append("                     </sml:identifier>");
    	sb.append("                     <sml:identifier name=\"简称\">"+"\n");
    	sb.append("                        <sml:Term definition=\"urn:ogc:def:identifier:OGC:shortName\">"+"\n");
    	sb.append("                           <sml:value>"+xinxi_thing.get(1)+"</sml:value>"+"\n");
    	sb.append("                        </sml:Term>"+"\n");
    	sb.append("                     </sml:identifier>"+"\n");
    	sb.append("                  </sml:IdentifierList>"+"\n");
    	sb.append("               </sml:identification>"+"\n");
    	sb.append("");
    	
    	sb.append("<sml:classification><sml:ClassifierList><sml:classifier name=\"传感器类型\"><sml:Term definition=\"urn:ogc:def:classifier:OGC:sensorType\">"+"\n");				           
    	sb.append(" <sml:value>"+fenlei+"</sml:value></sml:Term></sml:classifier></sml:ClassifierList></sml:classification>"+"\n");
    	
    	sb.append("<sml:capabilities/>"+"\n");
    	
    	sb.append("              <sml:contact  xlink:arcrole=\"urn:ogc:def:role:operator\" >"+"\n");
    	sb.append("                 <sml:ResponsibleParty>");
    	sb.append("                     <sml:organizationName>"+xinxi_thing.get(2)+"</sml:organizationName>"+"\n");
    	sb.append("                     <sml:contactInfo>"+"\n");
    	//sb.append("                         <sml:phone><sml:voice>"+xinxi_thing.get(5)+"</sml:voice></sml:phone>");
    	sb.append("                         <sml:phone><sml:voice>"+xinxi_thing.get(3)+"</sml:voice></sml:phone>"+"\n");
    	sb.append("                         <sml:address>"+"\n");
    	sb.append("                            <sml:deliveryPoint></sml:deliveryPoint>"+"\n");
    	//sb.append("                            <sml:city>"+xinxi_thing.get(4)+"</sml:city>");
    	sb.append("                            <sml:city>"+xinxi_thing.get(1)+"</sml:city>"+"\n");
    	sb.append("                            <sml:administrativeArea>1</sml:administrativeArea>"+"\n");
    	sb.append("                            <sml:postalCode></sml:postalCode>"+"\n");
    	sb.append("                            <sml:country>China</sml:country>"+"\n");
    	sb.append("                            <sml:electronicMailAddress></sml:electronicMailAddress>"+"\n");
    	sb.append("                        </sml:address>"+"\n");
    	sb.append("                     </sml:contactInfo>"+"\n");
    	sb.append("                 </sml:ResponsibleParty>"+"\n");
    	sb.append("              </sml:contact>"+"\n");

    	sb.append("              <sml:position name=\"传感器位置\">"+"\n");
    	sb.append("                 <swe:Position fixed=\"false\" referenceFrame=\"urn:ogc:def:crs:EPSG:4329\">"+"\n");
    	sb.append("                     <swe:location><swe:Vector definition=\"urn:ogc:def:property:OGC:location\">"+"\n");
    	sb.append("                     <swe:coordinate name=\"纬度\"><swe:Quantity axisID=\"y\" definition=\"urn:ogc:def:property:OGC:latitude\">"+"\n");
    	sb.append("                      <swe:uom code=\"deg\" /><swe:value>31</swe:value></swe:Quantity></swe:coordinate>"+"\n");
    	sb.append("                     <swe:coordinate name=\"经度\"><swe:Quantity axisID=\"x\" definition=\"urn:ogc:def:property:OGC:longitude\">"+"\n");
    	sb.append("                      <swe:uom code=\"deg\" /><swe:value>121</swe:value></swe:Quantity></swe:coordinate>"+"\n");
    	sb.append("                     <swe:coordinate name=\"高度\"><swe:Quantity axisID=\"z\" definition=\"urn:ogc:def:property:OGC:altitude\">"+"\n");
    	sb.append("                      <swe:uom code=\"m\" /><swe:value>1</swe:value></swe:Quantity></swe:coordinate>"+"\n");
    	sb.append("               </swe:Vector></swe:location></swe:Position></sml:position>"+"\n");
    	
    	
    	sb.append("<sml:interfaces><sml:InterfaceList><sml:interface name=\"BaoxieWeatherStation-LVDSC12_SOS\"><sml:InterfaceDefinition><sml:serviceLayer><swe:DataRecord definition=\"urn:ogc:def:interface:OGC:SWEServiceInterface\"><swe:field name=\"urn:ogc:def:interface:OGC:ServiceURL\"><swe:Text><swe:value>http://202.121.197.111:84/stock_sos/sos</swe:value></swe:Text></swe:field><swe:field name=\"urn:ogc:def:interface:OGC:ServiceType\"><swe:Text><swe:value>SOS</swe:value></swe:Text></swe:field> <swe:field name=\"urn:ogc:def:interface:OGC:ServiceSpecificSensorID\">	<swe:Text><swe:value>urn:shusvi:insitusensor:BaoxieWeatherStation-LVDSC12</swe:value></swe:Text></swe:field></swe:DataRecord></sml:serviceLayer></sml:InterfaceDefinition></sml:interface></sml:InterfaceList></sml:interfaces>"+"\n");
    	
    	
    	sb.append("                <sml:outputs>"+"\n");
    	sb.append("                    <sml:OutputList>"+"\n");
    	sb.append("                    "+output+""+"\n");
    	sb.append("                    </sml:OutputList>"+"\n");
    	sb.append("                </sml:outputs>"+"\n");
    	sb.append("             <sml:components />"+"\n");
    	sb.append("           </sml:System>"+"\n");
    	sb.append("        </sml:member>"+"\n");
    	sb.append("      </sml:SensorML>"+"\n");
    	sb.append("   </SensorDescription>"+"\n");
    	
    	
    	sb.append("<ObservationTemplate><om:Observation><om:samplingTime/><om:procedure/><om:observedProperty/><om:featureOfInterest/><om:result/></om:Observation></ObservationTemplate>"+"\n");
    	sb.append("</RegisterSensor>"+"\n");
           
        return sb.toString();
    	
    }
	
	
	public String getOutput(List<String> xinxi_thing, String biaoji) {
		// TODO Auto-generated method stub
		String kuaijiANDcaiwu[]={"yingyeshouru","guishushangshigongsidejinglirun","guishushangshigongsidekouchufeijingchangxingsunyijinlirun",
				"jingyinghuodongchanshengdexinjinliuliangjinge","jibenmeigushouyi","xishimeigushouyi",
				"gushushanghsigongsigudongdejinglirun","zongzichan"
		        };
		
		String kuaijiANDcaiwuXX[]={"yingyechengben","yingyefenyong","xiaoshoufenyong","guanlifeiyong",
				"cawufenyong","suodeishuifeiyong","yingyelirun","jinglirun","yanfazhichu",
				
				"jingyinghuodongxianjinliuruxiaoji","jingyinghuodongxianjinliuchuxiaoji","daozihuodongxianjinliuruxiaoji",
				"jingyinghuodongchanshengdexianjinliuliangjinge","touzihuodongchanshengdexianjinliuliangjinge",
				
				"chouzihuodongxianjinliuruxiaoji","chozihuodongliuchuxiaoji","chouzihuodongchanshengdexianjinliuliangjinge",
				"xianjianjixianjindengjianwujinzengjiae","huobizijin","yingshoupiaoju","yingshouzhangkuan","chunhuo",
				
				"changqiguquantouzi","gudingzichan","zaijiangongcheng","changqijiekuan","duanqijiekuan",
				
				"qitaliudongzijin","changqidaitanfeiyong","qitafeiliudongzichan","yingfuzhangkuan","yushouzhangkuan",
				"yingfulixi","yingfuguli","lingfuzhaiquan","qitafeiliudongfuzhai",
				
				"zibengongji","zhuanxiangchubei","yingyugongji","shaoshugudongquanyi",
				
				"dongshihuiguanyugongshibaogaoqineijingyingqingkuangdetaolunyufenxi","hexinjingzhengli",
		        };
		
//		String output="";
		//String bj=biaoji;
		StringBuilder output=new StringBuilder();

		for(int i=0;i<kuaijiANDcaiwu.length;i++){
			String kjcw=kuaijiANDcaiwu[i];
		output.append("    	<sml:output name=\""+kjcw+"\">"+"\n");
		output.append("    		<swe:Quantity definition=\"urn:ogc:def:property:OGC:1.0:"+kjcw+"\">"+"\n");
		output.append("    			<gml:metaDataProperty>" +"\n");
		output.append("    				<offering>"+"\n");
		output.append("    					<id>shusvi</id>"+"\n");
		output.append("    					<name>Shanghai University SVI</name>" +"\n");
		output.append("    				</offering>" +"\n");
		output.append("    			</gml:metaDataProperty>" +"\n");
		output.append("    			<swe:uom code=\"℃\" />" +"\n");
		output.append("    		</swe:Quantity>" +"\n");
		output.append("    	</sml:output> "+"\n");

//			output=output+"<sml:output name=\""+biaoji+kuaijiANDcaiwu[i]+"\">"+"\n"+
//					           "<swe:Quantity definition=\"urn:ogc:def:property:OGC:1.0:"+biaoji+kuaijiANDcaiwu[i]+"\">"+"\n" +
//							        "<gml:metaDataProperty>" +"\n"+
//							             "<offering>"+"\n" +
//							                 "<id>SHUSVI</id>"+"\n" +
//							                 "<name>Shanghai University SVI</name>" +"\n"+
//							             "</offering>" +"\n"+
//							        "</gml:metaDataProperty>" +"\n"+
//							        "<swe:uom code=\"℃\" />" +"\n"+
//							  "</swe:Quantity>" +"\n"+
//						 "</sml:output> "+"\n";
	}
		
		for(int i=0;i<kuaijiANDcaiwuXX.length-2;i++){
			String kjcwXX=kuaijiANDcaiwuXX[i];
			output.append("    					<sml:output name=\""+kjcwXX+"\">"+"\n");
			output.append("    						<swe:Quantity definition=\"urn:ogc:def:property:OGC:1.0:"+kjcwXX+"\">"+"\n");
			output.append("    							<gml:metaDataProperty>" +"\n");
			output.append("    								<offering>"+"\n");
			output.append("    									<id>shusvi</id>"+"\n");
			output.append("    									<name>Shanghai University SVI</name>" +"\n");
			output.append("    								</offering>" +"\n");
			output.append("    							</gml:metaDataProperty>" +"\n");
			output.append("    							<swe:uom code=\"℃\" />" +"\n");
			output.append("    						</swe:Quantity>" +"\n");
			output.append("    					</sml:output> "+"\n");
//			output=output+"<sml:output name=\""+biaoji+kuaijiANDcaiwuXX[i]+"\">" +"\n"+
//					           "<swe:Quantity definition=\"urn:ogc:def:property:OGC:1.0:"+biaoji+kuaijiANDcaiwuXX[i]+"\">" +"\n"+
//							        "<gml:metaDataProperty>" +"\n"+
//							             "<offering>" +"\n"+
//							                 "<id>SHUSVI</id>" +"\n"+
//							                 "<name>Shanghai University SVI</name>" +"\n"+
//							             "</offering>"+"\n" +
//							        "</gml:metaDataProperty>" +"\n"+
//							        "<swe:uom code=\"℃\" />" +"\n"+
//							  "</swe:Quantity>" +
//						 "</sml:output> ";
		}
		String kjcaXX2=kuaijiANDcaiwuXX[kuaijiANDcaiwuXX.length-2];
		output.append("    					<sml:output name=\""+kjcaXX2+"\">"+"\n");
		output.append("    						<swe:Text definition=\"urn:ogc:def:property:OGC:1.0:"+kjcaXX2+"\">"+"\n");
		output.append("    							<gml:metaDataProperty>" +"\n");
		output.append("    								<offering>"+"\n");
		output.append("    									<id>shusvi</id>"+"\n");
		output.append("    									<name>Shanghai University SVI</name>" +"\n");
		output.append("    								</offering>" +"\n");
		output.append("    							</gml:metaDataProperty>" +"\n");
		//output.append("    							<swe:uom code=\"℃\" />" +"\n");
		output.append("    						</swe:Text>" +"\n");
		output.append("    					</sml:output> "+"\n");	
		String kjcaXX1=kuaijiANDcaiwuXX[kuaijiANDcaiwuXX.length-1];		
		output.append("    					<sml:output name=\""+kjcaXX1+"\">"+"\n");
		output.append("    						<swe:Text definition=\"urn:ogc:def:property:OGC:1.0:"+kjcaXX1+"\">"+"\n");
		output.append("    							<gml:metaDataProperty>" +"\n");
		output.append("    								<offering>"+"\n");
		output.append("    									<id>shusvi</id>"+"\n");
		output.append("    									<name>Shanghai University SVI</name>" +"\n");
		output.append("    								</offering>" +"\n");
		output.append("    							</gml:metaDataProperty>" +"\n");
		//output.append("    							<swe:uom code=\"℃\" />" +"\n");
		output.append("    						</swe:Text>" +"\n");
		output.append("    					</sml:output> "+"\n");
		
		output.append("    					<sml:output name=\"outforcompany\">"+"\n");
		output.append("    						<swe:Text definition=\"urn:ogc:def:property:OGC:1.0:outforcompany\">"+"\n");
		output.append("    							<gml:metaDataProperty>" +"\n");
		output.append("    								<offering>"+"\n");
		output.append("    									<id>shusvi</id>"+"\n");
		output.append("    									<name>Shanghai University SVI</name>" +"\n");
		output.append("    								</offering>" +"\n");
		output.append("    							</gml:metaDataProperty>" +"\n");
		//output.append("    							<swe:uom code=\"℃\" />" +"\n");
		output.append("    						</swe:Text>" +"\n");
		output.append("    					</sml:output> "+"\n");
		
		output.append("    					<sml:output name=\"inforcompany\">"+"\n");
		output.append("    						<swe:Text definition=\"urn:ogc:def:property:OGC:1.0:inforcompany\">"+"\n");
		output.append("    							<gml:metaDataProperty>" +"\n");
		output.append("    								<offering>"+"\n");
		output.append("    									<id>shusvi</id>"+"\n");
		output.append("    									<name>Shanghai University SVI</name>" +"\n");
		output.append("    								</offering>" +"\n");
		output.append("    							</gml:metaDataProperty>" +"\n");
		//output.append("    							<swe:uom code=\"℃\" />" +"\n");
		output.append("    						</swe:Text>" +"\n");
		output.append("    					</sml:output> "+"\n");

//		output=output+"<sml:output name=\""+biaoji+kuaijiANDcaiwuXX[kuaijiANDcaiwuXX.length-2]+"\">"+"\n" +
//		           "<swe:Text definition=\"urn:ogc:def:property:OGC:1.0:"+biaoji+kuaijiANDcaiwuXX[kuaijiANDcaiwuXX.length-2]+"\">"+"\n" +
//				        "<gml:metaDataProperty>"+"\n" +
//				             "<offering>" +"\n"+
//				                 "<id>SHUSVI</id>" +"\n"+
//				                 "<name>Shanghai University SVI</name>" +"\n"+
//				             "</offering>"+"\n" +
//				        "</gml:metaDataProperty>" +"\n"+
//				  "</swe:Text>" +"\n"+
//			 "</sml:output> "+"\n";
//		output=output+"<sml:output name=\""+biaoji+kuaijiANDcaiwuXX[kuaijiANDcaiwuXX.length-1]+"\">" +"\n"+
//		           "<swe:Text definition=\"urn:ogc:def:property:OGC:1.0:"+biaoji+kuaijiANDcaiwuXX[kuaijiANDcaiwuXX.length-1]+"\">" +"\n"+
//				        "<gml:metaDataProperty>"+"\n" +
//				             "<offering>"+"\n" +
//				                 "<id>SHUSVI</id>" +"\n"+
//				                 "<name>Shanghai University SVI</name>"+"\n" +
//				             "</offering>"+"\n" +
//				        "</gml:metaDataProperty>" +"\n"+
//				  "</swe:Text>" +"\n"+
//			 "</sml:output> "+"\n";
//		
//		
//		output=output+"<sml:output name=\""+biaoji+"outforcompany\">" +"\n"+
//		           "<swe:Text definition=\"urn:ogc:def:property:OGC:1.0:"+biaoji+"outforcompany\">" +"\n"+
//				        "<gml:metaDataProperty>"+"\n" +
//				             "<offering>" +"\n"+
//				                 "<id>SHUSVI</id>" +"\n"+
//				                 "<name>Shanghai University SVI</name>"+"\n" +
//				             "</offering>"+"\n" +
//				        "</gml:metaDataProperty>" +"\n"+
//				  "</swe:Text>" +"\n"+
//			 "</sml:output> "+"\n";
//		
//		
//		output=output+"<sml:output name=\""+biaoji+"inforcompany\">" +"\n"+
//		           "<swe:Text definition=\"urn:ogc:def:property:OGC:1.0:"+biaoji+"inforcompany\">"+"\n" +
//				        "<gml:metaDataProperty>" +"\n"+
//				             "<offering>" +"\n"+
//				                 "<id>SHUSVI</id>"+"\n" +
//				                 "<name>Shanghai University SVI</name>" +"\n"+
//				             "</offering>" +"\n"+
//				        "</gml:metaDataProperty>" +"\n"+
//				  "</swe:Text>" +"\n"+
//			 "</sml:output> "+"\n";
		
		return output.toString();
	}
	
    
}
