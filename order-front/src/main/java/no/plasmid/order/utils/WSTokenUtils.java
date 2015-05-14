package no.plasmid.order.utils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import no.plasmid.order.usermanagement.im.User;

public class WSTokenUtils {

	private static final long TOKEN_EXPIRATION_TIME	= 1000 * 60 * 10;	//10 minutes
	
	private static final String LOCK	= "LOCK";
	
	private static WSTokenMap storedTokens = new WSTokenMap();
	
	public static synchronized String getWSToken(final User user) {
		//Generate token
		final WSToken token = new WSToken(user);
		final String tokenString = token.getTokenString();
		
		synchronized (LOCK) {
			//Store the token
			storedTokens.put(token.getTokenString(), token);
		}
		
		return tokenString;
	}
	
	public static synchronized User checkToken(final String tokenString) {
		User rc = null;
		
		final WSToken token;
		synchronized (LOCK) {
			//Get the token
			token = storedTokens.remove(tokenString);
		}
		
		//Get the user
		if (null != token && token.getExpirationDate().after(new Date())) {
			rc = token.getUser();
		}
		return rc;
	}
	
	private static class WSToken {
		
		private final String tokenString;
		
		private final User user;
		
		private final Date expirationDate;
		
		public WSToken(final User user) {
			this.user = user;
			
			String tempTokenString;
			do {
				tempTokenString = Long.toHexString((long)(Math.random() * Long.MAX_VALUE));
			} while (storedTokens.containsKey(tempTokenString));
			tokenString = tempTokenString;
			
			expirationDate = new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME);
		}

		public String getTokenString() {
			return tokenString;
		}

		public User getUser() {
			return user;
		}

		public Date getExpirationDate() {
			return expirationDate;
		}
		
	}
	
	private static class WSTokenMap extends LinkedHashMap<String, WSToken> {
		
		private static final long serialVersionUID = 1L;

		@Override
		protected boolean removeEldestEntry(Map.Entry<String, WSToken> eldest) {
			eldest.getValue().getExpirationDate().before(new Date());
			return false;
		}
		
	}
	
}
