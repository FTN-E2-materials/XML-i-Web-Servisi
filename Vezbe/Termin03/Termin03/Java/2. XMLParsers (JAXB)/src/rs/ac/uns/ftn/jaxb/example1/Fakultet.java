//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.01 at 03:08:57 PM CET 
//


package rs.ac.uns.ftn.jaxb.example1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="odsek" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="studenti">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="student" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="polozenIspit" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="predmet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                               &lt;/sequence>
 *                                               &lt;attribute name="ocena" use="required" type="{http://www.w3.org/2001/XMLSchema}short" />
 *                                               &lt;attribute name="datum" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                                               &lt;attribute name="nastavnik" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="brojIndeksa" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                                     &lt;attribute name="ime" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="prezime" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="naziv" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="naziv" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "odsek"
})
@XmlRootElement(name = "fakultet")
public class Fakultet {

    @XmlElement(required = true)
    protected List<Fakultet.Odsek> odsek;
    @XmlAttribute(name = "naziv", required = true)
    protected String naziv;

    /**
     * Gets the value of the odsek property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the odsek property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOdsek().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Fakultet.Odsek }
     * 
     * 
     */
    public List<Fakultet.Odsek> getOdsek() {
        if (odsek == null) {
            odsek = new ArrayList<Fakultet.Odsek>();
        }
        return this.odsek;
    }

    /**
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaziv(String value) {
        this.naziv = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="studenti">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="student" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="polozenIspit" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="predmet" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                     &lt;/sequence>
     *                                     &lt;attribute name="ocena" use="required" type="{http://www.w3.org/2001/XMLSchema}short" />
     *                                     &lt;attribute name="datum" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *                                     &lt;attribute name="nastavnik" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                           &lt;attribute name="brojIndeksa" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                           &lt;attribute name="ime" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="prezime" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="naziv" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "studenti"
    })
    public static class Odsek {

        @XmlElement(required = true)
        protected Fakultet.Odsek.Studenti studenti;
        @XmlAttribute(name = "naziv", required = true)
        protected String naziv;
        @XmlAttribute(name = "id", required = true)
        protected String id;

        /**
         * Gets the value of the studenti property.
         * 
         * @return
         *     possible object is
         *     {@link Fakultet.Odsek.Studenti }
         *     
         */
        public Fakultet.Odsek.Studenti getStudenti() {
            return studenti;
        }

        /**
         * Sets the value of the studenti property.
         * 
         * @param value
         *     allowed object is
         *     {@link Fakultet.Odsek.Studenti }
         *     
         */
        public void setStudenti(Fakultet.Odsek.Studenti value) {
            this.studenti = value;
        }

        /**
         * Gets the value of the naziv property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNaziv() {
            return naziv;
        }

        /**
         * Sets the value of the naziv property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNaziv(String value) {
            this.naziv = value;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="student" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="polozenIspit" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="predmet" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                           &lt;/sequence>
         *                           &lt;attribute name="ocena" use="required" type="{http://www.w3.org/2001/XMLSchema}short" />
         *                           &lt;attribute name="datum" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
         *                           &lt;attribute name="nastavnik" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *                 &lt;attribute name="brojIndeksa" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="ime" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="prezime" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "student"
        })
        public static class Studenti {

            @XmlElement(required = true)
            protected List<Fakultet.Odsek.Studenti.Student> student;

            /**
             * Gets the value of the student property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the student property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getStudent().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Fakultet.Odsek.Studenti.Student }
             * 
             * 
             */
            public List<Fakultet.Odsek.Studenti.Student> getStudent() {
                if (student == null) {
                    student = new ArrayList<Fakultet.Odsek.Studenti.Student>();
                }
                return this.student;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="polozenIspit" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="predmet" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                 &lt;/sequence>
             *                 &lt;attribute name="ocena" use="required" type="{http://www.w3.org/2001/XMLSchema}short" />
             *                 &lt;attribute name="datum" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
             *                 &lt;attribute name="nastavnik" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *       &lt;attribute name="brojIndeksa" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="ime" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="prezime" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "polozenIspit"
            })
            public static class Student {

                protected List<Fakultet.Odsek.Studenti.Student.PolozenIspit> polozenIspit;
                @XmlAttribute(name = "brojIndeksa", required = true)
                protected int brojIndeksa;
                @XmlAttribute(name = "ime", required = true)
                protected String ime;
                @XmlAttribute(name = "prezime", required = true)
                protected String prezime;

                /**
                 * Gets the value of the polozenIspit property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the polozenIspit property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getPolozenIspit().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Fakultet.Odsek.Studenti.Student.PolozenIspit }
                 * 
                 * 
                 */
                public List<Fakultet.Odsek.Studenti.Student.PolozenIspit> getPolozenIspit() {
                    if (polozenIspit == null) {
                        polozenIspit = new ArrayList<Fakultet.Odsek.Studenti.Student.PolozenIspit>();
                    }
                    return this.polozenIspit;
                }

                /**
                 * Gets the value of the brojIndeksa property.
                 * 
                 */
                public int getBrojIndeksa() {
                    return brojIndeksa;
                }

                /**
                 * Sets the value of the brojIndeksa property.
                 * 
                 */
                public void setBrojIndeksa(int value) {
                    this.brojIndeksa = value;
                }

                /**
                 * Gets the value of the ime property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getIme() {
                    return ime;
                }

                /**
                 * Sets the value of the ime property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setIme(String value) {
                    this.ime = value;
                }

                /**
                 * Gets the value of the prezime property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPrezime() {
                    return prezime;
                }

                /**
                 * Sets the value of the prezime property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPrezime(String value) {
                    this.prezime = value;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="predmet" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *       &lt;/sequence>
                 *       &lt;attribute name="ocena" use="required" type="{http://www.w3.org/2001/XMLSchema}short" />
                 *       &lt;attribute name="datum" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
                 *       &lt;attribute name="nastavnik" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "predmet"
                })
                public static class PolozenIspit {

                    @XmlElement(required = true)
                    protected String predmet;
                    @XmlAttribute(name = "ocena", required = true)
                    protected short ocena;
                    @XmlAttribute(name = "datum", required = true)
                    @XmlSchemaType(name = "date")
                    protected XMLGregorianCalendar datum;
                    @XmlAttribute(name = "nastavnik", required = true)
                    protected String nastavnik;

                    /**
                     * Gets the value of the predmet property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPredmet() {
                        return predmet;
                    }

                    /**
                     * Sets the value of the predmet property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPredmet(String value) {
                        this.predmet = value;
                    }

                    /**
                     * Gets the value of the ocena property.
                     * 
                     */
                    public short getOcena() {
                        return ocena;
                    }

                    /**
                     * Sets the value of the ocena property.
                     * 
                     */
                    public void setOcena(short value) {
                        this.ocena = value;
                    }

                    /**
                     * Gets the value of the datum property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     */
                    public XMLGregorianCalendar getDatum() {
                        return datum;
                    }

                    /**
                     * Sets the value of the datum property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     */
                    public void setDatum(XMLGregorianCalendar value) {
                        this.datum = value;
                    }

                    /**
                     * Gets the value of the nastavnik property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getNastavnik() {
                        return nastavnik;
                    }

                    /**
                     * Sets the value of the nastavnik property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setNastavnik(String value) {
                        this.nastavnik = value;
                    }

                }

            }

        }

    }

}
