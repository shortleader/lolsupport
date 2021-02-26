package com.sl.lolsupport.search.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sl.lolsupport.search.dto.LeagueEntryDto;

public class FrontTierCheckService {

	final GsonBuilder gsonBuilder = new GsonBuilder();
	final Gson gson = gsonBuilder.create();
	
	public LeagueEntryDto[] TierCheck(String summonerId, String apiKey) {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);	
		
		String result = restTemplate.getForObject("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerId +"?api_key="+apiKey, String.class);
		LeagueEntryDto[] leagueEntryDto = gson.fromJson(result, LeagueEntryDto[].class);
		
		return leagueEntryDto;
	}
}
