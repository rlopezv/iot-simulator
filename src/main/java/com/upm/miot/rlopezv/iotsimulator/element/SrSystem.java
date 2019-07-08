/**
 *
 */
package com.upm.miot.rlopezv.iotsimulator.element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.upm.miot.rlopezv.iotsimulator.model.SrSystemMeassure;

/**
 * @author ramon
 *
 */
@SuppressWarnings("serial")
public class SrSystem implements Serializable {

	private String id;
	private List<SrSensor> sensors = new ArrayList<SrSensor>();
	private List<SrActuator> actuators = new ArrayList<SrActuator>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<SrSensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<SrSensor> sensors) {
		this.sensors = sensors;
	}

	public List<SrActuator> getActuators() {
		return actuators;
	}

	public void setActuators(List<SrActuator> actuators) {
		this.actuators = actuators;
	}

	/**
	 * Obtains meassures from sensors
	 *
	 * @return
	 */
	public SrSystemMeassure getMeasures() {
		SrSystemMeassure result = new SrSystemMeassure();
		result.setSystemId(getId());
		for (SrSensor sensor : sensors) {
			result.addMeassure(sensor.getType(), sensor.getMeasure());
		}
		for (SrActuator actuator : actuators) {
			result.addMeassure(actuator.getType(), actuator.getStatus());
		}
		return result;
	}

	/**
	 *
	 * @param type
	 * @param status
	 */
	public void setActuatorStatus(String type, boolean status) {
		Optional<SrActuator> actuator = actuators.stream().filter(p -> type.equalsIgnoreCase(p.getType())).findFirst();
		if (actuator.isPresent()) {
			actuator.get().setStatus(status);
		}
	}

	public void addActuator(SrActuator actuator) {
		getActuators().add(actuator);
	}

	public void addSensor(SrSensor sensor) {
		getSensors().add(sensor);
	}

}
