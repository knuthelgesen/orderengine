package no.plasmid.order.gamemanagement.model.tictactoe;

import no.plasmid.order.gamemanagement.model.Player;

public class HumanPlayer extends Player {

	private static final long serialVersionUID = 1L;

	private final Integer userId;
	
	public HumanPlayer(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}
	
}
