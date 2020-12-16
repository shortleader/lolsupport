package com.sl.lolsupport.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sl.lolsupport.search.GetSummonerNameAPI;
import com.sl.lolsupport.search.MatchSearch;

@Controller
public class MainController {
	final static String apiKey = "RGAPI-3bd7da20-2025-410d-8b12-b9d631760643";
	
	@RequestMapping(value="/")
	public String home(){
		return "test.html";
	}
	
	@ResponseBody
	@RequestMapping(value="/valueTest")
	public String valueTest() {
		String value = "Test String!";		
		return value;
	}
	
	@ResponseBody
	@RequestMapping(value="/APITest")
	public String apiTest() {
		
		//GetSummonerNameAPI getSummonerNameAPI = new GetSummonerNameAPI(apiKey);
		MatchSearch matchSearch = new MatchSearch();
		return matchSearch.getMatchList(apiKey); 
		
		/*
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);
		
		
		// parameter setting
		//MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		//map.add("summonerName", "안양강현우");
		
		// Set the headers
		final HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
		headers.set("Accept-Language", "ko-KR,ko;q=0.9");
		headers.set("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
		headers.set("Origin", "https://developer.riotgames.com");
		headers.set("X-Riot-Token", apiKey);
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		Gson gson = new Gson();
		
		// HttpEntity
		final HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		// REST API CALL
		//String result = restTemplate.getForObject("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/안양강현우?api_key=" + apiKey, String.class);
		String result = restTemplate.getForObject("https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/Mt2IKNKBlsYMtEX-jDDbmlzgVJjiVlhfdej8jZuB1Tky?api_key=" + apiKey, String.class);
		//Object result = restTemplate.exchange("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/안양강현우", HttpMethod.GET, entity, String.class);
		ArrayList<Matches> list_matches = new ArrayList<>();
		JsonParser jsonParser = null;
		try {
			JsonObject jsonObj = (JsonObject)jsonParser.parseString(result);
			JsonArray jsonArray = jsonObj.getAsJsonArray("matches");
			int index = 0;
			while (index < jsonArray.size()) {
				Matches matchesVO = gson.fromJson(jsonArray.get(index).toString(), Matches.class);
				list_matches.add(matchesVO);
				index ++;
				System.out.println("Here parse" + index + " : " + matchesVO.getGameId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return result.toString();
		*/
	}	
}
