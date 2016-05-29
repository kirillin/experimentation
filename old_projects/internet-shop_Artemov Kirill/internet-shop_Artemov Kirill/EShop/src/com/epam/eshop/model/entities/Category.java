package com.epam.eshop.model.entities;

import java.io.Serializable;

/**
 * Class contains fields describe entity Category
 * 
 * @author Kirill Artemov
 *
 */
public class Category extends Entity implements Serializable {


	private static final long serialVersionUID = 1L;
	private String category;

	/**
	 * Default constructor
	 */
	public Category() {
		super();
	}

	/**
	 * @param id
	 */
	public Category(int id) {
		super(id);
	}

	/**
	 * @param categoryname
	 */
	public Category(int id, String category) {
		super(id);
		this.category = category;
	}

	@Override
	public int getId() {
		return super.getId();
	}

	@Override
	public void setId(int id) {
		super.setId(id);
	}

	/**
	 * @return the categoryname
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param categoryname
	 *            the categoryname to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
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
		Category other = (Category) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.getId() + "::" + category;
	}
}
