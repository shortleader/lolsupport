package com.sl.lolsupport.search.dto;

public class TeamBansDto {
	private String championId;
	private String pickTurn;
	
	// Database 전용
	private String gameId;
	private String teamId;
	
	public String getChampionId() {
		return championId;
	}
	public void setChampionId(String championId) {
		this.championId = championId;
	}
	public String getPickTurn() {
		return pickTurn;
	}
	public void setPickTurn(String pickTurn) {
		this.pickTurn = pickTurn;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
}
