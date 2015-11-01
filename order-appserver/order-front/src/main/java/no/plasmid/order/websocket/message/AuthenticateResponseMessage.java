package no.plasmid.order.websocket.message;


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
