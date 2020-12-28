package com.sl.lolsupport.search.dto;

public class RuneDto {
	private String runeId;
	private String rank;
	
	// Database 전용
	private String gameId;
	private String participantId;
	
	public String getRuneId() {
		return runeId;
	}
	public void setRuneId(String runeId) {
		this.runeId = runeId;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
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
