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
public class ElementConfig extends BaseConfig {

	private String elementId;

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

}
