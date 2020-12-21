package com.sl.lolsupport.search.dto;

public class ParticipantIdentityDto {
	private String participantId;
	private PlayerDto player;
	
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	public PlayerDto getPlayer() {
		return player;
	}
	public void setPlayer(PlayerDto player) {
		this.player = player;
	}
}
