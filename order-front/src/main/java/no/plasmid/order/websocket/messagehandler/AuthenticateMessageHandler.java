package no.plasmid.order.websocket.messagehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.Adapter;
import no.plasmid.order.usermanagement.im.User;
import no.plasmid.order.utils.WSTokenUtils;
import no.plasmid.order.websocket.WebsocketAdapter;
import no.plasmid.order.websocket.message.AuthenticateMessage;
import no.plasmid.order.websocket.message.AuthenticateResponseMessage;
import no.plasmid.order.message.Message;
import no.plasmid.order.messagehandler.MessageHandler;

public class AuthenticateMessageHandler implements MessageHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateMessageHandler.class);

	@Override
	public void handleMessage(Message message, Adapter adapter) {
		handleMessage((AuthenticateMessage)message, (WebsocketAdapter)adapter);
	}

	public void handleMessage(AuthenticateMessage message, WebsocketAdapter adapter) {
		LOGGER.debug("Start handling message: " + message);
		User user = WSTokenUtils.checkToken(message.getWsToken());
		
		if (null != user) {
			adapter.setUser(user);
			adapter.handleOutgoingMessage(new AuthenticateResponseMessage(true));
		} else {
			adapter.handleOutgoingMessage(new AuthenticateResponseMessage(true));
		}
		
		LOGGER.debug("End handling message: " + message);
	}

}
