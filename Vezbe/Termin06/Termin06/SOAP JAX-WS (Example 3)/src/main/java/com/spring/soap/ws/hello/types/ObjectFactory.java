
package com.spring.soap.ws.hello.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.spring.soap.ws.hello.types package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
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

    private final static QName _RequestMiss_QNAME = new QName("http://soap.spring.com/ws/hello/types", "RequestMiss");
    private final static QName _ResponseMiss_QNAME = new QName("http://soap.spring.com/ws/hello/types", "ResponseMiss");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.spring.soap.ws.hello.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RequestMissType }
     * 
     */
    public RequestMissType createRequestMissType() {
        return new RequestMissType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestMissType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RequestMissType }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.spring.com/ws/hello/types", name = "RequestMiss")
    public JAXBElement<RequestMissType> createRequestMiss(RequestMissType value) {
        return new JAXBElement<RequestMissType>(_RequestMiss_QNAME, RequestMissType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.spring.com/ws/hello/types", name = "ResponseMiss")
    public JAXBElement<String> createResponseMiss(String value) {
        return new JAXBElement<String>(_ResponseMiss_QNAME, String.class, null, value);
    }

}
