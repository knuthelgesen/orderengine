package no.plasmid.order.websocket.message;

import no.plasmid.order.gamemanagement.model.Game;

public class EnterGameResponseMessage extends Message {

	private final Game game;
	
	public EnterGameResponseMessage(Game game) {
		super("enterGameResponse");
	
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

}
