package no.plasmid.order.websocket;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.usermanagement.im.User;

public class WebsocketAdapterRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketAdapterRepository.class);

	private static WebsocketAdapterRepository instance = new WebsocketAdapterRepository();
	
	public static WebsocketAdapterRepository getInstance() {
		return instance;
	}

	private static final String USER_PROPERTY_USER	= "user";
	
	private Map<User, WebsocketAdapter> adapters;
	
	private WebsocketAdapterRepository() {
		adapters = new HashMap<User, WebsocketAdapter>();
	};
	
	public WebsocketAdapter getOrCreateAdapter(Session session) {
		LOGGER.debug("Get or create adapter for session");
		User user = (User) session.getUserProperties().get(USER_PROPERTY_USER);
		WebsocketAdapter rc =  getAdapter(user);
		if (null == rc) {
			rc = new WebsocketAdapter(session);
		}
		return rc;
	}

	public WebsocketAdapter getAdapter(User user) {
		LOGGER.debug("Get adapter for user");
		return adapters.get(user);
	}
	
	public void addAdapter(WebsocketAdapter adapter) {
		LOGGER.debug("Add adapter for user");
		User user = (User) adapter.getSession().getUserProperties().get(USER_PROPERTY_USER);
		if (null == user) {throw new IllegalArgumentException("Will not store adapter with no authenticated user!");}
		adapters.put(user, adapter);
	}
	
	public void removeAdapter(User user) {
		LOGGER.debug("Remove adapter for user");
		adapters.remove(user);
	}
	
}
