package com.spring.soap.ws.style.doc.bare;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.spring.soap.ws.style.doc.bare.types.ObjectFactory;
import com.spring.soap.ws.style.doc.bare.types.Person;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2020-12-26T04:55:32.415+01:00
 * Generated source version: 3.2.1
 * 
 */
@WebService(targetNamespace = "http://soap.spring.com/ws/style/doc/bare", name = "HelloDocBare")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface HelloDocBare {

    @WebMethod
    @WebResult(name = "sayHelloResponse", targetNamespace = "http://soap.spring.com/ws/style/doc/bare", partName = "sayHelloResponse")
    public java.lang.String sayHello(
        @WebParam(partName = "person", name = "person", targetNamespace = "http://soap.spring.com/ws/style/doc/bare")
        Person person
    );
}