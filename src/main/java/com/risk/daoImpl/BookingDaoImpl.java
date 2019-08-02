package com.risk.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risk.dao.BookingDao;
import com.risk.model.Bookings;

@Repository
public class BookingDaoImpl implements BookingDao {

	@Autowired
	private SessionFactory session;

	@Override
	public void saveBookings(Bookings bookingDetails) {
	session.getCurrentSession().save(bookingDetails);
	}

}
