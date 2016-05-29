package org.artemov.bts.model.entities;

public class ErrorsStatus extends Entity {

	private static final long serialVersionUID = 1L;
	private String statusName;

	public ErrorsStatus() {
	}

	public ErrorsStatus(int id, String statusName) {
		super(id);
		this.statusName = statusName;
	}

	@Override
	public int getId() {
		return super.getId();
	}

	@Override
	public void setId(int id) {
		super.setId(id);
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((statusName == null) ? 0 : statusName.hashCode());
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
		ErrorsStatus other = (ErrorsStatus) obj;
		if (statusName == null) {
			if (other.statusName != null)
				return false;
		} else if (!statusName.equals(other.statusName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ErrorsStatus [statusName=" + statusName + "]";
	}

}
