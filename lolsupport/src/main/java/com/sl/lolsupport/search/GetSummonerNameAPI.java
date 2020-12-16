package com.sl.lolsupport.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sl.lolsupport.controller.Summoner;

public class GetSummonerNameAPI {

	Summoner summonerVO = new Summoner();
	
	public GetSummonerNameAPI(String apiKey) {
		String summonerName = "로지컬";
		
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);
		
		Gson gson = new Gson();
		
		String result = restTemplate.getForObject("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + apiKey, String.class);
		// TsELjZ0OuDaWz5QuFZi6YKm6OXRoxpi5xquI0ufIy6of
		JsonParser jsonParser = null;
		JsonElement element = jsonParser.parseString(result);
		/*
		summonerVO.setId(element.getAsJsonObject().get("id").getAsString());
		summonerVO.setAccountId(element.getAsJsonObject().get("accountId").getAsString());
		summonerVO.setPuuid(element.getAsJsonObject().get("puuid").getAsString());
		summonerVO.setName(element.getAsJsonObject().get("name").getAsString());
		summonerVO.setProfileIconId(element.getAsJsonObject().get("profileIconId").getAsString());
		summonerVO.setRevisionDate(element.getAsJsonObject().get("revisionDate").getAsString());
		summonerVO.setSummonerLevel(element.getAsJsonObject().get("summonerLevel").getAsString());
		*/
		summonerVO = gson.fromJson(result, Summoner.class);
	}
	
	public String GetSummonerAccountID() {
		return summonerVO.getAccountId();
	}
}
