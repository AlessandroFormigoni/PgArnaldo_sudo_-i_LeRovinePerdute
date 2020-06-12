package it.unibs.ing.arnaldo.rovineperdute;

import java.io.FileInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class ReadFile {
	
	static Graph graph = new Graph();
	static String filename = "PgAr_Map_10000.xml";
	static XMLStreamReader xmlr = null;
	static int size;
	
	public static void initializeReader() {
		try {
		xmlr = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(filename));
		} catch (Exception e) {
		 System.out.println("Errore nell'inizializzazione del reader:");
		 System.out.println(e.getMessage());
		}
	}
	
	public static void extractCities() {
		try {
			while (xmlr.hasNext()) { 
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_DOCUMENT: 
						 break;
					 case XMLStreamConstants.START_ELEMENT: 
						 switch (xmlr.getLocalName()) {
							 case "map": 
								 //xmlr.next();
								 size = Integer.parseInt(xmlr.getAttributeValue(0));
								 break;
							 case "city":
								 //xmlr.next();
								 
							int id = Integer.parseInt(xmlr.getAttributeValue(0));
							String name = xmlr.getAttributeValue(1);
							int x = Integer.parseInt(xmlr.getAttributeValue(2));
							int y = Integer.parseInt(xmlr.getAttributeValue(3));
							int h = Integer.parseInt(xmlr.getAttributeValue(4));
							graph.getList().add(new City(name, id, new Point(x, y, h)));

						 }
						 break;
					 case XMLStreamConstants.END_ELEMENT:
						 break;
					 case XMLStreamConstants.COMMENT:
						 break; 
					 case XMLStreamConstants.CHARACTERS:
						 break;
				 }
				 xmlr.next();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
	}
	}
	
	public static void extractLink() {
		try {
			while (xmlr.hasNext()) { 
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_DOCUMENT: 
						 break;
					 case XMLStreamConstants.START_ELEMENT: 
						 switch (xmlr.getLocalName()) {
						 case "city":
							 int id = Integer.parseInt(xmlr.getAttributeValue(0));
							 xmlr.next();
							while(xmlr.hasNext()) {
								if (xmlr.getEventType()==(XMLStreamConstants.START_ELEMENT)) {
									int to = Integer.parseInt(xmlr.getAttributeValue(0));
									City city = graph.cityFromID(to);
									graph.getList().get(id).addCity(city);
	
								} else if (xmlr.getEventType() == XMLStreamConstants.END_ELEMENT) {
									if (xmlr.getLocalName().equals("city")) break;
								}
								xmlr.next();
							}
						 }
						 break;
					 case XMLStreamConstants.END_ELEMENT:
						 break;
					 case XMLStreamConstants.COMMENT:
						 break; 
					 case XMLStreamConstants.CHARACTERS:
						 break;
				 }
				 xmlr.next();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
	}
		
	}
	
	public static Graph getGraph() {
		return graph;
	}

}
