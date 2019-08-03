package com.risk.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risk.dao.AdminDao;
import com.risk.model.Hotel;
import com.risk.model.Rooms;



@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private SessionFactory session;
	
	private Session sessionref;
	
	
	
	@Override
	public void addHotel(Hotel hotel,int OneBeddedRoomNum,int TypeOneprice,int TwoBeddedRoomNum,int TypeTwoprice) {
		int lastTransactionHotelId=(int) session.getCurrentSession().save(hotel);
		List<Hotel> hotelList = session.getCurrentSession().createQuery("from Hotel where hotelId = " + lastTransactionHotelId).list();
		
		Hotel hotelObj = hotelList.get(0);
		
		addRoomTypeOne(hotelObj,OneBeddedRoomNum,TypeOneprice);
		addRoomTypeTwo(hotelObj,TwoBeddedRoomNum,TypeTwoprice);
	}

	@Override
	public void editHotel(Hotel hotel) {
		session.getCurrentSession().update(hotel);

	}

	@Override
	public void deleteHotelById(Integer hotelId) {
		Hotel hotel=new Hotel();
		hotel.setHotelId(hotelId);
		session.getCurrentSession().delete(hotel);
		

	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Hotel> getAllHotel() {
		return session.getCurrentSession().createQuery("from Hotel").list();
	}

	@Override
	public void addRoomTypeOne(Hotel hotelObj,int OneBeddedRoomNum,int TypeOneprice) {
		while(OneBeddedRoomNum>0)
		{	
			Rooms room = new Rooms(1, TypeOneprice, hotelObj);
			session.getCurrentSession().save(room);
			OneBeddedRoomNum--;
		}
		
	}

	@Override
	public void addRoomTypeTwo(Hotel hotelObj,int TwoBeddedRoomNum,int TypeTwoprice) {
		while(TwoBeddedRoomNum>0)
		{	
			Rooms room = new Rooms(2, TypeTwoprice, hotelObj);
			session.getCurrentSession().save(room);	
			TwoBeddedRoomNum--;
		}
		
	}


}
