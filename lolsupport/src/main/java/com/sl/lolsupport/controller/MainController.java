package com.sl.lolsupport.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sl.lolsupport.search.dto.MatchDto;
import com.sl.lolsupport.search.service.GetMatchListService;
import com.sl.lolsupport.search.service.GetMatchService;
import com.sl.lolsupport.search.service.GetSummonerNameService;
import com.sl.lolsupport.service.DbService;
import com.sl.lolsupport.service.MatchDbService;

@Controller
public class MainController {
	final static String apiKey = "RGAPI-4350837d-9930-434f-b970-bc30009fe76f";
	
	@Autowired(required=true)
	@Resource(name="dbService")
	DbService dbService = new DbService();
	
	@Autowired(required=true)
	@Resource(name="matchDbService")
	MatchDbService matchDbService = new MatchDbService();
	
	@RequestMapping(value="/")
	public String home(){
		return "test";
	}
	
	@ResponseBody
	@RequestMapping(value="/valueTest")
	public String valueTest() {
		String value = "Test String!";		
		return value;
	}
	
	@ResponseBody
	@RequestMapping(value="/dbTest")
	public void searchSummoner() {
		GetSummonerNameService NameService = new GetSummonerNameService();
		String name = "안양 정재훈";
		String accountId ="";
		 accountId = NameService.test(name, apiKey, dbService);
		 
		 System.out.println(accountId);
	}
	@ResponseBody
	@RequestMapping(value="/APITest")
	public String apiTest() throws Exception {
		String[] matchId;
		MatchDto matchDto = new MatchDto();
		//GetSummonerNameAPI getSummonerNameAPI = new GetSummonerNameAPI(apiKey);
		//return getSummonerNameAPI.GetSummonerAccountID();
		GetMatchListService matchSearch = new GetMatchListService();
		GetMatchService matchService = new GetMatchService();
		
		 matchId = matchSearch.getMatchList("fcMVfAmn911RWWmtBb0KH6GjygXwk9L_WajPh5KbTYbo",10 ,apiKey).split("\n");
		 for(int i=0;i<matchId.length;i++) {
			matchDto = matchService.getMatchData(matchId[i], apiKey);
			
			//matchDto2 = matchDbService.getMatchList(matchDto);
			matchDbService.insertMatchList(matchDto);
			for(int j=0;j<matchDto.getParticipantIdentities().size();j++) {
				matchDto.getParticipantIdentities().get(j).setGameId(matchDto.getGameId());
				matchDto.getParticipantIdentities().get(j).getPlayer().setGameId(matchDto.getGameId());
				matchDto.getParticipantIdentities().get(j).getPlayer().setParticipantId(matchDto.getParticipantIdentities().get(j).getParticipantId());
				
				matchDbService.insertParticipantIdentityDtoList(matchDto.getParticipantIdentities().get(j));
				matchDbService.insertPlayerDto(matchDto.getParticipantIdentities().get(j).getPlayer());
				
			}
			
			
			for(int j=0;j<matchDto.getTeams().size();j++) {
				matchDto.getTeams().get(j).setGameId(matchDto.getGameId());
				matchDbService.insertTeamStatsDto(matchDto.getTeams().get(j));
				
				for(int k=0;k<matchDto.getTeams().get(j).getBans().size();k++) {
					matchDto.getTeams().get(j).getBans().get(k).setGameId(matchDto.getGameId());
					matchDto.getTeams().get(j).getBans().get(k).setTeamId(matchDto.getTeams().get(j).getTeamId());
					matchDbService.insertTeamBansDto(matchDto.getTeams().get(j).getBans().get(k));
				}
			}
			
			for(int j=0;j<matchDto.getParticipants().size();j++) {
				matchDto.getParticipants().get(j).setGameId(matchDto.getGameId());
				matchDbService.insertParticipantDto(matchDto.getParticipants().get(j));
				
				// rune 부분
				/*for(int k=0;k<matchDto.getParticipants().get(j).getRunes().size();k++) {
					matchDto.getParticipants().get(j).getRunes().get(k).setGameId(matchDto.getGameId());
					matchDto.getParticipants().get(j).getRunes().get(k).setParticipantId(matchDto.getParticipants().get(j).getParticipantId());
					matchDbService.insertRuneDto(matchDto.getParticipants().get(j).getRunes().get(k));
				}*/
				
				matchDto.getParticipants().get(j).getStats().setGameId(matchDto.getGameId());
				matchDbService.insertParticipantStatsDto(matchDto.getParticipants().get(j).getStats());
				
				// Timeline 부분 -- 수정 필요
				Map<String, String> participantTimeLineDto = new HashMap<String, String>();
				participantTimeLineDto.put("participantId", matchDto.getParticipants().get(j).getParticipantId());
				
				String temp = "";
				for (String key : matchDto.getParticipants().get(j).getTimeline().getCsDiffPerMinDeltas().keySet()) {
					temp = temp + key + " : " + matchDto.getParticipants().get(j).getTimeline().getCsDiffPerMinDeltas().get(key)+ " ";
				}
				participantTimeLineDto.put("csDiffPerMinDeltas", temp);
				
				temp = "";
				for (String key : matchDto.getParticipants().get(j).getTimeline().getDamageTakenPerMinDeltas().keySet()) {
					temp = temp + key + " : " + matchDto.getParticipants().get(j).getTimeline().getDamageTakenPerMinDeltas().get(key)+ " ";
				}
				participantTimeLineDto.put("damageTakenPerMinDeltas", temp);
				participantTimeLineDto.put("role", matchDto.getParticipants().get(j).getTimeline().getRole());
				
				temp = "";
				for (String key : matchDto.getParticipants().get(j).getTimeline().getDamageTakenDiffPerMinDeltas().keySet()) {
					temp = temp + key + " : " + matchDto.getParticipants().get(j).getTimeline().getDamageTakenDiffPerMinDeltas().get(key)+ " ";
				}				
				participantTimeLineDto.put("damageTakenDiffPerMinDeltas", "");
				
				temp = "";
				for (String key : matchDto.getParticipants().get(j).getTimeline().getXpPerMinDeltas().keySet()) {
					temp = temp + key + " : " + matchDto.getParticipants().get(j).getTimeline().getXpPerMinDeltas().get(key)+ " ";
				}				
				participantTimeLineDto.put("xpPerMinDeltas", "");
				
				temp = "";
				for (String key : matchDto.getParticipants().get(j).getTimeline().getXpDiffPerMinDeltas().keySet()) {
					temp = temp + key + " : " + matchDto.getParticipants().get(j).getTimeline().getXpDiffPerMinDeltas().get(key)+ " ";
				}				
				participantTimeLineDto.put("xpDiffPerMinDeltas", "");
				participantTimeLineDto.put("lane", matchDto.getParticipants().get(j).getTimeline().getLane());
				
				temp = "";
				for (String key : matchDto.getParticipants().get(j).getTimeline().getCreepsPerMinDeltas().keySet()) {
					temp = temp + key + " : " + matchDto.getParticipants().get(j).getTimeline().getCreepsPerMinDeltas().get(key)+ " ";
				}				
				participantTimeLineDto.put("creepsPerMinDeltas", "");
				
				temp = "";
				for (String key : matchDto.getParticipants().get(j).getTimeline().getGoldPerMinDeltas().keySet()) {
					temp = temp + key + " : " + matchDto.getParticipants().get(j).getTimeline().getGoldPerMinDeltas().get(key)+ " ";
				}				
				participantTimeLineDto.put("goldPerMinDeltas", "");
				participantTimeLineDto.put("gameId", matchDto.getGameId());
				matchDto.getParticipants().get(j).getTimeline().setGameId(matchDto.getGameId());
				
				matchDbService.insertParticipantTimelineDto(participantTimeLineDto);
				
				// Mastery 부분
				/*for(int k=0;k<matchDto.getParticipants().get(j).getMasteries().size();k++) {
					matchDto.getParticipants().get(j).getMasteries().get(k).setGameId(matchDto.getGameId());
					matchDto.getParticipants().get(j).getMasteries().get(k).setParticipantId(matchDto.getParticipants().get(j).getParticipantId());
					matchDbService.insertMasteryDto(matchDto.getParticipants().get(j).getMasteries().get(k));
				}*/
			}
			
			
		 }
		
		return matchSearch.getMatchList("4o1xIuqfmj5SxXy2h46JmxW6-_tqE9DftIiyDPE1qCrE",10 ,apiKey);
		
		
		//GetMatchService getMatchDataServcie = new GetMatchService();
		//return getMatchDataServcie.getMatchData("4860990250", apiKey);
		
		
		
		/* backup
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
