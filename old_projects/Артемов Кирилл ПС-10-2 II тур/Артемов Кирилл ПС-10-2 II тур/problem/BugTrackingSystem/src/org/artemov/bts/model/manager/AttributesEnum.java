package org.artemov.bts.model.manager;

public enum AttributesEnum {

	COMMAND("command"), 
	GET_EMPLOYEES_LIST("getEmployeesList"), 
	GET_ERRORS_LIST("getErrorsList"),
	GET_DEPARTMENTS_LIST("getDepartmentsList"),
	GET_ERRORS_STATUSES_LIST("getErrorsStatusesList"),
	GET_ERROR_LEVELS_LIST("getErrorLevelsList"),
	GET_PROJECTS_LIST("getProjectsList"),
	GET_STATUSES_LIST("getStatusesList"),
	GET_EMPLOYEE("getEmployee"),
	UPDATE_EMPLOYEE("updateEmployee"),
	ADD_EMPLOYEE("addEmployee"),
	DELETE_EMPLOYEE("deleteEmployee"),
	ADD_ERROR("addError"),
	GET_STATUSES("getStatuses"),
	ADD_STATUS("addStatus"),

	STAUS_VIEW("statusViews"),
	EMPLOYEES("employeesView"),
	ERRORS("errorsView"),
	DEPARTMENTS("departments"),
	
	EMPLOYEES_LIST("employees"),
	ERROR_LEVELS("errorLevels"),
	PROJECTS("projects"),
	ERRORS_STATUS("errorsStatus"),


	EMPLOYEE("employee"),
	
	ERROR_MESSAGE("error_message"),

	EMPLOYEE_SERVICE("employee_service"), 
	ERROR_SERVICE("error_service"), 
	DEPARTMENT_SERVICE("department_service"), 
	ERRORS_STATUS_SERVICE("errors_status_service"), 
	ERROR_LEVEL_SERVICE("error_level_service"), 
	PROJECT_SERVICE("project_service"), 
	STATUS_SERVICE("status_service");

	private final String text;

	private AttributesEnum(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
