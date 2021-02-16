package com.sl.lolsupport.search.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sl.lolsupport.search.dto.MatchDto;
import com.sl.lolsupport.search.dto.MatchlistDto;
import com.sl.lolsupport.service.MatchDbService;

public class FrontMatchListService {
	Gson gson = new Gson();
	/*
	 * 승/패 게임모드 KDA 킬관여 스펠 팀챔피언목록 아이템 레벨/골드/CS 플레이한시간 와드
	 */
	public JsonObject GetMatchList(String accountID, int beginIndex, int endIndex, String apiKey, MatchDbService matchDbService) {
		GetMatchListService matchListService = new GetMatchListService();
		GetMatchService matchService = new GetMatchService();
		MatchlistDto matchlistDto = new MatchlistDto();
		matchlistDto = matchListService.getMatchList(accountID, beginIndex, endIndex, apiKey);
		JsonObject jsonObject = new JsonObject();
		JsonArray gameList = new JsonArray();
		// 해당 게임에서 사용자의 participantId
		int participantIndex = 0;
		
		for (int i=0; i<matchlistDto.getMatches().size(); i++) {
			MatchDto matchDto = new MatchDto();
			JsonObject gameObject = new JsonObject();
			matchDto = matchService.getMatchData(matchlistDto.getMatches().get(i).getGameId(), apiKey, matchDbService);
			int TotalKills100 = 0;
			int TotalKills200 = 0;
			JsonArray teamChampionIdList = new JsonArray();
			JsonObject teamChampionId100 = new JsonObject();
			JsonObject teamChampionId200 = new JsonObject();
			gameObject.addProperty("gameMode", matchDto.getGameMode());
			gameObject.addProperty("gameDuration", matchDto.getGameDuration());
			gameObject.addProperty("gameCreation", matchDto.getGameCreation());
			for (int j=0; j<matchDto.getParticipantIdentities().size(); j++) {
				if (accountID.equals(matchDto.getParticipantIdentities().get(j).getPlayer().getAccountId())){
					participantIndex = j;
					gameObject.addProperty("participantIndex", participantIndex);
					gameObject.addProperty("win", matchDto.getParticipants().get(participantIndex).getStats().getWin());
					gameObject.addProperty("kills", matchDto.getParticipants().get(participantIndex).getStats().getKills());
					gameObject.addProperty("deaths", matchDto.getParticipants().get(participantIndex).getStats().getDeaths());
					gameObject.addProperty("assists", matchDto.getParticipants().get(participantIndex).getStats().getAssists());
					gameObject.addProperty("spell1Id", matchDto.getParticipants().get(participantIndex).getSpell1Id());
					gameObject.addProperty("spell2Id", matchDto.getParticipants().get(participantIndex).getSpell2Id());
					gameObject.addProperty("perk0", matchDto.getParticipants().get(participantIndex).getStats().getPerk0());
					gameObject.addProperty("perkSubStyle", matchDto.getParticipants().get(participantIndex).getStats().getPerkSubStyle());
					gameObject.addProperty("item0", matchDto.getParticipants().get(participantIndex).getStats().getItem0());
					gameObject.addProperty("item1", matchDto.getParticipants().get(participantIndex).getStats().getItem1());
					gameObject.addProperty("item2", matchDto.getParticipants().get(participantIndex).getStats().getItem2());
					gameObject.addProperty("item3", matchDto.getParticipants().get(participantIndex).getStats().getItem3());
					gameObject.addProperty("item4", matchDto.getParticipants().get(participantIndex).getStats().getItem4());
					gameObject.addProperty("item5", matchDto.getParticipants().get(participantIndex).getStats().getItem5());
					gameObject.addProperty("item6", matchDto.getParticipants().get(participantIndex).getStats().getItem6());
					gameObject.addProperty("championLevel", matchDto.getParticipants().get(participantIndex).getStats().getChampLevel());
					gameObject.addProperty("goldEarned", matchDto.getParticipants().get(participantIndex).getStats().getGoldEarned());
					gameObject.addProperty("totalMinionsKilled", matchDto.getParticipants().get(participantIndex).getStats().getTotalMinionsKilled());
					gameObject.addProperty("wardsPlaced", matchDto.getParticipants().get(participantIndex).getStats().getWardsPlaced());
					gameObject.addProperty("visionWardsBoughtInGame", matchDto.getParticipants().get(participantIndex).getStats().getVisionWardsBoughtInGame());
					gameObject.addProperty("wardsKilled", matchDto.getParticipants().get(participantIndex).getStats().getWardsKilled());					
				}
				if (matchDto.getParticipants().get(j).getTeamId().equals("100")) {
					TotalKills100 += Integer.parseInt(matchDto.getParticipants().get(j).getStats().getKills());
					teamChampionId100.addProperty(String.valueOf(j), matchDto.getParticipants().get(j).getChampionId());
				}else {
					TotalKills200 += Integer.parseInt(matchDto.getParticipants().get(j).getStats().getKills());
					teamChampionId200.addProperty(String.valueOf(j), matchDto.getParticipants().get(j).getChampionId());
				}
			}
			// 킬관여율
			int participation = calcParticipation(Integer.parseInt(matchDto.getParticipants().get(participantIndex).getStats().getKills()),
					Integer.parseInt(matchDto.getParticipants().get(participantIndex).getStats().getAssists()), 
					matchDto.getParticipants().get(participantIndex).getTeamId().equals("100")? TotalKills100:TotalKills200);
			gameObject.addProperty("participation", participation);
			// 팀 챔피언 목록 배열
			teamChampionIdList.add(teamChampionId100);
			teamChampionIdList.add(teamChampionId200);
			gameObject.add("teamChampionId", teamChampionIdList);
			gameList.add(gameObject);
		}
		jsonObject.add("games", gameList);
		return jsonObject;
	}
	
	public int calcParticipation(int kills, int assists, int totalKills) {
		return (kills + assists) / totalKills * 100;
	}

}
