/**
 *
 */
package com.upm.miot.rlopezv.iotsimulator.element;

/**
 * @author ramon
 *
 */
@SuppressWarnings("serial")
public class SrActuator extends SrElement {

	private Boolean status = Boolean.FALSE;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
