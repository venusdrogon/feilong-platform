package train.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld{

	private static final Logger	log	= LoggerFactory.getLogger(HelloWorld.class);

	public static void main(String[] args){
		log.info("Hello World");
	}
}