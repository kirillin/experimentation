package org.artemov.bts.model.manager;

import java.util.ResourceBundle;

/**
 * Manager for ResourceBundle with configurations and pages of the application
 * 
 * @author Kirill Artemov
 *
 */
public class ConfigurationManager {

	private static ConfigurationManager instance;
	private ResourceBundle resourceBundle;

	private static final String BUNDLE_NAME = "config";
	
	public static final String DATABASE_DRIVER_NAME = "org.artemov.bts.model.manager.ConfigurationManager.jdbc.driver";
	public static final String DATABASE_URL = "org.artemov.bts.model.manager.ConfigurationManager.db.url";
	public static final String DATABASE_USER = "org.artemov.bts.model.manager.ConfigurationManager.db.user";
	public static final String DATABASE_PASSWORD = "org.artemov.bts.model.manager.ConfigurationManager.db.password";
	public static final String CONNECTION_POOL_SIZE = "org.artemov.bts.model.manager.ConfigurationManager.connection.pool.size";
	
	public static final String EMPLOYEES_PAGE = "org.artemov.bts.model.manager.ConfigurationManager.employees.page";
	public static final String ERRORS_PAGE = "org.artemov.bts.model.manager.ConfigurationManager.errors.page";
	public static final String EMPLOYEE_EDIT_PAGE = "org.artemov.bts.model.manager.ConfigurationManager.employee.edit.page";
	public static final String GET_EMPLOYEES_PAGE = "org.artemov.bts.model.manager.ConfigurationManager.get.employee.page";
	public static final String GET_ERRORS_PAGE = "org.artemov.bts.model.manager.ConfigurationManager.get.error.page";
	public static final String GET_SATATUSES_PAGE = "org.artemov.bts.model.manager.ConfigurationManager.statuses.page";
	
	
	
	public static final String OOPS_PAGE = "org.artemov.bts.model.manager.ConfigurationManager.oops.page";
	
	
	
	public static ConfigurationManager getInstance() {
		if (instance == null) {
			instance = new ConfigurationManager();
			instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		return instance;
	}
	
	public String getProperty(String key) {
		return (String)resourceBundle.getObject(key);
	}

}
