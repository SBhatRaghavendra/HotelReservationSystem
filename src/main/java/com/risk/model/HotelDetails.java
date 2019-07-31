package com.risk.model;

public class HotelDetails {
	private String hotelName;
	private String hotelImgUrl;
	private int roomPrice;
	
	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelImgUrl() {
		return hotelImgUrl;
	}

	public void setHotelImgUrl(String hotelImgUrl) {
		this.hotelImgUrl = hotelImgUrl;
	}

	public int getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}


	public HotelDetails(String hotelName, String hotelImgUrl, int roomPrice) {
		super();
		this.hotelName = hotelName;
		this.hotelImgUrl = hotelImgUrl;
		this.roomPrice = roomPrice;
	}

	public HotelDetails() {
	}

	@Override
	public String toString() {
		return "HotelDetails [hotelName=" + hotelName + ", hotelImgUrl=" + hotelImgUrl + ", roomPrice=" + roomPrice
				+ "]";
	}
}
