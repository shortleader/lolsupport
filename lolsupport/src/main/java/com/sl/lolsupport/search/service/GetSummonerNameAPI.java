package com.sl.lolsupport.search.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.sl.lolsupport.search.dto.SummonerDto;

public class GetSummonerNameAPI {

	SummonerDto summonerVO = new SummonerDto();
	
	public GetSummonerNameAPI(String apiKey) {
		String summonerName = "로지컬";
		
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);
		
		Gson gson = new Gson();
		
		String result = restTemplate.getForObject("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + apiKey, String.class);
		summonerVO = gson.fromJson(result, SummonerDto.class);
	}
	
	public String GetSummonerAccountID() {
		return summonerVO.getAccountId();
	}
}
