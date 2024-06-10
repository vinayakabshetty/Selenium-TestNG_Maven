package tests;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

public class Log4jDemoTest {

	@Test
	public void SimpleLog4jDemo() {
		Logger log = LogManager.getLogger("SimpleLog4jDemo.class");
		
		System.out.println("Logger Level is set as : "+log.getLevel());
		log.info("INFO");
		log.debug("DEBUG");
		log.warn("WARNING");
		log.error("ERROR");
		log.fatal("FATAL");
	}
}
