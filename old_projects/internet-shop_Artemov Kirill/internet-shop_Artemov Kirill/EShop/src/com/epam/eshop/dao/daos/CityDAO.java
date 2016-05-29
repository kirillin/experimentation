package com.epam.eshop.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.inrefaces.InterfaceCityDAO;
import com.epam.eshop.manager.DBQueryManager;
import com.epam.eshop.model.entities.City;

/**
 * Class for manipulation DB and table cities. 
 * 
 * @author Kirill Artemov
 *
 */
public class CityDAO implements InterfaceCityDAO {

	private static Logger logger = Logger.getLogger(CityDAO.class);
	private Connection connection;
	
	public CityDAO() {
		super();		
	}

	/**
	 * @param connection
	 */
	public CityDAO(Connection connection) {
		super();
		this.connection = connection;
	}
	/**
	 * Returns list of cities.
	 */
	@Override
	public List<City> getCities() throws SQLException {
		List<City> cities = new LinkedList<City>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_CITIES));
			ResultSet rs = null;			
			try {
				rs = ps.executeQuery();				
				while (rs.next()) {
					City city = new City();
					city.setId(rs.getInt(1));
					city.setCity(rs.getString(2));
					cities.add(city);
				}
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
		if (!cities.isEmpty()) {
			logger.info("List of cities returns!");
		} else {
			cities = null;
		}
		return cities;
	}
	
	@Override
	public City getCityById(int id) throws SQLException {
		City city = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_CITY_BY_ID));
			ps.setInt(1, id);
			ResultSet rs =  null;
			try {
				rs = ps.executeQuery();
				if (rs.next()) {
					city = new City();
					city.setId(rs.getInt(1));
					city.setCity(rs.getString(2));
				}
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
		if (city != null) {
			logger.info("City by id was returns!");
		}
		return city;
	}	
	/**
	 * The method for release connection
	 */
	@Override
	public Connection releaseConnection() {
		return this.connection;
	}

}
