package com.epam.eshop.model.entities;

import java.io.Serializable;

public class Shipping extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int shippingnethodId;
	private int cityId;
	private String adress;
	private String zip;
	private String phone;

	public Shipping() {
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
	 * @return the shippingnethodId
	 */
	public int getShippingnethodId() {
		return shippingnethodId;
	}

	/**
	 * @param shippingnethodId the shippingnethodId to set
	 */
	public void setShippingnethodId(int shippingnethodId) {
		this.shippingnethodId = shippingnethodId;
	}

	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + cityId;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + shippingnethodId;
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
		Shipping other = (Shipping) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (cityId != other.cityId)
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (shippingnethodId != other.shippingnethodId)
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}
	
}
