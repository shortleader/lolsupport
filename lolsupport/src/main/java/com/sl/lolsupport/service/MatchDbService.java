package com.sl.lolsupport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.lolsupport.mapper.MatchMapper;

@Service
public class MatchDbService {

	@Autowired
	MatchMapper matchMapper; 
	
	
}
