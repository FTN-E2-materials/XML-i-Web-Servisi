//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.01 at 03:08:57 PM CET 
//


package rs.ac.uns.ftn.jaxb.example1;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.jaxb.example1 package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.jaxb.example1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Fakultet }
     * 
     */
    public Fakultet createFakultet() {
        return new Fakultet();
    }

    /**
     * Create an instance of {@link Fakultet.Odsek }
     * 
     */
    public Fakultet.Odsek createFakultetOdsek() {
        return new Fakultet.Odsek();
    }

    /**
     * Create an instance of {@link Fakultet.Odsek.Studenti }
     * 
     */
    public Fakultet.Odsek.Studenti createFakultetOdsekStudenti() {
        return new Fakultet.Odsek.Studenti();
    }

    /**
     * Create an instance of {@link Fakultet.Odsek.Studenti.Student }
     * 
     */
    public Fakultet.Odsek.Studenti.Student createFakultetOdsekStudentiStudent() {
        return new Fakultet.Odsek.Studenti.Student();
    }

    /**
     * Create an instance of {@link Fakultet.Odsek.Studenti.Student.PolozenIspit }
     * 
     */
    public Fakultet.Odsek.Studenti.Student.PolozenIspit createFakultetOdsekStudentiStudentPolozenIspit() {
        return new Fakultet.Odsek.Studenti.Student.PolozenIspit();
    }

}
