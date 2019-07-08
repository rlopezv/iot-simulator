package com.upm.miot.rlopezv.iotsimulator.control;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.upm.miot.rlopezv.iotsimulator.AppConstants;
import com.upm.miot.rlopezv.iotsimulator.config.MqttClientConfig;

/**
 * Implements a client for mqtt
 *
 * @author ramon
 *
 */
public class Client {

	private Logger LOGGER = LoggerFactory.getLogger(Client.class);

	private MqttClient client = null;
	private MqttClientConfig config = null;
	private MemoryPersistence persistence = null;

	public Client(MqttClientConfig clientConfig) {
		this.config = clientConfig;
		this.persistence = new MemoryPersistence();
	}

	public MqttClient getClient() {
		return client;
	}

	protected void setClient(MqttClient client) {
		this.client = client;
	}

	public MqttClientConfig getConfig() {
		return config;
	}

	public void setConfig(MqttClientConfig config) {
		this.config = config;
	}

	/**
	 * Open connection and subscibes to the configured subject It reatempts
	 * connection every second
	 *
	 * @throws MqttException
	 */
	public void start() throws MqttException {
		client = new MqttClient(getConfig().getBrokerUrl(), getConfig().getClientId(), persistence);
		while (!client.isConnected()) {
			try {
				connect();
			} catch (MqttException e) {
				LOGGER.error("Cannot connect to mqttBorker");
				synchronized (this) {
					try {
						wait(1000);
					} catch (InterruptedException e1) {
						LOGGER.error("Interrupted exception");
					}
				}
			}
		}
		subscribe();
		LOGGER.info("Started client");
	}

	public void stop() throws MqttException {
		client.disconnect();
	}

	void connect() throws MqttException {
		if (this.client != null && !this.client.isConnected()) {
			this.client.connect(getMqttConnectOptions());
		}
		LOGGER.info("Connected to mqtt broker");
	}

	public boolean isConnected() {
		return client.isConnected();
	}

	private MqttConnectOptions getMqttConnectOptions() {
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setAutomaticReconnect(getConfig().getAutomaticReconnect());
		connOpts.setConnectionTimeout(getConfig().getConTimeout());
		connOpts.setKeepAliveInterval(getConfig().getKeepAliveInterval());
		if (!isWildcard()) {
			connOpts.setWill(getLastWillTopic(), AppConstants.OFF_TAG.getBytes(), 1, false);
			if (getConfig().getLastWillMessage() != null && getConfig().getControlTopic() != null) {
				connOpts.setWill(getConfig().getControlTopic(), getConfig().getLastWillMessage().getBytes(), 0, true);
			}
		}
		if (getConfig().getUser() != null) {
			connOpts.setUserName(getConfig().getUser());
		}
		if (getConfig().getPassword() != null) {
			connOpts.setPassword(getConfig().getPassword().toCharArray());
		}

		connOpts.setCleanSession(getConfig().getCleanSession());
		return connOpts;
	}

	void subscribe() throws MqttException {
		String topic = getConfig().getTopic();
		if (!isWildcard()) {
			topic = topic + "/command/#";
		}
		client.subscribe(topic, getConfig().getQos());
		LOGGER.info("Subscribed to:{}", topic);
	}

	private boolean isWildcard() {
		return getConfig().getTopic().indexOf("#") >= 0;
	}

	void publish(String message) throws MqttException {
		String topic = getConfig().getTopic();
		if (!isWildcard()) {
			topic = topic + "/data";
		}
		if (message != null) {
			client.publish(topic, message.getBytes(), getConfig().getQos(), true);
		}
		LOGGER.debug("Published to ({}):{}", topic, message);
	}

	String getLastWillTopic() {
		String result = getConfig().getTopic() + "/status";
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [config=").append(config).append("]");
		return builder.toString();
	}

	public void disconnect() throws MqttException {
		if (client.isConnected()) {
			this.client.disconnect();
		}
	}

	/**
	 * Assing handler that will treat the message received
	 *
	 * @param abstractClientHandler
	 */
	public void setHandler(AbstractSystemController abstractController) {
		this.client.setCallback(abstractController);

	}

}
