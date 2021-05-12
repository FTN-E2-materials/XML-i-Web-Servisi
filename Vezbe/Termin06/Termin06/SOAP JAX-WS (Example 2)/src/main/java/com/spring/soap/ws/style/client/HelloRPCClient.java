package com.spring.soap.ws.style.client;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.spring.soap.ws.style.rpc.HelloRPC;
import com.spring.soap.ws.style.rpc.Person;

public class HelloRPCClient {
    public void testIt() {
    	
		try {
			//Kreiranje web servisa
			URL wsdlLocation = new URL("http://localhost:8080/ws/helloRPC?wsdl");
			QName serviceName = new QName("http://soap.spring.com/ws/style/rpc", "HelloRPCService");
			QName portName = new QName("http://soap.spring.com/ws/style/rpc", "HelloRPCPort");

			Service service = Service.create(wsdlLocation, serviceName);
			
			HelloRPC hello = service.getPort(portName, HelloRPC.class); 
			
			//Poziv web servisa
			String response = hello.sayHelloSeparated("Pera", "Lukic");
			System.out.println("Response 1 from WS: " + response);
			
			response = hello.sayHello(new Person("Luka", "Peric"));
			System.out.println("Response 2 from WS: " + response);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		
		HelloRPCClient client = new HelloRPCClient();
		client.testIt();
    }

}