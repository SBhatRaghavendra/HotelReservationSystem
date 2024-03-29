package com.risk.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.risk.model.Bookings;
import com.risk.model.HotelDetails;
import com.risk.model.Rooms;
import com.risk.model.User;
import com.risk.service.BookingService;
import com.risk.service.HomeService;
import com.risk.service.RoomService;

@Controller
@RequestMapping("/")
public class CustomerController {

	@Autowired
	HomeService homeService;

	@Autowired
	BookingService bookingService;

	@Autowired
	RoomService roomService;

	List<HotelDetails> g_hotelDetails;
	String g_checkInDate;
	String g_checkOutDate;
	int g_roomType;
	int g_roomPrice;
	int g_roomId;
	String g_hotelName;

	@RequestMapping(value = { "/", "/index", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = { "/homeEntryValidation" }, method = RequestMethod.POST)
	public String validateHomeEntry(@RequestParam("location") String location,
			@RequestParam("checkInDate") String checkInDate, @RequestParam("checkOutDate") String checkOutDate,
			@RequestParam("roomType") int roomType, HttpServletRequest request) {

		if (!homeService.isValidDates(checkInDate, checkOutDate)) {
			request.getSession().setAttribute("invalidDate", "Please enter valid check-in and check-out dates");
			return "home";
		}

		g_checkInDate = checkInDate;
		g_checkOutDate = checkOutDate;
		g_roomType = roomType;

		g_hotelDetails = null;
		g_hotelDetails = homeService.getHotelList(location, checkInDate, checkOutDate, roomType);

		return "redirect:/listHotels";
	}

	@RequestMapping(value = "/listHotels", method = RequestMethod.GET)
	public ModelAndView listHotels(ModelAndView modelAndView, Model model) {
		modelAndView.setViewName("HotelList");
		modelAndView.addObject("hotelDetails", g_hotelDetails);
		return modelAndView;
	}

	@RequestMapping(value = "/checkAvailabilty", method = RequestMethod.POST)
	public ModelAndView checkAvailabilty(@RequestParam("hotelId") int hotelId, @RequestParam("roomPrice") int roomPrice,
			@RequestParam("hotelName") String hotelName, ModelAndView modelAndView, Model model) {
		modelAndView.setViewName("HotelList");

		int roomId = homeService.checkAvailability(hotelId, g_checkInDate, g_checkOutDate, g_roomType);
		g_roomPrice = roomPrice;
		g_hotelName = hotelName;
		g_roomId = roomId;
		if (roomId > 0) {
			model.addAttribute("available", true);
		} else {
			model.addAttribute("notAvailable", true);
			modelAndView.addObject("hotelDetails", g_hotelDetails);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public ModelAndView viewPayment(ModelAndView modelAndView, Model model, HttpServletRequest request) {

		request.getSession().setAttribute("hotelName", g_hotelName);
		request.getSession().setAttribute("roomPrice", g_roomPrice);
		request.getSession().setAttribute("roomId", g_roomId);

		if (request.getSession().getAttribute("loggedIn") != null) {
			modelAndView.setViewName("Payment");
			return modelAndView;
		}
		modelAndView.setViewName("Login");
		model.addAttribute("loginMessage", "Please login to continue");
		request.getSession().setAttribute("requestLogin", true);
		model.addAttribute("user", new User());
		return modelAndView;

	}

	@RequestMapping(value = "/paymentSuccess", method = RequestMethod.POST)
	public String paymentSuccess(ModelMap map, HttpServletRequest request) {

		Rooms room = roomService.getRoom(g_roomId);

		DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
		LocalDate checkInDate = LocalDate.parse(g_checkInDate, formatter);
		LocalDate checkOutDate = LocalDate.parse(g_checkOutDate, formatter);

		Bookings bookings = new Bookings(room, checkInDate, checkOutDate);
		int bookingId = bookingService.saveBookings(bookings);

		map.addAttribute("userEmailId", request.getSession().getAttribute("userEmailId").toString());
		map.addAttribute("userFullName", request.getSession().getAttribute("userFullName").toString());
		map.addAttribute("userPhoneNum", request.getSession().getAttribute("userPhoneNum").toString());
		map.addAttribute("checkInDate", g_checkInDate);
		map.addAttribute("checkOutDate", g_checkOutDate);
		map.addAttribute("bookingId", bookingId);
		return "Confirmation";
	}

}
