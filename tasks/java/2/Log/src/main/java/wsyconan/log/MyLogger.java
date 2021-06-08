package wsyconan.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger {
    private static Logger logger = null;

    public MyLogger(){
        logger = LoggerFactory.getLogger(getClass());

    }

    public void info(String str){
        logger.info(str);
    }
}
