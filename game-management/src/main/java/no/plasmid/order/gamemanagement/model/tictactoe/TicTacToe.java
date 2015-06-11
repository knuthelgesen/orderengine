package no.plasmid.order.gamemanagement.model.tictactoe;

import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.GameJson;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.gamemanagement.model.View;
import no.plasmid.order.gamemanagement.order.Order;
import no.plasmid.order.usermanagement.im.User;

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

	@Override
	public Player getPlayer(User user) {
		Player rc = null;
		if (oPlayer instanceof HumanPlayer && ((HumanPlayer) oPlayer).getUserId().equals(user.getUserId())) {
			rc = oPlayer;
		}
		if (xPlayer instanceof HumanPlayer && ((HumanPlayer) xPlayer).getUserId().equals(user.getUserId())) {
			rc = xPlayer;
		}
		return rc;
	}

	@Override
	public View<TicTacToe> toView() {
		// TODO Auto-generated method stub
		return new TicTacToeView();
	}

}
