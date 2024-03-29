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

	//This method is used to save the Booking Details to the database. It returns the bookingId.
	@Override
	public int saveBookings(Bookings bookingDetails) {
	return (int) session.getCurrentSession().save(bookingDetails);
	}

}
