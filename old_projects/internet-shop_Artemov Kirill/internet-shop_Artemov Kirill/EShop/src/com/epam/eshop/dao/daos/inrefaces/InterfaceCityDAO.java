package com.epam.eshop.dao.daos.inrefaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.eshop.model.entities.City;

public interface InterfaceCityDAO {

	Connection releaseConnection();

	List<City> getCities() throws SQLException;

	City getCityById(int id) throws SQLException;

}
