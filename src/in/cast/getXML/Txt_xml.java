package in.cast.getXML;

import java.util.List;

public class Txt_xml {
     
	public String getXML(List<String> xinxi_thing, String name, String TXT, String fenlei, String biaoji, String shijian_last){
		
		TXT=TXT.replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<InsertObservation xmlns=\"http://www.opengis.net/sos/1.0\" xmlns:ows=\"http://www.opengis.net/ows/1.1\" xmlns:ogc=\"http://www.opengis.net/ogc\" xmlns:om=\"http://www.opengis.net/om/1.0\" xmlns:sos=\"http://www.opengis.net/sos/1.0\" xmlns:sa=\"http://www.opengis.net/sampling/1.0\" xmlns:gml=\"http://www.opengis.net/gml\" xmlns:swe=\"http://www.opengis.net/swe/1.0.1\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.opengis.net/sos/1.0 http://schemas.opengis.net/sos/1.0.0/sosInsert.xsd http://www.opengis.net/sampling/1.0 http://schemas.opengis.net/sampling/1.0.0/sampling.xsd http://www.opengis.net/om/1.0 http://schemas.opengis.net/om/1.0.0/extensions/observationSpecialization_override.xsd\" service=\"SOS\" version=\"1.0.0\">");
		sb.append("   <AssignedSensorId>"+biaoji+xinxi_thing.get(0)+"</AssignedSensorId>");
		sb.append("   <om:Observation>");
		sb.append("   <om:samplingTime><gml:TimeInstant><gml:timePosition>"+shijian_last+"+08:00</gml:timePosition></gml:TimeInstant></om:samplingTime>");
		sb.append("   <om:procedure xlink:href=\""+biaoji+xinxi_thing.get(0)+"\"/>");
		
		sb.append("   <om:observedProperty>");
		sb.append("      <swe:CompositePhenomenon gml:id=\""+biaoji+xinxi_thing.get(0)+"\" dimension=\"1\">");
		sb.append("			<gml:name>resultComponents</gml:name>");
		sb.append("      	<swe:component xlink:href=\"http://www.opengis.net/def/uom/ISO-8601/0/Gregorian\"/>");
		sb.append("      	<swe:component xlink:href=\"urn:ogc:def:property:OGC:1.0:"+name+"\"/>");
				
		sb.append("      </swe:CompositePhenomenon>");
		sb.append("   </om:observedProperty>");
		//sb.append("      	<gml:name>"+fenlei+"</gml:name>");
		
		sb.append("   <om:featureOfInterest>");
		sb.append("      <sa:SamplingPoint gml:id=\""+biaoji+xinxi_thing.get(0)+"\">");
		sb.append("          <gml:name>"+fenlei+"</gml:name>");
		sb.append("          <sa:sampledFeature xlink:href=\"\"/>");
		//sb.append("          <sa:position><gml:Point srsName=\"urn:ogc:def:crs:EPSG::4326\"><gml:pos>35.535296 114.369321</gml:pos></gml:Point></sa:position>");
		sb.append("          <sa:position><gml:Point> <gml:pos srsName=\"urn:ogc:def:crs:EPSG::4326\">31 121 1</gml:pos></gml:Point></sa:position>");
		sb.append("      </sa:SamplingPoint>");
		sb.append("   </om:featureOfInterest>");
		
		
		sb.append("   <om:result>");
		sb.append("      <swe:DataArray>");
		sb.append("          <swe:elementCount><swe:Count><swe:value>1</swe:value></swe:Count></swe:elementCount>");
		sb.append("          <swe:elementType name=\"Components\">");
		sb.append("              <swe:DataRecord>");
		sb.append("                  <swe:field name=\"Time\"><swe:Time definition=\"http://www.opengis.net/def/uom/ISO-8601/0/Gregorian\"/></swe:field>");		
		sb.append("					 <swe:field name=\"feature\">");
		sb.append("					 	<swe:Text definition=\"http://www.opengis.net/def/property/OGC/0/FeatureOfInterest\" />");
		sb.append("					 </swe:field>");
		sb.append("                  <swe:field name=\""+name+"\">");
		sb.append("						<swe:Quantity definition=\"urn:ogc:def:property:OGC:1.0:"+name+"\">");
		sb.append("						<swe:uom code=\"cm\"/>");
		sb.append("						</swe:Quantity>");
		sb.append("					 </swe:field>");
		
		sb.append("              </swe:DataRecord>");
		sb.append("          </swe:elementType>");
		
		sb.append("         <swe:encoding><swe:TextBlock decimalSeparator=\".\" tokenSeparator=\",\" blockSeparator=\";\"/></swe:encoding>");
		
		sb.append("         <swe:values>");
		String bjAndXinxi=biaoji+xinxi_thing.get(0);
		String TXTResult=TXT;
		sb.append("              "+shijian_last+"+08:00,"+bjAndXinxi+","+TXTResult+";");
		sb.append("         </swe:values>");
		sb.append("      </swe:DataArray>");
		sb.append("   </om:result>");
		sb.append("  </om:Observation>");
		sb.append("</InsertObservation>");
		
		String result=sb.toString();
		System.out.println("xml½á¹û£º"+result);
		
		return sb.toString();
		
	}
}
