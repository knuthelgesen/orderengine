package no.plasmid.order.gamemanagement.model;

import java.io.Serializable;

import no.plasmid.order.gamemanagement.order.OrderListener;

public abstract class Game implements Serializable, OrderListener {

	private static final long serialVersionUID = 1L;
	
	private final Integer gameId;
	
	private final Integer creatorId;
	
	public Game(Integer gameId, Integer creatorId) {
		this.gameId = gameId;
		this.creatorId = creatorId;
	}

	public Integer getGameId() {
		return gameId;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

}
