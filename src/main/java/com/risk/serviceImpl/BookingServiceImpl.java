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

	@Override
	@Transactional
	public void saveBookings(Bookings bookingDetails) {
		bookingDao.saveBookings(bookingDetails);

	}

}