package rs.ac.uns.ftn.examples.stax.cursor;

import static javax.xml.stream.XMLStreamConstants.*;
import static rs.ac.uns.ftn.examples.util.ReflectionUtils.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;


/**
 * 
 * Primer demonstrira upotrebu kursor API-ja StAX parsera, 
 * uz validaciju XML fajla u odnosu na njegovu XML šemu.
 * 
 * @author Igor Cverdelj-Fogaraši
 *
 */
public class StAXCursorReader {

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
	 * Cursor API for parsing XML files.
	 * @param filePath Argument passed to main method (e.g. data/xml/zavrsni_rad.xml)
	 * @param schemaPath 
	 */
	private void parse(String filePath, String schemaPath) {

		try {

			XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new FileInputStream(filePath));
		    
		    // Validate XML document against XML schema 
			// Embed <test/> element in XML document.
		    if (validates(filePath, schemaPath)) {
		    
		    	System.out.println("[INFO] XML document passes validation against XML schema.");
		    	
				while (streamReader.hasNext()) {
					handleEvent(streamReader);
					streamReader.next();
					
				}
		    }
			
		} catch (XMLStreamException e) {
		    e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void handleEvent(XMLStreamReader streamReader) throws Exception {
		
		// Determine event type
		int eventType = streamReader.getEventType();

		// Do not print line number for whitespace characters
		if (!streamReader.isWhiteSpace())
			System.out.print("Line " + streamReader.getLocation().getLineNumber() + ", ");
		
	    switch (eventType) {
	        case START_ELEMENT:
	        	
	        	System.out.print(getConstantName(eventType, XMLStreamConstants.class) + ": ");
	        	System.out.print(streamReader.getLocalName());

	        	if (streamReader.getAttributeCount() > 0) {
	        		
	        		System.out.print(", ATTRIBUTES: ");
	        	
		        	for (int i=0; i<streamReader.getAttributeCount(); i++) {
		        		System.out.print(streamReader.getAttributeLocalName(i) + "=" + streamReader.getAttributeValue(i));
		        		if (i < streamReader.getAttributeCount()-1)
		        			System.out.print(", ");
		        	}
	        	}
	        	
	        	System.out.println();
	        	
	        	break;
	        case END_ELEMENT:
	        	
	        	System.out.print(getConstantName(eventType, XMLStreamConstants.class) + ": ");
	        	System.out.println(streamReader.getName().getLocalPart());
	        	
	        	break;
	        case CHARACTERS:
	        	
	        	if (!streamReader.isWhiteSpace())
	        		System.out.println(getConstantName(eventType,	XMLStreamConstants.class) + ": " + streamReader.getText());

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
		
		XMLStreamReader streamReader;
		
		try {
			streamReader = inputFactory.createXMLStreamReader(new FileInputStream(filePath));
			
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		    Schema schema = factory.newSchema(new File(schemaPath));

		    Validator validator = schema.newValidator();
		    validator.validate(new StAXSource(streamReader));
		    
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

		System.out.println("[INFO] StAX Cursor API");
		
		if (args.length != 1) {
          filePath = "data/xml/zavrsni_rad.xml";
          schemaPath = "data/xsd/zavrsni_rad.xsd";
          System.out.println("[INFO] No input file, using default \"" + filePath + "\"");
        }
        else { 
        	filePath = args[0];
        	schemaPath = args[1];
        }
        
		StAXCursorReader handler = new StAXCursorReader();
		handler.parse(filePath, schemaPath);
		
	}

}
