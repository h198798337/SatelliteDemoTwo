//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.21 at 09:45:36 ���� CST 
//


package com.ykse.jaxb.satellite.filminfo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ykse.jaxb.satellite.filminfo package. 
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

    private final static QName _Issuer_QNAME = new QName("", "issuer");
    private final static QName _IssueDate_QNAME = new QName("", "issueDate");
    private final static QName _Uuid_QNAME = new QName("", "uuid");
    private final static QName _Creator_QNAME = new QName("", "creator");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ykse.jaxb.satellite.filminfo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DcpList }
     * 
     */
    public DcpList createDcpList() {
        return new DcpList();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link Dcp }
     * 
     */
    public Dcp createDcp() {
        return new Dcp();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "issuer")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createIssuer(String value) {
        return new JAXBElement<String>(_Issuer_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "issueDate")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createIssueDate(String value) {
        return new JAXBElement<String>(_IssueDate_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "uuid")
    public JAXBElement<String> createUuid(String value) {
        return new JAXBElement<String>(_Uuid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "creator")
    public JAXBElement<String> createCreator(String value) {
        return new JAXBElement<String>(_Creator_QNAME, String.class, null, value);
    }

}