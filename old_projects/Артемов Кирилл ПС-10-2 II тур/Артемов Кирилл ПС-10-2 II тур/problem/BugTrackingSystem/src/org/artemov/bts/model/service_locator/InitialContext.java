package org.artemov.bts.model.service_locator;

import org.artemov.bts.model.services.DepartmentService;
import org.artemov.bts.model.services.EmployeeService;
import org.artemov.bts.model.services.ErrorLevelService;
import org.artemov.bts.model.services.ErrorService;
import org.artemov.bts.model.services.ErrorsStatusService;
import org.artemov.bts.model.services.ProjectService;
import org.artemov.bts.model.services.Service;
import org.artemov.bts.model.services.StatusService;


public class InitialContext {
	/**
	 * 
	 * @param serviceName
	 * @param daoFactory
	 *            - integer from @see com.epam.eshop.dao.factory.DAOFactory
	 * @return
	 */
	public Service lookup(String serviceName, int daoFactory) {
		switch (serviceName) {
		case "employee_service":
			return (Service) new EmployeeService(daoFactory, serviceName);
		case "error_service":
			return (Service) new ErrorService(daoFactory, serviceName);
		case "department_service":
			return (Service) new DepartmentService(daoFactory, serviceName);
		case "errors_status_service":
			return (Service) new ErrorsStatusService(daoFactory, serviceName);
		case "error_level_service":
			return (Service) new ErrorLevelService(daoFactory, serviceName);
		case "project_service":
			return (Service) new ProjectService(daoFactory, serviceName);
		case "status_service":
			return (Service) new StatusService(daoFactory, serviceName);
		default:
			return null;
		}
	}
}
