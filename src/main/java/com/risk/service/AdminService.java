package com.risk.service;

import java.util.List;

import com.risk.model.Hotel;

public interface AdminService {
	public void addHotel(Hotel hotel,int OneBeddedRoomNum,int TypeOneprice,int TwoBeddedRoomNum,int TypeTwoprice);
	public void editHotel(Hotel hotel);
	public void deleteHotelById(Integer hotelId);
	public List<Hotel> getAllHotel();	

}
