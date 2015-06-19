package no.plasmid.order.gamemanagement.model;

import java.io.Serializable;

import no.plasmid.order.gamemanagement.order.OrderListener;
import no.plasmid.order.usermanagement.im.User;

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
	
	public abstract GameJson toJson();
	
	public abstract View<?> generateViewForPlayer(Player player);

	public abstract Player getPlayer(User user);
	
}
