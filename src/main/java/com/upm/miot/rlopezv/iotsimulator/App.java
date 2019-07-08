/**
 *
 */
package com.upm.miot.rlopezv.iotsimulator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;
import com.upm.miot.rlopezv.iotsimulator.config.SystemConfig;
import com.upm.miot.rlopezv.iotsimulator.control.AbstractSystemController;

/**
 *
 * Class in charge of creating the environment for serving mqtt captured
 *
 * @author ramon
 *
 */
public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	private final ObjectMapper mapper = new ObjectMapper();

	private static App app = null;

	private SystemConfig config;

	private AbstractSystemController system = null;

	protected SystemConfig getConfig() {
		return config;
	}

	protected void setConfig(SystemConfig config) {
		this.config = config;
	}

	protected ObjectMapper getMapper() {
		return mapper;
	}

	public static void main(final String[] args) {
		String configFile = "/Users/ramon/master/git/iot-simulator/etc/gatewaySystem.json";
		LOGGER.info("Starting client");
		if (configFile == null && (args == null || args.length == 0)) {
			System.out.println("Usage: java -jar app.jar filename");
		} else {
			app = new App();
			try {
				if (configFile == null) {
					configFile = args[0];
				}
				app.configure(configFile);
				app.start();
			} catch (MqttException | JsonParseException | IOException e) {
				LOGGER.error("Error starting client", e);
			}
		}
		// Shutdown hook to ensure ordered close of consumers
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				app.finish();
			}
		});
	}

	private void finish() {
		LOGGER.info("Stopping system");
		try {
			system.stop();
		} catch (MqttException e) {
			LOGGER.error("Error stoping client", e);
		}
	}

	/**
	 * Start the application Uses ExecutorService to limit the number of threads
	 * involved
	 *
	 * @throws MqttException
	 */
	private void start() throws MqttException {
		system.run();
	}

	/**
	 * Instantiate and configure clients from file
	 *
	 * @param configPath file containing client configuration
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private void configure(String configPath) throws JsonParseException, JsonMappingException, IOException {
		try {
			setConfig(parseConfig(configPath));
			Class<?> systemClass = Class.forName(getConfig().getImplClassName());
			Constructor<?> systemConstructor = systemClass.getConstructor();
			system = (AbstractSystemController) systemConstructor.newInstance();
			system.config(getConfig());
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOGGER.error("Error creating client", e);
		}

	}

	final SystemConfig parseConfig(String path) throws JsonParseException, JsonMappingException, IOException {
		return getMapper().readValue(new File(path), SystemConfig.class);

	}

}
