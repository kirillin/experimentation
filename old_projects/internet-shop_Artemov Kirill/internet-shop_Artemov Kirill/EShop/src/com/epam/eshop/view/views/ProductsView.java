package com.epam.eshop.view.views;

import java.io.Serializable;

/**
 * Class-view for print to JSP information about Products 
 * 
 * @author Kirill Artemov
 *
 */
public class ProductsView implements Serializable {

	private static final long serialVersionUID = 1L;
	private int productId;
	private String category;
	private String productName;
	private float productPrice;
	private int quantity;
	private String description;
	private boolean actual;
	
	public ProductsView() {
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
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
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
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
	 * @param description the description to set
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

}
