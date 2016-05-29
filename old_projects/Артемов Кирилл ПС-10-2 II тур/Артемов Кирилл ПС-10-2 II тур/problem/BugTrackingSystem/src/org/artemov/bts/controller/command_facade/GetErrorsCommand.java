package org.artemov.bts.controller.command_facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.artemov.bts.model.entities.Employee;
import org.artemov.bts.model.entities.Error;
import org.artemov.bts.model.entities.ErrorLevel;
import org.artemov.bts.model.entities.Project;
import org.artemov.bts.model.manager.AttributesEnum;
import org.artemov.bts.model.manager.ConfigurationManager;
import org.artemov.bts.model.service_locator.ServiceLocator;
import org.artemov.bts.model.services.EmployeeService;
import org.artemov.bts.model.services.ErrorLevelService;
import org.artemov.bts.model.services.ErrorService;
import org.artemov.bts.model.services.ProjectService;
import org.artemov.bts.view.entities.ErrorView;

public class GetErrorsCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		ErrorService errorService = (ErrorService) ServiceLocator.getService(AttributesEnum.ERROR_SERVICE.toString());
		ErrorLevelService errorLevelService = (ErrorLevelService) ServiceLocator.getService(AttributesEnum.ERROR_LEVEL_SERVICE.toString());
		ProjectService projectService = (ProjectService) ServiceLocator.getService(AttributesEnum.PROJECT_SERVICE.toString());
		EmployeeService employeeService = (EmployeeService) ServiceLocator.getService(AttributesEnum.EMPLOYEE_SERVICE.toString());
		
		List<Error> errors = errorService.getErrorsList();
		List<ErrorView> errorViews = new ArrayList<ErrorView>();
		for (Iterator iterator = errors.iterator(); iterator.hasNext();) {
			Error error = (Error) iterator.next();
			ErrorView errorView = new ErrorView();
			errorView.setErrorId(error.getId());
			ErrorLevel errorLevel = errorLevelService.getErrorLevel(error.getErrorLevelId());
			errorView.setErrorLevelName(errorLevel.getLevelName());
			Project project = projectService.getProject(error.getProjectId());
			errorView.setProjectName(project.getProjectName());
			Employee employee = employeeService.getEmployee(error.getEmployeeId());
			errorView.setEmployeeName(employee.getFirstName() +" "+ employee.getLastName());
			errorView.setRegisterDate(error.getRegisterDate());
			errorView.setDescriprion(error.getDescription());
			errorViews.add(errorView);	
			System.out.println(errorView);
		}
		
		List<Employee> employees = employeeService.getEmployeesList();
		List<ErrorLevel> errorLevels = errorLevelService.getErrorLevelsList();
		List<Project> projects = projectService.getProjectssList();
		
		
		if (errors != null) {
			session.setAttribute(AttributesEnum.EMPLOYEES_LIST.toString(), employees);
			session.setAttribute(AttributesEnum.ERROR_LEVELS.toString(), errorLevels);
			session.setAttribute(AttributesEnum.PROJECTS.toString(), projects);
			session.setAttribute(AttributesEnum.ERRORS.toString(), errorViews);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERRORS_PAGE);
		} else {
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.OOPS_PAGE);
		}
		return page;
	}

}
