package org.artemov.bts.model.services;

import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_implement.ProjectDAOImpl;
import org.artemov.bts.model.dao.factory.MySQLDAOFactory;
import org.artemov.bts.model.entities.Project;

public class ProjectService extends Service {

	public ProjectService() {
		super();
	}

	public ProjectService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	@Override
	public String getNameService() {
		return super.getNameService();
	}

	public List<Project> getProjectssList() {
		ProjectDAOImpl projectDAOImpl = null;
		List<Project> projects = null;
		projectDAOImpl = dao.getProjectDAOImpl();
		projects = projectDAOImpl.getProjectsList();
		MySQLDAOFactory.releaseConnection(projectDAOImpl
					.releaseConnection());
		return projects;
	}
	
	public Project getProject(int id) {
		ProjectDAOImpl projectDAOImpl = null;
		Project project = null;
		projectDAOImpl = dao.getProjectDAOImpl();
		project = projectDAOImpl.getProject(id);
		MySQLDAOFactory.releaseConnection(projectDAOImpl
					.releaseConnection());
		return project;
	}

}
