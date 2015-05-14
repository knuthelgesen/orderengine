package no.plasmid.order.gamemanagement;

import java.sql.SQLException;
import java.util.Date;

import no.plasmid.order.gamemanagement.dao.GameDAO;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.GameEntity;
import no.plasmid.order.usermanagement.im.User;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameManagementService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameManagementService.class);
	
	private GameDAO gameDAO;
	
	public GameManagementService() {
		gameDAO = new GameDAO();
	}
	
	public Game createGame(String gameType, User creator) throws GameManagementException {
		LOGGER.debug("Start create game");
		
		if (StringUtils.isEmpty(gameType)) {
			throw new IllegalArgumentException("Game type can not be empty");
		}
    if (null == creator) {
      throw new IllegalArgumentException("Game creator can not be null!");
    }
		
    try {
			//Create and store the new game
			Game game = GameFactory.create(gameDAO.createGameId(), creator.getUserId(), gameType);
			GameEntity gameEntity = new GameEntity(game.getGameId(), new Date(), game.getCreatorId(), game);
			gameDAO.createGame(gameEntity);
	  	LOGGER.debug("End create game");
			return gameDAO.readGame(game.getGameId()).getGame();
    } catch (SQLException e) {
    	throw new GameManagementException(e);
		}
	}
	
}
