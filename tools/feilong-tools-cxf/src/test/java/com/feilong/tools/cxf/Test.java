package com.feilong.tools.cxf;
//
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//import org.apache.commons.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.BasicResponseHandler;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.protocol.HTTP;
//
//
///**
// * 类作用:演示了如何通过get、post以及httpclient封装soap请求三种方式调用webservice
// * 
// * @author qsw-Myonlystar @date 2010-7-6
// * @mail i@qinshuwei.com 说明：调用的webservice为http://webservice.webxml.com.cn网站的火车站查询服务
// */
//public class Test{
//
//	/**
//	 * 通过get方式演示webservice
//	 */
//	private void getMethodWS(){
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		HttpGet httpget = new HttpGet("http://webservice.webxml.com.cn/WebServices/TrainTimeWebService.asmx/getStationName");
//		ResponseHandler<String> responseHandler = new BasicResponseHandler();
//		String responseBody = null;
//		try{
//			responseBody = httpclient.execute(httpget, responseHandler);
//		}catch (ClientProtocolException e){
//			e.printStackTrace();
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//		log.info(responseBody);
//		httpclient.getConnectionManager().shutdown();
//	}
//
//	/**
//	 * 通过post方式调用webservice
//	 * 
//	 * @throws UnsupportedEncodingException
//	 */
//	private void postMethodWS(){
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		HttpPost httpost = new HttpPost("http://webservice.webxml.com.cn/WebServices/TrainTimeWebService.asmx/getStationAndTimeByTrainCode");
//		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//		nvps.add(new BasicNameValuePair("TrainCode", null));
//		nvps.add(new BasicNameValuePair("UserID", null));
//		UrlEncodedFormEntity urlEntity = null;
//		try{
//			urlEntity = new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
//		}catch (UnsupportedEncodingException e1){
//			e1.printStackTrace();
//		}
//		httpost.setEntity(urlEntity);
//		ResponseHandler<String> responseHandler = new BasicResponseHandler();
//		String responseBody = null;
//		try{
//			responseBody = httpclient.execute(httpost, responseHandler);
//		}catch (ClientProtocolException e){
//			e.printStackTrace();
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//		log.info(responseBody);
//		httpclient.getConnectionManager().shutdown();
//	}
//
//	/**
//	 * 通过soap方式调用webservice
//	 * 
//	 * @throws IOException
//	 * @throws ClientProtocolException
//	 */
//	private void soapMethodWS(){
//
//		String soapRequestData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
//				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
//				+ "<soap:Body>" + "<getStationName xmlns=\"http://WebXml.com.cn/\" />" + "</soap:Body>" + "</soap:Envelope>";
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		HttpPost httpost = new HttpPost("http://webservice.webxml.com.cn/WebServices/TrainTimeWebService.asmx");
//		httpost.setHeader("Content-Type", "text/xml; charset=utf-8");
//		HttpEntity entity = null;
//		try{
//			entity = new StringEntity(soapRequestData);
//		}catch (UnsupportedEncodingException e1){
//			e1.printStackTrace();
//		}
//		httpost.setEntity(entity);
//		ResponseHandler<String> responseHandler = new BasicResponseHandler();
//		String responseBody = null;
//		try{
//			responseBody = httpclient.execute(httpost, responseHandler);
//		}catch (ClientProtocolException e){
//			e.printStackTrace();
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//		log.info(responseBody);
//		httpclient.getConnectionManager().shutdown();
//	}
//
//	/**
//	 * @param args
//	 * @throws IOException
//	 * @throws ClientProtocolException
//	 */
//	public static void main(String[] args){
//		Test t = new Test();
//		t.getMethodWS();// get方式测试
//		t.soapMethodWS();// soap方式测试
//		t.postMethodWS();// post方式测试
//	}
//
//}
