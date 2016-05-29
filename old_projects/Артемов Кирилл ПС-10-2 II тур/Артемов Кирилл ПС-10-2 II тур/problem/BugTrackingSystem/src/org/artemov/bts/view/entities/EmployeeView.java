package org.artemov.bts.view.entities;

import java.io.Serializable;

public class EmployeeView implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String department;
	private String firstName;
	private String lastName;
	private String middleName;

	public EmployeeView() {
	}

	public String getDepartment() {
		return department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Override
	public String toString() {
		return "EmployeesView [department=" + department + ", firstName="
				+ firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + "]";
	}
	
	
	
}
