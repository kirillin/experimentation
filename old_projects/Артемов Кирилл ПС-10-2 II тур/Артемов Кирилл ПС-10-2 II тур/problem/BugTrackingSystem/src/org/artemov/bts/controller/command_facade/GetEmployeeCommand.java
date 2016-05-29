package org.artemov.bts.controller.command_facade;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.artemov.bts.libs.Validator;
import org.artemov.bts.model.entities.Department;
import org.artemov.bts.model.entities.Employee;
import org.artemov.bts.model.manager.AttributesEnum;
import org.artemov.bts.model.manager.ConfigurationManager;
import org.artemov.bts.model.service_locator.ServiceLocator;
import org.artemov.bts.model.services.DepartmentService;
import org.artemov.bts.model.services.EmployeeService;

public class GetEmployeeCommand implements Command {
	
	private static final String PARAM_NAME_ID = "employeeId";
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		
		EmployeeService employeeService = (EmployeeService) ServiceLocator
				.getService(AttributesEnum.EMPLOYEE_SERVICE.toString());
		DepartmentService departmentService = (DepartmentService) ServiceLocator.getService(AttributesEnum.DEPARTMENT_SERVICE.toString());
		
		String id = Validator.stripHarmful(request.getParameter(PARAM_NAME_ID));
		int employeeId = -1;
		if (id != null) {
			if (Validator.isNumber(id)) {
				employeeId = Integer.parseInt(id);
			}
		}
		List<Department> departments = departmentService.getDepartmentsList();
		Employee employee = employeeService.getEmployee(employeeId);
		if ((employee != null) && (departments != null)) {
			session.setAttribute(AttributesEnum.EMPLOYEE.toString(), employee);
			session.setAttribute(AttributesEnum.DEPARTMENTS.toString(), departments);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.EMPLOYEE_EDIT_PAGE);
		} else {
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.OOPS_PAGE);
		}
		return page;
	}

}
