package org.artemov.bts.controller.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.artemov.bts.controller.command_facade.AddEmployeeCommand;
import org.artemov.bts.controller.command_facade.AddErrorCommand;
import org.artemov.bts.controller.command_facade.AddStatusCommand;
import org.artemov.bts.controller.command_facade.Command;
import org.artemov.bts.controller.command_facade.DeleteEmployeeCommand;
import org.artemov.bts.controller.command_facade.GetEmployeeCommand;
import org.artemov.bts.controller.command_facade.GetEmployeesCommand;
import org.artemov.bts.controller.command_facade.GetErrorsCommand;
import org.artemov.bts.controller.command_facade.GetStatusesCommand;
import org.artemov.bts.controller.command_facade.NoCommand;
import org.artemov.bts.controller.command_facade.UpdateEmployeeCommand;
import org.artemov.bts.model.manager.AttributesEnum;

public class CommandInvoker {

	private static CommandInvoker instance;
	private HashMap<String, Command> commands;

	private CommandInvoker() {
		commands = new HashMap<String, Command>();
		commands.put(AttributesEnum.GET_EMPLOYEES_LIST.toString(),
				new GetEmployeesCommand());
		commands.put(AttributesEnum.GET_ERRORS_LIST.toString(),
				new GetErrorsCommand());
		commands.put(AttributesEnum.GET_EMPLOYEE.toString(),
				new GetEmployeeCommand());
		commands.put(AttributesEnum.UPDATE_EMPLOYEE.toString(),
				new UpdateEmployeeCommand());
		commands.put(AttributesEnum.ADD_EMPLOYEE.toString(),
				new AddEmployeeCommand());		
		commands.put(AttributesEnum.DELETE_EMPLOYEE.toString(),
				new DeleteEmployeeCommand());		
		commands.put(AttributesEnum.ADD_ERROR.toString(),
				new AddErrorCommand());		
		commands.put(AttributesEnum.ADD_ERROR.toString(),
				new AddErrorCommand());		
		commands.put(AttributesEnum.GET_STATUSES.toString(),
				new GetStatusesCommand());		
		commands.put(AttributesEnum.ADD_STATUS.toString(),
				new AddStatusCommand());		

	}

	public Command getCommand(HttpServletRequest request) {
		String action = request.getParameter("command");
		Command command = commands.get(action);
		if (command == null) {
			command = new NoCommand();
		}
		return command;
	}

	public String invoke(HttpServletRequest request,
			HttpServletResponse response) {
		String page = null;
		Command action = this.getCommand(request);
		try {
			page = action.execute(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}

	public static CommandInvoker getInstance() {
		if (instance == null) {
			instance = new CommandInvoker();
		}
		return instance;
	}
}
