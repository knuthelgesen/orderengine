package no.plasmid.order.gamemanagement.ai;

import no.plasmid.order.gamemanagement.GameFactory;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.tictactoe.TicTacToeAI;

public class AIFactory {

	public static AI<? extends Game> create(Game game) {
		switch (game.getType()) {
		case GameFactory.GAME_TYPE_TIC_TAC_TOE:
			return new TicTacToeAI();
		default:
			throw new IllegalArgumentException("Unknown game type " + game.getType());
		}
	}
	
}
