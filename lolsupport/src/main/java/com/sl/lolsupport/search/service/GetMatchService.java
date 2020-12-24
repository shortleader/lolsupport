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

public class GetMatchService {
	MatchDto matchDto = new MatchDto();
	Gson gson = new Gson();
	
	public String getMatchData(String gameId, String apiKey) {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);

		String result = restTemplate.getForObject("https://kr.api.riotgames.com/lol/match/v4/matches/" + gameId + "?api_key=" + apiKey, String.class);
		JsonParser jsonParser = null;
		try {
			JsonObject jsonObj = (JsonObject)jsonParser.parseString(result);
			matchDto = gson.fromJson(result, MatchDto.class);
			result = matchDto.getParticipants().get(0).getStats().getTotalDamageDealtToChampions();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
