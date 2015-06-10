package no.plasmid.order.websocket;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.gamemanagement.model.Player;

public class WebsocketAdapterRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketAdapterRepository.class);

	private static WebsocketAdapterRepository instance = new WebsocketAdapterRepository();
	
	public static WebsocketAdapterRepository getInstance() {
		return instance;
	}

	private final Map<Session, WebsocketAdapter> sessionAdapters;
	private final Map<Player, WebsocketAdapter> playerAdapters;	
	
	private WebsocketAdapterRepository() {
		sessionAdapters = new HashMap<Session, WebsocketAdapter>();
		playerAdapters = new HashMap<Player, WebsocketAdapter>();
	};
	
	public WebsocketAdapter getOrCreateAdapter(Session session) {
		LOGGER.debug("Get or create adapter for session");
		WebsocketAdapter rc = sessionAdapters.get(session);
		if (null == rc) {
			rc = new WebsocketAdapter(session);
			sessionAdapters.put(session, rc);
		}
		return rc;
	}
	
	public void removeAdapter(Session session) {
		LOGGER.debug("Remove adapter for user");
		sessionAdapters.remove(session);
	}
	
}
