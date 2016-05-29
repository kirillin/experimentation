package org.artemov.bts.model.dao.daos.entity_dao_interface;

import java.sql.Connection;
import java.util.List;

import org.artemov.bts.model.entities.Employee;

public interface EmployeeDAO {

	public Connection releaseConnection();

	public Employee getEmployee(int id);

	public void addEmployee(Employee employee);

	public void deleteEmployee(int id);

	public List<Employee> getEmployeesList();

	public void updateEmployee(Employee employee);

}
