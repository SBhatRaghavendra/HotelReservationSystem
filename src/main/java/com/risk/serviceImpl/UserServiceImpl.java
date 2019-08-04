package com.risk.serviceImpl;

import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.dao.UserDao;
import com.risk.model.User;
import com.risk.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	//Saving registration data in database
	@Transactional
	public void add(User user) {
		userDao.add(user);

	}

	//Login validation function
	public boolean checkLogin(String userName, String userPassword) {
		// System.out.println("In Service class...Check Login");
		return userDao.checkLogin(userName, userPassword);
	}

	//Validate Aadhar Number(Aadhar Number is unique or not)
	public boolean validateAdharNum(String userAdharNum) {
		// TODO Auto-generated method stub
		return userDao.validateAdharNum(userAdharNum);
	}

	//Validate User Name(User Name is unique or not)
	public boolean validateuserName(String userName) {
		return userDao.validateuserName(userName);
	}

	//Validate Email Id(Email Id is unique or not)
	public boolean validateuserEmailId(String userEmailId) {
		return userDao.validateuserEmailId(userEmailId);
	}

	//Validate Phone Number(Phone Number is unique or not)
	public boolean validateuserPhoneNum(String userPhoneNum) {
		return userDao.validateuserPhoneNum(userPhoneNum);
	}

	//Fetching User data with particular User Name
	public User getUserDetails(String userName) {
		return userDao.getUserDetails(userName);
	}

	//Service method to encrypt user password. Returns encrypted password.
	public String encryptPassword(String password) throws NoSuchAlgorithmException {
		return userDao.encryptPassword(password);
	}


}