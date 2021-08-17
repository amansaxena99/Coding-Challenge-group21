package com.team21.SocialDesking.services;

import com.team21.SocialDesking.entities.FloorLayout;

import java.util.List;

public interface FloorLayoutService {
    FloorLayout getFloorLayout(int bId, int fId);
    int updateFloorAvailability(int userId,int bId, int fId, int avail);
    List<FloorLayout> getAdminData();
}
