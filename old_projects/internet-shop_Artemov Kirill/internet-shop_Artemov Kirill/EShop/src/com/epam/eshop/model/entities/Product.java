package com.epam.eshop.model.entities;

import java.io.Serializable;

/**
 * Class contains fields describe entity Product
 * 
 * @author Kirill Artemov
 * 
 */
public class Product extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int categoryId;
	private String productName;
	private float productPrice;
	private int quantity;
	private String description;
	private boolean actual;

	/**
	 * Default constructor
	 */
	public Product() {
	}

	/**
	 * Constructor with parameters
	 * 
	 * @param categoryId
	 * @param productName
	 * @param productPrice
	 * @param unitsQuantity
	 * @param description
	 */
	public Product(int id, int categoryId, String productName,
			float productPrice, int quantity, String description) {
		super(id);
		this.categoryId = categoryId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
		this.description = description;
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
	 * @return the category
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productPrice
	 */
	public float getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice
	 *            the productPrice to set
	 */
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the unitsQuantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param unitsQuantity
	 *            the unitsQuantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the actual
	 */
	public boolean isActual() {
		return actual;
	}

	/**
	 * @param actual the actual to set
	 */
	public void setActual(boolean actual) {
		this.actual = actual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + categoryId;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + Float.floatToIntBits(productPrice);
		result = prime * result + quantity;
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
		Product other = (Product) obj;
		if (categoryId != other.categoryId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (Float.floatToIntBits(productPrice) != Float
				.floatToIntBits(other.productPrice))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	/**
	 * toString() in special format for further use.
	 */
	@Override
	public String toString() {
		return super.toString() + "::" + categoryId + "::" + productName + "::"
				+ productPrice + "::" + quantity + "::" + description;
	}
}
