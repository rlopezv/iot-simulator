/**
 *
 */
package com.upm.miot.rlopezv.iotsimulator.element;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ramon
 *
 */
@SuppressWarnings("serial")
public class SrSensor extends SrElement {

	private Double measure;
	private Double minValue;
	private Double maxValue;
	private String type;

	public Double getMeasure() {
		return buildValue();
	}

	protected Double buildValue() {
		return ThreadLocalRandom.current().nextDouble(minValue, maxValue);
	}

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public SrSensor type(String type) {
		this.type = type;
		return this;
	}

	public SrSensor maxValue(Double maxValue) {
		this.maxValue = maxValue;
		return this;
	}

	public SrSensor minValue(Double minValue) {
		this.minValue = minValue;
		return this;
	}

}
