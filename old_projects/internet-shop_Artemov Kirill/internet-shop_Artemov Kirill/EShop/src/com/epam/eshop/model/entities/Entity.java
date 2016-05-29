package com.epam.eshop.model.entities;

import java.io.Serializable;

/**
 * Parent class for other entities. It contains one field - id.
 * 
 * @author Kirill Artemov
 * 
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Id field for data base using.
	 */
	private int id;

	/**
	 * Default constructor
	 */
	public Entity() {
	}

	/**
	 * Constructor with parameters
	 * 
	 * @param id
	 */
	public Entity(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Simple hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/**
	 * no comments
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * toString() in special format for further use.
	 */
	@Override
	public String toString() {
		return String.valueOf(id);
	}

	@Override
	protected Object clone() {
		Entity clone = null;
		try {
			clone = (Entity) super.clone();
			clone.id = (int) this.id;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone();
	}

}
