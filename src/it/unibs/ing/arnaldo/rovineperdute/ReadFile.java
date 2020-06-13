package it.unibs.ing.arnaldo.rovineperdute;

import java.io.FileInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class ReadFile {
	
	static Graph graph = new Graph();
	static String filename = "PgAr_Map_5.xml"; // to use Test_min_citta.xml set destination to id = size - 2!
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
	/**
	 * 
     *extracts city data such as id coordinates name from xml
	 */
	public static void extractCities() {
		try {
			while (xmlr.hasNext()) {
				switch (xmlr.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:
					break;
				case XMLStreamConstants.START_ELEMENT:
					switch (xmlr.getLocalName()) {
					case "map":
						size = Integer.parseInt(xmlr.getAttributeValue(0));
						break;
					case "city":

						int id = Integer.parseInt(xmlr.getAttributeValue(0));
						String name = xmlr.getAttributeValue(1);
						int x = Integer.parseInt(xmlr.getAttributeValue(2));
						int y = Integer.parseInt(xmlr.getAttributeValue(3));
						int h = Integer.parseInt(xmlr.getAttributeValue(4));
						graph.getList().add(id, new City(name, id, new Point(x, y, h))); // we ensure index equals id

					}
					break;
				default:
					break;
				}
				xmlr.next();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	/**
	 * extracts link list city links by id
	 */
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
					 default: break;
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
