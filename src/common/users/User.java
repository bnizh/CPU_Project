package common.users;


import common.permissions.Permission;

import java.sql.Date;
import java.util.List;

public class User {

	private String name;
	private String id;
	private String email;
	private String mobile;
	private Date birthDate;
	private String password;
	private List<Permission> permissions;

	public User() {}

	public User(String name, String id, String email, String mobile, Date birthDate, String password) {
		this.name = name;
		this.id = id;
		this.email = email;
		this.mobile = mobile;
		this.birthDate = birthDate;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
