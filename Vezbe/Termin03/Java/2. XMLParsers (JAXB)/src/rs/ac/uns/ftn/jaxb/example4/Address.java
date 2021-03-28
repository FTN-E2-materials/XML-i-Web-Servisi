package rs.ac.uns.ftn.jaxb.example4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="addressType", propOrder={"country", "city", "street", "number"})
public class Address {
	
	@XmlElement(name="country", required=false)
	private String country;
	
	@XmlElement(name="city", required=true)
	private String city;
	
	@XmlElement(name="street", required=true)
	private String street;
	
	@XmlElement(name="number", required=true)
	private int number;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String toString() {
		if(country != null)
			return country + " " + city + " " + street + " " + number;
		else
			return city + " " + street + " " + number;
	}
	
	
	

}
