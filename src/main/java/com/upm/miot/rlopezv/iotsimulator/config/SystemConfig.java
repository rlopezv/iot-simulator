package com.upm.miot.rlopezv.iotsimulator.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration for the system
 *
 * Includes:
 * <ul>
 * <li>mqtt config data</li>
 * <li>systms sensors and actuators</li>
 * <li>Information about itself such as identifier</li>
 * </ul>
 *
 * @author ramon
 *
 */
@SuppressWarnings("serial")
public class SystemConfig extends BaseConfig {

	private MqttClientConfig mqttConfig;

	private String systemId;

	private List<SensorConfig> sensors = new ArrayList<SensorConfig>();

	private List<ActuatorConfig> actuators = new ArrayList<ActuatorConfig>();

	private String implClassName;

	/**
	 * @return the mqttConfig
	 */
	public MqttClientConfig getMqttConfig() {
		return mqttConfig;
	}

	/**
	 * @param mqttConfig the mqttConfig to set
	 */
	public void setMqttConfig(MqttClientConfig mqttConfig) {
		this.mqttConfig = mqttConfig;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public List<SensorConfig> getSensors() {
		return sensors;
	}

	public void setSensors(List<SensorConfig> sensors) {
		this.sensors = sensors;
	}

	public List<ActuatorConfig> getActuators() {
		return actuators;
	}

	public void setActuators(List<ActuatorConfig> actuators) {
		this.actuators = actuators;
	}

	public String getImplClassName() {
		return implClassName;
	}

	public void setImplClassName(String implClassName) {
		this.implClassName = implClassName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SystemConfig [mqttConfig=");
		builder.append(mqttConfig);
		builder.append(", systemId=");
		builder.append(systemId);
		builder.append(", sensors=");
		builder.append(sensors);
		builder.append(", actuators=");
		builder.append(actuators);
		builder.append(", implClassName=");
		builder.append(implClassName);
		builder.append("]");
		return builder.toString();
	}

}
