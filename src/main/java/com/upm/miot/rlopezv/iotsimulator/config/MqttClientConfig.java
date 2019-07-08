/**
 *
 */
package com.upm.miot.rlopezv.iotsimulator.config;

import java.util.UUID;

/**
 * Client config
 *
 * @author ramon
 *
 */
@SuppressWarnings("serial")
public class MqttClientConfig extends BaseConfig {

	private String mqttVersion;
	private Boolean cleanSession = Boolean.FALSE;
	private Integer conTimeout = 60;
	private Integer keepAliveInterval = 60;
	private String user;
	private String password;
	private String brokerUrl;
	private String lastWillMessage;
	private String implClassName;
	private Boolean automaticReconnect = Boolean.FALSE;
	private String clientId = UUID.randomUUID().toString();
	private String topic = "";
	private int qos = 0;
	private String controlTopic;

	public String getMqttVersion() {
		return mqttVersion;
	}

	public void setMqttVersion(String mqttVersion) {
		this.mqttVersion = mqttVersion;
	}

	public Boolean getCleanSession() {
		return cleanSession;
	}

	public void setCleanSession(Boolean cleanSession) {
		this.cleanSession = cleanSession;
	}

	public Integer getConTimeout() {
		return conTimeout;
	}

	public void setConTimeout(Integer conTimeout) {
		this.conTimeout = conTimeout;
	}

	public Integer getKeepAliveInterval() {
		return keepAliveInterval;
	}

	public void setKeepAliveInterval(Integer keepAliveInterval) {
		this.keepAliveInterval = keepAliveInterval;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBrokerUrl() {
		return brokerUrl;
	}

	public void setBrokerUrl(String brokerUrl) {
		this.brokerUrl = brokerUrl;
	}

	public String getLastWillMessage() {
		return lastWillMessage;
	}

	public void setLastWillMessage(String lastWillMessage) {
		this.lastWillMessage = lastWillMessage;
	}

	public String getImplClassName() {
		return implClassName;
	}

	public void setImplClassName(String implClassName) {
		this.implClassName = implClassName;
	}

	public void setAutomaticReconnect(Boolean automaticReconnect) {
		this.automaticReconnect = automaticReconnect;
	}

	public Boolean getAutomaticReconnect() {
		return automaticReconnect;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getTopic() {
		return topic;
	}

	public Integer getQos() {
		return qos;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setQos(int qos) {
		this.qos = qos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MqttClientConfig [mqttVersion=").append(mqttVersion).append(", cleanSession=")
				.append(cleanSession).append(", conTimeout=").append(conTimeout).append(", keepAliveInterval=")
				.append(keepAliveInterval).append(", user=").append(user).append(", password=").append(password)
				.append(", brokerUrl=").append(brokerUrl).append(", lastWillMessage=").append(lastWillMessage)
				.append(", implClassName=").append(implClassName).append(", automaticReconnect=")
				.append(automaticReconnect).append(", clientId=").append(clientId).append(", topic=").append(topic)
				.append(", qos=").append(qos).append("]");
		return builder.toString();
	}

	public String getControlTopic() {
		return controlTopic;
	}

	public void setControlTopic(String controlTopic) {
		this.controlTopic = controlTopic;
	}

}
