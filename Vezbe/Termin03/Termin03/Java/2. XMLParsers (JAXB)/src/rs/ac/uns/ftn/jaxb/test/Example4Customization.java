package rs.ac.uns.ftn.jaxb.test;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import rs.ac.uns.ftn.jaxb.example4.Address;
import rs.ac.uns.ftn.jaxb.example4.AddressBook;
import rs.ac.uns.ftn.jaxb.example4.Person;
import rs.ac.uns.ftn.jaxb.util.MyDatatypeConverter;
import rs.ac.uns.ftn.jaxb.util.NSPrefixMapper;

/** 
 * Primer 4.
 * 
 * Primer demonstrira konverziju iz XML u objektni (unmarshalling),
 * a zatim iz objektnog u XML model (marshalling). Prilikom konverzije 
 * iz objektnog u XML model postavljaju se custom namespace prefiksi
 * koji su definisani "NSPrefixMapper" klasom.
 *  
 */
public class Example4Customization {

	public void test() {
		try {
			System.out.println("[INFO] Example 3: JAXB XML Schema validation.\n");
			
			// Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.jaxb.example4");
			
			// Unmarshaller - zadužen za konverziju iz XML-a u objektni model
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			// Učitavanje XML-a u objektni model
			AddressBook addressBook = (AddressBook) unmarshaller.unmarshal(new File("./data/address_book.xml"));

			// Ispis sadržaja objekta
			System.out.println("[INFO] Unmarshalled content:");
			System.out.println(addressBook);
			
			// Marshaller - zadužen za konverziju iz objekta u XML
			Marshaller marshaller = context.createMarshaller();
			
			// Konfiguracija marshaller-a custom prefiks maperom
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// Kreira se novi imenik
			addressBook = createAddressBook();

			// Serijalizacija objektnog modela u XML
			System.out.println("[INFO] Marshalling customized address book:");
			marshaller.marshal(addressBook, System.out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private AddressBook createAddressBook() {
		
		AddressBook addressBook = new AddressBook();
		
		addressBook.setName("Sample address book");
		addressBook.getContacts().add(createContact1());
		addressBook.getContacts().add(createContact2());
		
		return addressBook;
	}
	
	private Person createContact1() {
		
		Person person = new Person();
		
		person.setFirstName("Stevo");
		person.setLastName("Simić");
		person.getPhones().add("021/444-555");
		person.getPhones().add("021/444-556");
		person.setBirthDate(MyDatatypeConverter.parseDate("1991-12-21"));
		
		Address homeAddress = new Address();
		homeAddress.setCountry("Serbia");
		homeAddress.setCity("Novi Sad");
		homeAddress.setStreet("Futoška");
		homeAddress.setNumber(33);
		person.setHomeAddress(homeAddress);
		
		Address officeAddress = new Address();
		officeAddress.setCity("Novi Sad");
		officeAddress.setStreet("Železnička");
		officeAddress.setNumber(4);
		person.setOfficeAddress(officeAddress);
		
		return person;
	}
	
	private Person createContact2() {

		Person person = new Person();
		
		person.setFirstName("Dragana");
		person.setLastName("Draganić");
		person.getPhones().add("011/777-555");
		person.getPhones().add("011/777-556");
		person.setBirthDate(MyDatatypeConverter.parseDate("1993-08-29"));
		
		Address homeAddress = new Address();
		homeAddress.setCountry("Serbia");
		homeAddress.setCity("Belgrade");
		homeAddress.setStreet("Šekspirova");
		homeAddress.setNumber(44);
		person.setHomeAddress(homeAddress);
		
		return person;
	}
	
    public static void main( String[] args ) {
    	Example4Customization test = new Example4Customization();
    	test.test();
    }
}
