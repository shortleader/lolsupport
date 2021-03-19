package com.sl.lolsupport.service.dto;

public class ChampionData {
	private String champion_key;
	private String champion_name;
	
	public String getId() {
		return champion_name;
	}
	public void setId(String id) {
		this.champion_name = id;
	}
	public String getKey() {
		return champion_key;
	}
	public void setKey(String key) {
		this.champion_key = key;
	}
}
