package no.plasmid.order.websocket.messagehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.Adapter;
import no.plasmid.order.PlayerAdapterRepository;
import no.plasmid.order.gamemanagement.GameManagementException;
import no.plasmid.order.gamemanagement.GameManagementService;
import no.plasmid.order.gamemanagement.GameNotFoundException;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.gamemanagement.model.View;
import no.plasmid.order.websocket.WebsocketAdapter;
import no.plasmid.order.websocket.message.EnterGameMessage;
import no.plasmid.order.websocket.message.EnterGameResponseMessage;
import no.plasmid.order.websocket.message.Message;

public class EnterGameMessageHandler implements MessageHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnterGameMessageHandler.class);
	
	GameManagementService gameManagementService;
	
	public EnterGameMessageHandler() {
		gameManagementService = new GameManagementService();
	}
	
	@Override
	public void handleMessage(Message message, Adapter adapter) {
		handleMessage((EnterGameMessage)message, (WebsocketAdapter)adapter);
	}
	
	private void handleMessage(EnterGameMessage message, WebsocketAdapter adapter) {
		LOGGER.debug("Start handling message EnterGame");
		View<?> rc = null;
		try {
			//Find the game in question
			Game game = gameManagementService.getGame(message.getGameId());
			if (null != game) {
				LOGGER.debug("Found game");
				//Check that the user is a player in the game
				Player player = game.getPlayer(adapter.getUser());
				if (null != player) {
					LOGGER.debug("Found player");
					adapter.setGameId(game.getGameId());
					adapter.setPlayer(player);
					//Register this websocket adapter as the adapter for this player, so messages can be sent to her later
					PlayerAdapterRepository.getInstance().registerPlayerAdapter(player, adapter);
					rc = game.generateViewForPlayer(player);
				} else {
					LOGGER.debug("Player not participating in game " + game.getGameId());
				}
			} else {
				LOGGER.debug("Could not find game with ID " + message.getGameId());
			}
		} catch (GameManagementException | GameNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		}
		adapter.handleOutgoingMessage(new EnterGameResponseMessage(rc));
	}

}
