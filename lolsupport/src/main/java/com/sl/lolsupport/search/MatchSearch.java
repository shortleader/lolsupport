package com.sl.lolsupport.search;

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
import com.sl.lolsupport.controller.MatchReferenceDto;

public class MatchSearch {
	String accountID = "TsELjZ0OuDaWz5QuFZi6YKm6OXRoxpi5xquI0ufIy6of";
	
	public String getMatchList(String apiKey) {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);
			
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
		String result = restTemplate.getForObject("https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/" + accountID + "?api_key=" + apiKey, String.class);
		ArrayList<MatchReferenceDto> list_matches = new ArrayList<>();
		JsonParser jsonParser = null;
		try {
			JsonObject jsonObj = (JsonObject)jsonParser.parseString(result);
			JsonArray jsonArray = jsonObj.getAsJsonArray("matches");
			int index = 0;
			while (index < jsonArray.size()) {
				MatchReferenceDto matchesVO = gson.fromJson(jsonArray.get(index).toString(), MatchReferenceDto.class);
				list_matches.add(matchesVO);
				index ++;
				System.out.println("Here parse" + index + " : " + matchesVO.getGameId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	public String getMatch(String gameId) {
		
		return "";
	}
}
