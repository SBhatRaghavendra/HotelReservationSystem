package com.risk.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.risk.dao.BookingDao;
import com.risk.model.Bookings;
import com.risk.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;

	//Service method to save booking details. Returns bookingId to the required controller.
	@Override
	@Transactional
	public int  saveBookings(Bookings bookingDetails) {
		return bookingDao.saveBookings(bookingDetails);

	}

}
