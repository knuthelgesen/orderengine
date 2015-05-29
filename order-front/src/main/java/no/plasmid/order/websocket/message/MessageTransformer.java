package no.plasmid.order.websocket.message;

import org.json.JSONObject;

public class MessageTransformer {

	public static Message transform(String jsonText) {
		final JSONObject jsonObject = new JSONObject(jsonText);
		return MessageFactory.create(jsonObject);
	}

	public static String transform(Message message) {
		return new JSONObject(message).toString();
	}
	
}
