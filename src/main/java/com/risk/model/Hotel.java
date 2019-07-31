package com.risk.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "hotel")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int hotelId;

	@NotEmpty
	@Size(min = 3, max = 50)
	@Pattern(regexp = "[^0-9]+")
	private String hotelName;

	@NotEmpty
	private String hotelArea;

	@NotEmpty
	private String hotelImgUrl;

	@NotNull
	@Min(value = 1)
	@Pattern(regexp = "[^a-zA-Z]+")
	private int hotelNumOfRooms;

	@OneToMany(mappedBy = "hotel")
	private Set<Rooms> rooms;

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelArea() {
		return hotelArea;
	}

	public void setHotelArea(String hotelArea) {
		this.hotelArea = hotelArea;
	}

	public String getHotelImgUrl() {
		return hotelImgUrl;
	}

	public void setHotelImgUrl(String hotelImgUrl) {
		this.hotelImgUrl = hotelImgUrl;
	}

	public int getHotelNumOfRooms() {
		return hotelNumOfRooms;
	}

	public void setHotelNumOfRooms(int hotelNumOfRooms) {
		this.hotelNumOfRooms = hotelNumOfRooms;
	}

	public Set<Rooms> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Rooms> rooms) {
		this.rooms = rooms;
	}

	public Hotel(String hotelName, String hotelArea, String hotelImgUrl, int hotelNumOfRooms,
			Set<Rooms> rooms) {
		super();
		this.hotelName = hotelName;
		this.hotelArea = hotelArea;
		this.hotelImgUrl = hotelImgUrl;
		this.hotelNumOfRooms = hotelNumOfRooms;
		this.rooms = rooms;
	}

	public Hotel() {
		super();
	}
	
	
	
	
}
