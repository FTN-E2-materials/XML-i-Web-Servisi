package rs.ac.uns.ftn.examples.sax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**

 * Primer demonstrira upotrebu SAX API-ja za potrebe
 * parsiranje/čitanja XML fajla.
 * 
 * Rukovanje greškama i upozorenjima prilikom parsiranja 
 * kroz implementaciju odgovarajućih callback-a (testirati 
 * sa nevalidnim, loše formiranim XML-om, npr <test> bez
 * matching end-taga).
 * 
 * Parsiranje XML fajla sa DTD specifikacijom omogućuje 
 * handlovanje ignorable whitespace-ova.
 * 
 * Validacija XML fajla u odnosu na njegovu DTD specifikaciju.
 * Testirati dodavanjem npr. test="abc" atributa u otvarajući 
 * tag korenskog elementa.
 * 
 * @author Igor Cverdelj-Fogaraši
 *
 */
public class SAXDtdHandler extends DefaultHandler {

	private static SAXParserFactory factory;
	
	/*
	 * Factory initialization static-block
	 */
	static {
		factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
	}

	private void parse(String filePath) {
		
		try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(new File(filePath), this);
		
		} catch (SAXParseException e) {
			
			System.out.println("[ERROR] Parsing error, line: " + e.getLineNumber() + ", uri: " + e.getSystemId());
			System.out.println("[ERROR] " + e.getMessage() );
			System.out.print("[ERROR] Embedded exception: ");
			
			Exception embeddedException = e;
			if (e.getException() != null)
				embeddedException = e.getException();

			// Print stack trace...
			embeddedException.printStackTrace();
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getLocalName(String unqualifiedName, String qualifiedName) {
		return "".equals(unqualifiedName) ? qualifiedName : unqualifiedName;
	}
	
	public static void main(String[] args) {

		String filePath = null;

		System.out.println("[INFO] SAX Parser");

		if (args.length != 1) {

			// Primer sa DTD specifikacijom
			filePath = "data/xml/licna_karta_dtd.xml";
			
			System.out.println("[INFO] No input file, using default \""	+ filePath + "\"");
			
		} else {
			filePath = args[0];
		}

		SAXDtdHandler handler = new SAXDtdHandler();
		handler.parse(filePath);

	}

	@Override
	public void startDocument() throws SAXException {
		
		System.out.println("START_DOCUMENT");
	}

	@Override
	public void endDocument() throws SAXException {

		System.out.println("END_DOCUMENT");
	}
	
	@Override
	public void startElement(String uri, String uName, String qName, Attributes attributes) throws SAXException {
		
		System.out.print("START_ELEMENT: " + getLocalName(uName, qName));
		
		if (attributes.getLength() > 0) {
			
			System.out.print(", ATTRIBUTES: ");
			
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.print(getLocalName(attributes.getLocalName(i), attributes.getQName(i)) + "=" + attributes.getValue(i));
				if (i < attributes.getLength()-1)
					System.out.print(", ");
			}
		}
		
		System.out.println();
	}

	@Override
	public void endElement(String uri, String uName, String qName) throws SAXException {
		
		System.out.println("END_ELEMENT: " + getLocalName(uName, qName));
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		String characters = new String(ch, start, length).trim();
		
		if (characters.length() > 0)
			System.out.println("CHARACTERS: " + new String(ch, start, length));
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		
		System.out.println("IGNORABLE_WHITESPACE");
	}
	
	/*
	 * Exception handling
	 */

	@Override
	public void error(SAXParseException err) throws SAXParseException {

		// Propagate the exception
		throw err;
	}

	@Override
    public void warning(SAXParseException err) throws SAXParseException {
        
    	System.out.println("[WARN] Warning, line: " + err.getLineNumber() + ", uri: " + err.getSystemId());
        System.out.println("[WARN] " + err.getMessage());
    }

}
