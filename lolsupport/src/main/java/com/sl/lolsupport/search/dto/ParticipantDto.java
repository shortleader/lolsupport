package com.sl.lolsupport.search.dto;

import java.util.List;

public class ParticipantDto {
	private String participantId;
	private String championId;
	private List<RuneDto> runes;
	private ParticipantStatsDto stats;
	private String teamId;
	private ParticipantTimelineDto timeline;
	private String spell1Id;
	private String spell2Id;
	private String highestAchievedSeasonTier;
	private List<MasteryDto> masteries;
	
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	public String getChampionId() {
		return championId;
	}
	public void setChampionId(String championId) {
		this.championId = championId;
	}
	public List<RuneDto> getRunes() {
		return runes;
	}
	public void setRunes(List<RuneDto> runes) {
		this.runes = runes;
	}
	public ParticipantStatsDto getStats() {
		return stats;
	}
	public void setStats(ParticipantStatsDto stats) {
		this.stats = stats;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public ParticipantTimelineDto getTimeline() {
		return timeline;
	}
	public void setTimeline(ParticipantTimelineDto timeline) {
		this.timeline = timeline;
	}
	public String getSpell1Id() {
		return spell1Id;
	}
	public void setSpell1Id(String spell1Id) {
		this.spell1Id = spell1Id;
	}
	public String getSpell2Id() {
		return spell2Id;
	}
	public void setSpell2Id(String spell2Id) {
		this.spell2Id = spell2Id;
	}
	public String getHighestAchievedSeasonTier() {
		return highestAchievedSeasonTier;
	}
	public void setHighestAchievedSeasonTier(String highestAchievedSeasonTier) {
		this.highestAchievedSeasonTier = highestAchievedSeasonTier;
	}
	public List<MasteryDto> getMasteries() {
		return masteries;
	}
	public void setMasteries(List<MasteryDto> masteries) {
		this.masteries = masteries;
	}
}
