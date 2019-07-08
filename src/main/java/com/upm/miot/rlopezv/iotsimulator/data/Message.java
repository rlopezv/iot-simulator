/**
 *
 */
package com.upm.miot.rlopezv.iotsimulator.data;

import java.io.Serializable;

import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author ramon
 *
 */
@SuppressWarnings("serial")
public class Message implements Serializable {

	private String topic;
	private MqttMessage mqttMessage;
	private MessageTypeEnum type = MessageTypeEnum.MQTTMESSAGE;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public MqttMessage getMqttMessage() {
		return mqttMessage;
	}

	public void setMqttMessage(MqttMessage mqttMessage) {
		this.mqttMessage = mqttMessage;
	}

	public Message mqttMessage(MqttMessage mqttMessage) {
		this.mqttMessage = mqttMessage;
		return this;
	}

	public Message topic(String topic) {
		this.topic = topic;
		return this;
	}

	public MessageTypeEnum getType() {
		return type;
	}

	public void setType(MessageTypeEnum type) {
		this.type = type;
	}

	public Message type(MessageTypeEnum type) {
		this.type = type;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [topic=").append(topic).append(", mqttMessage=").append(mqttMessage).append("]");
		return builder.toString();
	}

}