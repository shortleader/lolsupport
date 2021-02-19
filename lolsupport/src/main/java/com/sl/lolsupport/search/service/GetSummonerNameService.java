package com.sl.lolsupport.search.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sl.lolsupport.search.dto.SummonerDto;
import com.sl.lolsupport.service.DbService;



public class GetSummonerNameService {

	
	public String test(String summonerName, String apiKey, DbService dbService) {
		SummonerDto summonerData = new SummonerDto();		
		String summonerAccountId = "";
		
		BufferedReader in = null;
		
		try {
			
			List<SummonerDto> list = new ArrayList<SummonerDto>();
			list = dbService.searchSummoner(summonerName);
			if(list != null) {
				for(int i=0;i<list.size();i++) {
					summonerData.setId(list.get(i).getId());
					summonerData.setAccountId(list.get(i).getAccountId());
				}
				summonerAccountId = summonerData.getAccountId();
				//값이 있을경우 list안에서 값을 꺼내와서 쓰면댐.
				
			} else {
				
				summonerName = summonerName.replaceAll(" ","");
				
				URL obj = new URL("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+summonerName+"?api_key="+apiKey);//호출할 url
				HttpURLConnection con = (HttpURLConnection)obj.openConnection();
				
				con.setRequestMethod("GET");
				
				in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				
				String result="";
				String line;
				while((line = in.readLine()) != null) {	
					result = result.concat(line);
				}
		
				JsonParser jsonParser = new JsonParser();
				JsonObject summoner = (JsonObject)jsonParser.parse(result);
				
				
				SummonerDto data2 = new SummonerDto();
				
				
				String name = summoner.get("name").getAsString();
				String accountId = summoner.get("accountId").getAsString();
				String puuid = summoner.get("puuid").getAsString();
				String id = summoner.get("id").getAsString();
				String profileIconId = summoner.get("profileIconId").getAsString();
				String revisionDate = summoner.get("revisionDate").getAsString();
				String summonerLevel = summoner.get("summonerLevel").getAsString();
				
				summonerAccountId = summoner.get("accountId").getAsString();
				
				data2.setName(name);
				data2.setId(id);
				data2.setAccountId(accountId);
				data2.setPuuid(puuid);
				data2.setProfileIconId(profileIconId);
				data2.setRevisionDate(revisionDate);
				data2.setSummonerLevel(summonerLevel);
				
				dbService.insertSummoner(data2);
				
				
			}
			
			//in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			summonerAccountId = null;
		} finally {
			if(in != null) 
				try {
					in.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
		}
		return summonerAccountId;
	}
}
