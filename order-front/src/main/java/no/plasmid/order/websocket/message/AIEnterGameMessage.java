package no.plasmid.order.websocket.message;

import no.plasmid.order.gamemanagement.model.AIPlayer;

public class AIEnterGameMessage extends Message {

	private final Integer gameId;
	
	private final AIPlayer player;
	
	public AIEnterGameMessage(Integer gameId, AIPlayer player) {
		super("aiEnterGame");
		
		this.gameId = gameId;
		this.player = player;
	}

	public Integer getGameId() {
		return gameId;
	}

	public AIPlayer getPlayer() {
		return player;
	}

}
