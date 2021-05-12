package com.spring.soap.ws.client;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.spring.soap.ws.hello.Hello;

public class HelloClient {

    public void testIt() {
    	
		try {
			//kreiranje web servisa
			URL wsdlLocation = new URL("http://localhost:8080/ws/helloMessage?wsdl");
			QName serviceName = new QName("http://soap.spring.com/ws/hello", "HelloService");
			QName portName = new QName("http://soap.spring.com/ws/hello", "HelloPort");

			Service service = Service.create(wsdlLocation, serviceName);
			
			Hello hello = service.getPort(portName, Hello.class); 
			
			//poziv web servisa
			String response = hello.sayHi("Mika");
			System.out.println("Response from WS: " + response);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		
		HelloClient client = new HelloClient();
		client.testIt();
    }

}