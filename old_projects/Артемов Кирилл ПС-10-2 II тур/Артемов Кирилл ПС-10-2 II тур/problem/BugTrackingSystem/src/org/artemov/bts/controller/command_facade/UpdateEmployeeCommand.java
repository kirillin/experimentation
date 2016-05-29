package org.artemov.bts.controller.command_facade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.artemov.bts.libs.Validator;
import org.artemov.bts.model.entities.Employee;
import org.artemov.bts.model.manager.AttributesEnum;
import org.artemov.bts.model.manager.ConfigurationManager;
import org.artemov.bts.model.service_locator.ServiceLocator;
import org.artemov.bts.model.services.EmployeeService;

public class UpdateEmployeeCommand implements Command {

	private static final String PARAM_NAME_ID = "employeeId";
	private static final String PARAM_NAME_FIRSTNAME = "firstName";
	private static final String PARAM_NAME_LASTNAME = "lastName";
	private static final String PARAM_NAME_MIDDLETNAME = "middleName";
	private static final String PARAM_NAME_DEPARTMENT_ID = "departmentId";
	
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);

		String id = Validator.stripHarmful(request.getParameter(PARAM_NAME_ID));
		int employeeId = -1;
		if (id != null) {
			if (Validator.isNumber(id)) {
				employeeId = Integer.parseInt(id);
	
			}
		}
		String firstName = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_FIRSTNAME));
		String lastName = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_LASTNAME));
		String middleName = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_MIDDLETNAME));
		String did = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_DEPARTMENT_ID));
		int departmentId = -1;
		if ((did != null)) {
			if (Validator.isNumber(did)) {
				departmentId = Integer.parseInt(did);
			}
		}		
		
		EmployeeService employeeService = (EmployeeService) ServiceLocator
				.getService(AttributesEnum.EMPLOYEE_SERVICE.toString());
		Employee employee = new Employee(employeeId, departmentId, firstName, lastName, middleName, false);
		System.out.println(employee);
		employeeService.updateEmployee(employee);
		request.setAttribute("saved", true);
		session.setAttribute(AttributesEnum.EMPLOYEE.toString(), employee);
		page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.EMPLOYEE_EDIT_PAGE);
		return page;
	}

}
