package org.artemov.bts.controller.command_facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.artemov.bts.libs.Validator;
import org.artemov.bts.model.entities.Employee;
import org.artemov.bts.model.entities.Error;
import org.artemov.bts.model.entities.ErrorsStatus;
import org.artemov.bts.model.entities.Status;
import org.artemov.bts.model.manager.AttributesEnum;
import org.artemov.bts.model.manager.ConfigurationManager;
import org.artemov.bts.model.service_locator.ServiceLocator;
import org.artemov.bts.model.services.EmployeeService;
import org.artemov.bts.model.services.ErrorService;
import org.artemov.bts.model.services.ErrorsStatusService;
import org.artemov.bts.model.services.StatusService;
import org.artemov.bts.view.entities.StatusView;

public class GetStatusesCommand implements Command {

	private static final String PARAM_NAME_ERROR_ID = "errorId";

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		String serrorId = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_ERROR_ID));
		int errorId = -1;
		if (serrorId != null) {
			if (Validator.isNumber(serrorId)) {
				errorId = Integer.parseInt(serrorId);
			}
		}

		StatusService service = (StatusService) ServiceLocator
				.getService(AttributesEnum.STATUS_SERVICE.toString());
		List<Status> statuses = service.getStatusesList();
		ErrorService errorService = (ErrorService) ServiceLocator
				.getService(AttributesEnum.ERROR_SERVICE.toString());
		Error error = errorService.getError(errorId);
		ErrorsStatusService errorsStatusService = (ErrorsStatusService) ServiceLocator
				.getService(AttributesEnum.ERRORS_STATUS_SERVICE.toString());
		EmployeeService employeeService = (EmployeeService) ServiceLocator
				.getService(AttributesEnum.EMPLOYEE_SERVICE.toString());
		List<StatusView> statusViews = new ArrayList<StatusView>();
		for (Iterator<Status> iterator = statuses.iterator(); iterator
				.hasNext();) {
			Status status = (Status) iterator.next();
			if (status.getErrorId() == error.getId()) {
				StatusView statusView = new StatusView();
				statusView.setErrorId(status.getErrorId());
				ErrorsStatus errorsStatus = errorsStatusService
						.getErrorsStatus(status.getErrorsStatusId());
				statusView.setErrorsStatus(errorsStatus.getStatusName());
				Employee employee = employeeService.getEmployee(status
						.getEmployeeId());
				statusView.setEmployeeName(employee.getFirstName() + " "
						+ employee.getLastName());
				statusViews.add(statusView);
			}
		}
		System.out.println(statusViews);
		List<ErrorsStatus> errorsStatus = errorsStatusService
				.getErorsStatusesList();
		List<Employee> employees = employeeService.getEmployeesList();
		if (statuses != null) {
			request.setAttribute("errorId", errorId);
			session.setAttribute(AttributesEnum.STAUS_VIEW.toString(),
					statusViews);
			session.setAttribute(AttributesEnum.ERRORS_STATUS.toString(),
					errorsStatus);
			session.setAttribute(AttributesEnum.EMPLOYEES_LIST.toString(),
					employees);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.GET_SATATUSES_PAGE);
		}
		return page;
	}

}
