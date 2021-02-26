package com.sl.lolsupport.search.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sl.lolsupport.search.dto.LeagueEntryDto;
import com.sl.lolsupport.search.dto.MatchDto;
import com.sl.lolsupport.service.MatchDbService;

public class FrontAddListService {

	Gson gson = new Gson();
	/*
	 * 맨마지막 total kill부분 수정 해야댐
	 * 
	 */
	public JsonObject AddMatchList(String gameId, String apiKey, MatchDbService matchDbService) {
		
		GetMatchService matchService = new GetMatchService();
		MatchDto matchDto = new MatchDto();
		int TotalKills100 = 0;
		int TotalKills200 = 0;
		matchDto = matchService.getMatchData(gameId, apiKey, matchDbService);
		
		JsonObject jsonObject = new JsonObject();
		
		JsonArray infoList = new JsonArray();
		
		jsonObject.addProperty("gameId", gameId);
		
		for(int i=0; i<matchDto.getParticipantIdentities().size();i++) {
			JsonObject gameObject = new JsonObject();
			FrontTierCheckService frontTierCheckService = new FrontTierCheckService();
			
			LeagueEntryDto[] leagueEntryDto = frontTierCheckService.TierCheck(matchDto.getParticipantIdentities().get(i).getPlayer().getSummonerId(), apiKey);
					
			gameObject.addProperty("tier", leagueEntryDto[0].getTier());
			gameObject.addProperty("rank", leagueEntryDto[0].getRank());
			gameObject.addProperty("summonerName", matchDto.getParticipantIdentities().get(i).getPlayer().getSummonerName());
			gameObject.addProperty("kills", matchDto.getParticipants().get(i).getStats().getKills());
			gameObject.addProperty("deaths", matchDto.getParticipants().get(i).getStats().getDeaths());
			gameObject.addProperty("assists", matchDto.getParticipants().get(i).getStats().getAssists());
			gameObject.addProperty("spell1Id", matchDto.getParticipants().get(i).getSpell1Id());
			gameObject.addProperty("spell2Id", matchDto.getParticipants().get(i).getSpell2Id());
			gameObject.addProperty("perk0", matchDto.getParticipants().get(i).getStats().getPerk0());
			gameObject.addProperty("perkSubStyle", matchDto.getParticipants().get(i).getStats().getPerkSubStyle());
			gameObject.addProperty("championLevel", matchDto.getParticipants().get(i).getStats().getChampLevel());
			gameObject.addProperty("item0", matchDto.getParticipants().get(i).getStats().getItem0());
			gameObject.addProperty("item1", matchDto.getParticipants().get(i).getStats().getItem1());
			gameObject.addProperty("item2", matchDto.getParticipants().get(i).getStats().getItem2());
			gameObject.addProperty("item3", matchDto.getParticipants().get(i).getStats().getItem3());
			gameObject.addProperty("item4", matchDto.getParticipants().get(i).getStats().getItem4());
			gameObject.addProperty("item5", matchDto.getParticipants().get(i).getStats().getItem5());
			gameObject.addProperty("item6", matchDto.getParticipants().get(i).getStats().getItem6());
			gameObject.addProperty("wardsPlaced", matchDto.getParticipants().get(i).getStats().getWardsPlaced());
			gameObject.addProperty("visionWardsBoughtInGame", matchDto.getParticipants().get(i).getStats().getVisionWardsBoughtInGame());
			gameObject.addProperty("wardsKilled", matchDto.getParticipants().get(i).getStats().getWardsKilled());
			gameObject.addProperty("championId", matchDto.getParticipants().get(i).getChampionId());
			// 킬관여율 계산하기.
			if(matchDto.getParticipants().get(i).getTeamId().equals("100")) {
				TotalKills100 += Integer.parseInt(matchDto.getParticipants().get(i).getStats().getKills());
			} else {
				TotalKills200 += Integer.parseInt(matchDto.getParticipants().get(i).getStats().getKills());
			}
			
			infoList.add(gameObject);
		}
		for(int i=0;i<matchDto.getParticipantIdentities().size();i++) {
			JsonObject gameObject2 = new JsonObject();
			float participation = calcParticipation(Integer.parseInt(matchDto.getParticipants().get(i).getStats().getKills()),
					Integer.parseInt(matchDto.getParticipants().get(i).getStats().getAssists()), 
					matchDto.getParticipants().get(i).getTeamId().equals("100")? TotalKills100:TotalKills200);
			
			gameObject2.addProperty("participation", participation);
			infoList.add(gameObject2);
		}
		
		
		
		jsonObject.add("games", infoList);
		System.out.println(jsonObject.toString());
		return jsonObject;
	}
	
	public float calcParticipation(float kills, float assists, float totalKills) {
		if (totalKills == 0) return 0;
		return (kills + assists) / totalKills * 100;
	}
}
