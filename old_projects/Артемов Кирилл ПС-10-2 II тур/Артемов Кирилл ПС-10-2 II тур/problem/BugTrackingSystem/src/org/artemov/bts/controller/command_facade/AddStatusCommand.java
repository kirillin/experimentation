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
import org.artemov.bts.model.entities.Status;
import org.artemov.bts.model.manager.AttributesEnum;
import org.artemov.bts.model.manager.ConfigurationManager;
import org.artemov.bts.model.service_locator.ServiceLocator;
import org.artemov.bts.model.services.StatusService;

public class AddStatusCommand implements Command {

	private static final String PARAM_NAME_ES_ID = "errorsStatusId";
	private static final String PARAM_NAME_EMPLOYEE_ID = "employeeId";
	private static final String PARAM_NAME_ERROR_ID = "errorId";

	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		String serrorsStatusId = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_ES_ID));
		String semployeeId = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_EMPLOYEE_ID));
		String serrorId = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_ERROR_ID));
		int errorsStatusId = -1;
		if ((serrorsStatusId != null)) {
			if (Validator.isNumber(serrorsStatusId)) {
				errorsStatusId = Integer.parseInt(serrorsStatusId);
			}
		}
		int employeeId = -1;
		if ((semployeeId != null)) {
			if (Validator.isNumber(semployeeId)) {
				employeeId = Integer.parseInt(semployeeId);
			}
		}	
		int errorId = -1;
		if ((serrorId != null)) {
			if (Validator.isNumber(serrorId)) {
				errorId = Integer.parseInt(serrorId);
			}
		}	
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		Timestamp rDate = new Timestamp(calendar.getTimeInMillis());
		Status status = new Status(0, errorId, errorsStatusId, employeeId, rDate);
		StatusService statusService = (StatusService) ServiceLocator.getService(AttributesEnum.STATUS_SERVICE.toString());
		statusService.addStatus(status);
		System.out.println(status);
		request.setAttribute("added", true);
		page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.GET_SATATUSES_PAGE);
		return page;
	}

	
}
