package no.plasmid.order.gamemanagement.model.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.gamemanagement.ai.AI;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.gamemanagement.model.View;
import no.plasmid.order.gamemanagement.model.tictactoe.TicTacToe.PlayerColor;

public class TicTacToeAI extends AI<TicTacToe> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TicTacToeAI.class);
	
	PlayerColor playerColor;
	PlayerColor[] board;
	
	@Override
	public void enteredGame(Integer gameId, Player player, View<? extends Game> view) {
		if (!(view instanceof TicTacToeView)) {throw new IllegalArgumentException("View is not of correct type");}
		
		TicTacToeView ticTacToeView = (TicTacToeView)view;
		playerColor = ticTacToeView.getPlayerColor();
		board = ticTacToeView.getBoard();
		
		if (playerColor.equals(ticTacToeView.getPlayerUp())) {
			LOGGER.debug("AI's turn is up. Will perform next move");
		}
	}

	
	
}
