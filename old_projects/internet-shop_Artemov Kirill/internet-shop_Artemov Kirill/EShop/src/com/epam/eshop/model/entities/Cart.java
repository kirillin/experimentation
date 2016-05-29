package com.epam.eshop.model.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * The class contains IDs of each product added to cart and count of orders.
 * 
 * @author Kirill Artemov
 * 
 */
public class Cart implements Serializable {

	/**
	 * Serial id for serialization for using it in cookie
	 */
	private static final long serialVersionUID = -5183009880933031923L;
	private static Cart instance;
	private HashMap<Integer, Integer> items;
	private Float totalPrice;

	/**
	 * Default constructor with initialization fields.
	 */
	private Cart() {
		totalPrice = 0F;
		items = new HashMap<Integer, Integer>();
	}

	/**
	 * Returns total price in cart.
	 * 
	 * @return totalPrice
	 */
	public Float getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Sets total price to cart.
	 * 
	 * @param totalPrice
	 */
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Returns Cart from session if exists, else create new.
	 * 
	 * @param session
	 *            contains Cart object
	 * @return instance of cart
	 */
	public static Cart getInstance(HttpSession session) {
		instance = (Cart) session.getAttribute("cart");
		if (instance == null) {
			instance = new Cart();
			session.setAttribute("cart", instance);
		}
		return instance;
	}

	/**
	 * The method for adding products to cart. Cart consist of HashMap(id,
	 * count). Integer count - in's counter selected product by user in cart.
	 * 
	 * @param id
	 */
	public synchronized void addItem(String sId) {
		int id = Integer.parseInt(sId);
		// Add product to cart and if it exist then replace count +1;
		int count = 1;
		if (items.containsKey(id)) {
			count = items.get(id);
			count += 1;
			items.put(id, count);
		} else {
			items.put(id, count);
		}
	}

	public synchronized void delItem(int id, float price) {
		if (items.containsKey(id)) {
			this.totalPrice -= price;
			items.remove(id);
		}
	}

	public synchronized void clearCart() {
		items.clear();
		this.totalPrice = 0F;
	}

	@SuppressWarnings("rawtypes")
	public int getCount() {
		int count = 0;
		Iterator it = items.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			count += (int) pairs.getValue();
		}
		return count;
	}

	/**
	 * Returns pairs id-count for each added product.
	 * 
	 * @return
	 */
	public HashMap<Integer, Integer> getHashMap() {
		return new HashMap<Integer, Integer>(items);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result
				+ ((totalPrice == null) ? 0 : totalPrice.hashCode());
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
		Cart other = (Cart) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return items.toString();
	}
}