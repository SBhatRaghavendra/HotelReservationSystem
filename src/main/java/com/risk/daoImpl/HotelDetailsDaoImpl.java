package com.risk.daoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.util.HashMap;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risk.dao.HotelDetailsDao;
import com.risk.model.Hotel;
import com.risk.model.HotelDetails;
import com.risk.model.Rooms;

@Repository
public class HotelDetailsDaoImpl implements HotelDetailsDao {

	private List<HotelDetails> hotelList = new ArrayList<HotelDetails>();

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * This method returns a list of hotels available for booking by taking in the
	 * parameters location, checkInDate, checkOutDate and roomType
	 */
	@Override
	public List<HotelDetails> getHotelList(String location, String checkInDate, String checkOutDate, int roomType) {
		List<Object[]> hotels = sessionFactory.getCurrentSession().createSQLQuery(
				"select min(roomPrice), hotelName, hotelImgUrl from hotel, rooms where hotel.hotelId = rooms.hotelId and roomType = "
						+ roomType + " and hotelArea = '" + location + "'")
				.list();
		
		for(Object[] hotel: hotels) {
			hotelList.add((new HotelDetails(hotel[1].toString(), hotel[2].toString(), (Integer)hotel[0])));
		}

		return hotelList;
	}

	public Map<HotelDetails, Integer> getCountOfRooms(Set<HotelDetails> hotelDetails) {
		Map<HotelDetails, Integer> countOfRooms = new HashMap<HotelDetails, Integer>();

		for (HotelDetails hotel1 : hotelList) {
			for (HotelDetails hotel2 : hotelList) {
				if (!(hotel1.equals(hotel2))) {
					if (countOfRooms.get(hotel1) == null && countOfRooms.get(hotel2) == null) {
						countOfRooms.put(hotel1, 1);
					} else if (hotel1.toString().equals(hotel2.toString())) {
						if (countOfRooms.get(hotel1) != null) {
							int count = countOfRooms.get(hotel1);
							countOfRooms.put(hotel1, count + 1);
						}
					}
				}
			}
		}

		return countOfRooms;
	}

}
