package no.plasmid.order.gamemanagement.game.tictactoe;

import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.GameJson;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.gamemanagement.order.Order;

public class TicTacToe extends Game {

	private static final long serialVersionUID = 1L;

	private Player oPlayer;
	private Player xPlayer;
	
	public TicTacToe(Integer gameId, Integer creatorId) {
		super(gameId, creatorId);
	}

	public Player getoPlayer() {
		return oPlayer;
	}

	public void setoPlayer(Player oPlayer) {
		this.oPlayer = oPlayer;
	}

	public Player getxPlayer() {
		return xPlayer;
	}

	public void setxPlayer(Player xPlayer) {
		this.xPlayer = xPlayer;
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
