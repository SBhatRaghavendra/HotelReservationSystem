package com.risk.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.risk.model.HotelDetails;

@Repository
public interface HotelDetailsDao {
	public List<HotelDetails> getHotelList(String location, String checkInDate, String checkOutDate, int roomType);
	public int checkAvailability(int hotelId, String checkInDate, String checkOutDate, int roomType);
}
