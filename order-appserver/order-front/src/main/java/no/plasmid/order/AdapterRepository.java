package no.plasmid.order;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.websocket.WebsocketAdapter;

public class AdapterRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdapterRepository.class);

	private static AdapterRepository instance = new AdapterRepository();
	
	public static AdapterRepository getInstance() {
		return instance;
	}

	private final Map<Session, WebsocketAdapter> sessionAdapters;
	private final Map<Session, Player> sessionPlayers;
	
	private AdapterRepository() {
		sessionAdapters = new HashMap<Session, WebsocketAdapter>();
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
			
	public void removeAdapter(Session session) {
		LOGGER.debug("Remove adapter for session");
		sessionAdapters.remove(session);
		Player player = sessionPlayers.remove(session);
		LOGGER.debug("Found player " + player + ". Removing adapter.");
		if (null != player) {PlayerAdapterRepository.getInstance().unregisterPlayerAdapter(player);}
	}
	
}
