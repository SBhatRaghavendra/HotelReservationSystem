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
			e.printStackTrace();
		}
		return true;
	}

	@Override
	@Transactional
	public List<HotelDetails> getHotelList(String location, String checkInDate, String checkOutDate, int roomType) {
		return hotelDetailsDao.getHotelList(location, checkInDate, checkOutDate, roomType);
	}
	
	public Map<HotelDetails, Integer> getCountOfRooms(Set<HotelDetails> hotelDetails) {
		return hotelDetailsDao.getCountOfRooms(hotelDetails);
	}

}
