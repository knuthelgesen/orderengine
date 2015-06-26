package no.plasmid.order.gamemanagement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import no.plasmid.order.gamemanagement.dao.GameDAO;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.GameEntity;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.gamemanagement.model.View;
import no.plasmid.order.gamemanagement.order.Order;
import no.plasmid.order.usermanagement.im.User;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Resource
public class GameManagementService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameManagementService.class);
	
	private GameDAO gameDAO;
	
	public GameManagementService() {
		gameDAO = new GameDAO();
	}
	
	public List<Game> getGames() throws GameManagementException {
		LOGGER.debug("Start get games");
		
		try {
			List<GameEntity> entityList = gameDAO.readGames();
			List<Game> rc = new ArrayList<Game>();
			for (GameEntity entity : entityList) {
				rc.add(entity.getGame());
			}
			
			LOGGER.debug("End get games");
			return rc;
		} catch (SQLException e) {
    	throw new GameManagementException(e);
		}
	}

	public List<Game> getGames(User creator) throws GameManagementException {
		LOGGER.debug("Start get games");
		
		try {
			List<GameEntity> entityList = gameDAO.readGames(creator.getUserId());
			List<Game> rc = new ArrayList<Game>();
			for (GameEntity entity : entityList) {
				rc.add(entity.getGame());
			}
			
			LOGGER.debug("End get games");
			return rc;
		} catch (SQLException e) {
    	throw new GameManagementException(e);
		}
		
	}

	public Game getGame(Integer gameId) throws GameManagementException, GameNotFoundException {
		LOGGER.debug("Get game");
		return GameContainter.getInstance().getGame(gameId);
	}
	
	public Game createGame(String gameType, Map<String, String> gameData, User creator) throws GameManagementException {
		LOGGER.debug("Start create game");
		
		if (StringUtils.isEmpty(gameType)) {
			throw new IllegalArgumentException("Game type can not be empty");
		}
    if (null == creator) {
      throw new IllegalArgumentException("Game creator can not be null!");
    }
		
    try {
			//Create and store the new game
    	LOGGER.error("" + gameData.keySet());
			Game game = GameFactory.create(gameDAO.createGameId(), creator.getUserId(), gameType, gameData);
			LOGGER.debug("Created game " + game + ", inserting into database");
			GameEntity gameEntity = new GameEntity(game.getGameId(), new Date(), game.getCreatorId(), game);
			gameDAO.createGame(gameEntity);
	  	LOGGER.debug("End create game");
			return gameDAO.readGame(game.getGameId()).getGame();
    } catch (SQLException e) {
    	throw new GameManagementException(e);
		}
	}

	public Map<Player, View<? extends Game>> issueOrder(Integer gameId, Player issuingPlayer, JSONObject orderData) throws GameNotFoundException, GameManagementException {
		LOGGER.debug("Start issue order");
		
		Game game = getGame(gameId);

		Map<Player, View<? extends Game>> rc = null;
		synchronized (game) {
			//Issue the order
			Order order = game.issueOrder(issuingPlayer, orderData);
			
			//Save it
			//TODO
			
			//Create a delta view
			rc = game.generateViewsForOrder(order);
		}
  	LOGGER.debug("End issue order");
		return rc;
	}
	
}
