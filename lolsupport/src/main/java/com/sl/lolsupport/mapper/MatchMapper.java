package com.sl.lolsupport.mapper;

import java.util.List;
import java.util.Map;

import com.sl.lolsupport.search.dto.MasteryDto;
import com.sl.lolsupport.search.dto.MatchDto;
import com.sl.lolsupport.search.dto.ParticipantDto;
import com.sl.lolsupport.search.dto.ParticipantIdentityDto;
import com.sl.lolsupport.search.dto.ParticipantStatsDto;
import com.sl.lolsupport.search.dto.ParticipantTimelineDto;
import com.sl.lolsupport.search.dto.PlayerDto;
import com.sl.lolsupport.search.dto.RuneDto;
import com.sl.lolsupport.search.dto.TeamBansDto;
import com.sl.lolsupport.search.dto.TeamStatsDto;

public interface MatchMapper {

	public List<MatchDto> getMatchList(MatchDto matchDto) throws Exception;
	
	public List<ParticipantIdentityDto> getParticipantIdentityDtoList(MatchDto matchDto) throws Exception;
	
	public List<PlayerDto> getPlayerDtoList(MatchDto matchDto) throws Exception;
	
	public List<TeamStatsDto> getTeamStatsDtoList(MatchDto matchDto) throws Exception;
	
	public List<TeamBansDto> getTeamBansDtoList(MatchDto matchDto) throws Exception;
	
	public List<ParticipantDto> getParticipantDtoList(MatchDto matchDto) throws Exception;
	
	public List<RuneDto> getRuneDtoList(MatchDto matchDto) throws Exception;
	
	public List<ParticipantStatsDto> getParticipantStatsDtoList(MatchDto matchDto) throws Exception;
	
	public List<ParticipantTimelineDto> getParticipantTimelineDtoList(MatchDto matchDto) throws Exception;
	
	public List<MasteryDto> getMasteryDtoList(MatchDto matchDto) throws Exception;
	
	public void insertMatchList(MatchDto matchDto) throws Exception;
	
	public void insertParticipantIdentityDtoList(ParticipantIdentityDto participantIdentityDto) throws Exception;
	
	public void insertPlayerDto(PlayerDto playerDto) throws Exception;
	
	public void insertTeamStatsDto(TeamStatsDto teamStatsDto) throws Exception;
	
	public void insertTeamBansDto(TeamBansDto teamBansDto) throws Exception;
	
	public void insertParticipantDto(ParticipantDto participantDto) throws Exception;
	
	public void insertRuneDto(RuneDto runeDto) throws Exception;
	
	public void insertParticipantStatsDto(ParticipantStatsDto participantStatsDto) throws Exception;
	
	public void insertParticipantTimelineDto(Map<String, String> participantTimelineDto) throws Exception;
	
	public void insertMasteryDto(MasteryDto masteryDto) throws Exception;
}
