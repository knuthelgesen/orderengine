package no.plasmid.order.usermanagement;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import no.plasmid.order.usermanagement.dao.UserDAO;
import no.plasmid.order.usermanagement.im.User;
import no.plasmid.order.usermanagement.im.UserEntity;
import no.plasmid.order.usermanagement.util.PasswordHash;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserManagementService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementService.class);
	
	static {
		try {
			SecureRandom.getInstance("SHA1PRNG").nextBytes(new byte[1]);
			LOGGER.debug("SRNG init");
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
  private UserDAO userDAO;

	public UserManagementService() {
		userDAO = new UserDAO();
	}
	
  public User createUser(String userName, String password, String roleName) throws UserManagementException {
  	LOGGER.debug("Start create user");
    if (StringUtils.isEmpty(userName)) {
      throw new IllegalArgumentException("Username can not be null!");
    }
    if (StringUtils.isEmpty(password)) {
      throw new IllegalArgumentException("Password can not be null!");
    }
    if (StringUtils.isEmpty(roleName)) {
      throw new IllegalArgumentException("Role name can not be null!");
    }
    
		try {
			UserEntity user = userDAO.readUser(userName);
			if (null != user) {
	      throw new IllegalArgumentException("User already exists!");
			}

	    //Create and store the new user
			Integer userId = userDAO.createUser(new UserEntity(null, userName, PasswordHash.createHash(password), roleName));
	  	LOGGER.debug("End create user");
	    return generateUserFromEntity(userDAO.readUser(userId));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			throw new UserManagementException(e);
		}
  }

	public User login(String userName, String password) throws UserManagementException {
  	LOGGER.debug("Start login");
    User rc = null;
    if (StringUtils.isEmpty(userName)) {
      throw new IllegalArgumentException("Username can not be null!");
    }
    if (StringUtils.isEmpty(password)) {
      throw new IllegalArgumentException("Password can not be null!");
    }
    try {
      UserEntity userEntity = userDAO.readUser(userName);
      if (null != userEntity) {
      	if (PasswordHash.validatePassword(password, userEntity.getHashedPassword())) {
        	rc = generateUserFromEntity(userEntity);
      	}
      }
    } catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			throw new UserManagementException(e);
    }

  	LOGGER.debug("End login");
    return rc;
  }
	
	public void deleteUser(String userName) throws UserManagementException {
  	LOGGER.debug("Start delete user");
    if (StringUtils.isEmpty(userName)) {
      throw new IllegalArgumentException("Username can not be null!");
    }

    try {
      UserEntity userEntity = userDAO.readUser(userName);
			userDAO.deleteUser(userEntity);
		} catch (SQLException e) {
			throw new UserManagementException(e);
		}
    
  	LOGGER.debug("End delete user");
	}
	
  public User changeUser(String userName, String password, String roleName) throws UserManagementException {
  	LOGGER.debug("Start change user");
    if (StringUtils.isEmpty(userName)) {
      throw new IllegalArgumentException("Username can not be null!");
    }
    if (StringUtils.isEmpty(password)) {
      throw new IllegalArgumentException("Password can not be null!");
    }
    if (StringUtils.isEmpty(roleName)) {
      throw new IllegalArgumentException("Role name can not be null!");
    }

    //Change the user
		try {
			UserEntity user = userDAO.readUser(userName);
			if (null == user) {
	      throw new IllegalArgumentException("User not found!");
			}
	    user.setHashedPassword(PasswordHash.createHash(password));
	    user.setRoleName(roleName);
	    userDAO.updateUser(user);
	  	LOGGER.debug("End change user");
	    return generateUserFromEntity(userDAO.readUser(user.getUserId()));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			throw new UserManagementException(e);
		}
  }


	private User generateUserFromEntity(UserEntity userEntity) {
		return new User(userEntity.getUserId(), userEntity.getUserName(), userEntity.getRoleName());
	}

}