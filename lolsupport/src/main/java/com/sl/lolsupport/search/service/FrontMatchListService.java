package com.sl.lolsupport.search.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sl.lolsupport.search.dto.LeagueEntryDto;
import com.sl.lolsupport.search.dto.MatchDto;
import com.sl.lolsupport.search.dto.MatchlistDto;
import com.sl.lolsupport.service.RiotDataService;
import com.sl.lolsupport.util.TimeUtil;
import com.sl.lolsupport.service.DbService;
import com.sl.lolsupport.service.MatchDbService;

public class FrontMatchListService {
	Gson gson = new Gson();
	/*
	 * 승/패 게임모드 KDA 킬관여 스펠 팀챔피언목록 아이템 레벨/골드/CS 플레이한시간 와드
	 */
	public JsonObject GetMatchList(String accountID, int beginIndex, int endIndex, String apiKey, DbService dbService, MatchDbService matchDbService) {
		GetMatchListService matchListService = new GetMatchListService();
		GetMatchService matchService = new GetMatchService();
		RiotDataService rds = new RiotDataService();
		TimeUtil tUtil = new TimeUtil();
		
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
			JsonArray teamChampionId100 = new JsonArray();
			JsonArray teamChampionId200 = new JsonArray();
			gameObject.addProperty("gameQueueType", rds.getQueueTypeName(matchDto.getQueueId(), dbService));
			gameObject.addProperty("gameDuration", Integer.parseInt(matchDto.getGameDuration())/60+"분" );
			gameObject.addProperty("gameCreation", tUtil.UnixToUTC(matchDto.getGameCreation()));

			for (int j=0; j<matchDto.getParticipantIdentities().size(); j++) {
				if (accountID.equals(matchDto.getParticipantIdentities().get(j).getPlayer().getAccountId())){
					participantIndex = j;
					gameObject.addProperty("participantIndex", participantIndex);
					gameObject.addProperty("win", matchDto.getParticipants().get(participantIndex).getStats().getWin().equals("true") ? "승" : "패");
					gameObject.addProperty("kills", matchDto.getParticipants().get(participantIndex).getStats().getKills());
					gameObject.addProperty("deaths", matchDto.getParticipants().get(participantIndex).getStats().getDeaths());
					gameObject.addProperty("assists", matchDto.getParticipants().get(participantIndex).getStats().getAssists());
					gameObject.addProperty("spell1Id", rds.getSummonerSpellImgURL(matchDto.getParticipants().get(participantIndex).getSpell1Id(), dbService));
					gameObject.addProperty("spell2Id", rds.getSummonerSpellImgURL(matchDto.getParticipants().get(participantIndex).getSpell2Id(), dbService));
					gameObject.addProperty("perk0", matchDto.getParticipants().get(participantIndex).getStats().getPerk0());
					gameObject.addProperty("perkSubStyle", matchDto.getParticipants().get(participantIndex).getStats().getPerkSubStyle());
					gameObject.addProperty("item0", rds.getItemImgURL(matchDto.getParticipants().get(participantIndex).getStats().getItem0(), dbService));
					gameObject.addProperty("item1", rds.getItemImgURL(matchDto.getParticipants().get(participantIndex).getStats().getItem1(), dbService));
					gameObject.addProperty("item2", rds.getItemImgURL(matchDto.getParticipants().get(participantIndex).getStats().getItem2(), dbService));
					gameObject.addProperty("item3", rds.getItemImgURL(matchDto.getParticipants().get(participantIndex).getStats().getItem3(), dbService));
					gameObject.addProperty("item4", rds.getItemImgURL(matchDto.getParticipants().get(participantIndex).getStats().getItem4(), dbService));
					gameObject.addProperty("item5", rds.getItemImgURL(matchDto.getParticipants().get(participantIndex).getStats().getItem5(), dbService));
					gameObject.addProperty("item6", rds.getItemImgURL(matchDto.getParticipants().get(participantIndex).getStats().getItem6(), dbService));
					gameObject.addProperty("championLevel", matchDto.getParticipants().get(participantIndex).getStats().getChampLevel());
					gameObject.addProperty("goldEarned", matchDto.getParticipants().get(participantIndex).getStats().getGoldEarned());
					gameObject.addProperty("totalMinionsKilled", matchDto.getParticipants().get(participantIndex).getStats().getTotalMinionsKilled());
					gameObject.addProperty("wardsPlaced", matchDto.getParticipants().get(participantIndex).getStats().getWardsPlaced());
					gameObject.addProperty("visionWardsBoughtInGame", matchDto.getParticipants().get(participantIndex).getStats().getVisionWardsBoughtInGame());
					gameObject.addProperty("wardsKilled", matchDto.getParticipants().get(participantIndex).getStats().getWardsKilled());
					
				}
				if (matchDto.getParticipants().get(j).getTeamId().equals("100")) {
					TotalKills100 += Integer.parseInt(matchDto.getParticipants().get(j).getStats().getKills());
					//teamChampionId100.addProperty(String.valueOf(j), matchDto.getParticipants().get(j).getChampionId());
					teamChampionId100.add(rds.getChampionImgURL(matchDto.getParticipants().get(j).getChampionId(),dbService));
				}else {
					TotalKills200 += Integer.parseInt(matchDto.getParticipants().get(j).getStats().getKills());
					//teamChampionId200.addProperty(String.valueOf(j), matchDto.getParticipants().get(j).getChampionId());
					teamChampionId200.add(rds.getChampionImgURL(matchDto.getParticipants().get(j).getChampionId(),dbService));
				}
			}
			// 킬관여율
			float participation = calcParticipation(Integer.parseInt(matchDto.getParticipants().get(participantIndex).getStats().getKills()),
					Integer.parseInt(matchDto.getParticipants().get(participantIndex).getStats().getAssists()), 
					matchDto.getParticipants().get(participantIndex).getTeamId().equals("100")? TotalKills100:TotalKills200);
			gameObject.addProperty("participation", participation);
			// 팀 챔피언 목록 배열
			teamChampionIdList.add(teamChampionId100);
			teamChampionIdList.add(teamChampionId200);
			gameObject.add("teamChampionId", teamChampionIdList);
			
			// 자신의 챔피언 아이디
			String myChampion = "";
			if (participantIndex < 5) {
				myChampion = teamChampionId100.get(participantIndex).getAsString();
			}else {
				myChampion = teamChampionId200.get(participantIndex-5).getAsString();
			}
			gameObject.addProperty("myChampion", myChampion);
			
			gameList.add(gameObject);
		}
		jsonObject.add("games", gameList);
		return jsonObject;
	}
	
	public int calcParticipation(float kills, float assists, float totalKills) {
		if (totalKills == 0) return 0;
		return (int) ((kills + assists) / totalKills * 100);
	}

}
