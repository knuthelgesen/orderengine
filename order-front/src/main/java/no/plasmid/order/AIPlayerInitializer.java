package no.plasmid.order;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import no.plasmid.order.gamemanagement.GameManagementException;
import no.plasmid.order.gamemanagement.GameManagementService;
import no.plasmid.order.gamemanagement.model.AIPlayer;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.websocket.AdapterRepository;
import no.plasmid.order.websocket.message.AIEnterGameMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class AIPlayerInitializer implements ServletContextListener {

	private static Logger LOGGER = LoggerFactory.getLogger(AIPlayerInitializer.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			List<Game> gameList = new GameManagementService().getGames();
			for (Game game : gameList) {
				List<Player> playerList = game.getPlayers();
				for (Player player : playerList) {
					if (player instanceof AIPlayer) {
						//Is AI, so register AI adapter
						AIAdapter adapter = new AIAdapter();
						adapter.handleIncommingMessage(new AIEnterGameMessage(game.getGameId(), (AIPlayer)player));
					}
				}
			}
		} catch (GameManagementException e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.error("AI players initialized");
	}

}
