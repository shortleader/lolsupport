package com.sl.lolsupport.search.dto;

import java.util.List;

public class MatchlistDto {
	private String startindex;
	private String totalGame;
	private String endIndex;
	private List<MatchReferenceDto> matches;
	
	public String getStartindex() {
		return startindex;
	}
	public void setStartindex(String startindex) {
		this.startindex = startindex;
	}
	public String getTotalGame() {
		return totalGame;
	}
	public void setTotalGame(String totalGame) {
		this.totalGame = totalGame;
	}
	public String getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(String endIndex) {
		this.endIndex = endIndex;
	}
	public List<MatchReferenceDto> getMatches() {
		return matches;
	}
	public void setMatches(List<MatchReferenceDto> matches) {
		this.matches = matches;
	}
}
