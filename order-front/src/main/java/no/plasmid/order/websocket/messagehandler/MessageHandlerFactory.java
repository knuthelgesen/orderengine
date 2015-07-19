package no.plasmid.order.websocket.messagehandler;

import no.plasmid.order.messagehandler.MessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageHandlerFactory extends no.plasmid.order.message.MessageHandlerFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerFactory.class);
	
	public static MessageHandler create(String messageType) {
		LOGGER.debug("Atempting to create message handler for message type " + messageType);
		
		switch (messageType) {
		case "authenticate":
			return createAuthenticateMessageHandler();
		case "enterGame":
			return createEnterGameMessageHandler();
		case "aiEnterGame":
			return createAIEnterGameMessageHandler();
		default:
			return no.plasmid.order.message.MessageHandlerFactory.create(messageType);
		}
	}

	private static MessageHandler createAuthenticateMessageHandler() {
		return new AuthenticateMessageHandler();
	}
	
	private static MessageHandler createEnterGameMessageHandler() {
		return new EnterGameMessageHandler();
	}

	private static MessageHandler createAIEnterGameMessageHandler() {
		return new AIEnterGameMessageHandler();
	}

}
