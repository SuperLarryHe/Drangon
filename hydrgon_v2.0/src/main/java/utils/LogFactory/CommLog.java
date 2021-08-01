package utils.LogFactory;

import org.apache.log4j.Logger;

public class CommLog {
    public static final Logger
            ADD_LOG = Logger.getLogger(CommLog.class);

    public static void info (String message) {
        ADD_LOG.info(message);
    }

    public static void error (String message) {
        ADD_LOG.error(message);
    }

    public static void debug (String message) {
        ADD_LOG.debug(message);
    }
}