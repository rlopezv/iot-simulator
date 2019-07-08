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
public class SensorConfig extends ElementConfig {

	private String type;
	private Double maxValue;
	private Double minValue;

	public String getElementType() {
		return type;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public Double getMinValue() {
		return minValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

}
