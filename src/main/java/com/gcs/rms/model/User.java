package com.gcs.rms.model;

// Generated May 10, 2017 9:05:26 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", schema = "dbo", catalog = "demo")
public class User implements java.io.Serializable {

	private int userId;
	private String userName;
	private String password;
	private Boolean state;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public User() {
	}

	public User(int userId) {
		this.userId = userId;
	}

	public User(int userId, String userName, String password, Boolean state,
			Set<UserRole> userRoles) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.state = state;
		this.userRoles = userRoles;
	}

	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "user_name", length = 100)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "state")
	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}