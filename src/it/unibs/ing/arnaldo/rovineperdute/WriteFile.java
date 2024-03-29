package it.unibs.ing.arnaldo.rovineperdute;

import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/**
 * Utility class to write the output XML file
 * @author Simone, Alessandro, Francesca
 *
 */
public class WriteFile {

	static String version = "1.0";
	static String encoding = "UTF-8";
	static String filename = "Routes_5.xml";
	static XMLOutputFactory xmlof = null;
	static XMLStreamWriter xmlw = null;
	
	/**<b>set up instance of </b> {@linkplain XMLStreamWriter}   
	 *<b> to initialize file writer </b>
	 */
	public static void initializeWriter() {

		try {
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filename),encoding);
			xmlw.writeStartDocument(encoding, version);
		} catch (Exception e) { 
			System.out.println("Errore nell'inizializzazione del writer:");
			System.out.println(e.getMessage());
		}
	}
	
	/**
     * Writes the optimal path generated by DiJkstra for the two different teams.
	 */
	public static void printFile() {
		
		long time = System.currentTimeMillis();
		Dijkstra.dijkstra(ReadFile.getGraph(), ReadFile.getGraph().cityFromID(0), 0); // planar
		System.out.println("Durata esecuzione Dijkstra per TONATIUH: " + (System.currentTimeMillis() - time) + " ms");
		
		long time2 = System.currentTimeMillis();
		
		String[] autori = {"Simone Macobatti, ", "Alessandro Formigoni, ", "Francesca Gambi "}; 
		
		try { 
			xmlw.writeComment("LISTA AUTORI: " + autori[0] + autori[1] + autori[2]);
			xmlw.writeStartElement("routes"); 
			xmlw.writeStartElement("route");
			xmlw.writeAttribute("team", "Tonatiuh"); 
			xmlw.writeAttribute("cost", Double.toString(Dijkstra.getDist()[ReadFile.size - 1])); // to use test file use -2
			xmlw.writeAttribute("cities", Integer.toString(Dijkstra.getRoute().size()));
			for (City city : Dijkstra.getRoute()) {
				xmlw.writeStartElement("city"); 
				xmlw.writeAttribute("id", Integer.toString(city.getId())); 
				xmlw.writeAttribute("name", city.getName());
				xmlw.writeEndElement();
			}
			xmlw.writeEndElement();
		} catch (Exception e) {
			System.out.println("Errore nella scrittura");
		}
		
		time2 = System.currentTimeMillis() - time2;
		
		time = System.currentTimeMillis();
		Dijkstra.dijkstra(ReadFile.getGraph(), ReadFile.getGraph().cityFromID(0), 1); // vertical
		System.out.println("Durata esecuzione Dijkstra per METZTLI: " + (System.currentTimeMillis() - time) + " ms");
		
		long time3 = System.currentTimeMillis();
		
		try {  
			xmlw.writeStartElement("route");
			xmlw.writeAttribute("team", "Metztli"); 
			xmlw.writeAttribute("cost", Double.toString(Dijkstra.getDist()[ReadFile.size - 1])); // to use test file use -2
			xmlw.writeAttribute("cities", Integer.toString(Dijkstra.getRoute().size()));
			for (City city : Dijkstra.getRoute()) {
				xmlw.writeStartElement("city"); 
				xmlw.writeAttribute("id", Integer.toString(city.getId())); 
				xmlw.writeAttribute("name", city.getName());
				xmlw.writeEndElement();
			}
			xmlw.writeEndElement();
			xmlw.writeEndElement();
			xmlw.flush();
			xmlw.close();
		} catch (Exception e) {
			System.out.println("Errore nella scrittura");
		}
		
		time3 = System.currentTimeMillis() - time3 + time2;
		System.out.println("Tempo scrittura: " + time3 + " ms");
	}
}
