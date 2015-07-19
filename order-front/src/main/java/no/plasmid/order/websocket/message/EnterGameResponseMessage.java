package no.plasmid.order.websocket.message;

import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.View;
import no.plasmid.order.message.Message;

public class EnterGameResponseMessage extends Message {

	private final View<? extends Game> view;
	
	public EnterGameResponseMessage(View<? extends Game> view) {
		super("enterGameResponse");
	
		this.view = view;
	}

	public View<? extends Game> getView() {
		return view;
	}

}
