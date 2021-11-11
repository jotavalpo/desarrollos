package util;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogManager {

	private static LogManager instance = null;
	private static Logger logger = Logger.getLogger(LogManager.class.getName());

	public static LogManager getInstance() throws IOException {
		if (instance == null) {
			instance = new LogManager();
			PropertyConfigurator.configure(Paths
					.get(PropertiesManager.getInstance().getFrameworkProperty("log4j_properties_path"),
							PropertiesManager.getInstance().getFrameworkProperty("log4j_properties_file_name"))
					.toString());
		}
		return instance;
	}

	public void info(String message) {
		logger.info(message);
	}

	public void warn(String message) {
		logger.warn(message);
	}

	public void error(String message) {
		logger.error(message);
	}

	public void fatal(String message) {
		logger.fatal(message);
	}

	public void debug(String message) {
		logger.debug(message);
	}

}
