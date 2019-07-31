package com.risk.model;

import java.util.Set;

import javax.persistence.Column;
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

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "bookings")
public class Bookings {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;

	@ManyToOne
	@JoinColumn(name = "roomId", nullable = false)
	private Rooms rooms;
	
	@NotNull
    @DateTimeFormat(pattern="MM/dd/yyyy") 
    @Column(name = "checkInDate", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate checkInDate;
	
	@NotNull
    @DateTimeFormat(pattern="MM/dd/yyyy") 
    @Column(name = "checkOutDate", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate checkOutDate;

	public Rooms getRooms() {
		return rooms;
	}


	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
	}


	public LocalDate getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}


	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	public Bookings(Rooms rooms, LocalDate checkInDate, LocalDate checkOutDate) {
		super();
		this.rooms = rooms;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}


	public Bookings() {
		super();
	}
}
