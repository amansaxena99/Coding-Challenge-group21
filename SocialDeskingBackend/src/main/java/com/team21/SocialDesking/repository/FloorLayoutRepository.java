package com.team21.SocialDesking.repository;


import com.team21.SocialDesking.entities.Booking;
import com.team21.SocialDesking.entities.FloorLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorLayoutRepository extends JpaRepository<FloorLayout, Integer> {
    FloorLayout findByBuildingIdAndFloorId(int buildingId, int floorId);
}
