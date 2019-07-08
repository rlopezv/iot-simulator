package com.upm.miot.rlopezv.iotsimulator.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SrIrrigationPlan implements Serializable {
	private String date;

	private String startTimestamp;

	private String endTimestamp;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(String startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public String getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(String endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SrIrrigationPlan [date=").append(date).append(", startTimestamp=").append(startTimestamp)
				.append(", endTimestamp=").append(endTimestamp).append("]");
		return builder.toString();
	}

}
