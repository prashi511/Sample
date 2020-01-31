package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
	

    private static String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	
    	logger.debug("debug message slf4j");
        logger.info("info message slf4j");
        logger.warn("warn message slf4j");
        logger.error("error message slf4j");
        if("forceerror".equals(name)) {
	        try {
	            logger.info("{}" , 1/0 );
	        }catch(Exception exc) {
	            logger.error("error message with stack trace slf4j",
	                    new Exception("I forced this exception",exc));
	        }
        }

        return new Greeting(counter.incrementAndGet(),
                            String.format(TEMPLATE, name));
    }
}
