package no.plasmid.order.gamemanagement;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.gamemanagement.model.AIPlayer;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.HumanPlayer;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.gamemanagement.model.tictactoe.TicTacToe;

public class GameFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameFactory.class);

	/* *********************************************************************************
	 * Game types
	 ***********************************************************************************/
	public static final String GAME_TYPE_TIC_TAC_TOE	= "tic_tac_toe";
	
	/* *********************************************************************************
	 * Tic tac toe keys and values
	 ***********************************************************************************/
	private static final String USER_COLOR_KEY	= "userColor";
	private static final String BLUE_VALUE	= "blue";
	
	public static Game create(Integer gameId, Integer creatorId, String gameType, Map<String, String> gameData) {
		LOGGER.debug("Atempting to create game of type " + gameType);
		
		switch (gameType) {
			case GAME_TYPE_TIC_TAC_TOE:
				return createTickTackToe(gameId, creatorId, gameData);
			default:
				throw new IllegalArgumentException("Unknown game type " + gameType);
		}
	}

	private static Game createTickTackToe(Integer gameId, Integer creatorId, Map<String, String> gameData) {
		if (!gameData.containsKey(USER_COLOR_KEY)) { throw new IllegalArgumentException("No user color selected"); }
		
		TicTacToe game;

		Player humanPlayer = new HumanPlayer(gameId + "_1", creatorId);
		Player cpuPlayer = new AIPlayer(gameId + "_2");
		if (gameData.get(USER_COLOR_KEY).equals(BLUE_VALUE)) {
			LOGGER.debug("User will be blue");
			game = new TicTacToe(gameId, creatorId, humanPlayer, cpuPlayer);
		} else {
			LOGGER.debug("User will be red");
			game = new TicTacToe(gameId, creatorId, cpuPlayer, humanPlayer);
		}
		
		return game;
	}
	
}
