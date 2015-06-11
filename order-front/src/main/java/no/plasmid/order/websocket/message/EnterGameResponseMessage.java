package no.plasmid.order.websocket.message;

import no.plasmid.order.gamemanagement.model.View;

public class EnterGameResponseMessage extends Message {

	private final View<?> view;
	
	public EnterGameResponseMessage(View<?> view) {
		super("enterGameResponse");
	
		this.view = view;
	}

	public View<?> getView() {
		return view;
	}

}
