package org.artemov.bts.model.dao.factory;

import org.artemov.bts.model.dao.daos.entity_dao_implement.DepartmentDAOImpl;
import org.artemov.bts.model.dao.daos.entity_dao_implement.EmployeeDAOImpl;
import org.artemov.bts.model.dao.daos.entity_dao_implement.ErrorDAOImpl;
import org.artemov.bts.model.dao.daos.entity_dao_implement.ErrorLevelDAOImpl;
import org.artemov.bts.model.dao.daos.entity_dao_implement.ErrorsStatusDAOImpl;
import org.artemov.bts.model.dao.daos.entity_dao_implement.ProjectDAOImpl;
import org.artemov.bts.model.dao.daos.entity_dao_implement.StatusDAOImpl;


/**
 * The class contains databases which possible to use in the application
 * 
 * @author Kirill Artemov
 * 
 */
public abstract class DAOFactory {

	public static final int MYSQL = 1;

	public abstract EmployeeDAOImpl getEmployeeDAOImpl();
	public abstract ErrorDAOImpl getErrorDAOImpl();
	public abstract DepartmentDAOImpl getDepartmentDAOImpl();
	public abstract ErrorsStatusDAOImpl getErrorsStatusDAOImpl();
	public abstract ErrorLevelDAOImpl getErrorLevelDAOImpl();
	public abstract ProjectDAOImpl getProjectDAOImpl();
	public abstract StatusDAOImpl getStatusDAOImpl();
	
	/**
	 * 
	 * @param whichFactory
	 * @return Factory for selected database
	 */
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL:
			return new MySQLDAOFactory();
		default:
			return null;
		}
	}

}
