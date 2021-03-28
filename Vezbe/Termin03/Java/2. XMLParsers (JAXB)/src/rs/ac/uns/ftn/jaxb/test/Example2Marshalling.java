package rs.ac.uns.ftn.jaxb.test;
import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import rs.ac.uns.ftn.jaxb.example2.Fakultet;
import rs.ac.uns.ftn.jaxb.example2.ObjectFactory;
import rs.ac.uns.ftn.jaxb.example2.OdsekType;
import rs.ac.uns.ftn.jaxb.example2.OdsekType.Studenti;
import rs.ac.uns.ftn.jaxb.example2.PolozenIspit;
import rs.ac.uns.ftn.jaxb.example2.Student;

 
/** 
 * Primer 2.
 * 
 * Primer demonstrira "unmarshalling" tj. konverziju iz XML fajla
 * u objektni model, izmenu objektnog modela i "marshalling" načinjenih
 * izmena, tj. njegovu serijalizaciju nazad u XML fajl.
 * 
 */
public class Example2Marshalling {
	
	public void test() throws Exception {
		try {
			System.out.println("[INFO] Example 2: JAXB unmarshalling/marshalling.\n");
			
			// Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.jaxb.example2");
			
			// Unmarshaller je objekat zadužen za konverziju iz XML-a u objektni model
			Unmarshaller unmarshaller = context.createUnmarshaller();

			Fakultet fakultet = (Fakultet) unmarshaller.unmarshal(new File("./data/univerzitet2.xml"));
			
			// Izmena nad objektnim modelom dodavanjem novog odseka
			fakultet.getOdsek().add(createOdsek("23", "GRID"));
			
			// Marshaller je objekat zadužen za konverziju iz objektnog u XML model
			Marshaller marshaller = context.createMarshaller();
			
			// Podešavanje marshaller-a
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// Umesto System.out-a, može se koristiti FileOutputStream
			marshaller.marshal(fakultet, System.out);
			
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private OdsekType createOdsek(String id, String naziv) {

		// Eksplicitno instanciranje OdsekType klase
		OdsekType odsek = new OdsekType();
		odsek.setId(id);
		odsek.setNaziv(naziv);

		// Generišu se studenti
		odsek.setStudenti(createStudenti());
		
		return odsek;
	}
	
	private Studenti createStudenti() {

		// Instanciranje Studenti klase posredstvom ObjectFactory-a
		ObjectFactory factory = new ObjectFactory();
		Studenti studenti = factory.createOdsekTypeStudenti();
		
		// Generiše se novi student
		studenti.getStudent().add(createStudent(12345, "Tijana", "Novkovic"));
		
		return studenti;
	}
	
	private Student createStudent(int brojIndeksa, String ime, String prezime) {
		
		ObjectFactory factory = new ObjectFactory();
		Student student = factory.createStudent();
		student.setBrojIndeksa(brojIndeksa);
		student.setIme(ime);
		student.setPrezime(prezime);
		
		// Generiše položeni ispit
		student.getPolozenIspit().add(createPolozenIspit("Dizajn", "Stevan Simic", 10));
		
		return student;
	}
	
	private PolozenIspit createPolozenIspit(String predmet, String nastavnik, int ocena) {
		try {
			
			ObjectFactory factory = new ObjectFactory();
			PolozenIspit polozenIspit = factory.createPolozenIspit();
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
			
			polozenIspit.setDatum(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
			polozenIspit.setNastavnik(nastavnik);
			polozenIspit.setOcena((short) ocena);
			polozenIspit.setPredmet(predmet);
			
			return polozenIspit;
			
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}
	
    public static void main( String[] args ) throws Exception {
    	Example2Marshalling test = new Example2Marshalling();
    	test.test();
    }
}
