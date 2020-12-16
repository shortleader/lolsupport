package com.sl.lolsupport.search.dto;

import java.util.List;

public class MatchDto {
	private String gameId;
	private List<ParticipantIdentityDto> participantIdentities;
	private String queueId;
	private String gameType;
	private String gameDuration;
	private List<TeamStatsDto> teams;
	private String platformId;
	private String gameCreation;
	private String seasonId;
	private String gameVersion;
	private String mapId;
	private String gameMode;
	private List<ParticipantDto> participants;
	
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public List<ParticipantIdentityDto> getParticipantIdentities() {
		return participantIdentities;
	}
	public void setParticipantIdentities(List<ParticipantIdentityDto> participantIdentities) {
		this.participantIdentities = participantIdentities;
	}
	public String getQueueId() {
		return queueId;
	}
	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public String getGameDuration() {
		return gameDuration;
	}
	public void setGameDuration(String gameDuration) {
		this.gameDuration = gameDuration;
	}
	public List<TeamStatsDto> getTeams() {
		return teams;
	}
	public void setTeams(List<TeamStatsDto> teams) {
		this.teams = teams;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public String getGameCreation() {
		return gameCreation;
	}
	public void setGameCreation(String gameCreation) {
		this.gameCreation = gameCreation;
	}
	public String getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(String seasonId) {
		this.seasonId = seasonId;
	}
	public String getGameVersion() {
		return gameVersion;
	}
	public void setGameVersion(String gameVersion) {
		this.gameVersion = gameVersion;
	}
	public String getMapId() {
		return mapId;
	}
	public void setMapId(String mapId) {
		this.mapId = mapId;
	}
	public String getGameMode() {
		return gameMode;
	}
	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	public List<ParticipantDto> getParticipants() {
		return participants;
	}
	public void setParticipants(List<ParticipantDto> participants) {
		this.participants = participants;
	}
}
