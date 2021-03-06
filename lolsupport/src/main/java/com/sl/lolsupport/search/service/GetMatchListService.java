package com.sl.lolsupport.search.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.sl.lolsupport.search.dto.MatchlistDto;

public class GetMatchListService {
	MatchlistDto matchlistDto;
	Gson gson = new Gson();
	
	public MatchlistDto getMatchList(String accountID, int beginIndex, int endIndex, String apiKey) {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);
		try {
			String result = restTemplate.getForObject("https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/" + accountID + "?beginIndex=" + beginIndex + "&endIndex=" + endIndex + "&api_key=" + apiKey, String.class);			
			matchlistDto = gson.fromJson(result, MatchlistDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matchlistDto;
	}
}
