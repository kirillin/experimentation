package org.artemov.bts.model.dao.daos.entity_dao_implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_interface.EmployeeDAO;
import org.artemov.bts.model.entities.Employee;
import org.artemov.bts.model.manager.DBQueryManager;

public class EmployeeDAOImpl implements EmployeeDAO {

	private Connection connection;

	public EmployeeDAOImpl() {
		super();
	}

	public EmployeeDAOImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public Connection releaseConnection() {
		return this.connection;
	}

	@Override
	public Employee getEmployee(int id) {
		Employee employee = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_EMPLOYEE_BY_ID));
			ps.setInt(1, id);
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				if (rs.next()) {
					employee = new Employee();
					employee.setId(rs.getInt(1));
					employee.setDepartmentId(rs.getInt(2));
					employee.setFirstName(rs.getString(3));
					employee.setLastName(rs.getString(4));
					employee.setMiddleName(rs.getString(5));
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
				}
		}
		return employee;
	}

	@Override
	public void addEmployee(Employee employee) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.ADD_EMPLOYEE));
			ps.setInt(1, employee.getDepartmentId());
			ps.setString(2, employee.getFirstName());
			ps.setString(3, employee.getLastName());
			ps.setString(4, employee.getMiddleName());
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());				}
		}
	}

	@Override
	public void deleteEmployee(int id) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.DELETE_EMPLOYEE_BY_ID));
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());				}
		}
	}

	@Override
	public void updateEmployee(Employee employee) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.UPDATE_EMPLOYEE_BY_ID));
			ps.setInt(1, employee.getDepartmentId());
			ps.setString(2, employee.getFirstName());
			ps.setString(3, employee.getLastName());
			ps.setString(4, employee.getMiddleName());
			ps.setInt(5, employee.getId());			
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());				}
		}
	}

	@Override
	public List<Employee> getEmployeesList() {
		List<Employee> employees = new ArrayList<Employee>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_EMPLOYEES_LIST));
			rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Employee employee = new Employee();
					employee.setId(rs.getInt(1));
					employee.setDepartmentId(rs.getInt(2));
					employee.setFirstName(rs.getString(3));
					employee.setLastName(rs.getString(4));
					employee.setMiddleName(rs.getString(5));
					employees.add(employee);
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
				}
		}
		return employees;
	}

}
