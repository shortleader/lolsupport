package com.sl.lolsupport.search.dto;

import java.util.List;

public class TeamStatsDto {
	private String towerKills;
	private String riftHeraldKills;
	private String firstBlood;
	private String inhibitorKills;
	private List<TeamBansDto> bans;
	private String firstBaron;
	private String firstDragon;
	private String dominionVictoryScore;
	private String dragonKills;
	private String firstInhibitor;
	private String firstTower;
	private String vilemawKills;
	private String firstRiftHerald;
	private String teamId;
	private String win;
	
	public String getTowerKills() {
		return towerKills;
	}
	public void setTowerKills(String towerKills) {
		this.towerKills = towerKills;
	}
	public String getRiftHeraldKills() {
		return riftHeraldKills;
	}
	public void setRiftHeraldKills(String riftHeraldKills) {
		this.riftHeraldKills = riftHeraldKills;
	}
	public String getFirstBlood() {
		return firstBlood;
	}
	public void setFirstBlood(String firstBlood) {
		this.firstBlood = firstBlood;
	}
	public String getInhibitorKills() {
		return inhibitorKills;
	}
	public void setInhibitorKills(String inhibitorKills) {
		this.inhibitorKills = inhibitorKills;
	}
	public List<TeamBansDto> getBans() {
		return bans;
	}
	public void setBans(List<TeamBansDto> bans) {
		this.bans = bans;
	}
	public String getFirstBaron() {
		return firstBaron;
	}
	public void setFirstBaron(String firstBaron) {
		this.firstBaron = firstBaron;
	}
	public String getFirstDragon() {
		return firstDragon;
	}
	public void setFirstDragon(String firstDragon) {
		this.firstDragon = firstDragon;
	}
	public String getDominionVictoryScore() {
		return dominionVictoryScore;
	}
	public void setDominionVictoryScore(String dominionVictoryScore) {
		this.dominionVictoryScore = dominionVictoryScore;
	}
	public String getDragonKills() {
		return dragonKills;
	}
	public void setDragonKills(String dragonKills) {
		this.dragonKills = dragonKills;
	}
	public String getFirstInhibitor() {
		return firstInhibitor;
	}
	public void setFirstInhibitor(String firstInhibitor) {
		this.firstInhibitor = firstInhibitor;
	}
	public String getFirstTower() {
		return firstTower;
	}
	public void setFirstTower(String firstTower) {
		this.firstTower = firstTower;
	}
	public String getVilemawKills() {
		return vilemawKills;
	}
	public void setVilemawKills(String vilemawKills) {
		this.vilemawKills = vilemawKills;
	}
	public String getFirstRiftHerald() {
		return firstRiftHerald;
	}
	public void setFirstRiftHerald(String firstRiftHerald) {
		this.firstRiftHerald = firstRiftHerald;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getWin() {
		return win;
	}
	public void setWin(String win) {
		this.win = win;
	}	
}
