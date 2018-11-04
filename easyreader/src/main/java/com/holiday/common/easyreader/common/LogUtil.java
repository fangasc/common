package com.holiday.common.easyreader.common;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtil {

	public static void logInfo(Logger log, String desc) {
		log.log(Level.INFO, "[easy-reader] " + desc);
	}
	
	public static void logErr(Logger log, String desc, Exception e) {
		log.log(Level.WARNING, "[easy-reader] " + desc, e);
	}
}
