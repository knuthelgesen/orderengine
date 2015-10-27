package no.plasmid.order.websocket.message;

import no.plasmid.order.message.Message;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageFactory extends no.plasmid.order.message.MessageFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageFactory.class);

	private static final String MESSAGE_TYPE_KEY	= "messageType";

	/* *********************************************************************************
	 * Authenticate keys and values
	 ***********************************************************************************/
	private static final String WS_TOKEN_KEY	= "wsToken";

	/* *********************************************************************************
	 * EnterGame keys and values
	 ***********************************************************************************/
	private static final String GAME_ID_KEY	= "gameId";
	
	public static Message create(JSONObject jsonObject) {
		if (!jsonObject.has(MESSAGE_TYPE_KEY)) { throw new IllegalArgumentException("No message type in JSON"); }
		//Read the message type
		final String messageType = jsonObject.getString(MESSAGE_TYPE_KEY);
		
		LOGGER.debug("Atempting to create message of type " + messageType);
		
		switch (messageType) {
		case "authenticate":
			return createAuthenticate(jsonObject);
		case "enterGame":
			return createEnterGame(jsonObject);
		default:
			return no.plasmid.order.message.MessageFactory.create(jsonObject);
		}
	}

	private static Message createAuthenticate(JSONObject jsonObject) {
		if (!jsonObject.has(WS_TOKEN_KEY)) { throw new IllegalArgumentException("No WS token"); }
		
		return new AuthenticateMessage(jsonObject.getString(WS_TOKEN_KEY));
	}
	
	private static Message createEnterGame(JSONObject jsonObject) {
		if (!jsonObject.has(GAME_ID_KEY)) { throw new IllegalArgumentException("No game ID"); }

		return new EnterGameMessage(jsonObject.getInt(GAME_ID_KEY));
	}

}
