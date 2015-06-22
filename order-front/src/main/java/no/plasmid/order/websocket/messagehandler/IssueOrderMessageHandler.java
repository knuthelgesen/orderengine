package no.plasmid.order.websocket.messagehandler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.gamemanagement.GameManagementException;
import no.plasmid.order.gamemanagement.GameManagementService;
import no.plasmid.order.gamemanagement.GameNotFoundException;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.gamemanagement.model.View;
import no.plasmid.order.usermanagement.im.User;
import no.plasmid.order.websocket.WebsocketAdapter;
import no.plasmid.order.websocket.WebsocketAdapterRepository;
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
	public void handleMessage(Message message, WebsocketAdapter adapter) {
		handleMessage((IssueOrderMessage)message, adapter);
	}
	
	private void handleMessage(IssueOrderMessage message, WebsocketAdapter adapter) {
		LOGGER.debug("Start handling message IssueOrder");
		User user = adapter.getUser();
		if (null != user) {
			Integer gameId = adapter.getGameId();
			if (null != gameId) {
				try {
					//Issue the order, get delta views back
					Map<Player, View<? extends Game>> deltaViews = gameManagementService.issueOrder(gameId, adapter.getUser(), message.getOrderData());
					
					//Send delta views to all players that have entered the game
					for (Player player : deltaViews.keySet()) {
						WebsocketAdapter playerAdapter = WebsocketAdapterRepository.getInstance().getAdapter(player);
						if (null != playerAdapter) {
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
			LOGGER.debug("User not found on this session");
		}
	}

}
