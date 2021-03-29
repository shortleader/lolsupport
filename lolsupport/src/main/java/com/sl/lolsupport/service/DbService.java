package com.sl.lolsupport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.lolsupport.mapper.DbMapper;
import com.sl.lolsupport.search.dto.SummonerDto;
import com.sl.lolsupport.service.dto.ChampionData;
import com.sl.lolsupport.service.dto.ItemData;
import com.sl.lolsupport.service.dto.QueueTypeData;
import com.sl.lolsupport.service.dto.SpellData;;

@Service
public class DbService implements DbMapper{

	@Autowired
	DbMapper dbmapper;
	
	public List<SummonerDto> getList() throws Exception{
		
		return dbmapper.getList();
	}
	
	public void insertSummoner(SummonerDto data2) throws Exception{
		
		dbmapper.insertSummoner(data2);
	}
	
	public List<SummonerDto> searchSummoner(String name) throws Exception{
		
		if(dbmapper.searchSummoner(name).toString().equals("[]")) {
			return null;
		} else {
			return dbmapper.searchSummoner(name);
		}
	}
	
	public ChampionData searchChampion(String key) throws Exception{
		return dbmapper.searchChampion(key);
	}
	
	public SpellData searchSpell(String key) throws Exception{
		return dbmapper.searchSpell(key);
	}
	
	public QueueTypeData searchQueueType(String id) throws Exception{
		return dbmapper.searchQueueType(id);
	}
	
	public ItemData searchItem(String key) throws Exception{
		return dbmapper.searchItem(key);
	}
}
