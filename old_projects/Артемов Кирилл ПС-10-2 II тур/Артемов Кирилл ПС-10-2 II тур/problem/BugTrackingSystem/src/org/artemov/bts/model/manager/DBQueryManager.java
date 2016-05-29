package org.artemov.bts.model.manager;

import java.util.ResourceBundle;

/**
 * Manager for ResourceBundle with DBQueries
 * @author Kirill Artemov
 *
 */
public class DBQueryManager {

	private static DBQueryManager instance;
	private ResourceBundle resourceBundle;

	private static final String BUNDLE_NAME = "db_queries";
	
	public static final String GET_EMPLOYEE_BY_ID = "org.artemov.bts.model.manager.DBQueryManager.getEmployeeById";
	public static final String GET_EMPLOYEES_LIST = "org.artemov.bts.model.manager.DBQueryManager.getEmployeesList";
	public static final String ADD_EMPLOYEE = "org.artemov.bts.model.manager.DBQueryManager.addEmployee";
	public static final String DELETE_EMPLOYEE_BY_ID = "org.artemov.bts.model.manager.DBQueryManager.deleteEmployeById";
	public static final String UPDATE_EMPLOYEE_BY_ID = "org.artemov.bts.model.manager.DBQueryManager.updateEmployeById";
	public static final String GET_ERROR_BY_ID = "org.artemov.bts.model.manager.DBQueryManager.getErrorById";
	public static final String GET_ERRORS_LIST = "org.artemov.bts.model.manager.DBQueryManager.getErrorsList";
	public static final String ADD_ERROR = "org.artemov.bts.model.manager.DBQueryManager.addError";
	public static final String UPDATE_ERROR_BY_ID = "org.artemov.bts.model.manager.DBQueryManager.updateErrorById";
	public static final String GET_DEPARTMENTS_LIST = "org.artemov.bts.model.manager.DBQueryManager.getDepartmentsList";
	public static final String GET_ERRORS_STATUSES_LIST = "org.artemov.bts.model.manager.DBQueryManager.getErrorsStatusesList";
	public static final String GET_ERROR_LEVELS_LIST = "org.artemov.bts.model.manager.DBQueryManager.getErrorLevelsList";
	public static final String GET_PROJECTS_LIST = "org.artemov.bts.model.manager.DBQueryManager.getProectsList";
	public static final String GET_STATUSES_LIST = "org.artemov.bts.model.manager.DBQueryManager.getStatusesList";
	public static final String ADD_STATUS = "org.artemov.bts.model.manager.DBQueryManager.addStatus";
	public static final String UPDATE_STAUS_BY_ID = "org.artemov.bts.model.manager.DBQueryManager.updateStatus";
	public static final String GET_DEPARTMENT_BY_ID = "org.artemov.bts.model.manager.DBQueryManager.getDepartmentById";
	public static final String GET_ERROR_LEVEL_BY_ID = "org.artemov.bts.model.manager.DBQueryManager.getErrorLeveltById";
	public static final String GET_PROJECT_BY_ID = "org.artemov.bts.model.manager.DBQueryManager.getProjectById";
	public static final String GET_ERRORS_STATUS_BY_ID = "org.artemov.bts.model.manager.DBQueryManager.getErrorsStatusById";
	
	public static DBQueryManager getInstance() {
		if (instance == null) {
			instance = new DBQueryManager();
			instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		return instance;
	}
	
	public String getProperty(String key) {
		return (String)resourceBundle.getObject(key);
	}

}
