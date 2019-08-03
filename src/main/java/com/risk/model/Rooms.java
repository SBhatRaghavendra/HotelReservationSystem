package com.risk.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "rooms")
public class Rooms {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roomId;

	private int roomType;

	private int roomPrice;

	@ManyToOne
	@JoinColumn(name = "hotelId", nullable = false)
	private Hotel hotel;

	@OneToMany(mappedBy = "rooms")
	private Set<Bookings> bookings;

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}

	public int getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Set<Bookings> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Bookings> bookings) {
		this.bookings = bookings;
	}

	public Rooms(int roomType, int roomPrice, Hotel hotel, Set<Bookings> bookings) {
		super();
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.hotel = hotel;
		this.bookings = bookings;
	}

	public Rooms(int roomType, int roomPrice, Hotel hotel) {
		super();
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.hotel = hotel;

	}

	public Rooms() {
	}

}
