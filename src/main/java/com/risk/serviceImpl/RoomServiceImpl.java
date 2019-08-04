package com.risk.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.risk.dao.RoomDao;
import com.risk.model.Rooms;
import com.risk.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	RoomDao roomDao;
	
	//Service method to retrieve room object from database by passing roomId.
	@Override
	@Transactional
	public Rooms getRoom(int roomId) {
		return roomDao.getRoom(roomId);
	}

}
