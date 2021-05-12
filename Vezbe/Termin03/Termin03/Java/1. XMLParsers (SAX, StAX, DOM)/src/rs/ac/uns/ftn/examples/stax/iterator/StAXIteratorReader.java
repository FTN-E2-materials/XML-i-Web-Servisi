package rs.ac.uns.ftn.examples.stax.iterator;

import static javax.xml.stream.XMLStreamConstants.*;
import static rs.ac.uns.ftn.examples.util.ReflectionUtils.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.Attribute;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;


/**
 * 
 * Primer demonstrira upotrebu iterator API-ja StAX parsera, 
 * uz validaciju XML fajla u odnosu na njegovu XML šemu.
 * 
 * @author Igor Cverdelj-Fogaraši
 *
 */
public class StAXIteratorReader {

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
	 * Iterator API for parsing XML files.
	 * @param filePath Argument passed to main method (e.g. data/xml/licna_karta.xml)
	 * @param schemaPath Argument passed to main method (e.g. data/xsd/licna_karta.xsd)
	 */
	private void parse(String filePath, String schemaPath) {

		try {

			XMLEventReader eventReader = inputFactory.createXMLEventReader(new FileInputStream(filePath));

			// Validate XML document against XML schema
			// Embed <test/> element in XML document.
		    if (validates(filePath, schemaPath)) {

		    	System.out.println("[INFO] XML document passes validation against XML schema.");
		    	
				while (eventReader.hasNext()) {
					XMLEvent event = eventReader.nextEvent();
					handleEvent(event);
					
				}
		    }

		} catch (XMLStreamException e) {
		    e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("[ERROR] XML file failed validation against schema.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void handleEvent(XMLEvent event) throws Exception {
		
		// Determine event type
		int eventType = event.getEventType();

		// Do not print line number for whitespace characters
		if (eventType != CHARACTERS || !event.asCharacters().isWhiteSpace())
			System.out.print("Line " + event.getLocation().getLineNumber() + ", ");
		
	    switch (eventType) {
	        case START_ELEMENT:
	        	
	        	System.out.print(getConstantName(eventType, XMLStreamConstants.class) + ": ");
	        	StartElement startElement = event.asStartElement();
	        	System.out.print(startElement.getName().getLocalPart());
	        	
				Iterator<?> attrs = startElement.getAttributes();
	        	
	        	if (attrs.hasNext()) { 
	        		
	        		System.out.print(", ATTRIBUTES: ");

		        	while (attrs.hasNext()) {
		        		Attribute attr = (Attribute) attrs.next();
		        		System.out.print(attr.getName() + "=" + attr.getValue());

		        		// Pretty-print
		        		if (attrs.hasNext()) 
		        			System.out.print(", ");
		        			
		        	}
	        	} 
	        	System.out.println();
	        	
	        	break;
	        case END_ELEMENT:
	        	
	        	System.out.print(getConstantName(eventType, XMLStreamConstants.class) + ": ");
	        	EndElement endElement = event.asEndElement();
	        	System.out.println(endElement.getName().getLocalPart());
	        	
	        	break;
	        case CHARACTERS:
	        	
	        	Characters characters = event.asCharacters();
	        	
	        	if (!characters.isWhiteSpace())
	        		System.out.println(getConstantName(eventType,	XMLStreamConstants.class) + ": " + characters.getData());

	        	break;
	        case PROCESSING_INSTRUCTION:
	        case COMMENT:
	        case START_DOCUMENT:
	        case END_DOCUMENT:
	        case ENTITY_REFERENCE:
	        case ATTRIBUTE:
	        case DTD:
	        case CDATA:
	        case SPACE:
	            System.out.println(getConstantName(eventType, XMLStreamConstants.class));
	        	break;
	        default:
	        	System.out.println("UNKNOWN_EVENT_TYPE, " + eventType);
	    }
	    
	}
	
	/**
	 * 
	 * Validates XML document against XML schema.
	 * 
	 * @param filePath file path.
	 * @param schemaPath XML schema path.
	 * @return validation indicator. 
	 * @throws XMLStreamException 
	 * @throws SAXException 
	 * @throws IOException 
	 */
	private boolean validates(String filePath, String schemaPath) {
		
		XMLEventReader eventReader;
		
		try {
			eventReader = inputFactory.createXMLEventReader(new FileInputStream(filePath));
			
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		    Schema schema = factory.newSchema(new File(schemaPath));

		    Validator validator = schema.newValidator();
		    validator.validate(new StAXSource(eventReader));
		    
		} catch (SAXException e) {
			System.out.println("[ERROR] XML file failed validation against schema.");
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static void main(String[] args) {
		
		String filePath = null; 
		String schemaPath = null;

		System.out.println("[INFO] StAX Iterator API");
		
		if (args.length != 1) {
          filePath = "data/xml/zavrsni_rad.xml";
          schemaPath = "data/xsd/zavrsni_rad.xsd";
          System.out.println("[INFO] No input file, using default \"" + filePath + "\"");
        }
        else { 
        	filePath = args[0];
        	schemaPath = args[1];
        }
        
		StAXIteratorReader handler = new StAXIteratorReader();
		handler.parse(filePath, schemaPath);
		
	}

}
