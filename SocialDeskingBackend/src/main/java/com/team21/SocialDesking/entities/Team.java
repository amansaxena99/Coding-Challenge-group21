package com.team21.SocialDesking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "TEAMS")
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private int teamId;

    @Column(name = "TEAM_NAME")
    private String teamName;

    public Team(int teamId, String teamName) {
	
		this.teamId = teamId;
		this.teamName = teamName;
	}

	public Team() {
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamID(int teamID) {
        this.teamId = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
