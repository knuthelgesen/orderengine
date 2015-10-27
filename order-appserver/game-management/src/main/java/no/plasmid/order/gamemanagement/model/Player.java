package no.plasmid.order.gamemanagement.model;

import java.io.Serializable;

public abstract class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String playerId;

	public Player(String playerId) {
		this.playerId = playerId;
	}
	
	public String getPlayerId() {
		return playerId;
	}
		
}
