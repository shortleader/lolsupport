package com.sl.lolsupport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.lolsupport.mapper.DbMapper;
import com.sl.lolsupport.search.dto.SummonerDto;;

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
}
