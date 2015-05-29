package no.plasmid.order.websocket;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import no.plasmid.order.usermanagement.im.User;
import no.plasmid.order.websocket.message.MessageTransformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint ("/ws/order")
public class OrderEndPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderEndPoint.class);
	
	private static final long AUTHENTICATION_TIME_LIMIT	= 1000L;

	private static final String USER_PROPERTY_USER	= "user";
	
	@OnOpen
	public void onOpen(Session session) {
		LOGGER.debug("Connection open");
	}
	
	@OnMessage
	public void onMessage(final String messageString, final Session session) {
		LOGGER.debug("Got message " + messageString);
		WebsocketAdapterRepository.getInstance().getOrCreateAdapter(session).handleIncommingMessage(MessageTransformer.transform(messageString));
	}
	
	@OnError
	public void onError(final Session session, final Throwable error) {
		LOGGER.error(error.getMessage(), error);
	}
	
	@OnClose
	public void onClose(final Session session, final CloseReason reason) {
		LOGGER.debug("Connection closed");
		User user = (User) session.getUserProperties().get(USER_PROPERTY_USER);
		if (null != user) {
			LOGGER.debug("Removing adapter for user " + user.getUserName());
			WebsocketAdapterRepository.getInstance().removeAdapter(user);
		}
	}
	
}
