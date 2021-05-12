package rs.ac.uns.ftn.examples.stax.iterator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;


/**
 * 
 * Primer demonstrira upotrebu StAX-ovog iterator API-ja, 
 * za programsko generisanje XML dokumenata.
 * 
 * @author Igor Cverdelj-Fogaraši
 *
 */
public class StAXIteratorWriter {

	private static XMLInputFactory inputFactory;

	private static XMLOutputFactory outputFactory;
	
	private static XMLEventFactory eventFactory;
	
	/*
	 * Factory initialization static-block
	 */
	static {
		inputFactory = XMLInputFactory.newInstance();
		inputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
		
		outputFactory = XMLOutputFactory.newInstance();
		outputFactory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);
		
		eventFactory = XMLEventFactory.newInstance();
	}
	
	/**
	 * Writing a simple XML document using StAX iterator API.
	 * @param filePath Argument passed to main method.
	 */
	private void write(String filePath) {
		try {
			
			XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(filePath));
			
			// Početak XML dokumenta je označen "start document" event-om
			XMLEvent event = eventFactory.createStartDocument();
			eventWriter.add(event);
			
			// Postavljanje podrazumevanog prostora imena
			eventWriter.setDefaultNamespace("http://www.ftn.uns.ac.rs/zavrsni_rad");
			
			// Otvaranje korenskog elementa iz podrazumevanog namespace-a
			eventWriter.add(eventFactory.createStartElement("", "http://www.ftn.uns.ac.rs/zavrsni_rad", "rad"));
			
			// Mapiranje "xsi" prefiksa i definisanje schemaLocation-a atributa tj. schema binding
			eventWriter.add(eventFactory.createAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "schemaLocation", "http://www.ftn.uns.ac.rs/zavrsni_rad ../xsd/zavrsni_rad.xsd"));
			
			// Zadavanje atributa iz default namespace-a
			eventWriter.add(eventFactory.createAttribute("", "http://www.ftn.uns.ac.rs/zavrsni_rad", "vrsta_rada", "Master rad"));
			
			eventWriter.add(eventFactory.createStartElement("", null, "naslovna_strana"));

			eventWriter.add(eventFactory.createStartElement("", null, "institucija"));
			
			eventWriter.add(eventFactory.createStartElement("", null, "univerzitet"));
			eventWriter.add(eventFactory.createCharacters("Univerzitet u Novom Sadu"));
			eventWriter.add(eventFactory.createEndElement("", null, "univerzitet"));
			
			eventWriter.add(eventFactory.createStartElement("", null, "fakultet"));
			eventWriter.add(eventFactory.createCharacters("Fakultet tehničkih nauka"));
			eventWriter.add(eventFactory.createEndElement("", null, "fakultet"));

			eventWriter.add(eventFactory.createStartElement("", null, "departman"));
			eventWriter.add(eventFactory.createCharacters("Računarstvo i automatika"));
			eventWriter.add(eventFactory.createEndElement("", null, "departman"));
			
			eventWriter.add(eventFactory.createStartElement("", null, "katedra"));
			eventWriter.add(eventFactory.createCharacters("Katedra za informatiku"));
			eventWriter.add(eventFactory.createEndElement("", null, "katedra"));

			eventWriter.add(eventFactory.createEndElement("", null, "institucija"));

			eventWriter.add(eventFactory.createStartElement("", null, "autor"));
			eventWriter.add(eventFactory.createStartElement("", null, "ime"));
			eventWriter.add(eventFactory.createCharacters("Petar"));
			eventWriter.add(eventFactory.createEndElement("", null, "ime"));

			eventWriter.add(eventFactory.createStartElement("", null, "prezime"));
			eventWriter.add(eventFactory.createCharacters("Petrović"));
			eventWriter.add(eventFactory.createEndElement("", null, "prezime"));
			
			eventWriter.add(eventFactory.createStartElement("", null, "broj_indeksa"));
			eventWriter.add(eventFactory.createCharacters("RA 1/2012"));
			eventWriter.add(eventFactory.createEndElement("", null, "broj_indeksa"));

			eventWriter.add(eventFactory.createEndElement("", null, "autor"));
			
			eventWriter.add(eventFactory.createStartElement("", null, "tema_rada"));
			eventWriter.add(eventFactory.createAttribute("jezik", "srpski"));
			eventWriter.add(eventFactory.createCharacters("Implementacija podsistema banke u okviru sistema platnog prometa."));
			eventWriter.add(eventFactory.createEndElement("", null, "tema_rada"));
			
			eventWriter.add(eventFactory.createStartElement("", null, "tema_rada"));
			eventWriter.add(eventFactory.createAttribute("jezik", "engleski"));
			eventWriter.add(eventFactory.createCharacters("Implementation of banking subsystem in an electronic payment system."));
			eventWriter.add(eventFactory.createEndElement("", null, "tema_rada"));
			
			eventWriter.add(eventFactory.createStartElement("", null, "nivo_studija"));
			eventWriter.add(eventFactory.createCharacters("MAS"));
			eventWriter.add(eventFactory.createEndElement("", null, "nivo_studija"));
			
			eventWriter.add(eventFactory.createEndElement("", null, "naslovna_strana"));
			
			eventWriter.add(eventFactory.createStartElement("", null, "sadrzaj"));
			eventWriter.add(eventFactory.createComment("Generisati sadržaj"));
			
			/* Implementirati generisanje sadržaja kao u primeru sa Cursor API-jem */ 
			
			eventWriter.add(eventFactory.createEndElement("", null, "sadrzaj"));
			
			eventWriter.add(eventFactory.createStartElement("", null, "poglavlja"));
			eventWriter.add(eventFactory.createComment("Analogno, generisati poglavlja"));
			
			/* Implementirati generisanje sadržaja kao u primeru sa Cursor API-jem */ 
			
			eventWriter.add(eventFactory.createEndElement("", null, "poglavlja"));
			
			// Zatvaranje korenskog elementa
			eventWriter.add(eventFactory.createEndElement("", null, "rad"));
			
			
			eventWriter.add(eventFactory.createEndDocument());
			
			eventWriter.flush();
			eventWriter.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
		
		String filePath = null; 

		System.out.println("[INFO] StAX Iterator API");
		
		if (args.length != 1) {
          filePath = "data/xml/zavrsni_rad_out_2.xml";
          System.out.println("[INFO] No file path defined, using default \"" + filePath + "\"");
        }
        else { 
        	filePath = args[0];
        }
        
		StAXIteratorWriter handler = new StAXIteratorWriter();
		handler.write(filePath);
		
		System.out.println("[INFO] StAX Iterator writing - done.");
	}

}
