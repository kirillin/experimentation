package org.artemov.bts.model.services;

import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_implement.EmployeeDAOImpl;
import org.artemov.bts.model.dao.factory.MySQLDAOFactory;
import org.artemov.bts.model.entities.Employee;

public class EmployeeService extends Service {

	public EmployeeService() {
		super();
	}

	public EmployeeService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	@Override
	public String getNameService() {
		return super.getNameService();
	}

	public List<Employee> getEmployeesList() {
		EmployeeDAOImpl employeeDAOImpl = null;
		List<Employee> employees = null;
		employeeDAOImpl = dao.getEmployeeDAOImpl();
		employees = employeeDAOImpl.getEmployeesList();
		MySQLDAOFactory.releaseConnection(employeeDAOImpl
				.releaseConnection());
		return employees;
	}

	public Employee getEmployee(int id) {
		EmployeeDAOImpl employeeDAOImpl = null;
		Employee employee = null;
		employeeDAOImpl = dao.getEmployeeDAOImpl();
		employee = employeeDAOImpl.getEmployee(id);
		MySQLDAOFactory.releaseConnection(employeeDAOImpl
				.releaseConnection());
		return employee;
	}

	
	public void addEmployee(Employee employee) {
		EmployeeDAOImpl employeeDAOImpl = null;
		employeeDAOImpl = dao.getEmployeeDAOImpl();
		employeeDAOImpl.addEmployee(employee);
		MySQLDAOFactory.releaseConnection(employeeDAOImpl
				.releaseConnection());
	}

	public void updateEmployee(Employee employee) {
		EmployeeDAOImpl employeeDAOImpl = null;
		employeeDAOImpl = dao.getEmployeeDAOImpl();
		employeeDAOImpl.updateEmployee(employee);
		MySQLDAOFactory.releaseConnection(employeeDAOImpl
				.releaseConnection());
	}
	
	public void deleteEmployee(int id) {
		EmployeeDAOImpl employeeDAOImpl = null;
		employeeDAOImpl = dao.getEmployeeDAOImpl();
		employeeDAOImpl.deleteEmployee(id);
		MySQLDAOFactory.releaseConnection(employeeDAOImpl
				.releaseConnection());
	}
}