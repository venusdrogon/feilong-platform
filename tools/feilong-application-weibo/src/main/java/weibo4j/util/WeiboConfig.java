package weibo4j.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class WeiboConfig{

	private static final Logger	log	= LoggerFactory.getLogger(WeiboConfig.class);

	public WeiboConfig(){}

	private static Properties	props	= new Properties();
	static{
		try{
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
		}catch (FileNotFoundException e){
			log.error(e.getClass().getName(), e);
		}catch (IOException e){
			log.error(e.getClass().getName(), e);
		}
	}

	public static String getValue(String key){
		return props.getProperty(key);
	}

	public static void updateProperties(String key,String value){
		props.setProperty(key, value);
	}
}
