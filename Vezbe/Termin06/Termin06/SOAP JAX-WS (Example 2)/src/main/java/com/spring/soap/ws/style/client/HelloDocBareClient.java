package com.spring.soap.ws.style.client;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.spring.soap.ws.style.doc.bare.HelloDocBare;
import com.spring.soap.ws.style.doc.bare.types.Person;

public class HelloDocBareClient {

    public void testIt() {
    	
		try {
			//Kreiranje web servisa
			URL wsdlLocation = new URL("http://localhost:8080/ws/helloDocBare?wsdl");
			QName serviceName = new QName("http://soap.spring.com/ws/style/doc/bare", "HelloDocBareService");
			QName portName = new QName("http://soap.spring.com/ws/style/doc/bare", "HelloDocBarePort");

			Service service = Service.create(wsdlLocation, serviceName);
			
			HelloDocBare hello = service.getPort(portName, HelloDocBare.class); 
			
			//Poziv web servisa
			String response = hello.sayHello(new Person("Pera", "Lukic"));
			System.out.println("Response 1 from WS: " + response);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		
		HelloDocBareClient client = new HelloDocBareClient();
		client.testIt();
    }

}