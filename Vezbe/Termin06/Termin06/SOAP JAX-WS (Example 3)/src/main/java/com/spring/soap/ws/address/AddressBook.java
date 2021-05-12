package com.spring.soap.ws.address;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2020-12-26T02:11:43.957+01:00
 * Generated source version: 3.2.1
 * 
 */
@WebService(targetNamespace = "http://soap.spring.com/ws/address", name = "AddressBook")
@XmlSeeAlso({com.spring.soap.ws.address.types.ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AddressBook {

    @WebMethod
    @WebResult(name = "result", targetNamespace = "http://soap.spring.com/ws/address", partName = "result")
    public com.spring.soap.ws.address.types.ContactType getContact(
        @WebParam(partName = "index", name = "index")
        int index
    );

    @WebMethod
    @WebResult(name = "result", targetNamespace = "http://soap.spring.com/ws/address", partName = "result")
    public com.spring.soap.ws.address.types.ContactsType getAllContacts();

    @WebMethod
    @WebResult(name = "result", targetNamespace = "http://soap.spring.com/ws/address", partName = "result")
    public com.spring.soap.ws.address.types.ContactType getContactByName(
        @WebParam(partName = "firstName", name = "firstName")
        java.lang.String firstName,
        @WebParam(partName = "lastName", name = "lastName")
        java.lang.String lastName
    );

    @WebMethod
    public void addContact(
        @WebParam(partName = "contact", name = "contact")
        com.spring.soap.ws.address.types.ContactType contact
    );
}
