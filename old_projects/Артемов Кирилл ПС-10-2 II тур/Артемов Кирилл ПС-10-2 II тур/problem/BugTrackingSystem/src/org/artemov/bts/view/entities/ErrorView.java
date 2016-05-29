package org.artemov.bts.view.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class ErrorView implements Serializable {

	private static final long serialVersionUID = 1L;
	private int errorId;
	private Timestamp registerDate;
	private String employeeName;
	private String errorLevelName;
	private String projectName;
	private String descriprion;

	public ErrorView() {
	}

	public int getErrorId() {
		return errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getErrorLevelName() {
		return errorLevelName;
	}

	public void setErrorLevelName(String errorLevelName) {
		this.errorLevelName = errorLevelName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescriprion() {
		return descriprion;
	}

	public void setDescriprion(String descriprion) {
		this.descriprion = descriprion;
	}

	@Override
	public String toString() {
		return "ErrorView [errorId=" + errorId + ", registerDate="
				+ registerDate + ", employeeName=" + employeeName
				+ ", errorLevelName=" + errorLevelName + ", projectName="
				+ projectName + ", descriprion=" + descriprion + "]";
	}

}
