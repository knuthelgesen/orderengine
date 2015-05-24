package no.plasmid.order.gamemanagement;

import java.util.Map;

import no.plasmid.order.gamemanagement.game.tictactoe.CPUPlayer;
import no.plasmid.order.gamemanagement.game.tictactoe.HumanPlayer;
import no.plasmid.order.gamemanagement.game.tictactoe.TicTacToe;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.Player;

public class GameFactory {

	public static Game create(Integer gameId, Integer creatorId, String gameType, Map<String, String> gameData) {
		switch (gameType) {
			case "tic_tac_toe":
				return createTickTackToe(gameId, creatorId, gameData);
			default:
				throw new IllegalArgumentException("Unknown game type " + gameType);
		}
	}

	private static Game createTickTackToe(Integer gameId, Integer creatorId, Map<String, String> gameData) {
		if (!gameData.containsKey("userColor")) { throw new IllegalArgumentException("No user color selected"); }
		
		TicTacToe game;

		Player humanPlayer = new HumanPlayer(creatorId);
		Player cpuPlayer = new CPUPlayer();
		if (gameData.get("userColor").equals("blue")) {
			game = new TicTacToe(gameId, creatorId, humanPlayer, cpuPlayer);
		} else {
			game = new TicTacToe(gameId, creatorId, cpuPlayer, humanPlayer);
		}
		
		return game;
	}
	
}
