package com.sl.lolsupport.service;

public class RiotDataService {
	private String cdnURL = "https://ddragon.leagueoflegends.com/cdn/11.6.1/img/";
	public String getChampionImgURL(String key, DbService dbService) {
		String url = "";
		String name = "";
		try {
			name = dbService.searchChampion(key).getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		if (1==1) {
			url = cdnURL + "champion/" + name + ".png";
		}else {	// 신 챔프 등 이미지 없는 경우 기본 이미지
			url = "";
		}
		return url+","+name;
	}
	
	public String getSummonerSpellImgURL(String key, DbService dbService) {
		String url = "";
		String name = "";
		try {
			name = dbService.searchSpell(key).getImg_url();
		}catch(Exception e) {
			e.printStackTrace();
		}
		url = cdnURL + "spell/" + name;
		return url;
	}
	
	public String getItemImgURL(String key, DbService dbService) {
		String url = "";
		String name = "";
		
		if (key.equals("0")) {
			url = "img/ccc.png";
		}else {
			try {
				name = dbService.searchItem(key).getItem_name();
			}catch(Exception e) {
				e.printStackTrace();
			}
			url = cdnURL + "item/" + key + ".png";
		}
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
}
