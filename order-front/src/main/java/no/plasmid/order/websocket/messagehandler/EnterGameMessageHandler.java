package no.plasmid.order.websocket.messagehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.gamemanagement.GameManagementException;
import no.plasmid.order.gamemanagement.GameManagementService;
import no.plasmid.order.gamemanagement.GameNotFoundException;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.usermanagement.im.User;
import no.plasmid.order.websocket.WebsocketAdapter;
import no.plasmid.order.websocket.message.EnterGameMessage;
import no.plasmid.order.websocket.message.Message;

public class EnterGameMessageHandler implements MessageHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateMessageHandler.class);
	
	GameManagementService gameManagementService;
	
	public EnterGameMessageHandler() {
		gameManagementService = new GameManagementService();
	}
	
	@Override
	public void handleMessage(Message message, WebsocketAdapter adapter) {
		handleMessage((EnterGameMessage)message, adapter);
	}
	
	public void handleMessage(EnterGameMessage message, WebsocketAdapter adapter) {
		//Find the game in question
		try {
			Game game = gameManagementService.getGame(message.getGameId());
			if (null == game) {
				
			}
			Player player = game.getPlayer((User)adapter.getSession().getUserProperties().get(WebsocketAdapter.USER_PROPERTY_USER));
			
		} catch (GameManagementException | GameNotFoundException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
		}
		
		//Check that the user is a player in the game
		
		//Register this websocket adapter as the adapter for this player, so messages can be sent to her later
	
	}

}
