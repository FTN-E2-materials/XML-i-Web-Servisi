package rs.ac.uns.ftn.jaxb.example4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import rs.ac.uns.ftn.jaxb.util.MyDatatypeConverter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="person")
@XmlType(name="personType", propOrder={"birthDate", "phones", "homeAddress", "officeAddress"})

public class Person {
	
	@XmlAttribute(name="first_name", required=true)
	private String firstName;
	
	@XmlAttribute(name="last_name", required=true)
	private String lastName;
	
	@XmlElement(name="birth_date", required=false)
	@XmlSchemaType(name="date")
	private Date birthDate;
	
	@XmlElement(name="phone", required=true)
	private List<String> phones = new ArrayList<String>(5);
	
	@XmlElement(name="home_address", required=true)
	private Address homeAddress;
	
	@XmlElement(name="office_address", required=false)
	private Address officeAddress;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public List<String> getPhones() {
		return phones;
	}
	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("\t- Contact: ");
		buffer.append(firstName);
		buffer.append(" ");
		buffer.append(lastName);
		buffer.append(", ");
		
		if(birthDate != null) {
			buffer.append(MyDatatypeConverter.printDate(birthDate));
		}
		
		buffer.append("\n");
		
		buffer.append("\t\t- Phones: ");
		for(String phone : phones) {
			buffer.append(phone);
			buffer.append(", ");
		}
		
		buffer.append("\n");
		buffer.append("\t\t- Home address: ");
		buffer.append(homeAddress);
		
		if(officeAddress != null) {
			buffer.append("\n");
			buffer.append("\t\t- Office address: ");
			buffer.append(officeAddress);
		}
		
		buffer.append("\n");
		return buffer.toString();
			
	}
	
	

}
