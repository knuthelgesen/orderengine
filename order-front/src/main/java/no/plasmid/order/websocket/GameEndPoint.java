package no.plasmid.order.websocket;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint ("/ws/order")
public class GameEndPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameEndPoint.class);
	
	@OnOpen
	public void onOpen(Session session) {
		LOGGER.debug("Connection open");
	}
	
	@OnMessage
	public void onMessage(final String messageString, final Session session) {
		LOGGER.debug("Got message" + messageString);
	}
	
	@OnClose
	public void onClose(final Session session, final CloseReason reason) {
		LOGGER.debug("Connection closed");
	}
	
}
