package org.artemov.bts.controller.command_facade;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.artemov.bts.libs.Validator;
import org.artemov.bts.model.entities.Error;
import org.artemov.bts.model.manager.AttributesEnum;
import org.artemov.bts.model.manager.ConfigurationManager;
import org.artemov.bts.model.service_locator.ServiceLocator;
import org.artemov.bts.model.services.ErrorService;


public class AddErrorCommand implements Command {

	private static final String PARAM_NAME_DATE = "registerDate";
	private static final String PARAM_NAME_EMPLOYEE_ID = "employeeId";
	private static final String PARAM_NAME_ERROR_LEVEL_ID = "errorLevelId";
	private static final String PARAM_NAME_PROJECT_ID = "projectId";
	private static final String PARAM_NAME_DESCRIPTION_ID = "description";
	
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		String sregisterDate = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_DATE));
		String semployeeId = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_EMPLOYEE_ID));
		String serrorLevelId = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_ERROR_LEVEL_ID));
		String sprojectId = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_PROJECT_ID));
		String description = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_DESCRIPTION_ID));
		
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		Timestamp rDate = new Timestamp(calendar.getTimeInMillis());
		int employeeId = -1;
		if ((semployeeId != null)) {
			if (Validator.isNumber(semployeeId)) {
				employeeId = Integer.parseInt(semployeeId);
			}
		}
		int errorLevelId = -1;
		if ((serrorLevelId != null)) {
			if (Validator.isNumber(serrorLevelId)) {
				errorLevelId = Integer.parseInt(serrorLevelId);
			}
		}
		int projectId = -1;
		if ((sprojectId != null)) {
			if (Validator.isNumber(sprojectId)) {
				projectId = Integer.parseInt(sprojectId);
			}
		}
		
		Error error = new Error(0,0,errorLevelId,projectId,employeeId,rDate,description);
		ErrorService errorService = (ErrorService) ServiceLocator.getService(AttributesEnum.ERROR_SERVICE.toString());
		
		errorService.addError(error);
		request.setAttribute("added", true);
		page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.GET_ERRORS_PAGE);
		return page;
	}

	
}
