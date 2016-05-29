package org.artemov.bts.model.services;

import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_implement.DepartmentDAOImpl;
import org.artemov.bts.model.dao.factory.MySQLDAOFactory;
import org.artemov.bts.model.entities.Department;

public class DepartmentService extends Service {

	public DepartmentService() {
		super();
	}

	public DepartmentService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	@Override
	public String getNameService() {
		return super.getNameService();
	}

	public List<Department> getDepartmentsList() {
		DepartmentDAOImpl departmentDAOImpl = null;
		List<Department> departments = null;
		departmentDAOImpl = dao.getDepartmentDAOImpl();
		departments = departmentDAOImpl.getDepartmentsList();
		MySQLDAOFactory.releaseConnection(departmentDAOImpl.releaseConnection());
		return departments;
	}

	public Department getDepartment(int id) {
		DepartmentDAOImpl departmentDAOImpl = null;
		Department department = null;
		departmentDAOImpl = dao.getDepartmentDAOImpl();
		department = departmentDAOImpl.getDepartment(id);
		MySQLDAOFactory.releaseConnection(departmentDAOImpl.releaseConnection());
		return department;
	}

}
