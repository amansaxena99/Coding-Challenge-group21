package com.team21.SocialDesking.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int userID;

    @Column(name = "NAME")
    private String name;

    public User(int userID, String name, String email, Team team, List<Booking> bookings) {
	
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.team = team;
		this.bookings = bookings;
	}

	@Column(name = "EMAIL")
    private String email;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID", insertable=false, updatable=false)
    private Team team;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    List<Booking> bookings;

    public User () {

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
