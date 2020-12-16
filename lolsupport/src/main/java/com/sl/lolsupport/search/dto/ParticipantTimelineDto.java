package com.sl.lolsupport.search.dto;

public class ParticipantTimelineDto {
	private String participantId;
	private String csDiffPerMinDeltas;
	private String damageTakenPerMinDeltas;
	private String damageTakenDiffPerMinDeltas;
	private String xpPerMinDeltas;
	private String xpDiffPerMinDeltas;
	private String lane;
	private String creepsPerMinDeltas;
	private String goldPerMinDeltas;
	private String role;
	
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	public String getCsDiffPerMinDeltas() {
		return csDiffPerMinDeltas;
	}
	public void setCsDiffPerMinDeltas(String csDiffPerMinDeltas) {
		this.csDiffPerMinDeltas = csDiffPerMinDeltas;
	}
	public String getDamageTakenPerMinDeltas() {
		return damageTakenPerMinDeltas;
	}
	public void setDamageTakenPerMinDeltas(String damageTakenPerMinDeltas) {
		this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
	}
	public String getDamageTakenDiffPerMinDeltas() {
		return damageTakenDiffPerMinDeltas;
	}
	public void setDamageTakenDiffPerMinDeltas(String damageTakenDiffPerMinDeltas) {
		this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
	}
	public String getXpPerMinDeltas() {
		return xpPerMinDeltas;
	}
	public void setXpPerMinDeltas(String xpPerMinDeltas) {
		this.xpPerMinDeltas = xpPerMinDeltas;
	}
	public String getXpDiffPerMinDeltas() {
		return xpDiffPerMinDeltas;
	}
	public void setXpDiffPerMinDeltas(String xpDiffPerMinDeltas) {
		this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
	}
	public String getLane() {
		return lane;
	}
	public void setLane(String lane) {
		this.lane = lane;
	}
	public String getCreepsPerMinDeltas() {
		return creepsPerMinDeltas;
	}
	public void setCreepsPerMinDeltas(String creepsPerMinDeltas) {
		this.creepsPerMinDeltas = creepsPerMinDeltas;
	}
	public String getGoldPerMinDeltas() {
		return goldPerMinDeltas;
	}
	public void setGoldPerMinDeltas(String goldPerMinDeltas) {
		this.goldPerMinDeltas = goldPerMinDeltas;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
