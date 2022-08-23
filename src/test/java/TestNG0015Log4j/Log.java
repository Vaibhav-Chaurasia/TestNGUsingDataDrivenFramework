package TestNG0015Log4j;

import org.apache.log4j.Logger;

public class Log {

	//This method is used to get Logs.
	public static Logger Log = Logger.getLogger(Log.class.getName());

	//This method is used to start printing dollars when test case starts execution
	public static void startTestCase(String sTestCaseName) {
		Log.info("$$$$$$$$$$$$$$$$$$$$$" + sTestCaseName + "Execution Started" + "$$$$$$$$$$$$$$$$$$$$$");
	}

	//This method is used to print dollars when test case ends
	public static void endTestCase(String sTestCaseName) {
		Log.info("$$$$$$$$$$$$$$$$$$$$$" + sTestCaseName + "Execution Ended" + "$$$$$$$$$$$$$$$$$$$$$$$");
	}

	//This method is used to print Log message.
	public static void info(String message) {
		Log.info(message);
	}

	//This method is used to print warning messages.
	public static void warn(String message) {
		Log.warn(message);
	}

	//This method is used to print error messages.
	public static void error(Exception message) {
		Log.error(message);
	}

	//This method is used to print Fatal messages.
	public static void fatal(String message) {
		Log.fatal(message);
	}

	//This method is used in case, application is debugging and need to print any message.
	public static void debug(String message) {
		Log.debug(message);
	}
}