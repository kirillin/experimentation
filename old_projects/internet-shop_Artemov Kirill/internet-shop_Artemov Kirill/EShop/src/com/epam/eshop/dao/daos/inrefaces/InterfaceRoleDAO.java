package com.epam.eshop.dao.daos.inrefaces;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.eshop.model.entities.Role;

public interface InterfaceRoleDAO {

	Role getRoleById(int id) throws SQLException;

	Connection releaseConnection();
}
