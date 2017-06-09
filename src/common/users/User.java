package common.users;


import common.permissions.Permission;

import java.sql.Date;
import java.util.List;

public class User {

	private String name;
	private String surName;
	private String id;
	private String email;
	private String mobile;
	private Date birthDate;
	private String userName;
	private String password;
	private List<Permission> permissions;

	public User(String name, String surName, String id, String email, String mobile, Date birthDate, String userName, String password, List<Permission> permissions) {
		this.name = name;
		this.surName = surName;
		this.id = id;
		this.email = email;
		this.mobile = mobile;
		this.birthDate = birthDate;
		this.userName = userName;
		this.password = password;
		this.permissions = permissions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
