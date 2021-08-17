package com.team21.SocialDesking.entities;

import javax.persistence.*;
import java.sql.Date;

@Table(name="bookings")
@Entity(name = "BOOKINGS")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKING_ID")
    private int bookingID;

    public Booking(int bookingID, int userId, int buildingId, int floorId, String date, String location) {
		
		this.bookingID = bookingID;
		this.userId = userId;
		this.buildingId = buildingId;
		this.floorId = floorId;
		this.date = date;
		this.location = location;
	}

	@Column(name = "USER_ID")
    private int userId;

    @Column(name = "BUILDING_ID")
    private int buildingId;

    @Column(name = "FLOOR_ID")
    private int floorId;

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    @Column(name = "B_DATE")
    private Date bDate;

    @Transient
    private String date;

    @Column(name = "LOCATION")
    private String location;

    public Booking() {
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
