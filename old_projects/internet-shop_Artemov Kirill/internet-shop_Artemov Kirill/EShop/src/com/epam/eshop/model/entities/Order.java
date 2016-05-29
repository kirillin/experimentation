package com.epam.eshop.model.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Class contains fields describe entity Order
 * 
 * @author Kirill Artemov
 * 
 */
public class Order extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	private int userId;
	private int paymentMethodId;
	private int shippingId;
	private int statusId;
	private Timestamp orderDate;
	private float totalPrice;

	/**
	 * Default constructor
	 */
	public Order() {
	}

	/**
	 * @param userId
	 * @param paymentMethodId
	 * @param shippingId
	 * @param statusId
	 * @param orderDate
	 * @param totalPrice
	 */
	public Order(int orderId, int userId, int paymentMethodId, int shippingId, int statusId,
			Timestamp orderDate, float totalPrice) {
		super(orderId);
		this.userId = userId;
		this.paymentMethodId = paymentMethodId;
		this.shippingId = shippingId;
		this.statusId = statusId;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
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
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the paymentMethodId
	 */
	public int getPaymentMethodId() {
		return paymentMethodId;
	}

	/**
	 * @param paymentMethodId the paymentMethodId to set
	 */
	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	/**
	 * @return the shippingId
	 */
	public int getShippingId() {
		return shippingId;
	}

	/**
	 * @param shippingId the shippingId to set
	 */
	public void setShippingId(int shippingId) {
		this.shippingId = shippingId;
	}

	/**
	 * @return the statusId
	 */
	public int getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the orderDate
	 */
	public Timestamp getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + paymentMethodId;
		result = prime * result + shippingId;
		result = prime * result + statusId;
		result = prime * result + Float.floatToIntBits(totalPrice);
		result = prime * result + userId;
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
		Order other = (Order) obj;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (paymentMethodId != other.paymentMethodId)
			return false;
		if (shippingId != other.shippingId)
			return false;
		if (statusId != other.statusId)
			return false;
		if (Float.floatToIntBits(totalPrice) != Float
				.floatToIntBits(other.totalPrice))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return userId +"::"+
				paymentMethodId +"::"+
				shippingId +"::"+
				statusId +"::"+
				orderDate +"::"+
				totalPrice;
	}
	
}
