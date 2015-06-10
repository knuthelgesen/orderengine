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
	private final Map<Session, Player> sessionPlayers;
	
	private WebsocketAdapterRepository() {
		sessionAdapters = new HashMap<Session, WebsocketAdapter>();
		playerAdapters = new HashMap<Player, WebsocketAdapter>();
		sessionPlayers = new HashMap<Session, Player>();
	}
	
	public WebsocketAdapter getOrCreateAdapter(Session session) {
		LOGGER.debug("Get or create adapter for session");
		WebsocketAdapter rc = sessionAdapters.get(session);
		if (null == rc) {
			rc = new WebsocketAdapter(session);
			sessionAdapters.put(session, rc);
		}
		return rc;
	}
	
	public void registerPlayerAdapter(Player player, WebsocketAdapter adapter) {
		LOGGER.debug("Adding adapter for player " + player);
		playerAdapters.put(player, adapter);
		sessionPlayers.put(adapter.getSession(), player);
	}
	
	public void removeAdapter(Session session) {
		LOGGER.debug("Remove adapter for session");
		sessionAdapters.remove(session);
		Player player = sessionPlayers.remove(session);
		LOGGER.debug("Found player " + player + ". Removing adapter.");
		if (null != player) {playerAdapters.remove(player);}
	}
	
}
