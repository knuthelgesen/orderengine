package no.plasmid.order.rest;

import java.util.HashMap;
import java.util.Map;

public class CreateGameJson {

	private String gameType;
	private Map<String, String> gameData = new HashMap<String, String>();
	
	public String getGameType() {
		return gameType;
	}
	
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	
	public Map<String, String> getGameData() {
		return gameData;
	}
	
	public void setGameData(Map<String, String> gameData) {
		this.gameData = gameData;
	}
	
}
