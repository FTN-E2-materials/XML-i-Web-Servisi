
package com.spring.soap.ws.style.message;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.spring.soap.ws.style.message package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SayHello_QNAME = new QName("http://soap.spring.com/ws/style/message", "sayHello");
    private final static QName _SayHelloResponse_QNAME = new QName("http://soap.spring.com/ws/style/message", "sayHelloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.spring.soap.ws.style.message
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayHelloType }
     * 
     */
    public SayHelloType createSayHelloType() {
        return new SayHelloType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHelloType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.spring.com/ws/style/message", name = "sayHello")
    public JAXBElement<SayHelloType> createSayHello(SayHelloType value) {
        return new JAXBElement<SayHelloType>(_SayHello_QNAME, SayHelloType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.spring.com/ws/style/message", name = "sayHelloResponse")
    public JAXBElement<String> createSayHelloResponse(String value) {
        return new JAXBElement<String>(_SayHelloResponse_QNAME, String.class, null, value);
    }

}
