package no.plasmid.order.websocket.message;

public abstract class Message {
	
	private final String messageType;

	public Message(String messageType) {
		this.messageType = messageType;
	}
	
	public String getMessageType() {
		return messageType;
	}

}
