package com.risk.daoImpl;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risk.dao.UserDao;
import com.risk.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	// Saving registration data in database
	public void add(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	// Login validation function
	public boolean checkLogin(String userName, String userPassword) {
		Session session = sessionFactory.openSession();
		boolean userFound = false;
		
		String SQL_QUERY = " from User as o where o.userName= '" + userName + "'";
		Query query = (Query) session.createQuery(SQL_QUERY);
		List list = ((org.hibernate.Query) query).list();

		String encryptedPassword = ((User)list.get(0)).getUserPassword();
		
		if ((list != null) && (list.size() > 0) && BCrypt.checkpw(userPassword, encryptedPassword)) {
			userFound = true;
		}

		session.close();
		return userFound;
	}

	public String encryptPassword(String password) throws NoSuchAlgorithmException {
		String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
		return generatedSecuredPasswordHash;
	}

	// Validate Aadhar Number(Aadhar Number is unique or not)
	public boolean validateAdharNum(String userAdharNum) {
		Session session = sessionFactory.openSession();
		boolean AdharNumfound = false;
		String SQL_QUERY = " from User as o where o.userAdharNum= '" + userAdharNum + "'";
		Query query = (Query) session.createQuery(SQL_QUERY);
		List list = ((org.hibernate.Query) query).list();

		if ((list != null) && (list.size() > 0)) {
			AdharNumfound = true;
		}
		session.close();
		return AdharNumfound;
	}

	// Validate User Name(User Name is unique or not)
	public boolean validateuserName(String userName) {
		Session session = sessionFactory.openSession();
		boolean UserNumfound = false;
		String SQL_QUERY = " from User as o where o.userName= '" + userName + "'";
		Query query = (Query) session.createQuery(SQL_QUERY);
		List list = ((org.hibernate.Query) query).list();

		if ((list != null) && (list.size() > 0)) {
			UserNumfound = true;
		}
		session.close();
		return UserNumfound;
	}

	// Validate Email Id(Email Id is unique or not)
	public boolean validateuserEmailId(String userEmailId) {
		Session session = sessionFactory.openSession();
		boolean userEmailfound = false;
		String SQL_QUERY = " from User as o where o.userEmailId= '" + userEmailId + "'";
		Query query = (Query) session.createQuery(SQL_QUERY);
		List list = ((org.hibernate.Query) query).list();

		if ((list != null) && (list.size() > 0)) {
			userEmailfound = true;
		}
		session.close();
		return userEmailfound;
	}

	// Validate Phone Number(Phone Number is unique or not)
	public boolean validateuserPhoneNum(String userPhoneNum) {
		Session session = sessionFactory.openSession();
		boolean UserPhoneNumfound = false;
		// using Hibernate Query Language
		String SQL_QUERY = " from User as o where o.userPhoneNum= '" + userPhoneNum + "'";
		Query query = (Query) session.createQuery(SQL_QUERY);
		List list = ((org.hibernate.Query) query).list();
		if ((list != null) && (list.size() > 0)) {
			UserPhoneNumfound = true;
		}
		session.close();
		return UserPhoneNumfound;
	}

	// Fetching User data with particular User Name
	public User getUserDetails(String userName) {
		Session session = sessionFactory.openSession();
		// using Hibernate Query Language
		String SQL_QUERY = " from User as o where o.userName= '" + userName + "'";
		Query query = (Query) session.createQuery(SQL_QUERY);
		List list = ((org.hibernate.Query) query).list();
		User user = (User) list.get(0);
		session.close();
		return user;

	}
}
