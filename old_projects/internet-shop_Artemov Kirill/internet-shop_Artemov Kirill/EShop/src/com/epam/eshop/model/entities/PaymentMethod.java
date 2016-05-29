package com.epam.eshop.model.entities;

import java.io.Serializable;

public class PaymentMethod extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String paymentMethod;
	private String description;
	
	public PaymentMethod() {
	}

	/**
	 * @param id
	 */
	public PaymentMethod(int id) {
		super(id);
	}

	/**
	 * @param payment_method
	 * @param description
	 */
	public PaymentMethod(String paymentMethod, String description) {
		super();
		this.paymentMethod = paymentMethod;
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
	 * @return the payment_method
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param payment_method the payment_method to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
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
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((paymentMethod == null) ? 0 : paymentMethod.hashCode());
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
		PaymentMethod other = (PaymentMethod) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (paymentMethod == null) {
			if (other.paymentMethod != null)
				return false;
		} else if (!paymentMethod.equals(other.paymentMethod))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PaymentMethod [payment_method=" + paymentMethod
				+ ", description=" + description + "]";
	}
}
