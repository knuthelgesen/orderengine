package no.plasmid.order.gamemanagement;

import no.plasmid.order.gamemanagement.game.ticktacktoe.TickTackToe;
import no.plasmid.order.gamemanagement.model.Game;

public class GameFactory {

	public static Game create(Integer gameId, Integer creatorId, String gameType) {
		switch (gameType) {
			case "tick_tack_toe":
				return createTickTackToe(gameId, creatorId);
			default:
				throw new IllegalArgumentException("Unknown game type " + gameType);
		}
	}

	private static Game createTickTackToe(Integer gameId, Integer creatorId) {
		return new TickTackToe(gameId, creatorId);
	}
	
}
