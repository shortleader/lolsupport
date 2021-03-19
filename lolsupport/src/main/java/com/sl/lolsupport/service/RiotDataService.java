package com.sl.lolsupport.service;

public class RiotDataService {	
	public String getChampionImgURL(String id) {
		String url = "";
		if (1==1) {
			url = "https://ddragon.leagueoflegends.com/cdn/11.6.1/img/champion/" + id + ".png";
		}else {	// 신 챔프 등 이미지 없는 경우 기본 이미지
			url = "";
		}
		return url;
	}
	
	public String getSummonerSpellImgURL(String key, DbService dbService) {
		String url = "";
		String name = "";
		try {
			name = dbService.searchSpell(key).getImg_url();
		}catch(Exception e) {
			e.printStackTrace();
		}
		url = "http://ddragon.leagueoflegends.com/cdn/11.6.1/img/spell/" + name;
		return url;
	}
	
	public String getQueueTypeName(String id, DbService dbService) {
		String name = "";
		try {
			name = dbService.searchQueueType(id).getQueue_kor();
		}catch(Exception e) { 
			e.printStackTrace();
		}
		return name;
	}
	
	public String ChampionIdToName(String key, DbService dbService) {
		String name = ""; 
		try {
			name = dbService.searchChampion(key).getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
		
	}
}
