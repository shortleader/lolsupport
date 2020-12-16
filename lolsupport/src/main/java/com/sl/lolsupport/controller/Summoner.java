package com.sl.lolsupport.controller;


public class Summoner {
	private String id;
	private String accountId;
	private String puuid;
	private String name;
	private String profileIconId;
	private String revisionDate;
	private String summonerLevel;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getPuuid() {
		return puuid;
	}
	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}
	public String getName() {
		return name;
	}
	public String getProfileIconId() {
		return profileIconId;
	}
	public void setProfileIconId(String profileIconId) {
		this.profileIconId = profileIconId;
	}
	public String getSummonerLevel() {
		return summonerLevel;
	}
	public void setSummonerLevel(String summonerLevel) {
		this.summonerLevel = summonerLevel;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRevisionDate() {
		return revisionDate;
	}
	public void setRevisionDate(String revisionDate) {
		this.revisionDate = revisionDate;
	}
}
