package com.risk.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.risk.service.AdminService;
import com.risk.model.Hotel;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;
	
	//admin view Page (author=harshitha)
	@RequestMapping(value="/hotel",method=RequestMethod.GET)
	public String show(Model model)
	{
		model.addAttribute("hotel", new Hotel());
		model.addAttribute("hotelList", adminService.getAllHotel());
		return "AdminOperation";
		
	}
	//admin CRUD Operation (author=harshitha)
	@RequestMapping("/hotelaction")
	public String doAction(@Valid @ModelAttribute("hotel") Hotel hotel,BindingResult result,@RequestParam String action,Map<String,Object> map,@RequestParam String OneBeddedRoomNum,@RequestParam String TwoBeddedRoomNum,@RequestParam String TypeOneprice,@RequestParam String TypeTwoprice) {
		Hotel hotelResult =new Hotel();
		
		if(result.hasErrors())
		{
			return "AdminOperation";
		}
		
		
		switch (action.toLowerCase()) {
		case "add":
			adminService.addHotel(hotel,Integer.parseInt(OneBeddedRoomNum),Integer.parseInt(TypeOneprice),Integer.parseInt(TwoBeddedRoomNum),Integer.parseInt(TypeTwoprice));
			System.out.println("OneBeddedRoomNum "+OneBeddedRoomNum);
			System.out.println("TwoBeddedRoomNum "+TwoBeddedRoomNum);
			hotelResult=hotel;
			break;
		case "edit":
			adminService.editHotel(hotel);
			hotelResult=hotel;
			break;
		case "delete":
			adminService.deleteHotelById(hotel.getHotelId());
			hotelResult=new Hotel();
			break;
		
		}
		map.put("hotel", hotelResult);
		map.put("hotelList", adminService.getAllHotel());
		map.put("action",action);
		return "AdminOperation";
		
	}

}
