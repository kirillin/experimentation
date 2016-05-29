package org.artemov.bts.model.dao.daos.entity_dao_implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_interface.DepartmentDAO;
import org.artemov.bts.model.entities.Department;
import org.artemov.bts.model.manager.DBQueryManager;

public class DepartmentDAOImpl implements DepartmentDAO {

	private Connection connection;

	public DepartmentDAOImpl() {
		super();
	}

	public DepartmentDAOImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public Connection releaseConnection() {
		return this.connection;
	}

	@Override
	public List<Department> getDepartmentsList() {
		List<Department> departments = new ArrayList<Department>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_DEPARTMENTS_LIST));
			rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Department department = new Department();
					department.setId(rs.getInt(1));
					department.setDepartmentName(rs.getString(2));
					departments.add(department);
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
		return departments;
	}

	@Override
	public Department getDepartment(int id) {
		Department department = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_DEPARTMENT_BY_ID));
			ps.setInt(1, id);
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				if (rs.next()) {
					department = new Department();
					department.setId(id);
					department.setDepartmentName(rs.getString(2));
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
		return department;
	}
}
