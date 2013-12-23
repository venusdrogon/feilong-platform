package com.feilong.tools.net.om;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * nginx log format
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 12:40:54 AM
 */
public class NginxLogFormat{

	/**
	 * 记录客户端的ip地址
	 */
	private String	remote_addr;

	/**
	 * 记录客户端用户名称,忽略属性"-"
	 */
	private String	remote_user;

	/**
	 * 记录访问时间与时区
	 */
	private String	time_local;

	/**
	 * 记录请求的url与http协议
	 */
	private String	request;

	/**
	 * 记录请求状态；成功是200
	 */
	private String	status;

	/**
	 * 记录发送给客户端文件主体内容大小
	 */
	private String	body_bytes_sent;

	/**
	 * 用来记录从那个页面链接访问过来的
	 */
	private String	http_referer;

	/**
	 * 记录客户浏览器的相关信息
	 */
	private String	http_user_agent;

	private String	proxy_add_x_forwarded_for;

	private String	upstream_addr;

	private String	request_time;

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\nremote_addr:" + this.remote_addr);
		sb.append("\nremote_user:" + this.remote_user);
		sb.append("\ntime_local:" + this.time_local);
		sb.append("\nrequest:" + this.request);
		sb.append("\nstatus:" + this.status);
		sb.append("\nbody_bytes_sent:" + this.body_bytes_sent);
		sb.append("\nhttp_referer:" + this.http_referer);
		sb.append("\nhttp_user_agent:" + this.http_user_agent);
		return sb.toString();
	}

	public String getRemote_addr(){
		return remote_addr;
	}

	public void setRemote_addr(String remote_addr){
		this.remote_addr = remote_addr;
	}

	public String getRemote_user(){
		return remote_user;
	}

	public void setRemote_user(String remote_user){
		this.remote_user = remote_user;
	}

	public String getTime_local(){
		return time_local;
	}

	public Date getTime_local_Date() throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.US);
		return df.parse(this.time_local);
	}

	public String getTime_local_Date_hour() throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
		return df.format(this.getTime_local_Date());
	}

	public void setTime_local(String time_local){
		this.time_local = time_local;
	}

	public String getRequest(){
		return request;
	}

	public void setRequest(String request){
		this.request = request;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getBody_bytes_sent(){
		return body_bytes_sent;
	}

	public void setBody_bytes_sent(String body_bytes_sent){
		this.body_bytes_sent = body_bytes_sent;
	}

	public String getHttp_referer(){
		return http_referer;
	}

	public String getHttp_referer_domain(){
		if (http_referer.length() < 8){
			return http_referer;
		}

		String str = this.http_referer.replace("\"", "").replace("http://", "").replace("https://", "");
		return str.indexOf("/") > 0 ? str.substring(0, str.indexOf("/")) : str;
	}

	public void setHttp_referer(String http_referer){
		this.http_referer = http_referer;
	}

	public String getHttp_user_agent(){
		return http_user_agent;
	}

	public void setHttp_user_agent(String http_user_agent){
		this.http_user_agent = http_user_agent;
	}

}