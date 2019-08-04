package com.risk.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.risk.model.User;

@Service
public interface UserService 
{
	//Saving registration data in database
	 public void add(User user);
	 
	//Login validation function
	 public boolean checkLogin(String userName, String userPassword);
	 
	//Validate Aadhar Number(Aadhar Number is unique or not)
	 public boolean validateAdharNum(String userAdharNum);
	 
	//Validate User Name(User Name is unique or not)
	 public boolean validateuserName(String userName);
	 
	//Validate Email Id(Email Id is unique or not)
	 public boolean validateuserEmailId(String userEmailId); 
	 
	//Validate Phone Number(Phone Number is unique or not)
	 public boolean validateuserPhoneNum(String userPhoneNum); 
	 
	//Fetching User data with particular User Name
	 public User getUserDetails(String userName);
	 
	public String encryptPassword(String password) throws NoSuchAlgorithmException;

}
