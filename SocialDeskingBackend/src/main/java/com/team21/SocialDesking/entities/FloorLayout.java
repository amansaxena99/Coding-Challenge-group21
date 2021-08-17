package com.team21.SocialDesking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "FLOOR_LAYOUT")
public class FloorLayout {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "BUILDING_ID")
    private int buildingId;

    public FloorLayout(int id, int buildingId, int floorId, String layoutMatrix, int available) {
		this.id = id;
		this.buildingId = buildingId;
		this.floorId = floorId;
		this.layoutMatrix = layoutMatrix;
		this.available = available;
	}

	@Column(name = "FLOOR_ID")
    private int floorId;

    @Column(name = "LAYOUT_MATRIX")
    private String layoutMatrix;

    @Column(name = "AVAILABLE")
    private int available;

    public FloorLayout() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLayoutMatrix() {
        return layoutMatrix;
    }

    public void setLayoutMatrix(String layoutMatrix) {
        this.layoutMatrix = layoutMatrix;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
