package no.plasmid.order.websocket;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.Adapter;
import no.plasmid.order.gamemanagement.model.Player;

public class AdapterRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdapterRepository.class);

	private static AdapterRepository instance = new AdapterRepository();
	
	public static AdapterRepository getInstance() {
		return instance;
	}

	private final Map<Session, WebsocketAdapter> sessionAdapters;
	private final Map<String, Adapter> playerAdapters;	//<Player id, Adapter>
	private final Map<Session, Player> sessionPlayers;
	
	private AdapterRepository() {
		sessionAdapters = new HashMap<Session, WebsocketAdapter>();
		playerAdapters = new HashMap<String, Adapter>();
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
	
	public Adapter getAdapter(Player player) {
		LOGGER.debug("Get adapter for player " + player.getPlayerId());
		Adapter rc = playerAdapters.get(player.getPlayerId());
		return rc;
	}
	
	public void registerPlayerAdapter(Player player, Adapter adapter) {
		LOGGER.debug("Adding adapter for player " + player.getPlayerId());
		playerAdapters.put(player.getPlayerId(), adapter);
	}
	
	public void removeAdapter(Session session) {
		LOGGER.debug("Remove adapter for session");
		sessionAdapters.remove(session);
		Player player = sessionPlayers.remove(session);
		LOGGER.debug("Found player " + player + ". Removing adapter.");
		if (null != player) {playerAdapters.remove(player.getPlayerId());}
	}
	
}
