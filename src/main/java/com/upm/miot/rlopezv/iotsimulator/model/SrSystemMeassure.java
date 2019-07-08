package com.upm.miot.rlopezv.iotsimulator.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class SrSystemMeassure implements Serializable {

	private Map<String, Object> measures = new HashMap<String, Object>();

	private String systemId;

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public void addMeassure(final String key, final Object measure) {
		measures.put(key, measure);
	}

	public Map<String, Object> getMeasures() {
		return measures;
	}

	public void setMeasures(Map<String, Object> measures) {
		this.measures = measures;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SrSystemMeassure [measures=").append(measures).append("]");
		return builder.toString();
	}


}
