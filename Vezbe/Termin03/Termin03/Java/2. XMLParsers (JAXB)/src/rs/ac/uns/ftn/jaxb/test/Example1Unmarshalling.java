package rs.ac.uns.ftn.jaxb.test;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import rs.ac.uns.ftn.jaxb.example1.Fakultet;

/** 
 * Primer 1.
 * 
 * Primer demonstrira "unmarshalling" tj. konverziju 
 * iz XML fajla u objektni model. 
 *
 */
public class Example1Unmarshalling {
	
	public void test() {
		try {
			
			System.out.println("[INFO] Example 1: JAXB unmarshalling.\n");
			
			// Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.jaxb.example1");
			
			// Unmarshaller je objekat zadužen za konverziju iz XML-a u objektni model
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			// Unmarshalling generiše objektni model na osnovu XML fajla 
			Fakultet fakultet = (Fakultet) unmarshaller.unmarshal(new File("./data/univerzitet1.xml"));

			// Prikazuje unmarshallovan objekat
			printFakultet(fakultet);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private void printFakultet(Fakultet fakultet) {
		
		// Prikaz naziva fakulteta (getter metoda)
		System.out.println("- Naziv fakulteta: " + fakultet.getNaziv() + "\n");
		
		// Prikaz svih odseka
		for(Fakultet.Odsek odsek : fakultet.getOdsek())
			printOdsek(odsek);
		
	}
	
	private void printOdsek(Fakultet.Odsek odsek) {
		
		// Prikaz naziva i id-a odseka 
		System.out.println("\t- Naziv odseka: " + odsek.getNaziv() + ", id: " + odsek.getId() + "\n");
		
		// Prikaz svih studenata
		for(Fakultet.Odsek.Studenti.Student student : odsek.getStudenti().getStudent())
			printStudent(student);
		
	}
	
	private void printStudent(Fakultet.Odsek.Studenti.Student student) {
		
		// Prikaz imena, prezimena i broja indeksa
		System.out.print("\t\t- Student: " + student.getIme() + " ");
		System.out.print(student.getPrezime() + ", ");
		System.out.println(student.getBrojIndeksa());
		
		// Prikaz svih položenih ispita
		for(Fakultet.Odsek.Studenti.Student.PolozenIspit polozenIspit : student.getPolozenIspit())
			printPolozenIspit(polozenIspit);
		
	}
	
	private void printPolozenIspit(Fakultet.Odsek.Studenti.Student.PolozenIspit polozenIspit) {
		
		// Prikaz detalja o položenom ispitu
		System.out.print("\t\t\t- Položen ispit: " + polozenIspit.getPredmet() + ", ");
		System.out.print(polozenIspit.getNastavnik() + ", ");
		System.out.print(polozenIspit.getOcena() + ", ");
		System.out.println(polozenIspit.getDatum());
		System.out.println();
		
	}
	
    public static void main( String[] args ) {
    	Example1Unmarshalling test = new Example1Unmarshalling();
    	test.test();
    }
}
