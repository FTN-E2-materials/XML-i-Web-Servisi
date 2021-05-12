package com.spring.soap.ws.endpoint;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.soap.ws.address.AddressBookPortImpl;
import com.spring.soap.ws.hello.HelloPortImpl;



@Configuration
public class EndpointConfig {

	@Autowired
	private Bus bus;
	
	@Bean
	public Endpoint addressBookEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new AddressBookPortImpl());
		endpoint.publish("/addressBook");
		return endpoint;
	}


	
	@Bean
	public Endpoint helloMessageEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new HelloPortImpl());
		endpoint.publish("/helloMessage");
		return endpoint;
	}
	
	
}
