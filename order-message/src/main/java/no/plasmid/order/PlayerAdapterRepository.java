package no.plasmid.order;

import java.util.HashMap;
import java.util.Map;

import no.plasmid.order.gamemanagement.model.Player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerAdapterRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerAdapterRepository.class);

	private static PlayerAdapterRepository instance = new PlayerAdapterRepository();
	
	public static PlayerAdapterRepository getInstance() {
		return instance;
	}
	
	private final Map<String, Adapter> playerAdapters;	//<Player id, Adapter>
	
	private PlayerAdapterRepository() {
		playerAdapters = new HashMap<String, Adapter>();
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

	public void unregisterPlayerAdapter(Player player) {
		LOGGER.debug("Removing adapter for player " + player.getPlayerId());
		playerAdapters.remove(player.getPlayerId());
	}

}
