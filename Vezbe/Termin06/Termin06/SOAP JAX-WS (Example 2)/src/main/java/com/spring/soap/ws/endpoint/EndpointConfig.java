package com.spring.soap.ws.endpoint;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.soap.ws.style.doc.bare.HelloDocBarePortImpl;
import com.spring.soap.ws.style.doc.wrap.HelloDocWrappedPortImpl;
import com.spring.soap.ws.style.message.HelloMessagePortImpl;
import com.spring.soap.ws.style.rpc.HelloRPCPortImpl;

@Configuration
public class EndpointConfig {

	@Autowired
	private Bus bus;
	
	@Bean
	public Endpoint helloDocBareEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new HelloDocBarePortImpl());
		endpoint.publish("/helloDocBare");
		return endpoint;
	}
	

	
	@Bean
	public Endpoint helloMessageEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new HelloMessagePortImpl());
		endpoint.publish("/helloMessage");
		return endpoint;
	}
	
	@Bean
	public Endpoint helloRPCEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new HelloRPCPortImpl());
		endpoint.publish("/helloRPC");
		return endpoint;
	} 
	
	@Bean
	public Endpoint HelloDocWrappedEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new HelloDocWrappedPortImpl());
		endpoint.publish("/helloDocWrapped");
		return endpoint;
	}
}
