package org.artemov.bts.model.dao.daos.entity_dao_interface;

import java.sql.Connection;
import java.util.List;

import org.artemov.bts.model.entities.Project;

public interface ProjectDAO {
	
	public Connection releaseConnection();
	
	public List<Project> getProjectsList();

	public Project getProject(int id);
}
