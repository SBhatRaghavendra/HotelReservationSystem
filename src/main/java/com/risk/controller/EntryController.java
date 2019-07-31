package com.risk.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.risk.model.HotelDetails;
import com.risk.service.HomeService;

@Controller
@RequestMapping("/")
public class EntryController {
	
	List<HotelDetails> hotelDetails;
	Map<HotelDetails, Integer> countOfRooms;
	
	@Autowired
	HomeService homeService;
	
	@RequestMapping(value = { "/", "/index", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = { "/homeEntryValidation" }, method = RequestMethod.POST)
    public String validateHomeEntry(
    		@RequestParam("location") String location,
			@RequestParam("checkInDate") String checkInDate,
			@RequestParam("checkOutDate") String checkOutDate,
			@RequestParam("roomType") int roomType,HttpServletRequest request) {
		if(!homeService.isValidDates(checkInDate, checkOutDate)) {
			request.getSession().setAttribute("invalidDate", "invalidDate");
			return "home";
		}
	
		hotelDetails = homeService.getHotelList(location, checkInDate, checkOutDate, roomType);
		
		for(HotelDetails hotel: hotelDetails) {
			System.out.println(hotel.getRoomPrice());
		}
		
		//countOfRooms = homeService.getCountOfRooms(hotelDetails);
		return "redirect:/listHotels";
    }
	
	@RequestMapping(value = "/listHotels", method = RequestMethod.GET)
	public ModelAndView listHotels(ModelAndView modelAndView) {
		modelAndView.setViewName("HotelList");
		modelAndView.addObject("hotelDetails", hotelDetails);
		//modelAndView.addObject("countOfRooms", countOfRooms);
		return modelAndView;
	}
	
}
