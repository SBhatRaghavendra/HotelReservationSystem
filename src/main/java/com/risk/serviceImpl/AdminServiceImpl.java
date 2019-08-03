package com.risk.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.dao.AdminDao;
import com.risk.model.Hotel;
import com.risk.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;

	@Override
	@Transactional
	public void addHotel(Hotel hotel,int OneBeddedRoomNum,int TypeOneprice,int TwoBeddedRoomNum,int TypeTwoprice) {
		adminDao.addHotel(hotel,OneBeddedRoomNum,TypeOneprice,TwoBeddedRoomNum,TypeTwoprice);
		
	}

	@Override
	@Transactional
	public void editHotel(Hotel hotel) {
		adminDao.editHotel(hotel);
		
	}

	@Override
	@Transactional
	public void deleteHotelById(Integer hotelId) {
		adminDao.deleteHotelById(hotelId);
	}


	@Override
	@Transactional
	public List<Hotel> getAllHotel() {
		return adminDao.getAllHotel();
		
	}

	
}
