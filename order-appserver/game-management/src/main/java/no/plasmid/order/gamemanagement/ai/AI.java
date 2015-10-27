package no.plasmid.order.gamemanagement.ai;

import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.gamemanagement.model.View;

public abstract class AI<T extends Game> {

	public abstract void enteredGame(Integer gameId, Player player, View<? extends Game> view);

}
