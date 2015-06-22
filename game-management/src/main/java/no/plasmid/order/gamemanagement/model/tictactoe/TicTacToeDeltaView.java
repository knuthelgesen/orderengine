package no.plasmid.order.gamemanagement.model.tictactoe;

import no.plasmid.order.gamemanagement.model.View;
import no.plasmid.order.gamemanagement.model.tictactoe.TicTacToe.PlayerColor;

public class TicTacToeDeltaView extends View<TicTacToe> {

	private final PlayerColor playerColor;
	private final PlayerColor playerUp;
	private final int square;
	
	public TicTacToeDeltaView(PlayerColor playerColor, PlayerColor playerUp, int square) {
		this.playerColor = playerColor;
		this.playerUp = playerUp;
		this.square = square;
	}
	
	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public PlayerColor getPlayerUp() {
		return playerUp;
	}

	public int getSquare() {
		return square;
	}

	
}
