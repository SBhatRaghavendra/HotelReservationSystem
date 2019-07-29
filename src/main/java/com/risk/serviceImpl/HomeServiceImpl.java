package com.risk.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.risk.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {

	@Override
	public boolean isValidDates(String checkInDate, String checkOutDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			return sdf.parse(checkInDate).before(sdf.parse(checkOutDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}

}
