package com.team21.SocialDesking.services;

import com.team21.SocialDesking.entities.Booking;
import com.team21.SocialDesking.entities.FloorLayout;
import com.team21.SocialDesking.entities.User;

import java.sql.Date;
import java.util.List;

public interface BookingService {
    Booking addBooking(Booking booking);
    Booking updateBooking (Booking new_booking);
    int deleteBookingById(int bookingid);
    FloorLayout getCurrentFloorBooking(int userId, int buildingId, int floorId, Date date);
    List<Booking> getBookingsByDate(Date date);
}
