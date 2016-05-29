package com.epam.eshop.model.entities;

import java.io.Serializable;

/**
 * Class contains fields describe entity User
 * 
 * @author Kirill Artemov
 * 
 */
public class User extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	private int roleId;
	private String login;
	private String password;
	private String email;
	private String firstName;
	private String lastName;

	/**
	 * Default constructor
	 */
	public User() {
		this.roleId = 1;
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @see {@link Entity#}
	 * @param login
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param roleId
	 */
	public User(int id, int roleId, String login, String password,
			String email, String firstName, String lastName) {
		super(id);
		this.roleId = roleId;
		this.login = login;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
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
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + roleId;
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roleId != other.roleId)
			return false;
		return true;
	}

	/**
	 * toString() in special format for further use.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString() + "::" + roleId + "::" + login + "::"
				+ password + "::" + firstName + "::" + lastName + "::" + email;
	}

	/**
	 * The method that i wanted to use in one place in this program but not
	 * used.
	 */
	@Override
	public Object clone() {
		User clone = null;
		clone = (User) super.clone();
		clone.roleId = (int) this.roleId;
		clone.login = (String) this.login;
		clone.password = (String) this.password;
		clone.email = (String) this.email;
		clone.firstName = (String) this.firstName;
		clone.lastName = (String) this.lastName;
		return clone;
	}

}
