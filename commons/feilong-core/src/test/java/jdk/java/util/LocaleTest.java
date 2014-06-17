package jdk.java.util;

import java.util.Locale;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class LocaleTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-4 4:13:48
 */
public class LocaleTest{

	/** The Constant log. */
	private static final Logger	log		= LoggerFactory.getLogger(LocaleTest.class);

	/** The en loc. */
	private Locale				enLoc	= new Locale("en", "US");						// 表示美国地区

	/** The fr loc. */
	private Locale				frLoc	= new Locale("fr", "FR");						// 表示法国地区

	/** The zh loc. */
	private Locale				zhLoc	= new Locale("zh", "CN");						// 表示中国地区

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args){
		Locale myLocale = Locale.getDefault();
		log.info(myLocale.getCountry());
		log.info(myLocale.getLanguage());
		log.info(myLocale.getDisplayCountry());
		log.info(myLocale.getDisplayLanguage());
		log.info("" + (2.00 - 1.10));
	}

	/**
	 * Name.
	 */
	@Test
	public void name(){
		if (log.isInfoEnabled()){
			log.info(new Locale("en", "US").toString());
			log.info(Locale.ENGLISH.toString());
			log.info(Locale.US.toString());
			log.info(Locale.CHINA.toString());
			log.info(Locale.CHINESE.toString());
			log.info(Locale.SIMPLIFIED_CHINESE.toString());
		}
	}
}
