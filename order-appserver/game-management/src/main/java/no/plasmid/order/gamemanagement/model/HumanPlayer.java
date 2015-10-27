package no.plasmid.order.gamemanagement.model;

import no.plasmid.order.gamemanagement.model.Player;

public class HumanPlayer extends Player {

	private static final long serialVersionUID = 1L;

	private final Integer userId;
	
	public HumanPlayer(String playerId, Integer userId) {
		super(playerId);
		
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}
	
}
