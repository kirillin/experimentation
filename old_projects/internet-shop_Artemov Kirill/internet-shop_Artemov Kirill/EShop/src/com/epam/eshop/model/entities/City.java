package com.epam.eshop.model.entities;

import java.io.Serializable;

/**
 * Class contains fields describe entity City
 * @author Kirill Artemov
 *
 */
public class City extends Entity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String city;
	/**
	 * Default constructor
	 */
	public City() {
		super();
	}

	/**
	 * @param id
	 */
	public City(int id) {
		super(id);
	}
	/**
	 * 
	 * @param id
	 * @param city
	 */
	public City(int id, String city) {
		super(id);
		this.city = city;
	}

	@Override
	public void setId(int id) {
		super.setId(id);
	}
	@Override
	public int getId() {
		return super.getId();
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return city;
	}
	
}
