package com.upm.miot.rlopezv.iotsimulator.control;

import java.util.Map;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.upm.miot.rlopezv.iotsimulator.config.SystemConfig;
import com.upm.miot.rlopezv.iotsimulator.element.SrSystem;

public interface ISystemController {

	void config(SystemConfig systemConfig);

	void stop() throws MqttException;

	boolean isActive();

	Map<String, String> getConfig();

	void setConfig(Map<String, String> config);

	void run();

	void setSystem(SrSystem system);

}