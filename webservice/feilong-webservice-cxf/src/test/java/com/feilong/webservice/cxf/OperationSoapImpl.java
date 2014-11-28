package com.feilong.webservice.cxf;
///*
// * Copyright (C) 2008 feilong (venusdrogon@163.com)
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *         http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.nio.charset.Charset;
//
//import javax.xml.namespace.QName;
//import javax.xml.soap.MessageFactory;
//import javax.xml.soap.MimeHeaders;
//import javax.xml.soap.SOAPBody;
//import javax.xml.soap.SOAPConnection;
//import javax.xml.soap.SOAPConnectionFactory;
//import javax.xml.soap.SOAPElement;
//import javax.xml.soap.SOAPEnvelope;
//import javax.xml.soap.SOAPException;
//import javax.xml.soap.SOAPHeader;
//import javax.xml.soap.SOAPMessage;
//import javax.xml.soap.SOAPPart;
//
///**
// * <SOAP操作接口实现类> <用于实现soap的拼装和消息发送> <该实现类封装了创建AP和网管之间通信的公共方法>.
// *
// * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
// * @version 1.0 2014-5-5 18:17:02
// */
//public class OperationSoapImpl implements OperationSoap{
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public SOAPMessage createMessage() throws SOAPException{
//		MessageFactory messageFactory = MessageFactory.newInstance();
//		SOAPMessage message = messageFactory.createMessage();
//		return message;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public SOAPMessage createMessage(String cwmpID) throws SOAPException{
//		MessageFactory messageFactory = MessageFactory.newInstance();
//		SOAPMessage message = messageFactory.createMessage();
//		SOAPEnvelope envelope = createEnvelope(message);
//		createHeader(envelope, cwmpID);
//		createBody(envelope);
//		return message;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public SOAPEnvelope createEnvelope(SOAPMessage message) throws SOAPException{
//		SOAPPart soapPart = message.getSOAPPart();
//		SOAPEnvelope envelope = soapPart.getEnvelope();
//		envelope.addNamespaceDeclaration("SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/");
//		envelope.addNamespaceDeclaration("SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/");
//		envelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
//		envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
//		envelope.addNamespaceDeclaration("cwmp", "urn:dslforum-org:cwmp-1-1");
//		return envelope;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public SOAPHeader createHeader(SOAPEnvelope envelope,String cwmpID) throws SOAPException{
//		SOAPHeader header = envelope.getHeader();
//		QName qname = new QName("urn:dslforum-org:cwmp-1-1", "ID", "cwmp");
//		SOAPElement element = header.addHeaderElement(qname);
//		element.addTextNode(cwmpID);
//		return header;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public SOAPBody createBody(SOAPEnvelope envelope) throws SOAPException{
//		SOAPBody body = envelope.getBody();
//		body.setEncodingStyle("http://schemas.xmlsoap.org/soap/encoding/");
//		return body;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	public SOAPMessage sendMessage(SOAPMessage message,String address) throws SOAPException{
//		// 保存所需发送的消息
//		message.saveChanges();
//
//		// 创建链接
//		SOAPConnectionFactory connectionFactory = SOAPConnectionFactory.newInstance();
//		SOAPConnection connection = connectionFactory.createConnection();
//
//		// 发送消息
//		SOAPMessage responseMessage = connection.call(message, address);
//		return responseMessage;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public SOAPMessage sendMessage(String message,String address) throws SOAPException{
//		MessageFactory msgFactory;
//		try{
//			msgFactory = MessageFactory.newInstance();
//			SOAPMessage reqMsg = msgFactory.createMessage(
//					new MimeHeaders(),
//					new ByteArrayInputStream(message.getBytes(Charset.forName("UTF-8"))));
//			return sendMessage(reqMsg, address);
//		}catch (Exception e){
//			log.error(e.getClass().getName(), e);
//		}
//		return null;
//	}
//
//	/**
//	 * <用于测试输出拼装的XML内容，该方法没有什么意义> <消息输出到D盘soapMessage.xml文件中>
//	 *
//	 * @param message the message
//	 * @param file the file
//	 * @see [类、类#方法、类#成员]
//	 */
//	public void writeTestXml(SOAPMessage message,String file){
//		File respFile = new File(file);
//		OutputStream respSoap;
//		try{
//			respSoap = new FileOutputStream(respFile);
//			if (message != null){
//
//				message.writeTo(respSoap);
//			}else{
//				log.info("the message is null.");
//			}
//		}catch (Exception e){
//			log.info("Error");
//			// TODO Auto-generated catch block
//			log.error(e.getClass().getName(), e);
//		}
//	}
//}
