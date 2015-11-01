package no.plasmid.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.gamemanagement.ai.AIException;
import no.plasmid.order.gamemanagement.ai.AIExecutor;
import no.plasmid.order.websocket.message.EnterGameResponseMessage;
import no.plasmid.order.websocket.message.Message;
import no.plasmid.order.websocket.message.ViewChangedMessage;
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
		LOGGER.debug("Start handle outgoing message of type " + message.getMessageType());
		
		try {
			switch (message.getMessageType()) {
			case "enterGameResponse":
				AIExecutor.getInstance().scheduleEnteredGame(getPlayer(), getGameId(), ((EnterGameResponseMessage)message).getView());
				break;
			case "viewChanged":
				AIExecutor.getInstance().scheduleViewChanged(getPlayer(), getGameId(), ((ViewChangedMessage)message).getView());
				break;
			default:
				throw new IllegalArgumentException("Unknown message type " + message.getMessageType());
			}
		
		} catch (AIException e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		
		
		
		
//		AIExecutor.getInstance().scheduleAI(message);
		LOGGER.debug("End handle outgoing message of type " + message.getMessageType());
	}

}
