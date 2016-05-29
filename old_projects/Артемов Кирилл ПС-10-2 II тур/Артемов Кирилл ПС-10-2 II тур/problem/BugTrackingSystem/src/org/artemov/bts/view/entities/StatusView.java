package org.artemov.bts.view.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class StatusView implements Serializable {

	private static final long serialVersionUID = 1L;
	private int Id;
	private int errorId;
	private String errorsStatus;
	private String employeeName;
	private Timestamp registerDate;

	public StatusView() {
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	
	public int getErrorId() {
		return errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}

	public String getErrorsStatus() {
		return errorsStatus;
	}

	public void setErrorsStatus(String errorsStatus) {
		this.errorsStatus = errorsStatus;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return "StatusView [Id=" + Id + ", errorsStatus=" + errorsStatus
				+ ", employeeName=" + employeeName + ", registerDate="
				+ registerDate + "]";
	}

}
