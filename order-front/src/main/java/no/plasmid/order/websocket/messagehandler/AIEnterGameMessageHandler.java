package no.plasmid.order.websocket.messagehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.AIAdapter;
import no.plasmid.order.Adapter;
import no.plasmid.order.gamemanagement.GameManagementException;
import no.plasmid.order.gamemanagement.GameManagementService;
import no.plasmid.order.gamemanagement.GameNotFoundException;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.View;
import no.plasmid.order.websocket.AdapterRepository;
import no.plasmid.order.websocket.message.AIEnterGameMessage;
import no.plasmid.order.websocket.message.EnterGameResponseMessage;
import no.plasmid.order.websocket.message.Message;

public class AIEnterGameMessageHandler implements MessageHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AIEnterGameMessageHandler.class);
	
	GameManagementService gameManagementService;
	
	public AIEnterGameMessageHandler() {
		gameManagementService = new GameManagementService();
	}
	
	@Override
	public void handleMessage(Message message, Adapter adapter) {
		handleMessage((AIEnterGameMessage)message, (AIAdapter)adapter);
	}
	
	private void handleMessage(AIEnterGameMessage message, Adapter adapter) {
		LOGGER.debug("Start handling message AIEnterGame");
		View<?> rc = null;
		try {
			//Find the game in question
			Game game = gameManagementService.getGame(message.getGameId());
			if (null != game) {
				LOGGER.debug("Found game");
				adapter.setGameId(game.getGameId());
				adapter.setPlayer(message.getPlayer());
				//Register this ai adapter as the adapter for this player, so messages can be sent to her later
				AdapterRepository.getInstance().registerPlayerAdapter(message.getPlayer(), adapter);
				rc = game.generateViewForPlayer(message.getPlayer());
			} else {
				LOGGER.debug("Could not find game with ID " + message.getGameId());
			}
		} catch (GameManagementException | GameNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		}
		adapter.handleOutgoingMessage(new EnterGameResponseMessage(rc));
	}

}
