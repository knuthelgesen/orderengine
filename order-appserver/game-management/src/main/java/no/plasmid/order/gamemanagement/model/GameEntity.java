package no.plasmid.order.gamemanagement.model;

import java.util.Date;

public class GameEntity {

	private final Integer gameId;
	private final Date created;
	private final Integer createdBy;
	private final Game game;
	
	public GameEntity(Integer gameId, Date created, Integer createdBy, Game game) {
		this.gameId = gameId;
		this.created = created;
		this.createdBy = createdBy;
		this.game = game;
	}

	public Integer getGameId() {
		return gameId;
	}

	public Date getCreated() {
		return created;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public Game getGame() {
		return game;
	}
	
}
