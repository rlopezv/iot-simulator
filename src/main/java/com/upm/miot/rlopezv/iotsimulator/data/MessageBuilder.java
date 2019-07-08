/**
 *
 */
package com.upm.miot.rlopezv.iotsimulator.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ramon
 *
 */
public class MessageBuilder {

	private final static MessageBuilder builder = new MessageBuilder();

	private ObjectMapper objectMapper = new ObjectMapper();

	private MessageBuilder() {

	}

	public static MessageBuilder getBuilder() {
		return builder;
	}

	protected Double parseDouble(String value) {
		Double result = null;
		if (value != null) {
			result = Double.valueOf(value);
		}
		return result;
	}

	public String buildMessage(Object message) throws MessageException {
		String result = null;
		try {
			result = objectMapper.writeValueAsString(message);
		} catch (JsonProcessingException e) {
			throw new MessageException(e);
		}
		return result;
	}

	public byte[] buildBytesMessage(Object message) throws MessageException {
		byte[] result = null;
		try {
			result = objectMapper.writeValueAsBytes(message);
		} catch (JsonProcessingException e) {
			throw new MessageException(e);
		}
		return result;
	}

	/**
	 * Build message from byte array
	 *
	 * @param message
	 * @param         messageClass, target class
	 * @return parsed object
	 * @throws MessageException
	 */
	public <T> T parseObject(byte[] message, Class<T> messageClass) throws MessageException {
		T result = null;
		try {
			result = objectMapper.readValue(message, messageClass);
		} catch (IOException e) {
			throw new MessageException(e);
		}
		return result;
	}

	/**
	 * Splits string on values
	 *
	 * @param message
	 * @return
	 */
	public Map<String, String> buildMap(String message) {
		Map<String, String> result = new HashMap<>();
		if (message != null) {
			String[] sValues = StringUtils.split(message, "&");
			for (String sValue : sValues) {
				String[] value = StringUtils.split(sValue, "=");
				if (value.length == 2) {
					result.put(value[0], value[1]);
				}
			}
		}
		return result;
	}

}
