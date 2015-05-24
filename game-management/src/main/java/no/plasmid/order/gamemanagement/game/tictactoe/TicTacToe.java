package no.plasmid.order.gamemanagement.game.tictactoe;

import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.GameJson;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.gamemanagement.order.Order;

public class TicTacToe extends Game {

	private static final long serialVersionUID = 1L;

	private Player oPlayer;
	private Player xPlayer;
	
	private Player currentTurn;
	
	public TicTacToe(Integer gameId, Integer creatorId, Player oPlayer, Player xPlayer) {
		super(gameId, creatorId);
		this.oPlayer = oPlayer;
		this.xPlayer = xPlayer;
		this.currentTurn = oPlayer;
	}

	public Player getOPlayer() {
		return oPlayer;
	}

	public Player getXPlayer() {
		return xPlayer;
	}
	
	public Player getCurrentTurn() {
		return currentTurn;
	}

	@Override
	public void handleOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameJson toJson() {
		TicTacToeJson rc = new TicTacToeJson();
		
		rc.setGameId(getGameId());
		rc.setCreatorId(getCreatorId());
		
		return rc;
	}

}
