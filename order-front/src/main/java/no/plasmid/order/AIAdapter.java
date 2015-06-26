package no.plasmid.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.websocket.message.Message;
import no.plasmid.order.websocket.messagehandler.MessageHandlerFactory;

public class AIAdapter extends Adapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AIAdapter.class);

	@Override
	public void handleIncommingMessage(Message message) {
		LOGGER.debug("Start handle incomming message of type " + message.getMessageType());
		MessageHandlerFactory.create(message.getMessageType()).handleMessage(message, this);;
		LOGGER.debug("End handle incomming message of type " + message.getMessageType());
	}

	@Override
	public void handleOutgoingMessage(Message message) {
		LOGGER.debug("AI adapter asked to handle outgoing message" + message);

	}

}
