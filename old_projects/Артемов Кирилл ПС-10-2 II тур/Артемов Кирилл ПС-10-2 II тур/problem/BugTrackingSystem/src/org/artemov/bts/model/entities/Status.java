package org.artemov.bts.model.entities;

import java.sql.Timestamp;

public class Status extends Entity {

	private static final long serialVersionUID = 1L;
	private int errorId;
	private int errorsStatusId;
	private int employeeId;
	private Timestamp dateSetting;

	public Status() {
	}


	public Status(int id, int errorId, int errorsStatusId, int employeeId,
			Timestamp dateSetting) {
		super(id);
		this.errorId = errorId;
		this.errorsStatusId = errorsStatusId;
		this.employeeId = employeeId;
		this.dateSetting = dateSetting;
	}


	@Override
	public int getId() {
		return super.getId();
	}

	@Override
	public void setId(int id) {
		super.setId(id);
	}

	public int getErrorId() {
		return errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}

	public int getErrorsStatusId() {
		return errorsStatusId;
	}

	public void setErrorsStatusId(int errorsStatusId) {
		this.errorsStatusId = errorsStatusId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Timestamp getDateSetting() {
		return dateSetting;
	}

	public void setDateSetting(Timestamp dateSetting) {
		this.dateSetting = dateSetting;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dateSetting == null) ? 0 : dateSetting.hashCode());
		result = prime * result + employeeId;
		result = prime * result + errorsStatusId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		if (dateSetting == null) {
			if (other.dateSetting != null)
				return false;
		} else if (!dateSetting.equals(other.dateSetting))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (errorsStatusId != other.errorsStatusId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Status [errorsStatusId=" + errorsStatusId + ", employeeId="
				+ employeeId + ", dateSetting=" + dateSetting + "]";
	}

}
