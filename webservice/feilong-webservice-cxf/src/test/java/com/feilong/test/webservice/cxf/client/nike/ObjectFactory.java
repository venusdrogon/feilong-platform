/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    /** The Constant _TransferOrderSign_QNAME. */
    private final static QName _TransferOrderSign_QNAME = new QName("http://www.nikestore.com.cn/webService", "transferOrderSign");
    
    /** The Constant _TransferOrderSignResponse_QNAME. */
    private final static QName _TransferOrderSignResponse_QNAME = new QName("http://www.nikestore.com.cn/webService", "transferOrderSignResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.feilong.test.webservice.cxf.client.nike
     * 
     */
    public ObjectFactory() {
    }

    /**
	 * Create an instance of {@link TransferOrderSignResponse }.
	 * 
	 * @return the transfer order sign response
	 */
    public TransferOrderSignResponse createTransferOrderSignResponse() {
        return new TransferOrderSignResponse();
    }

    /**
	 * Create an instance of {@link TransferOrderSign }.
	 * 
	 * @return the transfer order sign
	 */
    public TransferOrderSign createTransferOrderSign() {
        return new TransferOrderSign();
    }

    /**
	 * Create an instance of {@link JAXBElement }{@code <}{@link TransferOrderSign }{@code >} .
	 * 
	 * @param value
	 *            the value
	 * @return the JAXB element< transfer order sign>
	 */
    @XmlElementDecl(namespace = "http://www.nikestore.com.cn/webService", name = "transferOrderSign")
    public JAXBElement<TransferOrderSign> createTransferOrderSign(TransferOrderSign value) {
        return new JAXBElement<TransferOrderSign>(_TransferOrderSign_QNAME, TransferOrderSign.class, null, value);
    }

    /**
	 * Create an instance of {@link JAXBElement }{@code <}{@link TransferOrderSignResponse }{@code >} .
	 * 
	 * @param value
	 *            the value
	 * @return the JAXB element< transfer order sign response>
	 */
    @XmlElementDecl(namespace = "http://www.nikestore.com.cn/webService", name = "transferOrderSignResponse")
    public JAXBElement<TransferOrderSignResponse> createTransferOrderSignResponse(TransferOrderSignResponse value) {
        return new JAXBElement<TransferOrderSignResponse>(_TransferOrderSignResponse_QNAME, TransferOrderSignResponse.class, null, value);
    }

}
