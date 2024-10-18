package com.api.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {
    private static final Logger logger = LogManager.getLogger(LogHelper.class);

    public static void log(String message) {
        logger.info(message);
    }
}
