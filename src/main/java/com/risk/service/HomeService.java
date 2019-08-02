package com.risk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.risk.model.HotelDetails;

public interface HomeService {
	public boolean isValidDates(String checkInDate, String checkOutDate);
	public List<HotelDetails> getHotelList(String location, String checkInDate, String checkOutDate, int roomType);
	public int checkAvailability(int hotelId, String checkInDate, String checkOutDate, int roomType);
}
