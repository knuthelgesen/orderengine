package no.plasmid.order.websocket;

import java.io.IOException;

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
	
	private static final long AUTHENTICATION_TIME_LIMIT	= 5000L;

	private static final String USER_PROPERTY_USER	= "user";
	
	@OnOpen
	public void onOpen(Session session) {
		LOGGER.debug("Connection open");

		new CheckAuthenticationThread(session).start();
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
	
	/*
	 * Used to ensure that user authenticates within the set time limit
	 */
	private class CheckAuthenticationThread extends Thread {
		private String LOCK	= "LOCK";
	
		private final Session session;
		
		public CheckAuthenticationThread(Session session) {
			this.session = session;
		}
	
		@Override
		public void run() {
			//Wait for the time limit
			synchronized (LOCK) {
				try {
					LOCK.wait(AUTHENTICATION_TIME_LIMIT);
				} catch (InterruptedException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
			
			//Check that the user is authenticated
			if (null == session.getUserProperties().get(USER_PROPERTY_USER) && session.isOpen()) {
				try {
					//No? Then close the session
					LOGGER.debug("User not authenticated. Will close session");
					session.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
			
		}
		
	}
	
}
