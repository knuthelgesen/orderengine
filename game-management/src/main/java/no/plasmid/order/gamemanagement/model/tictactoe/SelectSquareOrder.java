package no.plasmid.order.gamemanagement.model.tictactoe;

import no.plasmid.order.gamemanagement.model.tictactoe.TicTacToe.PlayerColor;
import no.plasmid.order.gamemanagement.order.Order;

public class SelectSquareOrder extends Order {

	private final int square;
	private final PlayerColor playerColor;
	
	public SelectSquareOrder(int square, PlayerColor playerColor) {
		this.square = square;
		this.playerColor = playerColor;
	}

	public int getSquare() {
		return square;
	}

	public PlayerColor getPlayerColor() {
		return playerColor;
	}

}
