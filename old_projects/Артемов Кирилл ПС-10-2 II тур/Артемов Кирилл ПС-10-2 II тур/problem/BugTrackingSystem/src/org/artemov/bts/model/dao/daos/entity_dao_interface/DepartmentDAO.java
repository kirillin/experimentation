package org.artemov.bts.model.dao.daos.entity_dao_interface;

import java.sql.Connection;
import java.util.List;

import org.artemov.bts.model.entities.Department;

public interface DepartmentDAO {
	
	public Connection releaseConnection();
	
	public List<Department> getDepartmentsList();

	public Department getDepartment(int id);
}
