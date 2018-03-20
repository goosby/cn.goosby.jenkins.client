package cn.goosby.jenkins.utils;

import java.io.ByteArrayOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XMLUtils {
	
	public static DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	public static TransformerFactory transFactory = TransformerFactory.newInstance(); 
	public static String generateXml(){
		DocumentBuilder documentBuilder = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		try {
			documentBuilder = docFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			
			DOMSource docSource = new DOMSource(document);
			StreamResult result = new StreamResult();
			
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    transformer.setOutputProperty(OutputKeys.INDENT, "no");
		    
	        result.setOutputStream(baos); 
	        transformer.transform(docSource, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baos.toString();
	}
	
	
}
