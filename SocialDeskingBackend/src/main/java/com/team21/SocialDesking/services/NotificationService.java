package com.team21.SocialDesking.services;

import com.team21.SocialDesking.entities.Booking;
import com.team21.SocialDesking.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@EnableScheduling
public class NotificationService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;

    @Scheduled(cron = "0 0 7 * * MON-FRI", zone = "IST")
    public void notificationSchedule() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        java.sql.Date sqlDate = new java.sql.Date(c.getTime().getTime());
        System.out.println("scheduled notifications: " + sqlDate);
        List<Booking> bookings = bookingService.getBookingsByDate(sqlDate);
        try {
            for (Booking booking : bookings) {
                sendMailNotification(booking, userService.getUserById(booking.getUserId()));
                System.out.println("sent successfully for booking " + booking.getBookingID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("notification sent for " + sqlDate);
    }



    public void sendMailNotification(Booking booking, User user) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setSubject("Upcoming Desk Booking - Notification");
        String msgBody = "Hi " + user.getName() +
                ",\n\nThis mail is regarding the notification of your upcoming booking at office in building " + booking.getBuildingId() +
                ", floor " + booking.getFloorId() + " and desk " + booking.getLocation() +
                " on " + booking.getbDate() + ".\n\nHave a great day." +
                "\n\n--\nSocial Desking";
        msg.setText(msgBody);
        javaMailSender.send(msg);
    }

}
