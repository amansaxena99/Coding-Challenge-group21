package com.team21.SocialDesking.controllers;

import com.team21.SocialDesking.entities.FloorLayout;
import com.team21.SocialDesking.services.FloorLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@CrossOrigin
public class FloorController {

    @Autowired
    FloorLayoutService floorLayoutService;


    @GetMapping("/getAdminData")
    public List<FloorLayout> getAdminData(){
        return floorLayoutService.getAdminData();
    }

    @GetMapping("/getFloorData/{bId}/{fId}")
    public FloorLayout getFloorData(@PathVariable(name = "bId") int bId, @PathVariable(name = "fId") int fId) {
        FloorLayout floorLayout = floorLayoutService.getFloorLayout(bId,fId);
        System.out.println("here");
        return floorLayout;
    }

    @PutMapping("/updateFloorAvailability")
    public int updateFloorAvailability(@RequestParam("userId") int userId,
                                        @RequestParam("floorId") int floorId,
                                        @RequestParam("buildingId") int buildingId,
                                        @RequestParam("availability") int  availability) {
        return floorLayoutService.updateFloorAvailability(userId,buildingId,floorId,availability);

    }
}
