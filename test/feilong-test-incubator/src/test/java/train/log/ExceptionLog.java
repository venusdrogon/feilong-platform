package train.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class ExceptionLog{

	private static final Logger	log	= LoggerFactory.getLogger(ExceptionLog.class);

	/**
	 * TestExceptionLog.
	 */
	@Test
	public void testExceptionLog(){
		try{
			String a = null;

			if (null == a){
				throw new NullPointerException("the a is null or empty!");
			}
		}catch (NullPointerException e){
			//	log.error(e.getClass().getName(), e);
			log.error("when xxx,occur exception:", e);

			Marker fatal = MarkerFactory.getMarker("FATAL");
			log.error(fatal, log.getName(), e);
		}
	}

	

	/**
	 * TestExceptionLog.
	 */
	@Test
	public void testExceptionLog3(){

		Marker fatal = MarkerFactory.getMarker("FATAL");

		log.trace(fatal, log.getName());
	}

}