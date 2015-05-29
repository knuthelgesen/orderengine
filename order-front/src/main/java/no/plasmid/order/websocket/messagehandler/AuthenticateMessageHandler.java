package no.plasmid.order.websocket.messagehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.usermanagement.im.User;
import no.plasmid.order.utils.WSTokenUtils;
import no.plasmid.order.websocket.WebsocketAdapter;
import no.plasmid.order.websocket.WebsocketAdapterRepository;
import no.plasmid.order.websocket.message.AuthenticateMessage;
import no.plasmid.order.websocket.message.AuthenticateResponseMessage;
import no.plasmid.order.websocket.message.Message;

public class AuthenticateMessageHandler implements MessageHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateMessageHandler.class);

	private static final String USER_PROPERTY_USER	= "user";

	@Override
	public void handleMessage(Message message, WebsocketAdapter adapter) {
		handleMessage((AuthenticateMessage)message, adapter);
	}

	public void handleMessage(AuthenticateMessage message, WebsocketAdapter adapter) {
		LOGGER.debug("Start handling message: " + message);
		User user = WSTokenUtils.checkToken(message.getWsToken());
		
		if (null != user) {
			adapter.getSession().getUserProperties().put(USER_PROPERTY_USER, user);
			WebsocketAdapterRepository.getInstance().addAdapter(adapter);
			adapter.handleOutgoingMessage(new AuthenticateResponseMessage(true));
		} else {
			adapter.handleOutgoingMessage(new AuthenticateResponseMessage(true));
		}
		
		LOGGER.debug("End handling message: " + message);
	}

}
