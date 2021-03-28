package rs.ac.uns.ftn.examples.stax.cursor;

import java.io.FileOutputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;


/**
 * 
 * Primer demonstrira upotrebu StAX-ovog kursor API-ja, 
 * za programsko generisanje XML dokumenata.
 * 
 * @author Igor Cverdelj-Fogaraši
 *
 */
public class StAXCursorWriter {

	private static XMLInputFactory inputFactory;

	private static XMLOutputFactory outputFactory;
	
	/*
	 * Factory initialization static-block
	 */
	static {
		inputFactory = XMLInputFactory.newInstance();
		inputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
		
		outputFactory = XMLOutputFactory.newInstance();
		outputFactory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);
	}
	
	/**
	 * Writing a simple XML document using StAX cursor API.
	 * @param filePath Argument passed to main method.
	 */
	private void write(String filePath) {
		try {
			
			XMLStreamWriter streamWriter = outputFactory.createXMLStreamWriter(new FileOutputStream(filePath));
			
			streamWriter.writeStartDocument();
			
			// Postavljanje podrazumevanog prostora imena
			streamWriter.setDefaultNamespace("http://www.ftn.uns.ac.rs/zavrsni_rad");
			
			// Definisanje XML schema instance prefiksa
			streamWriter.setPrefix("xsi", "http://www.w3.org/2001/XMLSchema-instance");
			
			// Otvaranje korenskog elementa
			streamWriter.writeStartElement("rad");
			
			// Definisanje schemaLocation atributa (schema binding)
			streamWriter.writeAttribute("http://www.w3.org/2001/XMLSchema-instance", "schemaLocation", "http://www.ftn.uns.ac.rs/zavrsni_rad ../xsd/zavrsni_rad.xsd");
			
			// Zadavanje atributa iz default namespace-a
			streamWriter.writeAttribute("vrsta_rada", "Master rad");
			
			streamWriter.writeStartElement("naslovna_strana");
			
			streamWriter.writeStartElement("institucija");
			
			streamWriter.writeStartElement("univerzitet");
			streamWriter.writeCharacters("Univerzitet u Novom Sadu");
			streamWriter.writeEndElement();

			streamWriter.writeStartElement("fakultet");
			streamWriter.writeCharacters("Fakultet tehničkih nauka");
			streamWriter.writeEndElement();
			
			streamWriter.writeStartElement("departman");
			streamWriter.writeCharacters("Računarstvo i automatika");
			streamWriter.writeEndElement();
			
			streamWriter.writeStartElement("katedra");
			streamWriter.writeCharacters("Katedra za informatiku");
			streamWriter.writeEndElement();
			
			// Zatvaranje "institucija" podelementa
			streamWriter.writeEndElement();
			
			streamWriter.writeStartElement("autor");
			
			streamWriter.writeStartElement("ime");
			streamWriter.writeCharacters("Petar");
			streamWriter.writeEndElement();
			
			streamWriter.writeStartElement("prezime");
			streamWriter.writeCharacters("Petrović");
			streamWriter.writeEndElement();
			
			streamWriter.writeStartElement("broj_indeksa");
			streamWriter.writeCharacters("RA 1/2012");
			streamWriter.writeEndElement();
			
			// Zatvaranje "autor" podelementa
			streamWriter.writeEndElement();
			
			streamWriter.writeStartElement("tema_rada");
			streamWriter.writeAttribute("jezik", "srpski");
			streamWriter.writeCharacters("Implementacija podsistema banke u okviru sistema platnog prometa.");
			streamWriter.writeEndElement();
			
			streamWriter.writeStartElement("tema_rada");
			streamWriter.writeAttribute("jezik", "engleski");
			streamWriter.writeCharacters("Implementation of banking subsystem in an electronic payment system.");
			streamWriter.writeEndElement();
			
			streamWriter.writeStartElement("nivo_studija");
			streamWriter.writeCharacters("MAS");
			streamWriter.writeEndElement();
			
			// Zatvaranje "naslovna_strana" podelementa
			streamWriter.writeEndElement();
			
			streamWriter.writeStartElement("sadrzaj");
			
			streamWriter.writeStartElement("stavka");
			
			streamWriter.writeStartElement("naslov_poglavlja");
			streamWriter.writeCharacters("1. UVOD");
			streamWriter.writeEndElement();
			
			streamWriter.writeStartElement("broj_strane");
			streamWriter.writeCharacters("1");
			streamWriter.writeEndElement();
			
			// Zatvaranje "stavka" podelementa
			streamWriter.writeEndElement();

			// Zatvaranje "sadrzaj" podelementa
			streamWriter.writeEndElement();

			streamWriter.writeStartElement("poglavlja");
			
			streamWriter.writeStartElement("poglavlje");
			
			streamWriter.writeStartElement("naslov");
			streamWriter.writeCharacters("1. UVOD");
			streamWriter.writeEndElement();
			
			streamWriter.writeStartElement("pasus");
			streamWriter.writeCharacters("Ovde ide tekst prvog pasusa uvodnog poglavlja i prateća ilustracija (slika 1.).");
			
			streamWriter.writeStartElement("slika");

			streamWriter.writeStartElement("natpis");
			streamWriter.writeCharacters("Slika 1. Prikaz sistema platnog prometa");
			streamWriter.writeEndElement();
			
			streamWriter.writeStartElement("sadrzaj");
			streamWriter.writeCharacters("d2hpbGUoeG1sci5oYXNOZXh0KCkpIHsNCiAgICAgICAg");
			streamWriter.writeEndElement();
			
			// Zatvaranje "slika" podelementa
			streamWriter.writeEndElement();
			
			// Zatvaranje "pasus" podelementa
			streamWriter.writeEndElement();
			
			// Zatvaranj "poglavlje" podelementa
			streamWriter.writeEndElement();
			
			// Komentar
			streamWriter.writeComment("U nastavku slede ostala poglavlja.");
			
			// Zatvaranj "poglavlja" podelementa			
			streamWriter.writeEndElement();
			
			// Zatvaranje korenskog elementa
			streamWriter.writeEndElement();
			streamWriter.writeEndDocument();
			
			streamWriter.flush();
			streamWriter.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		String filePath = null; 

		System.out.println("[INFO] StAX Cursor API");
		
		if (args.length != 1) {
          filePath = "data/xml/zavrsni_rad_out_1.xml";
          System.out.println("[INFO] No file path defined, using default \"" + filePath + "\"");
        }
        else { 
        	filePath = args[0];
        }
        
		StAXCursorWriter handler = new StAXCursorWriter();
		handler.write(filePath);
		
		System.out.println("[INFO] StAX Cursor writing - done.");
		
	}

}
