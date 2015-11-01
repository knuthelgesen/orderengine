package no.plasmid.order.websocket.messagehandler;

import java.util.Map;

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
import no.plasmid.order.websocket.message.IssueOrderMessage;
import no.plasmid.order.websocket.message.Message;
import no.plasmid.order.websocket.message.ViewChangedMessage;

public class IssueOrderMessageHandler implements MessageHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(IssueOrderMessageHandler.class);
	
	private GameManagementService gameManagementService;
	
	public IssueOrderMessageHandler() {
		gameManagementService = new GameManagementService();
	}
	
	@Override
	public void handleMessage(Message message, Adapter adapter) {
		handleMessage((IssueOrderMessage)message, adapter);
	}
	
	private void handleMessage(IssueOrderMessage message, Adapter adapter) {
		LOGGER.debug("Start handling message IssueOrder");
		Player issuingPlayer = adapter.getPlayer();
		if (null != issuingPlayer) {
			Integer gameId = adapter.getGameId();
			if (null != gameId) {
				try {
					//Issue the order, get delta views back
					Map<Player, View<? extends Game>> deltaViews = gameManagementService.issueOrder(gameId, issuingPlayer, message.getOrderData());
					
					//Send delta views to all players that have entered the game
					for (Player player : deltaViews.keySet()) {
						Adapter playerAdapter = PlayerAdapterRepository.getInstance().getAdapter(player);
						if (null != playerAdapter) {
							LOGGER.debug("Found adapter for player, will send delta view message");
							playerAdapter.handleOutgoingMessage(new ViewChangedMessage(deltaViews.get(player)));
						}
					}
				} catch (GameNotFoundException | GameManagementException e) {
					LOGGER.debug(e.getMessage(), e);
				}
			} else {
				LOGGER.debug("Game ID not found on this session");
			}
		} else {
			LOGGER.debug("Player not found on this session");
		}
	}

}
