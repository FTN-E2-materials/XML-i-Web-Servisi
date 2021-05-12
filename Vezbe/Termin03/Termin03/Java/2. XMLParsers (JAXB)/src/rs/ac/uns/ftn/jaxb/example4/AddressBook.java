package rs.ac.uns.ftn.jaxb.example4;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="address_book")
@XmlType(name="", propOrder={"name", "contacts"})
public class AddressBook {
	
	@XmlElement(name="name", required=true)
	private String name;
	
	@XmlElementWrapper(name="contacts", required=false)
	@XmlElement(name="contact", required=false)
	private List<Person> contacts = new ArrayList<Person>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Person> getContacts() {
		return contacts;
	}

	public void setContacts(List<Person> contacts) {
		this.contacts = contacts;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("- Address book: ");
		buffer.append(name);
		buffer.append("\n");
		
		if(contacts.size() > 0)
			for(Person contact : contacts) {
				buffer.append(contact);
			}

		return buffer.toString();
	}

}
