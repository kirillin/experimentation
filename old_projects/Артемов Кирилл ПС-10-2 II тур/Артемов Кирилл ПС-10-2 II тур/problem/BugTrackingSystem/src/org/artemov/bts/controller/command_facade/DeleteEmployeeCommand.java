package org.artemov.bts.controller.command_facade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.artemov.bts.libs.Validator;
import org.artemov.bts.model.manager.AttributesEnum;
import org.artemov.bts.model.manager.ConfigurationManager;
import org.artemov.bts.model.service_locator.ServiceLocator;
import org.artemov.bts.model.services.EmployeeService;

public class DeleteEmployeeCommand implements Command {

	private static final String PARAM_NAME_ID = "employeeId";
	
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
		EmployeeService employeeService = (EmployeeService) ServiceLocator
				.getService(AttributesEnum.EMPLOYEE_SERVICE.toString());
		
		employeeService.deleteEmployee(employeeId);
		request.setAttribute("deleted", true);
		page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.GET_EMPLOYEES_PAGE);
		return page;
	}

}
