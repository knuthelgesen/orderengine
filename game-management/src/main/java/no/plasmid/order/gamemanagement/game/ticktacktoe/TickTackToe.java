package no.plasmid.order.gamemanagement.game.ticktacktoe;

import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.gamemanagement.order.Order;

public class TickTackToe extends Game {

	private static final long serialVersionUID = 1L;

	private Player oPlayer;
	private Player xPlayer;
	
	public TickTackToe(Integer gameId, Integer creatorId) {
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

}
