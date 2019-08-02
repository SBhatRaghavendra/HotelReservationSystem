package com.risk.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risk.dao.RoomDao;
import com.risk.model.Rooms;

@Repository
public class RoomDaoImpl implements RoomDao{

	@Autowired
	private SessionFactory session;
	
	@Override
	public Rooms getRoom(int roomId) {
		List<Rooms> rooms = session.getCurrentSession().createQuery("from Rooms where roomId = " + roomId).list();
		
		Rooms room = null;
		
		for(Rooms row: rooms) {
			room = row;
		}
		
		return room;
	}

}
