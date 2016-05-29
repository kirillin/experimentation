package org.artemov.bts.controller.command_facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.artemov.bts.model.entities.Department;
import org.artemov.bts.model.entities.Employee;
import org.artemov.bts.model.manager.AttributesEnum;
import org.artemov.bts.model.manager.ConfigurationManager;
import org.artemov.bts.model.service_locator.ServiceLocator;
import org.artemov.bts.model.services.DepartmentService;
import org.artemov.bts.model.services.EmployeeService;
import org.artemov.bts.view.entities.EmployeeView;

public class GetEmployeesCommand implements Command {

	@SuppressWarnings("unused")
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		EmployeeService employeeService = (EmployeeService) ServiceLocator
				.getService(AttributesEnum.EMPLOYEE_SERVICE.toString());
		DepartmentService departmentService = (DepartmentService) ServiceLocator.getService(AttributesEnum.DEPARTMENT_SERVICE.toString());
		List<Department> departments = departmentService.getDepartmentsList();
		List<Employee> employees = employeeService.getEmployeesList();
		List<EmployeeView> employeesView = new ArrayList<EmployeeView>();
		
		for (Iterator<Employee> iterator = employees.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			EmployeeView employeeView = new EmployeeView();
			Department department = departmentService.getDepartment(employee.getDepartmentId());
			employeeView.setDepartment(department.getDepartmentName());
			employeeView.setFirstName(employee.getFirstName());
			employeeView.setLastName(employee.getLastName());
			employeeView.setMiddleName(employee.getMiddleName());
			employeeView.setId(employee.getId());
			employeesView.add(employeeView);
		}

		if (employees != null) {
			session.setAttribute(AttributesEnum.EMPLOYEES.toString(), employeesView);
			session.setAttribute(AttributesEnum.DEPARTMENTS.toString(), departments);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.EMPLOYEES_PAGE);
		} else {
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.OOPS_PAGE);
		}
		return page;
	}

}
