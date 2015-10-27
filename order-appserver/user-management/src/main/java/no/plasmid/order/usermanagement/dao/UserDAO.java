package no.plasmid.order.usermanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.persistence.AbstractDAO;
import no.plasmid.order.usermanagement.im.UserEntity;

public class UserDAO extends AbstractDAO {
	
	private final static String QUERY_SELECT_USER_BY_ID		= "SELECT * FROM users WHERE users.user_id = ?";
	private final static String QUERY_SELECT_USER_BY_NAME	= "SELECT * FROM users WHERE users.user_name = ?";
	private final static String QUERY_INSERT_USER					=	"INSERT INTO users (users.user_name, users.user_password, users.user_role_name) VALUES (?, ?, ?)";
	private final static String QUERY_UPDATE_USER_BY_ID		= "UPDATE users SET users.user_name = ?, users.user_password = ?, users.user_role_name = ? WHERE users.user_id = ?";
	private final static String QUERY_DELETE_USER					= "DELETE FROM users WHERE users.user_id = ?";
	
	private final static String COLUMN_USER_ID				= "user_id";
	private final static String COLUMN_USER_NAME			= "user_name";
	private final static String COLUMN_USER_PASSWORD	= "user_password";
	private final static String COLUMN_USER_ROLE			= "user_role_name";
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);
	
	DataSource datasource;
	
	public UserDAO() {
		datasource = getDataSource();
	}
	
  public UserEntity readUser(Integer userId) throws SQLException {
  	try (Connection connection = datasource.getConnection();
  			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_USER_BY_ID, Statement.NO_GENERATED_KEYS);){
			statement.setInt(1, userId);
	  	
			if (!statement.execute()) {
				throw new SQLException("Could not execute statmement QUERY_SELECT_USER_BY_NAME");
			}
			ResultSet rs = statement.getResultSet();
			UserEntity rc = createUserEntityFromResultSet(rs);
			rs.close();
			return rc;
  	}
  }
  
  public UserEntity readUser(String userName) throws SQLException {
  	LOGGER.debug("Start readUser");
  	
  	try (Connection connection = datasource.getConnection();
  			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_USER_BY_NAME, Statement.NO_GENERATED_KEYS);){
			statement.setString(1, userName);
	  	
			if (!statement.execute()) {
				throw new SQLException("Could not execute statmement QUERY_SELECT_USER_BY_NAME");
			}
			ResultSet rs = statement.getResultSet();
			UserEntity rc = createUserEntityFromResultSet(rs);
			rs.close();
			return rc;
  	}
  }
  
  public Integer createUser(UserEntity userEntity) throws SQLException {
  	try (Connection connection = datasource.getConnection();
  			PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_USER, Statement.RETURN_GENERATED_KEYS);){
	  	
			statement.setString(1, userEntity.getUserName());
			statement.setString(2, userEntity.getHashedPassword());
			statement.setString(3, userEntity.getRoleName());
	
			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();
			connection.commit();
			
			if (rs.first()) {
				int generatedKey = rs.getInt(COLUMN_USER_ID);
				rs.close();
				return generatedKey;
			} else {
				throw new SQLException("Could not get generated key from QUERY_INSERT_USER");
			}
		}
  }
  
  public void updateUser(UserEntity userEntity) throws SQLException {
  	try (Connection connection = datasource.getConnection();
  			PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_USER_BY_ID, Statement.NO_GENERATED_KEYS);){
	  	
			statement.setString(1, userEntity.getUserName());
			statement.setString(2, userEntity.getHashedPassword());
			statement.setString(3, userEntity.getRoleName());
			statement.setInt(4, userEntity.getUserId());
	
			statement.execute();
			connection.commit();
		}
  }
  
	public void deleteUser(UserEntity userEntity) throws SQLException {
		try (Connection connection = datasource.getConnection();
			PreparedStatement statement = connection.prepareStatement(QUERY_DELETE_USER, Statement.NO_GENERATED_KEYS);){

			statement.setInt(1, userEntity.getUserId());

			statement.execute();
			connection.commit();
  	}
  }
		
  private UserEntity createUserEntityFromResultSet(ResultSet rs) throws SQLException {
  	UserEntity rc = null;
		if (rs.first()) {
	  	rc = new UserEntity(rs.getInt(COLUMN_USER_ID), rs.getString(COLUMN_USER_NAME),
	  			rs.getString(COLUMN_USER_PASSWORD), rs.getString(COLUMN_USER_ROLE));
		}
		return rc;
  }
  
}