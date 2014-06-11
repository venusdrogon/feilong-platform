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
//
//import javax.xml.namespace.QName;
//import javax.xml.soap.SOAPBody;
//import javax.xml.soap.SOAPElement;
//import javax.xml.soap.SOAPEnvelope;
//import javax.xml.soap.SOAPMessage;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// *
// * @author <a href="mailto:venusdrogon@163.com">金鑫</a> 
// * @version 1.0 2014年5月5日 下午6:15:16  
// */
//public class Snippet{
//
//	private static final Logger	log	= LoggerFactory.getLogger(Snippet.class);
//	
//
//	public void createConn(String internetID)
//	    {
//	        try
//	        {
//	
//	          
//	            SOAPMessage message = operaSoap.createMessage();
//	            SOAPEnvelope envelope = operaSoap.createEnvelope(message);
//	            SOAPBody body = operaSoap.createBody(envelope);
//	            
//	            QName qname = new QName("urn:dslforum-org:cwmp-1-1", "Inform", "cwmp");
//	            SOAPElement element = body.addBodyElement(qname);
//	            createDeviceId(element, internetID);
//	            createEvent(element, internetID);
//	            createOther(element, internetID);
//	            createParameterList(element, internetID);
//	            message.saveChanges();
//	            SOAPMessage responseMessage = operaSoap.sendMessage(message, "http://18.250.0.137:7547/");
//	            operaSoap.writeTestXml(responseMessage, "D:\\soapMessageResponse.xml");     
//	        }
//	        catch (Exception e)
//	        {
//	            e.printStackTrace();
//	        } finally {
//	
//	        }
//	      
//	    }
//	
//}
//
