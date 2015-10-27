package no.plasmid.order.message;

public abstract class Message {
	
	private final String messageType;

	public Message(String messageType) {
		this.messageType = messageType;
	}
	
	public String getMessageType() {
		return messageType;
	}

}
