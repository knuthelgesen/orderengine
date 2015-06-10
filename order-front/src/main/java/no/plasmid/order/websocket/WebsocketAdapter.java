package no.plasmid.order.websocket;

import java.io.IOException;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.websocket.message.Message;
import no.plasmid.order.websocket.message.MessageTransformer;
import no.plasmid.order.websocket.messagehandler.MessageHandlerFactory;

public class WebsocketAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketAdapter.class);
	
	public static final String USER_PROPERTY_USER	= "user";

	private final Session session;
	
	public WebsocketAdapter(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	public void handleIncommingMessage(Message message) {
		LOGGER.debug("Start handle incomming message of type " + message.getMessageType());
		MessageHandlerFactory.create(message.getMessageType()).handleMessage(message, this);;
		LOGGER.debug("End handle incomming message of type " + message.getMessageType());
	}

	public void handleOutgoingMessage(Message message) {
		LOGGER.debug("Start handle outgoing message of type " + message.getMessageType());
		if (!session.isOpen()) {throw new IllegalStateException("Trying to write to a closed session");}
		
		String messageString = MessageTransformer.transform(message);
		LOGGER.debug("Will send message text to client: " + messageString);
		try {
			session.getBasicRemote().sendText(messageString);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		LOGGER.debug("End handle outgoing message of type " + message.getMessageType());
	}

}
