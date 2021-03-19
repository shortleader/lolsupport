package com.sl.lolsupport.mapper;

import java.util.List;

import com.sl.lolsupport.search.dto.SummonerDto;
import com.sl.lolsupport.service.dto.ChampionData;
import com.sl.lolsupport.service.dto.QueueTypeData;
import com.sl.lolsupport.service.dto.SpellData;;

public interface DbMapper {

	public List<SummonerDto> getList() throws Exception;
	
	public void insertSummoner(SummonerDto data2) throws Exception;
	
	public List<SummonerDto> searchSummoner(String name) throws Exception;

	public ChampionData searchChampion(String key) throws Exception;
	
	public SpellData searchSpell(String key) throws Exception;
	
	public QueueTypeData searchQueueType(String id) throws Exception;
	
}
