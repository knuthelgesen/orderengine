package no.plasmid.order.gamemanagement;

import no.plasmid.order.gamemanagement.game.ticktacktoe.TicTacToe;
import no.plasmid.order.gamemanagement.model.Game;

public class GameFactory {

	public static Game create(Integer gameId, Integer creatorId, String gameType) {
		switch (gameType) {
			case "tic_tac_toe":
				return createTickTackToe(gameId, creatorId);
			default:
				throw new IllegalArgumentException("Unknown game type " + gameType);
		}
	}

	private static Game createTickTackToe(Integer gameId, Integer creatorId) {
		Game game = new TicTacToe(gameId, creatorId);
		return game;
	}
	
}
