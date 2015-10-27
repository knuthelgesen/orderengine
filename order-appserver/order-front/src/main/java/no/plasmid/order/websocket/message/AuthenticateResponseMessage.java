package no.plasmid.order.websocket.message;

import no.plasmid.order.message.Message;

public class AuthenticateResponseMessage extends Message {

	private final boolean accepted;
	
	public AuthenticateResponseMessage(boolean accepted) {
		super("authenticateResponse");
		
		this.accepted = accepted;
	}

	public boolean isAccepted() {
		return accepted;
	}

}
