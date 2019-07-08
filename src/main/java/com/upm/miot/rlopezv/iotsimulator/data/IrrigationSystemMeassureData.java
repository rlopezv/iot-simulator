package com.upm.miot.rlopezv.iotsimulator.data;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IrrigationSystemMeassureData extends SystemMeassureData {

	@JsonProperty("temperature")
	private Double temperature;
	@JsonProperty("humidity")
	private Double humidity;
	@JsonProperty("sm")
	private Double sm;
	@JsonProperty("valve")
	private Boolean valve;

	@JsonProperty("element_id")
	private String elementId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("temperature")
	public Double getTemperature() {
		return temperature;
	}

	@JsonProperty("temperature")
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public IrrigationSystemMeassureData withTemperature(Double temperature) {
		this.temperature = temperature;
		return this;
	}

	@JsonProperty("humidity")
	public Double getHumidity() {
		return humidity;
	}

	@JsonProperty("humidity")
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public IrrigationSystemMeassureData withHumidity(Double humidity) {
		this.humidity = humidity;
		return this;
	}

	@JsonProperty("valve")
	public Boolean getValve() {
		return valve;
	}

	@JsonProperty("valve")
	public void setValve(Boolean valve) {
		this.valve = valve;
	}

	public IrrigationSystemMeassureData withBattery(Boolean valve) {
		this.valve = valve;
		return this;
	}

	@JsonProperty("sn")
	public Double getSm() {
		return sm;
	}

	@JsonProperty("sm")
	public void setSm(Double sm) {
		this.sm = sm;
	}

	public IrrigationSystemMeassureData withSm(Double sm) {
		this.sm = sm;
		return this;
	}

}
