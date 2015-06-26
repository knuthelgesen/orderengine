package no.plasmid.order.gamemanagement.dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.GameEntity;
import no.plasmid.order.persistence.AbstractDAO;

public class GameDAO extends AbstractDAO {

	private final static String QUERY_INSERT_GAME_ID			= "INSERT INTO game_id_seq () VALUES ()";

	private final static String QUERY_SELECT_GAME_BY_ID						= "SELECT * FROM games WHERE games.game_id = ?";
	private final static String QUERY_SELECT_GAMES_BY_CREATOR_ID	= "SELECT * FROM games WHERE games.created_by = ?";
	private final static String QUERY_SELECT_GAMES								= "SELECT * FROM games";
	private final static String QUERY_INSERT_GAME									= "INSERT INTO games (games.game_id, games.created, games.created_by, games.game_data) VALUES (?, ?, ?, ?)";
	
	private final static String COLUMN_GAME_ID				= "game_id";
	private final static String COLUMN_CREATED				= "created";
	private final static String COLUMN_CREATED_BY			= "created_by";
	private final static String COLUMN_GAME_DATA			= "game_data";
	
	private DataSource dataSource;

	public GameDAO() {
		dataSource = getDataSource();
	}
	
	public Integer createGameId() throws SQLException {
  	try (Connection connection = dataSource.getConnection();
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
  	try (Connection connection = dataSource.getConnection();
  			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_GAME_BY_ID, Statement.NO_GENERATED_KEYS);){
			statement.setInt(1, gameId);
	  	
			if (!statement.execute()) {
				throw new SQLException("Could not execute statmement QUERY_SELECT_GAME_BY_ID");
			}
			ResultSet rs = statement.getResultSet();
			GameEntity rc = createGameEntityFromResultSet(rs);
			rs.close();
			return rc;
  	} catch (ClassNotFoundException | IOException e) {
			throw new SQLException("Could not execute statmement QUERY_SELECT_GAME_BY_ID", e);
		}
	}
	
	public List<GameEntity> readGames() throws SQLException {
  	try (Connection connection = dataSource.getConnection();
  			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_GAMES, Statement.NO_GENERATED_KEYS);){
  		
  		if (!statement.execute()) {
				throw new SQLException("Could not execute statmement QUERY_SELECT_GAME_BY_ID");
  		}
			ResultSet rs = statement.getResultSet();
			List<GameEntity> rc = createGameEntityListFromResultSet(rs);
			rs.close();
  		
			return rc;
  	} catch (ClassNotFoundException | IOException e) {
			throw new SQLException("Could not execute statmement QUERY_SELECT_GAMES_BY_CREATOR_ID", e);
		}
	}
	
	public List<GameEntity> readGames(Integer creatorId) throws SQLException {
  	try (Connection connection = dataSource.getConnection();
  			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_GAMES_BY_CREATOR_ID, Statement.NO_GENERATED_KEYS);){
  		statement.setInt(1, creatorId);
  		
  		if (!statement.execute()) {
				throw new SQLException("Could not execute statmement QUERY_SELECT_GAME_BY_ID");
  		}
			ResultSet rs = statement.getResultSet();
			List<GameEntity> rc = createGameEntityListFromResultSet(rs);
			rs.close();
  		
			return rc;
  	} catch (ClassNotFoundException | IOException e) {
			throw new SQLException("Could not execute statmement QUERY_SELECT_GAMES_BY_CREATOR_ID", e);
		}
	}
	
	public void createGame(GameEntity gameEntity) throws SQLException {
  	try (Connection connection = dataSource.getConnection();
  			PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_GAME, Statement.NO_GENERATED_KEYS);){

  		Blob blob = connection.createBlob();
  		ObjectOutputStream oos = new ObjectOutputStream(blob.setBinaryStream(1));
  		oos.writeObject(gameEntity.getGame());
  		oos.flush();
  		oos.close();
  		
			statement.setInt(1, gameEntity.getGameId());
			statement.setTimestamp(2, new Timestamp(gameEntity.getCreated().getTime()));
			statement.setInt(3, gameEntity.getCreatedBy());
			statement.setBlob(4, blob);
	
			statement.execute();
			connection.commit();

			blob.free();
			
			if (statement.getUpdateCount() != 1) {
				throw new SQLException("Could not call QUERY_INSERT_GAME");
			}
		} catch (IOException e) {
			throw new SQLException("Could not call QUERY_INSERT_GAME", e);
		}
	}
	
	private GameEntity createGameEntityFromResultSet(ResultSet rs) throws SQLException, IOException, ClassNotFoundException {
		GameEntity rc = null;
		if (rs.next()) {
			Blob blob = rs.getBlob(COLUMN_GAME_DATA);
			ObjectInputStream ois = new ObjectInputStream(blob.getBinaryStream());
			Game game = (Game)ois.readObject();
			ois.close();
			
	  	rc = new GameEntity(rs.getInt(COLUMN_GAME_ID), rs.getTimestamp(COLUMN_CREATED),
	  			rs.getInt(COLUMN_CREATED_BY), game);
		}
		
		return rc;
	}
	
	private List<GameEntity> createGameEntityListFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException, IOException {
		List<GameEntity> rc = new ArrayList<GameEntity>();
		GameEntity entity = null;
		while ((entity = createGameEntityFromResultSet(rs)) != null) {
			rc.add(entity);
			entity = null;
		}		
		return rc;
	}

}
