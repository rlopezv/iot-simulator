package com.upm.miot.rlopezv.iotsimulator.config;

/**
 * Configuration for the element
 *
 * Includes:
 * <ul>
 * <li>Information about itself such as identifier</li>
 * </ul>
 *
 * @author ramon
 *
 */
@SuppressWarnings("serial")
public class ActuatorConfig extends ElementConfig {

	private String type;
	private Boolean defaultValue = Boolean.FALSE;

	public String getElementType() {
		return type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Boolean defaultValue) {
		this.defaultValue = defaultValue;
	}



}
