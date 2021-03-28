package rs.ac.uns.ftn.jaxb.test;
import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import rs.ac.uns.ftn.jaxb.example3.PolozenIspit;
import rs.ac.uns.ftn.jaxb.example3.Predmet;
import rs.ac.uns.ftn.jaxb.example3.Student;
import rs.ac.uns.ftn.jaxb.util.MyDatatypeConverter;
import rs.ac.uns.ftn.jaxb.util.MyValidationEventHandler;

/** 
 * Primer 3.
 * 
 * Primer demonstrira XML schema validaciju prilikom 
 * unmarshalovanja objekta iz XML fajla.
 *  
 */
public class Example3Validation {
	
	public void test() {
		try {
			System.out.println("[INFO] Example 3: JAXB XML Schema validation .\n");
			
			// Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.jaxb.example3");

			// Unmarshaller je objekat zadužen za konverziju iz XML-a u objektni model
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			// XML schema validacija
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("./data/student3.xsd"));
            
			// Podešavanje unmarshaller-a za XML schema validaciju
			unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(new MyValidationEventHandler());
			
            // Test: proširiti XML fajl nepostojećim elementom (npr. <test></test>)
            Student student = (Student) unmarshaller.unmarshal(new File("./data/student3.xml"));

            // Ispis sadržaja objektnog modela, nakon uspešne validacije
            printStudent(student);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	private void printStudent(Student student) {
		
		// Prikaz detalja o položenim ispitima
		System.out.println("- Položeni ispiti \n");
		for(PolozenIspit polozenIspit : student.getPolozeniIspiti())
			printPolozenIspit(polozenIspit);
		
		// Prikaz detalja o odslušanim predmetima
		System.out.println("- Odslušani predmeti \n");
		for(Predmet predmet : student.getOdslusaniPredmeti().getList())
			printOdslusaniPredmet(predmet);
			
	}
	
	private void printPolozenIspit(PolozenIspit polozenIspit) {
		
		System.out.print("\t- Ispit: " + polozenIspit.getPredmet().getNaziv() + ", ");
		System.out.print(polozenIspit.getPredmet().getNastavnik() + ", ");
		System.out.print(polozenIspit.getOcena() + ", ");
		System.out.print(MyDatatypeConverter.printDate(polozenIspit.getDatum()) + ", ");
		System.out.println("šk. godina: " + polozenIspit.getPredmet().getSkolskaGodina() + ".");
		System.out.println();
		
	}
	
	private void printOdslusaniPredmet(Predmet predmet) {
		
		System.out.print("\t- Predmet: " + predmet.getNaziv() + ", ");
		System.out.print(predmet.getNastavnik() + ", ");
		System.out.println("šk. godina: " + predmet.getSkolskaGodina() + ". ");
		System.out.println();
		
	}
	
    public static void main( String[] args ) {
    	Example3Validation test = new Example3Validation();
    	test.test();
    }
}
