package org.artemov.bts.model.entities;

import java.sql.Timestamp;

public class Error extends Entity {

	private static final long serialVersionUID = 1L;
	private int statusId;
	private int errorLevelId;
	private int projectId;
	private int employeeId;
	private Timestamp registerDate;
	private String description;

	public Error() {
	}

	public Error(int id, int statusId, int errorLevelId, int projectId,
			int employeeId, Timestamp registerDate, String description) {
		super(id);
		this.statusId = statusId;
		this.errorLevelId = errorLevelId;
		this.projectId = projectId;
		this.employeeId = employeeId;
		this.registerDate = registerDate;
		this.description = description;
	}

	@Override
	public int getId() {
		return super.getId();
	}

	@Override
	public void setId(int id) {
		super.setId(id);
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getErrorLevelId() {
		return errorLevelId;
	}

	public void setErrorLevelId(int errorLevelId) {
		this.errorLevelId = errorLevelId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + employeeId;
		result = prime * result + errorLevelId;
		result = prime * result + projectId;
		result = prime * result
				+ ((registerDate == null) ? 0 : registerDate.hashCode());
		result = prime * result + statusId;
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
		Error other = (Error) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (errorLevelId != other.errorLevelId)
			return false;
		if (projectId != other.projectId)
			return false;
		if (registerDate == null) {
			if (other.registerDate != null)
				return false;
		} else if (!registerDate.equals(other.registerDate))
			return false;
		if (statusId != other.statusId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Error [statusId=" + statusId + ", errorLevelId=" + errorLevelId
				+ ", projectId=" + projectId + ", employeeId=" + employeeId
				+ ", registerDate=" + registerDate + ", description="
				+ description + "]";
	}

}
