package com.spring.soap.ws.style.client;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.spring.soap.ws.style.doc.wrap.HelloDocWrapped;

public class HelloDocWrappedClient {

    public void testIt() {
    	
		try {
			//Kreiranje web servisa
			URL wsdlLocation = new URL("http://localhost:8080/ws/helloDocWrapped?wsdl");
			QName serviceName = new QName("http://soap.spring.com/ws/style/doc/wrap", "HelloDocWrappedService");
			QName portName = new QName("http://soap.spring.com/ws/style/doc/wrap", "HelloDocWrappedPort");

			Service service = Service.create(wsdlLocation, serviceName);
			
			HelloDocWrapped hello = service.getPort(portName, HelloDocWrapped.class); 
			
			//Poziv web servisa
			String response = hello.sayHello("Luka", "Peric");
			System.out.println("Response 1 from WS: " + response);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		
		HelloDocWrappedClient client = new HelloDocWrappedClient();
		client.testIt();
    }

}