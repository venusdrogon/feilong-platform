package train.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class JUL{

	private static Logger	log	= Logger.getLogger(JUL.class.getName());

	@Test
	public void testJUL(){
		log.info("begin");
		try{
			throw new NullPointerException("");
		}catch (NullPointerException ex){
			// Log the error
			log.log(Level.WARNING, "trouble sneezing", ex);
		}
		log.info("end");
	}

	private static Logger	logger	= Logger.getLogger("com.wombat.nose");

	public static void main(String argv[]) throws SecurityException,IOException{
		FileHandler fh = new FileHandler("mylog.txt");
		// Send logger output to our FileHandler.
		logger.addHandler(fh);
		// Request that every detail gets logged.
		logger.setLevel(Level.ALL);
		// Log a simple INFO message.
		logger.info("doing stuff");
		try{
			throw new NullPointerException("");
		}catch (Error ex){
			logger.log(Level.WARNING, "trouble sneezing", ex);
		}
		logger.fine("done");
	}

}