package com.spring.soap.ws.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.spring.soap.ws.address.AddressBook;
import com.spring.soap.ws.address.types.ContactType;
import com.spring.soap.ws.address.types.ContactsType;

public class AddressBookClient {
	
	public void testIt() {
		try {
			URL wsdl = new URL("http://localhost:8080/ws/address?wsdl");
	    	QName serviceName = new QName("http://soap.spring.com/ws/address", "AddressBookService");
	    	QName portName = new QName("http://soap.spring.com/address", "AddressBookPort");
	    	
	    	Service service = Service.create(wsdl, serviceName);
	    	
	        AddressBook address = service.getPort(portName, AddressBook.class);
			
			//kreiranje objekta
	        ContactType contact1 = new ContactType();
	        contact1.setFirstName("Mitar");
	        contact1.setLastName("Peric");
	        contact1.setEmail("mitar@pesma.com");
	        contact1.getPhones().add("222-333");
	        contact1.getPhones().add("456-765");
	        contact1.setDefaultPhoneIndex(1);
	        
	        //poziv metode ws-a
	        address.addContact(contact1);
	        
	        //kreiranje objekta
	        ContactType contact2 = new ContactType();
	        contact2.setFirstName("Miroslav");
	        contact2.setLastName("Mirkovic");
	        contact2.setEmail("slavuj@pesma.com");
	        contact1.getPhones().add("232-333");
	        contact1.getPhones().add("436-735");
	        contact2.setDefaultPhoneIndex(1);
	        //poziv metode ws-a
	        address.addContact(contact2);
	        
	        System.out.println("Ucitavanje kontakta po indeksu...");
	        //preuzimanje kontakta
	        ContactType contact = address.getContact(1);
	        if(contact != null) 
	        	printContact(contact);
	       
	        System.out.println("Ucitavanje kontakta po imenu i prezimenu...");
	        //preuzimanje kontakta
	        contact = address.getContactByName("Mitar", "Peric");
	        if(contact != null)
	        	printContact(contact);
	        
	        System.out.println("Ucitavanje svih kontakata...");
	        //get all contacts
	        ContactsType contacts = address.getAllContacts();
	        if(contacts.getValue() != null && contacts.getValue().size() > 0)
	        	for(ContactType acontact : contacts.getValue())
	        		printContact(acontact);
	

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
		
	private void printContact(ContactType contact) {
        System.out.print(contact.getFirstName());
        System.out.print(" ");
        System.out.print(contact.getLastName());
        System.out.print(" ");
        System.out.print(contact.getEmail());
        System.out.print(" ");
        if(contact.getPhones() != null) {
            for(String phone: contact.getPhones()) {
            	System.out.print(phone);
            	System.out.print(" ");
            }
            System.out.print("DEFAULT: " + contact.getDefaultPhoneIndex());
        }
        System.out.println();
        
    }
	
	
	public static void main(String[] args) {
		AddressBookClient test = new AddressBookClient();
		
		test.testIt();
	}

}
