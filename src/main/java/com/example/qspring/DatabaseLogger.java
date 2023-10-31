package com.example.qspring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseLogger {
    private static final Logger logger = LogManager.getLogger(DatabaseLogger.class);

    public static void logChange(String tableName, String changeDescription) {
        
        logger.info("Table changes '{}': {}", tableName, changeDescription);
    }
}
