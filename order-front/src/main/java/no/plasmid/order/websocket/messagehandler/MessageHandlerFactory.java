package no.plasmid.order.websocket.messagehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageHandlerFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerFactory.class);
	
	public static MessageHandler create(String messageType) {
		LOGGER.debug("Atempting to create message handler for message type " + messageType);
		
		switch (messageType) {
		case "authenticate":
			return createAuthenticateMessageHandler();
		default:
			throw new IllegalArgumentException("Unknown message type: " + messageType);
		}
	}

	private static MessageHandler createAuthenticateMessageHandler() {
		return new AuthenticateMessageHandler();
	}
	
}
