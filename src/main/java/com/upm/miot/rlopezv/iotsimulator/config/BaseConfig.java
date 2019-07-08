package com.upm.miot.rlopezv.iotsimulator.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class BaseConfig implements Serializable {

	private final Map<String, String> additionalProperties = new HashMap<>();

	public Map<String, String> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(final Map<String, String> additionalProperties) {
		getAdditionalProperties().clear();
		if (additionalProperties != null) {
			getAdditionalProperties().putAll(additionalProperties);
		}
	}

}
