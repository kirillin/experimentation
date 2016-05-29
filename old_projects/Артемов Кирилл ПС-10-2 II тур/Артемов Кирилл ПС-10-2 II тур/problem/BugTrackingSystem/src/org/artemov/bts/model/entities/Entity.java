package org.artemov.bts.model.entities;

import java.io.Serializable;

/**
 * 
 * Abstract entity for inheritance from it
 * 
 * Contains only field Id 
 * 
 * @author admin
 *
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;

	public Entity() {
	}

	public Entity(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

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
