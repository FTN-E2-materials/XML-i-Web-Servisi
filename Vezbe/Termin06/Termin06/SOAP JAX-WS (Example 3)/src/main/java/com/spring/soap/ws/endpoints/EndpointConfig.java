package com.spring.soap.ws.endpoints;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.spring.soap.ws.address.AddressBookPortImpl;
import com.spring.soap.ws.hello.HelloDocumentImpl;

@Configuration
public class EndpointConfig {

	@Autowired
	private Bus bus;
	
	
   
	
	@Bean
	public Endpoint helloEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new HelloDocumentImpl());
		endpoint.publish("/hello");
		return endpoint;
	}
	
	@Bean
	public Endpoint AddressBookEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new AddressBookPortImpl());
		endpoint.publish("/address");
		return endpoint;
	}
	
}
