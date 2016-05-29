package org.artemov.bts.model.manager;

public enum BDFieldsEnum {

	EMPLOYEE_ID("employee_id"),
	EMPLOYEE_LEVEL_ID("employee_level_id"),
	DEPARTMENT_ID("department_id"),
	DEPARTMENT_NAME("department_name"),
	GET_CATALOG("getCatalog");
	
	
	private final String text;

	private BDFieldsEnum(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
	
}