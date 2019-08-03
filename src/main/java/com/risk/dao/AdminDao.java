package com.risk.dao;

import java.util.List;

import com.risk.model.Hotel;

public interface AdminDao {

	// adding hotels
	public void addHotel(Hotel hotel, int OneBeddedRoomNum, int TypeOneprice, int TwoBeddedRoomNum, int TypeTwoprice);

	// editing hotels
	public void editHotel(Hotel hotel);

	// deleting hotels by Id
	public void deleteHotelById(Integer hotelId);

	// To get all hotel details
	public List<Hotel> getAllHotel();

	// To add Single Bed Room
	public void addRoomTypeOne(Hotel hotelObj, int OneBeddedRoomNum, int price);

	// To add Double Bed Room
	public void addRoomTypeTwo(Hotel hotelObj, int TwoBeddedRoomNum, int price);

}
