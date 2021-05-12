package com.spring.soap.ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.spring.soap.ws.address.AddressBook;
import com.spring.soap.ws.address.data.Contact;
import com.spring.soap.ws.address.data.ContactArray;
import com.spring.soap.ws.address.data.ObjectFactory;

import net.java.dev.jaxb.array.StringArray;

import com.spring.soap.ws.address.data.Contact.Phones;

public class AddressBookClient {
	
	public void testIt() {
		try {
			//kreiranje servisa
			URL wsdl = new URL("http://localhost:8080/ws/addressBook?wsdl");
	    	QName serviceName = new QName("http://soap.spring.com/ws/address", "AddressBookService");
	    	QName portName = new QName("http://soap.spring.com/ws/address", "AddressBookPort");
	    	
	    	Service service = Service.create(wsdl, serviceName);
	    	
	        AddressBook address = service.getPort(portName, AddressBook.class);
			//kreiranje objekta
			ArrayList<String> phones1 = new ArrayList<String>();
			phones1.add("222-333");
			phones1.add("456-765");
			
	        Contact contact1 = new Contact();
	        contact1.setFirstName("Mitar");
	        contact1.setLastName("Peric");
	        contact1.setEmail("mitar@mail.com");
	        contact1.setPhones(new Contact.Phones(phones1));
	        contact1.setDefaultPhoneIndex(1);
	        //poziv metode ws-a
	        System.out.println("\nIndex of added item " + address.addContact(contact1));
	        
	        //kreiranje objekta
	        ArrayList<String> phones2 = new ArrayList<String>();
	        phones2.add("232-333");
	        phones2.add("436-735");
	        
	        Contact contact2 = new Contact();
	        contact2.setFirstName("Miroslav");
	        contact2.setLastName("Mirkovic");
	        contact2.setEmail("slavuj@mail.com");
	        contact2.setPhones(new Contact.Phones(phones2));
	        contact2.setDefaultPhoneIndex(1);
	        //poziv metode ws-a
	        System.out.println("\nIndex of added item " + address.addContact(contact2));
	        
	        //poziv metode ws-a
	        ArrayList<String> phones3 = new ArrayList<String>();
	        phones3.add("444-444");
	        phones3.add("111-222");
	        phones3.add("888-888");
	        
	        net.java.dev.jaxb.array.ObjectFactory factory = new net.java.dev.jaxb.array.ObjectFactory();
	        
	        StringArray testArray = factory.createStringArray();
	        testArray.getItem().addAll(phones3);
	        
	        System.out.println("\nIndex of added item " + address.addContactSeparated("Stevan", "Steovic", "stevo@mail.com", testArray, 3));

	        //ispis svih item-a
	        System.out.println("\nGeting all items");
	        ContactArray items = address.getContacts();
	        if(items != null)
			    for(int i = 0 ; i < items.getItem().size(); i++) 
			        System.out.println(items.getItem().get(i));
			else
			    System.out.println("No item in address book!");
	        
	        //preuzimanje item-a
			System.out.println("\nGeting item on position 2");
	        System.out.println(address.getContact(2));
	        
	        //uklanjanje item-a
	        System.out.println("\nRemoving item on position 1");
	        address.removeContact(1);
	        
	        //prikaz svih item-a
	        System.out.println("\nGeting all items");
	        items = address.getContacts();
	        if(items != null)
			    for(int i = 0 ; i < items.getItem().size(); i++) 
			        System.out.println(items.getItem().get(i));
			else
			    System.out.println("No item in address book!");
	

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
	
	
	public static void main(String[] args) {
		AddressBookClient test = new AddressBookClient();
		
		test.testIt();
	}

}
