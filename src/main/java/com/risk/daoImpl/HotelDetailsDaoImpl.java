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

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * This method returns a list of hotels available for booking by taking in the
	 * parameters location, checkInDate, checkOutDate and roomType
	 */
	@Override
	public List<HotelDetails> getHotelList(String location, String checkInDate, String checkOutDate, int roomType) {
			List<Object[]> hotels = sessionFactory.getCurrentSession().createSQLQuery(
					"select distinct roomPrice, hotelName, hotelImgUrl, hotel.hotelId from hotel, rooms where hotel.hotelId = rooms.hotelId and roomType = "
							+ roomType + " and hotelArea = '" + location + "'")
					.list();
			List<HotelDetails> hotelList = new ArrayList<HotelDetails>();
			
			for (Object[] hotel : hotels) {
				hotelList.add((new HotelDetails(hotel[1].toString(), hotel[2].toString(), (Integer) hotel[0], (Integer)hotel[3])));
			}

		return hotelList;
	}

	
	/*
	 * This method returns a boolean value indicating whether the hotel room can be booked or not by taking in the
	 * parameters hotelId, checkInDate, checkOutDate
	 */
	@Override
	public int checkAvailability(int hotelId, String checkInDate, String checkOutDate, int roomType) {
		int int_roomId = 0;
		
		List<Object[]> rooms = sessionFactory.getCurrentSession().createSQLQuery(
				"select roomId, checkInDate, checkOutDate from bookings where roomId in (select roomId from rooms where rooms.hotelId = " + hotelId+ " and roomType = " + roomType + ")")
				.list();

		Map<Integer, Boolean> roomIds = new HashMap<Integer, Boolean>();

		for (Object[] room : rooms) {
			roomIds.put((Integer) room[0], true);
		}

		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

		for (Object[] room : rooms) {
			String dbCheckInDate = room[1].toString();
			String dbCheckOutDate = room[2].toString();

			// If rooms are not booked
			if (roomIds.get((Integer) room[0]) != false) {
				try {
					// Check for availability of rooms
					if (sdf2.parse(dbCheckInDate).before(sdf1.parse(checkInDate))) {
						if (sdf1.parse(checkInDate).before(sdf2.parse(dbCheckOutDate))) {
							// roomIds.remove((Integer)room[0]);
							roomIds.put((Integer) room[0], false);
						}
					}

					if (sdf2.parse(dbCheckInDate).before(sdf1.parse(checkOutDate))) {
						if (sdf1.parse(checkOutDate).before(sdf2.parse(dbCheckOutDate))) {
							// roomIds.remove((Integer)room[0]);
							roomIds.put((Integer) room[0], false);
						}
					}

					// If DB dates are within the range of user entered dates
					if (sdf1.parse(checkInDate).before(sdf2.parse(dbCheckInDate))
							&& sdf2.parse(dbCheckOutDate).before(sdf1.parse(checkOutDate))) {
						// roomIds.remove((Integer)room[0]);
						roomIds.put((Integer) room[0], false);
					}

					// Same dates cannot be reserved
					if (sdf1.parse(checkInDate).compareTo(sdf2.parse(dbCheckInDate)) == 0
							&& sdf1.parse(checkOutDate).compareTo(sdf2.parse(dbCheckOutDate)) == 0) {
						// roomIds.remove((Integer)room[0]);
						roomIds.put((Integer) room[0], false);
					}

				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

		Set<Integer> roomIdsSet = new HashSet<Integer>();

		// Store available roomIds in a Set to keep unique roomIds
		for (Object[] room : rooms) {
			if (roomIds.get((Integer) room[0]) == true) {
				roomIdsSet.add((Integer) room[0]);
				int_roomId = (Integer) room[0];
			}
		}
		
		List<HotelDetails> hotelList = new ArrayList<HotelDetails>();

		// Retrieve hotel details
		for (Integer roomId : roomIdsSet) {
			List<Object[]> hotelDetails = sessionFactory.getCurrentSession().createSQLQuery(
					"select hotel.hotelId, hotelName, hotelImgUrl, roomPrice from hotel, rooms where hotel.hotelId = rooms.hotelId and roomId = "
							+ roomId)
					.list();
			
			for (Object[] hotel : hotelDetails) {
				hotelList.add(new HotelDetails(hotel[1].toString(), hotel[2].toString(), (Integer) hotel[3], (Integer) hotel[0]));
			}

		}
		
		// If none of the roooms of hotel are booked, add that hotel to availability list
				List<Object[]> hotelDetails = sessionFactory.getCurrentSession().createSQLQuery(
						"select hotel.hotelId, hotelName, hotelImgUrl, roomPrice, roomId from hotel, rooms where hotel.hotelId = rooms.hotelId and hotel.hotelId = " + hotelId
								+ " and roomType = " + roomType + " and roomId not in (select roomId from bookings)")
						.list();

				for (Object[] hotel : hotelDetails) {
					hotelList.add(new HotelDetails(hotel[1].toString(), hotel[2].toString(), (Integer) hotel[3], (Integer) hotel[0]));
					int_roomId = (Integer) hotel[4];
				}
				
		return int_roomId;
	}
}