package no.plasmid.order.gamemanagement.model.tictactoe;

import no.plasmid.order.gamemanagement.model.View;
import no.plasmid.order.gamemanagement.model.tictactoe.TicTacToe.PlayerColor;

public class TicTacToeView extends View<TicTacToe> {

	private final PlayerColor playerColor;
	private final PlayerColor playerUp;
	private final PlayerColor[] board;
	
	public TicTacToeView(String playerColor, String playerUp, PlayerColor[] board) {
		this.playerColor = PlayerColor.get(playerColor);
		this.playerUp = PlayerColor.get(playerUp);
		this.board = board.clone();
	}
	
	public PlayerColor getPlayerUp() {
		return playerUp;
	}
	
	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public PlayerColor[] getBoard() {
		return board;
	}
	
}
