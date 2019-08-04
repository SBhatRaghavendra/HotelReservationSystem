package com.risk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.risk.validation.ValidPassword;
@Entity
@Table(name="user")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private int userId;
	
	@NotEmpty
	@Size(min=5, max=50)
	@Pattern(regexp="[^0-9]+")
	private String userFullName;
	
	@NotEmpty
	@ValidPassword
	private String userPassword;
	
	@NotEmpty
	@Size(min=5,max=10)
	private String userName;
	
	@Size(min=12,max=12)
	@NotNull
	@Pattern(regexp="[^A-Z]+")
	private String userAdharNum;
	
	@NotEmpty
	@Size(min=10,max=10)
	@Pattern(regexp="[^A-Z]+")
	private String userPhoneNum;
	
	@Email
	@NotEmpty
	private String userEmailId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAdharNum() {
		return userAdharNum;
	}
	public void setUserAdharNum(String userAdharNum) {
		this.userAdharNum = userAdharNum;
	}
	public String getUserPhoneNum() {
		return userPhoneNum;
	}
	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public User(int userId, String userFullName, String userPassword, String userName, String userAdharNum,
			String userPhoneNum, String userEmailId) {
		super();
		this.userId = userId;
		this.userFullName = userFullName;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userAdharNum = userAdharNum;
		this.userPhoneNum = userPhoneNum;
		this.userEmailId = userEmailId;
	}
	public User() {
		super();
	}
	
	
}
