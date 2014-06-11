package com.feilong.webservice.cxf;

/**
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.webservice.cxf.JaxWsDynamicClientUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 30, 2014 1:35:28 PM
 */
public class JaxWsDynamicClientUtilTest{

	private static final Logger	log						= LoggerFactory.getLogger(JaxWsDynamicClientUtilTest.class);

	private String				wsdlUrl					= "https://training.doappx.com/sprintAsia/api/advance.cfc?wsdl";

	private String				namespace				= "http://api.sprintasia";

	private String				operationName			= "transactionQuery";

	private String				merchantTransactionID	= "010003170001";

	private String				serviceVersion			= "2.0";

	private String				siteID					= "Blanja2";

	private String				transactionID			= "";

	private String				transactionType			= "AUTHORIZATION";

	@Test
	public void call() throws Exception{
		String wddxPacketXML = JaxWsDynamicClientUtil.call(
				wsdlUrl,
				operationName,
				merchantTransactionID,
				serviceVersion,
				siteID,
				transactionID,
				transactionType);
		log.info(wddxPacketXML);

	}

	@Test
	public void name1() throws MalformedURLException{
		URL url = new URL(wsdlUrl);
		operationName = "AdvanceService";
		QName qName = new QName(namespace, operationName);

		// 根据targetNamespace，serverName来查找服务
		Service service = Service.create(url, qName);

		if (log.isInfoEnabled()){
			Iterator<QName> ports = service.getPorts();

			while (ports.hasNext()){
				QName qName2 = (QName) ports.next();
				log.info(qName2.toString());
			}
		}
		BCAOperation bcaOperation = service.getPort(BCAOperation.class);

		if (log.isInfoEnabled()){

			String transactionQuery = bcaOperation.transactionQuery(
					merchantTransactionID,
					serviceVersion,
					siteID,
					merchantTransactionID,
					transactionType);
			log.info(transactionQuery);
		}
	}

	@SuppressWarnings("javadoc")
	@Test
	public void name122() throws SOAPException,IOException{
		URL url = new URL(wsdlUrl);

		operationName = "AdvanceService";
		QName serviceName = new QName(namespace, operationName);

		QName portName = new QName(namespace, "advance.cfc");
		Service service = Service.create(url, serviceName);

		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		Dispatch<SOAPMessage> dispatch = service.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);
		SOAPMessage res = dispatch.invoke(soapMessage);
		res.writeTo(System.out);
	}

	@Test
	public void name12(){
		// ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
		//
		// factory.setAddress("https://training.doappx.com/sprintAsia/api/advance.cfc?wsdl");
		// factory.getServiceFactory().setDataBinding(new AegisDatabinding());
		// HelloWorld client = factory.create(HelloWorld.class);
		// log.info("Invoke sayHi()....");
		// log.info(client.sayHi(System.getProperty("user.name")));
		// Document doc = client.getADocument();
		// Element e = (Element) doc.getFirstChild();
		// log.info(e.getTagName());
		// Text t = (Text) e.getFirstChild();
		// log.info(t);
	}
}
