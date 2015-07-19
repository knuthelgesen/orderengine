package no.plasmid.order.websocket.message;

import no.plasmid.order.message.Message;

public class AuthenticateMessage extends Message {

	private String wsToken;

	public AuthenticateMessage(String wsToken) {
		super("authenticate");
		
		this.wsToken = wsToken;
	}
	
	public String getWsToken() {
		return wsToken;
	}

}
