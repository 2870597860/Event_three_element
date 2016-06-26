package in.cast.getXML;

public class getXmlInfo {
	
	
	
	//注册文档
	public String getXmlInfo(String stockname,String nowprice,String sendsesurl,double increase, int z,String Address){
    	
    	String getxml = null;
    	//z文1的时候生成注册文档
        if(z==1){
        	getxml=register(stockname,nowprice,sendsesurl,increase,Address);
        }
        
		return getxml;
                                  
         
	}
	//z是2为观测文档  
    public String getXmlInfo(String stockname,String nowprice,String sendsesurl,int z,int mubiao){
    	
    	String getxml = null;
    	//z文2的时候生成观测文档
        if(z==2){
        	getxml=watch(stockname,nowprice,sendsesurl,mubiao);
        }
        
        //if(z==3||z==4){
        	//getxml=destroy(stockname,nowprice,sendsesurl);
        //}
		return getxml;
                                  
         
	}
    
    
  //z是3或4为注销文档
    public String getXmlInfo(String registerstockname,String sendsesdestroyurl,int z){
    	
    	String getxml = null;
        
        if(z==4){
        	getxml=destroy(registerstockname,sendsesdestroyurl);
        }
		return getxml;
                                  
         
	}
    
    
    
    
    //对传感器进行注册
    public String register(String stockname,String nowprice,String sendsesurl,double increase,String Address){
    	System.out.println(stockname);
    	//System.out.println(nowprice*(1+increase));
    	String observedProperty="<value>"+stockname+"</value>";    	
    	//String value="<swe:value>"+nowprice*(1+increase)+"</swe:value>";
    	//String Address="http://202.121.197.111:8083";
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<SOAP-ENV:Envelope xmlns:ses=\"http://www.opengis.net/ses/0.0\" xmlns:wsa=\"http://www.w3.org/2005/08/addressing\" xmlns:essf=\"http://www.opengis.net/es-sf/0.0\" xmlns:wsn-b=\"http://docs.oasis-open.org/wsn/b-2\" xmlns:wsn-br=\"http://docs.oasis-open.org/wsn/br-2\"  xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:SOAP-ENV=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
           sb.append("<SOAP-ENV:Header>");
              
              sb.append("<wsa:Action>http://docs.oasis-open.org/wsn/bw-2/NotificationProducer/SubscribeRequest</wsa:Action>");
              sb.append("<wsa:To>"+sendsesurl+"</wsa:To>");
              
              
              sb.append("<wsa:From>");
                 sb.append("<wsa:Address>http://www.w3.org/2005/08/addressing/role/anonymous</wsa:Address>");
              sb.append("</wsa:From>");
              sb.append("<wsa:MessageID>uuid:4e595160-185a-9b3c-3eb6-592c7c5b0c7a</wsa:MessageID>");
              
           sb.append("</SOAP-ENV:Header>");
           sb.append("<SOAP-ENV:Body>");
              sb.append("<wsn-b:Subscribe>");
                 sb.append("<wsn-b:ConsumerReference>");
                   sb.append("<wsa:Address>"+Address+"</wsa:Address>");
                 sb.append("</wsn-b:ConsumerReference>");
                 sb.append("<wsn-b:Filter>");
                   sb.append("<wsn-b:MessageContent Dialect=\"http://www.opengis.net/ses/filter/level3\">");
                   
                     sb.append("<EML xmlns=\"http://www.opengis.net/eml/0.0.1\">");
                        
                       sb.append("<SimplePatterns>");
                       
                         sb.append("<SimplePattern inputName=\"Input\" patternID=\"In2\">");
                           sb.append("<SelectFunctions>"); 
                           sb.append("<SelectFunction createCausality=\"false\" newEventName=\"lastIn2\">"); 
                             sb.append("<SelectMax propertyName=\"Input/doubleValue\"/>"); 
                             sb.append("</SelectFunction>");  
                                
                             sb.append("</SelectFunctions>"); 
                             
                             sb.append("<View>"); 
                           sb.append("<LengthView isBatch=\"false\">"); 
                         sb.append("<EventCount>3</EventCount>");                               
                         sb.append("</LengthView>"); 
                           sb.append("</View>"); 
                           
                          // sb.append("<Guard>"); 
                          // sb.append("<fes:Filter xmlns:fes=\"http://www.opengis.net/fes/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\">"); 
                          // sb.append("<fes:Within>"); 
                          // sb.append("<fes:ValueReference>Input/geometry</fes:ValueReference>"); 
                          // sb.append("<fes:Literal>"); 
                          // sb.append("<gml:Polygon gml:id=\"aoi_01\">"); 
                          // sb.append("<gml:exterior>"); 
                          // sb.append("<gml:LinearRing>"); 
                          // sb.append("<gml:posList>122.12 31.53 120.51 31.53 120.51 30.40 122.12 30.40</gml:posList>"); 
                          // sb.append("</gml:LinearRing>"); 
                          // sb.append("</gml:exterior>"); 
                          // sb.append("</gml:Polygon>"); 
                          // sb.append("</fes:Literal>"); 
                          // sb.append("</fes:Within>"); 
                          // sb.append("</fes:Filter>"); 
                          // sb.append("</Guard>"); 
                           
                           
                           sb.append("<PropertyRestrictions>"); 
                             sb.append("<PropertyRestriction>"); 
                               sb.append("<name>observedProperty</name>"); 
                               sb.append(observedProperty); 
                             sb.append("</PropertyRestriction>");  
                           sb.append("</PropertyRestrictions>"); 
                             
                               sb.append("</SimplePattern>"); 
                              
                               
                               
                             
                               
                               sb.append("<SimplePattern inputName=\"Input\" patternID=\"In1\">");
                                 sb.append("<SelectFunctions>"); 
                                 sb.append("<SelectFunction createCausality=\"false\" newEventName=\"lastIn1\">"); 
                                   sb.append("<SelectMin propertyName=\"Input/doubleValue\"/>"); 
                                   sb.append("</SelectFunction>");                             
                                   sb.append("</SelectFunctions>"); 
                                   
                                   sb.append("<View>"); 
                                 sb.append("<LengthView isBatch=\"false\">"); 
                               sb.append("<EventCount>3</EventCount>");                               
                               sb.append("</LengthView>"); 
                                 sb.append("</View>"); 
                                 
                                 //sb.append("<Guard>"); 
                                 //sb.append("<fes:Filter xmlns:fes=\"http://www.opengis.net/fes/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\">"); 
                                 //sb.append("<fes:Within>"); 
                                 //sb.append("<fes:ValueReference>Input/geometry</fes:ValueReference>"); 
                                 //sb.append("<fes:Literal>"); 
                                 //sb.append("<gml:Polygon gml:id=\"aoi_02\">"); 
                                 //sb.append("<gml:exterior>"); 
                                 //sb.append("<gml:LinearRing>"); 
                                 //sb.append("<gml:posList>122.12 31.53 120.51 31.53 120.51 30.40 122.12 30.40</gml:posList>"); 
                                 //sb.append("</gml:LinearRing>"); 
                                 //sb.append("</gml:exterior>"); 
                                 //sb.append("</gml:Polygon>"); 
                                 //sb.append("</fes:Literal>"); 
                                 //sb.append("</fes:Within>"); 
                                 //sb.append("</fes:Filter>"); 
                                 //sb.append("</Guard>"); 
                                 
                                 sb.append("<PropertyRestrictions>"); 
                                   sb.append("<PropertyRestriction>"); 
                                     sb.append("<name>observedProperty</name>"); 
                                     sb.append(observedProperty); 
                                   sb.append("</PropertyRestriction>");
                                 sb.append("</PropertyRestrictions>"); 
                                   
                                     sb.append("</SimplePattern>"); 
                               
                               
                             sb.append("</SimplePatterns>"); 
                             
                             
                           sb.append("<ComplexPatterns>"); 
                         sb.append("<ComplexPattern patternID=\"In3\">"); 
                             
                             
                       sb.append("<SelectFunctions>"); 
                           
                     sb.append("<SelectFunction createCausality=\"true\" newEventName=\"Alarm\" outputName=\"filealarm\">");
                  
                     sb.append("<NotifyOnSelect>"); 
                         
                 sb.append("<Message>Stock</Message>"); 
               
               sb.append("</NotifyOnSelect>");
               
             sb.append("</SelectFunction>"); 
           sb.append("</SelectFunctions>"); 
           
           //sb.append("<Guard>"); 
           //sb.append("<fes:Filter xmlns:fes=\"http://www.opengis.net/fes/2.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.opengis.net/fes/2.0  http://schemas.opengis.net/filter/2.0/filterAll.xsd\">"); 
           //sb.append("<fes:PropertyIsGreaterThan>"); 
           //sb.append("<fes:ValueReference>lastIn2/doubleValue</fes:ValueReference>"); 
           //sb.append("<fes:ValueReference>lastIn1/doubleValue</fes:ValueReference>"); 
           //sb.append("</fes:PropertyIsGreaterThan>"); 
           //sb.append("</fes:Filter>"); 
           //sb.append("</Guard>"); 
           //sb.append("<StructuralOperator>"); 
           //sb.append("<BEFORE/>"); 
           //sb.append("</StructuralOperator>"); 
           sb.append("<Logicaloperator>"); 
           sb.append("<AND/>"); 
           sb.append("</Logicaloperator>"); 
           sb.append("<FirstPattern>"); 
           sb.append("<PatternReference>In2</PatternReference>"); 
           sb.append("<SelectFunctionNumber>0</SelectFunctionNumber>"); 
           sb.append("</FirstPattern>"); 
           sb.append("<SecondPattern>"); 
           sb.append("<PatternReference>In1</PatternReference>"); 
           sb.append("<SelectFunctionNumber>0</SelectFunctionNumber>"); 
           sb.append("</SecondPattern>"); 
           sb.append("</ComplexPattern>"); 
           sb.append("</ComplexPatterns>"); 
           sb.append("<TimerPatterns/>"); 
           sb.append("<RepetitivePatterns/>"); 
           sb.append("</EML>"); 
           sb.append("</wsn-b:MessageContent>"); 
           sb.append("</wsn-b:Filter>"); 
           sb.append("</wsn-b:Subscribe>"); 
           sb.append("</SOAP-ENV:Body>"); 
           sb.append("</SOAP-ENV:Envelope>"); 
           
           
           
           
        return sb.toString();
    	
    }
    
    
  //对传感器进行观测的生成
    public String watch(String stockname,String nowprice,String sendsesurl,int mubiao){
    	//总共2个公司
    	String[] place={"115.120727 30.206861","112.987987 28.189813","114.133819 36.613736","120.199221 30.182306","114.481849 38.035417","112.561691 37.924701","120.25471 31.913483","116.200158 39.925469","123.770385 41.317013","122.944454 41.138702","113.651694 24.699274","101.694233 26.553369","119.417458 41.268412","118.748814 32.201308","104.088718 36.018108","123.805621 41.840293","114.296638 36.112249","87.606182 43.844254","114.924003 27.797932","117.004885 36.648417","118.487879 31.68545","113.474223 22.981397","114.443289 30.62772","109.770409 40.658628","121.490847 31.272886","117.038447 36.655817","120.339622 31.566296","120.177828 30.350676","106.240507 38.48089"};
    	String observedProperty=stockname;
    	String value=nowprice;
    	System.out.println(stockname);
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:wsa=\"http://www.w3.org/2005/08/addressing\" xmlns:wsnt=\"http://docs.oasis-open.org/wsn/b-2\">");
           sb.append("<soap:Header>");
              sb.append("<wsa:To>"+sendsesurl+"</wsa:To>");
              sb.append("<wsa:Action>http://docs.oasis-open.org/wsn/bw-2/NotificationConsumer/Notify</wsa:Action>");
              sb.append("<wsa:MessageID>uuid:1b4d3025-f80a-a5b6-aa37-864c47fa1a7e</wsa:MessageID>");
              sb.append("<wsa:From>");
                 sb.append("<wsa:Address>http://www.w3.org/2005/08/addressing/role/anonymous</wsa:Address>");
              sb.append("</wsa:From>");
           sb.append("</soap:Header>");
           sb.append("<soap:Body>");
              sb.append("<wsnt:Notify>");
                 sb.append("<wsnt:NotificationMessage>");
                    sb.append("<wsnt:Topic Dialect=\"http://docs.oasis-open.org/wsn/t-1/TopicExpression/Simple\">Measurements</wsnt:Topic>");
                    sb.append("<wsnt:Message>");
                       sb.append("<om:Observation gml:id=\"ot_279501-4\" xmlns:gml=\"http://www.opengis.net/gml\" xmlns:om=\"http://www.opengis.net/om/1.0\" xmlns:sa=\"http://www.opengis.net/sampling/1.0\" xmlns:swe=\"http://www.opengis.net/swe/1.0.1\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">");
                         sb.append("<om:samplingTime>");
                           sb.append("<gml:TimePeriod>");
                             sb.append("<gml:beginPosition>2011-09-10T01:54:00.000+02:00</gml:beginPosition>");
                             sb.append("<gml:endPosition>2011-09-10T01:54:00.000+02:00</gml:endPosition>");
                           sb.append("</gml:TimePeriod>"); 
                         sb.append("</om:samplingTime>"); 
                         sb.append("<om:procedure xlink:href=\"water_gauge_sensor-1\"/>"); 
                         sb.append("<om:observedProperty xlink:href=\""+observedProperty+"\"/>"); 
                         sb.append("<om:featureOfInterest>"); 
                           sb.append("<gml:FeatureCollection>"); 
                             sb.append("<gml:featureMember>"); 
                               sb.append("<sa:SamplingPoint gml:id=\"water_gauge_sensor-1\">"); 
                                 sb.append("<gml:name>water_gauge_sensor-1</gml:name>"); 
                                   sb.append("<sa:sampledFeature xlink:href=\"urn:ogc:def:nil:OGC:unknown\"/>"); 
                                     sb.append("<sa:position>"); 
                                       sb.append("<gml:Point>"); 
                                         sb.append("<gml:pos srsName=\"urn:ogc:def:crs:EPSG:31466\">"+place[mubiao]+"</gml:pos>"); 
                                       sb.append("</gml:Point>"); 
                                     sb.append("</sa:position>"); 
                               sb.append("</sa:SamplingPoint>"); 
                             sb.append("</gml:featureMember>"); 
                           sb.append("</gml:FeatureCollection>"); 
                         sb.append("</om:featureOfInterest>"); 
                         sb.append("<om:result>"+value+"</om:result>"); 
                       sb.append("</om:Observation>");
                     sb.append("</wsnt:Message>");
                   sb.append("</wsnt:NotificationMessage>");
                sb.append("</wsnt:Notify>");
              sb.append("</soap:Body>");
            sb.append("</soap:Envelope>");
            
            
    	return sb.toString();
    	
    }
    
    
  //对传感器进行注销
    public String destroy(String registerstockname,String sendsesdestroyurl){
    	String destroy=registerstockname;
    	System.out.println(registerstockname);   	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:wsa=\"http://www.w3.org/2005/08/addressing\" xmlns:wsnt=\"http://docs.oasis-open.org/wsn/b-2\">");
           sb.append("<soap:Header>");
              sb.append("<wsa:To>"+sendsesdestroyurl+"</wsa:To>");
              sb.append("<wsa:Action>http://docs.oasis-open.org/wsn/bw-2/SubscriptionManager/UnsubscribeRequest</wsa:Action>");
              sb.append("<wsa:MessageID>uuid:4e595160-185a-9b3c-3eb6-592c7c5b0c7a</wsa:MessageID>");
              sb.append("<wsa:From>");
                 sb.append("<wsa:Address>http://www.w3.org/2005/08/addressing/role/anonymous</wsa:Address>");
              sb.append("</wsa:From>");
              sb.append("<muse-wsa:ResourceId xmlns:muse-wsa=\"http://ws.apache.org/muse/addressing\" wsa:IsReferenceParameter=\"true\">"+destroy+"</muse-wsa:ResourceId>");
           sb.append("</soap:Header>");
           sb.append("<soap:Body>");
              sb.append("<wsnt:Unsubscribe/>");                       
           sb.append("</soap:Body>"); 
        sb.append("</soap:Envelope>");
    	
    	
    	
		return sb.toString();
    	
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	

}
