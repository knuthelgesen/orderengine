package no.plasmid.order.gamemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.inject.Inject;
import javax.sql.DataSource;

import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.GameEntity;

public class GameDAO {

	private final static String QUERY_INSERT_GAME_ID			= "INSERT INTO game_id_seq";

	private final static String QUERY_SELECT_GAME_BY_ID		= "SELECT * FROM games WHERE games.game_id = ?";
	private final static String QUERY_INSERT_GAME					= "INSERT INTO games (games.game_id, games.created, games.created_by, games.game_data) VALUES (?, ?, ?, ?)";
	
	private final static String COLUMN_GAME_ID				= "game_id";
	private final static String COLUMN_CREATED				= "created";
	private final static String COLUMN_CREATED_BY			= "created_by";
	private final static String COLUMN_GAME_DATA			= "game_data";
	
	@Inject
	private DataSource datasource;

	public Integer createGameId() throws SQLException {
  	try (Connection connection = datasource.getConnection();
  			PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_GAME_ID, Statement.RETURN_GENERATED_KEYS);){
	  		
			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();
			connection.commit();
			
			if (rs.first()) {
				int generatedKey = rs.getInt(COLUMN_GAME_ID);
				rs.close();
				return generatedKey;
			} else {
				throw new SQLException("Could not get generated key from QUERY_INSERT_GAME_ID");
			}
		}
	}
	
	public GameEntity readGame(Integer gameId) throws SQLException {
  	try (Connection connection = datasource.getConnection();
  			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_GAME_BY_ID, Statement.NO_GENERATED_KEYS);){
			statement.setInt(1, gameId);
	  	
			if (!statement.execute()) {
				throw new SQLException("Could not execute statmement QUERY_SELECT_GAME_BY_ID");
			}
			ResultSet rs = statement.getResultSet();
			GameEntity rc = createGameEntityFromResultSet(rs);
			rs.close();
			return rc;
  	}
	}

	public void createGame(GameEntity gameEntity) throws SQLException {
  	try (Connection connection = datasource.getConnection();
  			PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_GAME, Statement.NO_GENERATED_KEYS);){
	  	
			statement.setInt(1, gameEntity.getGameId());
			statement.setTimestamp(2, new Timestamp(gameEntity.getCreated().getTime()));
			statement.setInt(3, gameEntity.getCreatedBy());
			statement.setObject(4, gameEntity.getGame());
	
			statement.execute();
			connection.commit();

			if (statement.getUpdateCount() != 1) {
				throw new SQLException("Could not call QUERY_INSERT_GAME");
			}
		}
	}
	
	private GameEntity createGameEntityFromResultSet(ResultSet rs) throws SQLException {
		GameEntity rc = null;
		if (rs.first()) {
	  	rc = new GameEntity(rs.getInt(COLUMN_GAME_ID), rs.getTimestamp(COLUMN_CREATED),
	  			rs.getInt(COLUMN_CREATED_BY), (Game)rs.getObject(COLUMN_GAME_DATA));
		}
		return rc;
	}
	
}
