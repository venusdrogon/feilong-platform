
package com.feilong.test.webservice.cxf.client.nike;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.feilong.test.webservice.cxf.client.nike package. 
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

    private final static QName _TransferOrderSign_QNAME = new QName("http://www.nikestore.com.cn/webService", "transferOrderSign");
    private final static QName _TransferOrderSignResponse_QNAME = new QName("http://www.nikestore.com.cn/webService", "transferOrderSignResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.feilong.test.webservice.cxf.client.nike
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TransferOrderSignResponse }
     * 
     */
    public TransferOrderSignResponse createTransferOrderSignResponse() {
        return new TransferOrderSignResponse();
    }

    /**
     * Create an instance of {@link TransferOrderSign }
     * 
     */
    public TransferOrderSign createTransferOrderSign() {
        return new TransferOrderSign();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferOrderSign }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.nikestore.com.cn/webService", name = "transferOrderSign")
    public JAXBElement<TransferOrderSign> createTransferOrderSign(TransferOrderSign value) {
        return new JAXBElement<TransferOrderSign>(_TransferOrderSign_QNAME, TransferOrderSign.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferOrderSignResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.nikestore.com.cn/webService", name = "transferOrderSignResponse")
    public JAXBElement<TransferOrderSignResponse> createTransferOrderSignResponse(TransferOrderSignResponse value) {
        return new JAXBElement<TransferOrderSignResponse>(_TransferOrderSignResponse_QNAME, TransferOrderSignResponse.class, null, value);
    }

}
