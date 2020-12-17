package com.sl.lolsupport.mapper;

import java.util.List;

import com.sl.lolsupport.search.dto.SummonerDto;;

public interface DbMapper {

	public List<SummonerDto> getList() throws Exception;
	
	public void insertSummoner(SummonerDto data2) throws Exception;
	
	public List<SummonerDto> searchSummoner(String name) throws Exception;
	
	
}
