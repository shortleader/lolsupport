package com.sl.lolsupport.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.lolsupport.mapper.MatchMapper;
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

@Service
public class MatchDbService implements MatchMapper{

	@Autowired
	MatchMapper matchMapper;

	public List<MatchDto> getMatchList(MatchDto matchDto) throws Exception {
		if(matchMapper.getMatchList(matchDto).toString().equals("[]")) {
			return null;
		} else {
			return matchMapper.getMatchList(matchDto);
		}
	}

	public List<ParticipantIdentityDto> getParticipantIdentityDtoList(MatchDto matchDto) throws Exception {
		if(matchMapper.getParticipantIdentityDtoList(matchDto).toString().equals("[]")) {
			return null;
		} else {
			return matchMapper.getParticipantIdentityDtoList(matchDto);
		}
	}

	public List<PlayerDto> getPlayerDtoList(MatchDto matchDto) throws Exception {
		if(matchMapper.getPlayerDtoList(matchDto).toString().equals("[]")) {
			return null;
		} else {
			return matchMapper.getPlayerDtoList(matchDto);
		}
	}

	public List<TeamStatsDto> getTeamStatsDtoList(MatchDto matchDto) throws Exception {
		if(matchMapper.getTeamStatsDtoList(matchDto).toString().equals("[]")) {
			return null;
		} else {
			return matchMapper.getTeamStatsDtoList(matchDto);
		}
	}

	public List<TeamBansDto> getTeamBansDtoList(MatchDto matchDto) throws Exception {
		if(matchMapper.getTeamBansDtoList(matchDto).toString().equals("[]")) {
			return null;
		} else {
			return matchMapper.getTeamBansDtoList(matchDto);
		}
	}

	public List<ParticipantDto> getParticipantDtoList(MatchDto matchDto) throws Exception {
		if(matchMapper.getParticipantDtoList(matchDto).toString().equals("[]")) {
			return null;
		} else {
			return matchMapper.getParticipantDtoList(matchDto);
		}
	}

	public List<RuneDto> getRuneDtoList(MatchDto matchDto) throws Exception {
		if(matchMapper.getRuneDtoList(matchDto).toString().equals("[]")) {
			return null;
		} else {
			return matchMapper.getRuneDtoList(matchDto);
		}
	}

	public List<ParticipantStatsDto> getParticipantStatsDtoList(MatchDto matchDto) throws Exception {
		if(matchMapper.getParticipantStatsDtoList(matchDto).toString().equals("[]")) {
			return null;
		} else {
			return matchMapper.getParticipantStatsDtoList(matchDto);
		}
	}

	public List<ParticipantTimelineDto> getParticipantTimelineDtoList(MatchDto matchDto) throws Exception {
		if(matchMapper.getParticipantTimelineDtoList(matchDto).toString().equals("[]")) {
			return null;
		} else {
			return matchMapper.getParticipantTimelineDtoList(matchDto);
		}
	}

	public List<MasteryDto> getMasteryDtoList(MatchDto matchDto) throws Exception {
		if(matchMapper.getMasteryDtoList(matchDto).toString().equals("[]")) {
			return null;
		} else {
			return matchMapper.getMasteryDtoList(matchDto);
		}
	}

	public void insertMatchList(MatchDto matchDto) throws Exception {
		// TODO Auto-generated method stub
		matchMapper.insertMatchList(matchDto);
	}

	public void insertParticipantIdentityDtoList(ParticipantIdentityDto participantIdentityDto) throws Exception {
		// TODO Auto-generated method stub
		matchMapper.insertParticipantIdentityDtoList(participantIdentityDto);
	}

	public void insertPlayerDto(PlayerDto playerDto) throws Exception {
		// TODO Auto-generated method stub
		matchMapper.insertPlayerDto(playerDto);
	}

	public void insertTeamStatsDto(TeamStatsDto teamStatsDto) throws Exception {
		// TODO Auto-generated method stub
		matchMapper.insertTeamStatsDto(teamStatsDto);
	}

	public void insertTeamBansDto(TeamBansDto teamBansDto) throws Exception {
		// TODO Auto-generated method stub
		matchMapper.insertTeamBansDto(teamBansDto);
	}

	public void insertParticipantDto(ParticipantDto participantDto) throws Exception {
		// TODO Auto-generated method stub
		matchMapper.insertParticipantDto(participantDto);
	}

	public void insertRuneDto(RuneDto runeDto) throws Exception {
		// TODO Auto-generated method stub
		matchMapper.insertRuneDto(runeDto);
	}

	public void insertParticipantStatsDto(ParticipantStatsDto participantStatsDto) throws Exception {
		// TODO Auto-generated method stub
		matchMapper.insertParticipantStatsDto(participantStatsDto);
	}

	public void insertParticipantTimelineDto(Map<String,String> participantTimelineDto) throws Exception {
		// TODO Auto-generated method stub
		matchMapper.insertParticipantTimelineDto(participantTimelineDto);
	}

	public void insertMasteryDto(MasteryDto masteryDto) throws Exception {
		// TODO Auto-generated method stub
		matchMapper.insertMasteryDto(masteryDto);
	}

	

	
	
}
