package com.sl.lolsupport.search.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sl.lolsupport.search.dto.MatchDto;
import com.sl.lolsupport.search.dto.MatchReferenceDto;
import com.sl.lolsupport.search.dto.ParticipantDto;
import com.sl.lolsupport.search.dto.ParticipantIdentityDto;
import com.sl.lolsupport.search.dto.TeamStatsDto;

public class GetMatchDataService {
	MatchDto matchDto = new MatchDto();
	Gson gson = new Gson();
	
	public String getMatchData(String gameId, String apiKey) {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);

		String result = restTemplate.getForObject("https://kr.api.riotgames.com/lol/match/v4/matches/" + gameId + "?api_key=" + apiKey, String.class);
		ArrayList<MatchReferenceDto> list_matches = new ArrayList<>();
		JsonParser jsonParser = null;
		try {
			JsonObject jsonObj = (JsonObject)jsonParser.parseString(result);
			matchDto.setGameId(jsonObj.get("gameId").toString());
			matchDto.setPlatformId(jsonObj.get("platformId").toString());
			matchDto.setGameCreation(jsonObj.get("gameCreation").toString());
			matchDto.setGameDuration(jsonObj.get("gameDuration").toString());
			matchDto.setQueueId(jsonObj.get("queueId").toString());
			matchDto.setMapId(jsonObj.get("mapId").toString());
			matchDto.setSeasonId(jsonObj.get("seasonId").toString());
			matchDto.setGameVersion(jsonObj.get("gameVersion").toString());
			matchDto.setGameMode(jsonObj.get("gameMode").toString());
			matchDto.setGameType(jsonObj.get("gameType").toString());
			
			// matchDto arrays
			JsonArray arrayTeams = jsonObj.getAsJsonArray("teams");
			JsonArray arrayParticipants = jsonObj.getAsJsonArray("participants");
			JsonArray arrayParticipantIdentities  = jsonObj.getAsJsonArray("participantIdentities");
			List<TeamStatsDto> teams = new ArrayList<TeamStatsDto>();
			List<ParticipantDto> participants = new ArrayList<ParticipantDto>();
			List<ParticipantIdentityDto> participantIdentities = new ArrayList<ParticipantIdentityDto>();
			
			int index = 0;
			while (index < arrayTeams.size()) {
				TeamStatsDto teamStatsDto = gson.fromJson(arrayTeams.get(index).toString(), TeamStatsDto.class);
				teams.add(teamStatsDto);
				index ++;
			}
			matchDto.setTeams(teams);
			
			index = 0;
			while (index < arrayParticipants.size()) {
				ParticipantDto participantDto = gson.fromJson(arrayParticipants.get(index).toString(), ParticipantDto.class);
				participants.add(participantDto);
				index ++;
			}
			matchDto.setParticipants(participants);

			
			index = 0;
			while (index < arrayParticipantIdentities.size()) {
				ParticipantIdentityDto participantIdentityDto = gson.fromJson(arrayParticipantIdentities.get(index).toString(), ParticipantIdentityDto.class);
				participantIdentities.add(participantIdentityDto);
				index ++;
			}
			matchDto.setParticipantIdentities(participantIdentities);

			result = matchDto.getParticipants().get(0).getStats().getTotalDamageDealtToChampions();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
