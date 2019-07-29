package com.risk.controller;

import java.util.List;
import java.util.Locale;

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

import com.risk.service.HomeService;

@Controller
@RequestMapping("/")
public class EntryController {

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
			@RequestParam("roomType") int roomType,
			@RequestParam("noOfRooms") int noOfRooms) {
		boolean isValidDates = homeService.isValidDates(checkInDate, checkOutDate);
		System.out.println(isValidDates);
		return "hotelList";
    }
	
	
}
