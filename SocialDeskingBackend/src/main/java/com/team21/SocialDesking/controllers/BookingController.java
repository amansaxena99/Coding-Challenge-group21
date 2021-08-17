package com.team21.SocialDesking.controllers;

import com.team21.SocialDesking.entities.Booking;
import com.team21.SocialDesking.entities.FloorLayout;
import com.team21.SocialDesking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Date;

@RestController
@CrossOrigin
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/addNewBooking")
    public Booking addBooking(@RequestBody Booking booking)  {
        return this.bookingService.addBooking(booking);
    }

    @GetMapping("/getCurrentFloorBooking") //buidID floorId and date
    public FloorLayout getCurrentFloorBooking(@RequestParam("userId") int userId,
                                  @RequestParam("floorId") int floorId,
                                  @RequestParam("buildingId") int buildingId,
                                  @RequestParam("date") String date){

        return this.bookingService.getCurrentFloorBooking(userId, buildingId, floorId, Date.valueOf(date));
    }
    
    @DeleteMapping("/deleteBooking/{id}")
    public int deleteBooking(@PathVariable Integer id)
    {
        return bookingService.deleteBookingById(id);
    }

    @PutMapping("/updateBooking") //anything can be updated
    public Booking updateBooking(@RequestBody Booking new_booking)
    {
        return bookingService.updateBooking(new_booking);
    }
}
