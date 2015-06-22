package no.plasmid.order.gamemanagement;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.gamemanagement.dao.GameDAO;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.GameEntity;

public class GameContainter {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameContainter.class);

	private static GameContainter instance = new GameContainter();
	
	public static GameContainter getInstance() {
		return instance;
	}
	
	private GameDAO gameDAO;
	
	private Map<Integer, Game> gameRepository;
	
	private GameContainter() {
		gameDAO = new GameDAO();
		
		gameRepository = new HashMap<Integer, Game>();
	}
	
	public Game getGame(int gameId) throws GameNotFoundException, GameManagementException {
		LOGGER.debug("Get game");
		if (gameRepository.containsKey(gameId)) { return gameRepository.get(gameId); }
		
		try {
			LOGGER.debug("Did not find game in containter, will try database");
			GameEntity gameEntity = gameDAO.readGame(gameId);
			if (null == gameEntity) { throw new GameNotFoundException(); }
			
			LOGGER.debug("Found game in database, will add it to containter");
			gameRepository.put(gameId, gameEntity.getGame());
			return gameEntity.getGame();
		} catch (SQLException e) {
			throw new GameManagementException(e);
		}
	}
	
}
