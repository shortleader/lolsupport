package com.sl.lolsupport.search.dto;

public class MasteryDto {
	private String rank;
	private String masteryId;
	
	// Database 전용
	private String gameId;
	private String participantId;
	
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getMasteryId() {
		return masteryId;
	}
	public void setMasteryId(String masteryId) {
		this.masteryId = masteryId;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
}
