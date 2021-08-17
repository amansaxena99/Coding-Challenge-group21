package com.team21.SocialDesking.repository;

import com.team21.SocialDesking.entities.Booking;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	@Query("select b FROM BOOKINGS b where b.buildingId =:bid AND b.floorId =:fid AND b.bDate =:bdt")
	public List<Booking> getLocationsByUserSel(@Param("bid") int Bid,@Param("fid") int Fid,@Param("bdt") Date dt);
	
	@Query("select u.team.teamId FROM USER u where u.userID =:uid")
	public int getTeamId(@Param("uid") int id);
	
	@Query("select b FROM BOOKINGS b where b.buildingId =:bid AND b.floorId =:fid AND b.bDate =:bdt AND b.userId IN (select u.userID FROM USER u where u.team.teamId =:tid AND u.userID <>:uid)")
	public List<Booking> getBookingsForTeammates(@Param("uid") int Uid,@Param("tid") int Tid,@Param("bid") int Bid,@Param("fid") int Fid,@Param("bdt") Date dt);

	@Query("select b FROM BOOKINGS b where b.bDate =:bdt")
	List<Booking> findBookingByBDate(@Param("bdt") Date dt);
}
