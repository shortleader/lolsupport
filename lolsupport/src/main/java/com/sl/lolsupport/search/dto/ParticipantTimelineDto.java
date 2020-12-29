package com.sl.lolsupport.search.dto;

import java.util.HashMap;
import java.util.Map;

public class ParticipantTimelineDto {
	private String participantId;
	private Map<String, Double> csDiffPerMinDeltas = new HashMap<String, Double>();
	private Map<String, Double> damageTakenPerMinDeltas = new HashMap<String, Double>();
	private String role;
	private Map<String, Double> damageTakenDiffPerMinDeltas = new HashMap<String, Double>();
	private Map<String, Double> xpPerMinDeltas = new HashMap<String, Double>();
	private Map<String, Double> xpDiffPerMinDeltas = new HashMap<String, Double>();
	private String lane;
	private Map<String, Double> creepsPerMinDeltas = new HashMap<String, Double>();
	private Map<String, Double> goldPerMinDeltas = new HashMap<String, Double>();
	
	// Database 전용
	private String gameId;
	
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	public Map<String, Double> getCsDiffPerMinDeltas() {
		return csDiffPerMinDeltas;
	}
	public void setCsDiffPerMinDeltas(Map<String, Double> csDiffPerMinDeltas) {
		this.csDiffPerMinDeltas = csDiffPerMinDeltas;
	}
	public Map<String, Double> getDamageTakenPerMinDeltas() {
		return damageTakenPerMinDeltas;
	}
	public void setDamageTakenPerMinDeltas(Map<String, Double> damageTakenPerMinDeltas) {
		this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Map<String, Double> getDamageTakenDiffPerMinDeltas() {
		return damageTakenDiffPerMinDeltas;
	}
	public void setDamageTakenDiffPerMinDeltas(Map<String, Double> damageTakenDiffPerMinDeltas) {
		this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
	}
	public Map<String, Double> getXpPerMinDeltas() {
		return xpPerMinDeltas;
	}
	public void setXpPerMinDeltas(Map<String, Double> xpPerMinDeltas) {
		this.xpPerMinDeltas = xpPerMinDeltas;
	}
	public Map<String, Double> getXpDiffPerMinDeltas() {
		return xpDiffPerMinDeltas;
	}
	public void setXpDiffPerMinDeltas(Map<String, Double> xpDiffPerMinDeltas) {
		this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
	}
	public String getLane() {
		return lane;
	}
	public void setLane(String lane) {
		this.lane = lane;
	}
	public Map<String, Double> getCreepsPerMinDeltas() {
		return creepsPerMinDeltas;
	}
	public void setCreepsPerMinDeltas(Map<String, Double> creepsPerMinDeltas) {
		this.creepsPerMinDeltas = creepsPerMinDeltas;
	}
	public Map<String, Double> getGoldPerMinDeltas() {
		return goldPerMinDeltas;
	}
	public void setGoldPerMinDeltas(Map<String, Double> goldPerMinDeltas) {
		this.goldPerMinDeltas = goldPerMinDeltas;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
}
