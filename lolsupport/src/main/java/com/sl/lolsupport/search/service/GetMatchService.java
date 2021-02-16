package com.sl.lolsupport.search.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.sl.lolsupport.search.dto.MasteryDto;
import com.sl.lolsupport.search.dto.MatchDto;
import com.sl.lolsupport.search.dto.ParticipantDto;
import com.sl.lolsupport.search.dto.ParticipantIdentityDto;
import com.sl.lolsupport.search.dto.ParticipantStatsDto;
import com.sl.lolsupport.search.dto.ParticipantTimelineDto;
import com.sl.lolsupport.search.dto.PlayerDto;
import com.sl.lolsupport.search.dto.RuneDto;
import com.sl.lolsupport.search.dto.SummonerDto;
import com.sl.lolsupport.search.dto.TeamBansDto;
import com.sl.lolsupport.search.dto.TeamStatsDto;
import com.sl.lolsupport.service.MatchDbService;

public class GetMatchService {
	MatchDto matchDto = new MatchDto();
	Gson gson = new Gson();
	
	public MatchDto getMatchData(String gameId, String apiKey, MatchDbService matchDbService) {
		matchDto.setGameId(gameId);
		List<MatchDto> list = new ArrayList<MatchDto>();
		List<ParticipantDto> participantList = new ArrayList<ParticipantDto>();
		List<ParticipantIdentityDto> participantidentityList = new ArrayList<ParticipantIdentityDto>();
		List<ParticipantStatsDto> participantstatsList = new ArrayList<ParticipantStatsDto>();
		List<ParticipantTimelineDto> participanttimelineList = new ArrayList<ParticipantTimelineDto>();
		List<PlayerDto> playerList = new ArrayList<PlayerDto>();
		List<TeamStatsDto> teamstatsList = new ArrayList<TeamStatsDto>();
		List<TeamBansDto> teambansList = new ArrayList<TeamBansDto>();
		
		
		try {
			list = matchDbService.getMatchList(matchDto);
			// matchData 
			if(list != null) {
				list = matchDbService.getMatchList(matchDto);
				participantList = matchDbService.getParticipantDtoList(matchDto);
				participantstatsList = matchDbService.getParticipantStatsDtoList(matchDto);
				participantidentityList = matchDbService.getParticipantIdentityDtoList(matchDto);
				playerList = matchDbService.getPlayerDtoList(matchDto);
				teambansList = matchDbService.getTeamBansDtoList(matchDto);
				teamstatsList = matchDbService.getTeamStatsDtoList(matchDto);
				
				for(int i=0;i<list.size();i++) {
					matchDto = list.get(i);
				}
				matchDto.setParticipants(participantList);
				matchDto.setParticipantIdentities(participantidentityList);
				for(int i=0; i<participantList.size(); i++) {
						matchDto.getParticipants().get(i).setStats(participantstatsList.get(i));
				}
				for (int i=0; i<participantidentityList.size(); i++) {
						matchDto.getParticipantIdentities().get(i).setPlayer(playerList.get(i));					
				}
				matchDto.setTeams(teamstatsList);
				for(int i=0;i<teamstatsList.size();i++) {
					matchDto.getTeams().get(i).setBans(teambansList);
				}			
			}else {
				List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
				converters.add(new FormHttpMessageConverter());
				converters.add(new StringHttpMessageConverter());
				
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.setMessageConverters(converters);
				String result = restTemplate.getForObject("https://kr.api.riotgames.com/lol/match/v4/matches/" + gameId + "?api_key=" + apiKey, String.class);
				matchDto = gson.fromJson(result, MatchDto.class);

				matchDbService.insertMatchList(matchDto);
				for (int j = 0; j < matchDto.getParticipantIdentities().size(); j++) {
					matchDto.getParticipantIdentities().get(j).setGameId(matchDto.getGameId());
					matchDto.getParticipantIdentities().get(j).getPlayer().setGameId(matchDto.getGameId());
					matchDto.getParticipantIdentities().get(j).getPlayer()
							.setParticipantId(matchDto.getParticipantIdentities().get(j).getParticipantId());

					matchDbService.insertParticipantIdentityDtoList(matchDto.getParticipantIdentities().get(j));
					matchDbService.insertPlayerDto(matchDto.getParticipantIdentities().get(j).getPlayer());

				}

				for (int j = 0; j < matchDto.getTeams().size(); j++) {
					matchDto.getTeams().get(j).setGameId(matchDto.getGameId());
					matchDbService.insertTeamStatsDto(matchDto.getTeams().get(j));

					for (int k = 0; k < matchDto.getTeams().get(j).getBans().size(); k++) {
						matchDto.getTeams().get(j).getBans().get(k).setGameId(matchDto.getGameId());
						matchDto.getTeams().get(j).getBans().get(k).setTeamId(matchDto.getTeams().get(j).getTeamId());
						matchDbService.insertTeamBansDto(matchDto.getTeams().get(j).getBans().get(k));
					}
				}

				for (int j = 0; j < matchDto.getParticipants().size(); j++) {
					matchDto.getParticipants().get(j).setGameId(matchDto.getGameId());
					matchDbService.insertParticipantDto(matchDto.getParticipants().get(j));

					// rune 부분
					/*
					 * for(int k=0;k<matchDto.getParticipants().get(j).getRunes().size();k++) {
					 * matchDto.getParticipants().get(j).getRunes().get(k).setGameId(matchDto.
					 * getGameId());
					 * matchDto.getParticipants().get(j).getRunes().get(k).setParticipantId(matchDto
					 * .getParticipants().get(j).getParticipantId());
					 * matchDbService.insertRuneDto(matchDto.getParticipants().get(j).getRunes().get
					 * (k)); }
					 */

					matchDto.getParticipants().get(j).getStats().setGameId(matchDto.getGameId());
					matchDbService.insertParticipantStatsDto(matchDto.getParticipants().get(j).getStats());

					// Timeline 부분 -- 폐기
					/*
					 * Map<String, String> participantTimeLineDto = new HashMap<String, String>();
					 * participantTimeLineDto.put("participantId",
					 * matchDto.getParticipants().get(j).getParticipantId());
					 * 
					 * String temp = ""; for (String key :
					 * matchDto.getParticipants().get(j).getTimeline().getCsDiffPerMinDeltas().
					 * keySet()) { temp = temp + key + " : " +
					 * matchDto.getParticipants().get(j).getTimeline().getCsDiffPerMinDeltas().get(
					 * key)+ " "; System.out.println(key + " : " +
					 * matchDto.getParticipants().get(j).getTimeline().getCsDiffPerMinDeltas().get(
					 * key)+ " "); } participantTimeLineDto.put("csDiffPerMinDeltas", temp);
					 * 
					 * temp = ""; for (String key :
					 * matchDto.getParticipants().get(j).getTimeline().getDamageTakenPerMinDeltas().
					 * keySet()) { temp = temp + key + " : " +
					 * matchDto.getParticipants().get(j).getTimeline().getDamageTakenPerMinDeltas().
					 * get(key)+ " "; } participantTimeLineDto.put("damageTakenPerMinDeltas", temp);
					 * participantTimeLineDto.put("role",
					 * matchDto.getParticipants().get(j).getTimeline().getRole());
					 * 
					 * temp = ""; for (String key : matchDto.getParticipants().get(j).getTimeline().
					 * getDamageTakenDiffPerMinDeltas().keySet()) { temp = temp + key + " : " +
					 * matchDto.getParticipants().get(j).getTimeline().
					 * getDamageTakenDiffPerMinDeltas().get(key)+ " "; }
					 * participantTimeLineDto.put("damageTakenDiffPerMinDeltas", "");
					 * 
					 * temp = ""; for (String key :
					 * matchDto.getParticipants().get(j).getTimeline().getXpPerMinDeltas().keySet())
					 * { temp = temp + key + " : " +
					 * matchDto.getParticipants().get(j).getTimeline().getXpPerMinDeltas().get(key)+
					 * " "; } participantTimeLineDto.put("xpPerMinDeltas", "");
					 * 
					 * temp = ""; for (String key :
					 * matchDto.getParticipants().get(j).getTimeline().getXpDiffPerMinDeltas().
					 * keySet()) { temp = temp + key + " : " +
					 * matchDto.getParticipants().get(j).getTimeline().getXpDiffPerMinDeltas().get(
					 * key)+ " "; } participantTimeLineDto.put("xpDiffPerMinDeltas", "");
					 * participantTimeLineDto.put("lane",
					 * matchDto.getParticipants().get(j).getTimeline().getLane());
					 * 
					 * temp = ""; for (String key :
					 * matchDto.getParticipants().get(j).getTimeline().getCreepsPerMinDeltas().
					 * keySet()) { temp = temp + key + " : " +
					 * matchDto.getParticipants().get(j).getTimeline().getCreepsPerMinDeltas().get(
					 * key)+ " "; } participantTimeLineDto.put("creepsPerMinDeltas", "");
					 * 
					 * temp = ""; for (String key :
					 * matchDto.getParticipants().get(j).getTimeline().getGoldPerMinDeltas().keySet(
					 * )) { temp = temp + key + " : " +
					 * matchDto.getParticipants().get(j).getTimeline().getGoldPerMinDeltas().get(key
					 * )+ " "; } participantTimeLineDto.put("goldPerMinDeltas", "");
					 * participantTimeLineDto.put("gameId", matchDto.getGameId());
					 * matchDto.getParticipants().get(j).getTimeline().setGameId(matchDto.getGameId(
					 * ));
					 * 
					 * matchDbService.insertParticipantTimelineDto(participantTimeLineDto);
					 */

					// Mastery 부분
					/*
					 * for(int k=0;k<matchDto.getParticipants().get(j).getMasteries().size();k++) {
					 * matchDto.getParticipants().get(j).getMasteries().get(k).setGameId(matchDto.
					 * getGameId());
					 * matchDto.getParticipants().get(j).getMasteries().get(k).setParticipantId(
					 * matchDto.getParticipants().get(j).getParticipantId());
					 * matchDbService.insertMasteryDto(matchDto.getParticipants().get(j).
					 * getMasteries().get(k)); }
					 */
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matchDto;
	}
}
