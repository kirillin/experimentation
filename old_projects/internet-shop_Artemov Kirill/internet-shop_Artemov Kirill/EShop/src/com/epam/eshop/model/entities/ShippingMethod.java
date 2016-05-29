package com.epam.eshop.model.entities;

import java.io.Serializable;

public class ShippingMethod extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String shippingMethod;
	private float cost;
	private int length;
	private String description;
	
	public ShippingMethod() {
	}

	/**
	 * @param id
	 */
	public ShippingMethod(int id) {
		super(id);
	}

	/**
	 * @param shipping_method
	 * @param cost
	 * @param lenth
	 * @param description
	 */
	public ShippingMethod(String shippingMethod, float cost, int length,
			String description) {
		super();
		this.shippingMethod = shippingMethod;
		this.cost = cost;
		this.length = length;
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
	 * @return the shipping_method
	 */
	public String getShippingMethod() {
		return shippingMethod;
	}

	/**
	 * @param shipping_method the shipping_method to set
	 */
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	/**
	 * @return the cost
	 */
	public float getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}

	/**
	 * @return the lenth
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param lenth the lenth to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Float.floatToIntBits(cost);
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + length;
		result = prime * result
				+ ((shippingMethod == null) ? 0 : shippingMethod.hashCode());
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
		ShippingMethod other = (ShippingMethod) obj;
		if (Float.floatToIntBits(cost) != Float.floatToIntBits(other.cost))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (length != other.length)
			return false;
		if (shippingMethod == null) {
			if (other.shippingMethod != null)
				return false;
		} else if (!shippingMethod.equals(other.shippingMethod))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShippingMethod [shipping_method=" + shippingMethod + ", cost="
				+ cost + ", lenth=" + length + ", description=" + description
				+ "]";
	}
}
