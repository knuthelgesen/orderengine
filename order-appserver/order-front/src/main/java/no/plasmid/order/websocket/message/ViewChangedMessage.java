package no.plasmid.order.websocket.message;

import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.View;

public class ViewChangedMessage extends Message {

	private final View<? extends Game> view;
	
	public ViewChangedMessage(View<? extends Game> view) {
		super("viewChanged");
		
		this.view = view;
	}

	public View<? extends Game> getView() {
		return view;
	}

}
