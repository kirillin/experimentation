package com.epam.eshop.controller.services;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.RoleDAO;
import com.epam.eshop.dao.daos.UserDAO;
import com.epam.eshop.dao.factory.MySQLDAOFactory;
import com.epam.eshop.model.entities.Role;
import com.epam.eshop.model.entities.User;
import com.epam.eshop.view.views.UsersView;

public class UserService extends Service {
	
	private static Logger logger = Logger.getLogger(UserService.class);
	
	public UserService() {
		super();
	}

	public UserService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	@Override
	public String getNameService() {
		return super.getNameService();
	}
	
	public void addToBlackList(int id) {
		
	}
	
	public List<UsersView> getUsers() {
		UserDAO userDAO = null;	
		RoleDAO roleDAO = null;
		List<User> users = null;
		List<UsersView> viewUsers = null;
		try {
			userDAO = (UserDAO) dao.getUserDAO();
			users = userDAO.getUsers();
			MySQLDAOFactory.releaseConnection(userDAO.releaseConnection());
			if (users != null) {
				viewUsers = new LinkedList<UsersView>();
				for (Iterator<User> iterator = users.iterator(); 
						iterator.hasNext();) {
					User user = (User) iterator.next();
					roleDAO = dao.getRoleDAO();
					int roleId = user.getRoleId();
					Role role = roleDAO.getRoleById(roleId);
					MySQLDAOFactory.releaseConnection(roleDAO.releaseConnection());
					UsersView userView = new UsersView();
					userView.setId(user.getId());
					userView.setRole(role.getRole());
					userView.setLogin(user.getLogin());
					userView.setEmail(user.getEmail());
					userView.setFirstName(user.getFirstName());
					userView.setLastName(user.getLastName());	
					viewUsers.add(userView);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		if (viewUsers != null) {
			logger.trace("User's view was returned");
		} 
		return viewUsers;
	}
	
	public User getUser(String login) {
		UserDAO userDAO = null;
		User user = null; 
		try {
			userDAO = (UserDAO) dao.getUserDAO();
			user = userDAO.getUser(login);
			MySQLDAOFactory.releaseConnection(userDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User getUser(int userId) {
		UserDAO userDAO = null;
		User user = null; 
		try {
			userDAO = (UserDAO) dao.getUserDAO();
			user = userDAO.getUser(userId);
			MySQLDAOFactory.releaseConnection(userDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}	
	
	public boolean updateUser(String login, String password, String email,
			String firstName, String lastName) {
		UserDAO userDAO = null;
		boolean success = false;
		try {
			userDAO = (UserDAO) dao.getUserDAO();
			success = userDAO.updateUser(login, password, email, firstName, lastName);
			MySQLDAOFactory.releaseConnection(userDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public boolean isUser(String login) {
		UserDAO userDAO = null;
		boolean success = false;
		try {
			userDAO = (UserDAO) dao.getUserDAO();
			success = userDAO.isUser(login);
			MySQLDAOFactory.releaseConnection(userDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public boolean isAdmin(String login) {
		boolean isAdmin = false;
		UserDAO userDAO = null;
		User user = null;
		try {
			userDAO = dao.getUserDAO();
			user = userDAO.getUser(login);
			MySQLDAOFactory.releaseConnection(userDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user.getRoleId() == 1) {
			isAdmin = true;
			logger.info("Admin enter!");
		} else {
			isAdmin = false;
		}
		return isAdmin;
	}
}
