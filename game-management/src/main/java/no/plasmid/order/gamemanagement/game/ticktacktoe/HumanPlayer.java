package no.plasmid.order.gamemanagement.game.ticktacktoe;

import no.plasmid.order.gamemanagement.model.Player;

public class HumanPlayer extends Player {

	private final Integer userId;
	
	public HumanPlayer(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}
	
}
