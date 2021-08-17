package com.team21.SocialDesking.services;

import com.team21.SocialDesking.entities.FloorLayout;
import com.team21.SocialDesking.repository.FloorLayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorLayoutServiceImpl implements FloorLayoutService{

    @Autowired
    FloorLayoutRepository floorLayoutRepository;

    @Override
    public FloorLayout getFloorLayout(int bId, int fId) {
        return floorLayoutRepository.findByBuildingIdAndFloorId(bId, fId);
    }

    @Override
    public int updateFloorAvailability(int userId,int bId, int fId, int avail){
        if(userId==1234) //hardcoded admin userId
        {
            FloorLayout flayout = floorLayoutRepository.findByBuildingIdAndFloorId(bId, fId);
            flayout.setAvailable(avail);
            floorLayoutRepository.save(flayout);
            return 1;
        }
        return 0;
    }
    
    @Override
    public List<FloorLayout> getAdminData() {
        return floorLayoutRepository.findAll();
    }
}
