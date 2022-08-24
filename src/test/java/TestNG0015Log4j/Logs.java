package TestNG0015Log4j;

import org.apache.log4j.Logger;

//Always keep this file in your src/main/java -> Utility package
//This I keep in custom package just for the demo

public class Logs {
	
	//This method is used to get Logs.
	public static Logger Logs = Logger.getLogger(Logs.class);

	//This method is used to start printing dollars when test case starts execution
	public static void startTestCase(String sTestCaseName) {
		Logs.info("$$$$$$$$$$$$$$$$$$$$$" + sTestCaseName + "Execution Started" + "$$$$$$$$$$$$$$$$$$$$$");
	}

	//This method is used to print dollars when test case ends
	public static void endTestCase(String sTestCaseName) {
		Logs.info("$$$$$$$$$$$$$$$$$$$$$" + sTestCaseName + "Execution Ended" + "$$$$$$$$$$$$$$$$$$$$$$$");
	}

	//This method is used to print Log message.
	public static void information(String message) {
		Logs.info(message);
	}

	//This method is used to print warning messages.
	public static void warn(String message) {
		Logs.warn(message);
	}

	//This method is used to print Fatal messages.
	public static void fatal(String message) {
		Logs.fatal(message);
	}

	//This method is used in case, application is debugging and need to print any message.
	public static void debug(String message) {
		Logs.debug(message);
	}
}