package org.artemov.bts.model.dao.daos.entity_dao_implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_interface.ProjectDAO;
import org.artemov.bts.model.entities.Project;
import org.artemov.bts.model.manager.DBQueryManager;

public class ProjectDAOImpl implements ProjectDAO {
	
	private Connection connection;

	public ProjectDAOImpl() {
		super();
	}

	public ProjectDAOImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public Connection releaseConnection() {
		return this.connection;
	}

	@Override
	public List<Project> getProjectsList() {
		List<Project> projects = new ArrayList<Project>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_PROJECTS_LIST));
			rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Project project = new Project();
					project.setId(rs.getInt(1));
					project.setProjectName(rs.getString(2));					
					projects.add(project);
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
		return projects;
	}
	
	@Override
	public Project getProject(int id) {
		Project project = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_PROJECT_BY_ID));
			ps.setInt(1, id);
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				if (rs.next()) {
					project = new Project();
					project.setId(id);
					project.setProjectName(rs.getString(2));
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
		return project;
	}

}
