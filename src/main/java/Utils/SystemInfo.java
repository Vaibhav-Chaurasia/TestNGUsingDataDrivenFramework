package Utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemInfo {
	
	//Object to print logs
	private static final Logger logger = LogManager.getLogger(SystemInfo.class);

	//This method is used to get Host Name on which Appium server is running. For e.g. localhost
	//Also print logs
	public static String getHostName() {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {

		}
		logger.info("Host Address \t" + address.getHostAddress());
		return address.getHostAddress();
	}

	//This method is used to get Host Address on which Appium server is running. For e.g. 192.0.0.1
	public static String getHostAddress() {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();

		} catch (UnknownHostException e) {
			logger.info("Failure in Get host Information");
			logger.error(e);
		}
		logger.info("Host Name  \t" + address.getHostName());
		return address.getHostName();
	}

	//This method is used to get Operating System.
	public static String getOperatingSystem() {
		logger.info("Operating System \t" + System.getProperty("os.name"));
		return System.getProperty("os.name");
	}

	//This method is used to get Directory/Folder
	public static String getUserDir() {
		logger.info("User Directory \t" + System.getProperty("user.dir"));
		return System.getProperty("user.dir");
	}

	//Main function to access above functions
	public static void main(String[] args) {
		SystemInfo.getHostName();
		SystemInfo.getHostAddress();
		SystemInfo.getOperatingSystem();
		SystemInfo.getUserDir();
	}
}