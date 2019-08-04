package com.risk.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.dao.HotelDetailsDao;
import com.risk.model.HotelDetails;
import com.risk.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	HotelDetailsDao hotelDetailsDao;

	@Override
	public boolean isValidDates(String checkInDate, String checkOutDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			return sdf.parse(checkInDate).before(sdf.parse(checkOutDate));
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		return true;
	}

	//Service method to retrieve required hotel details such as hotel name, hotel image and room price
	@Override
	@Transactional
	public List<HotelDetails> getHotelList(String location, String checkInDate, String checkOutDate, int roomType) {
		return hotelDetailsDao.getHotelList(location, checkInDate, checkOutDate, roomType);
	}
	
	//Service method to check for availability of rooms in a particular hotel.
	@Transactional
	public int checkAvailability(int hotelId, String checkInDate, String checkOutDate, int roomType) {
		return hotelDetailsDao.checkAvailability(hotelId, checkInDate, checkOutDate, roomType);
	}

}
