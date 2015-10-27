package no.plasmid.order;

import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.message.Message;

public abstract class Adapter {

	private Player player;
	
	private Integer gameId;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public abstract void handleIncommingMessage(Message message);

	public abstract void handleOutgoingMessage(Message message);

}