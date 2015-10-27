package no.plasmid.order.message;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageFactory.class);

	private static final String MESSAGE_TYPE_KEY	= "messageType";
	
	/* *********************************************************************************
	 * IssueOrder keys and values
	 ***********************************************************************************/
	private static final String ORDER_DATA_KEY	= "orderData";
	
	public static Message create(JSONObject jsonObject) {
		if (!jsonObject.has(MESSAGE_TYPE_KEY)) { throw new IllegalArgumentException("No message type in JSON"); }
		//Read the message type
		final String messageType = jsonObject.getString(MESSAGE_TYPE_KEY);
		
		LOGGER.debug("Atempting to create message of type " + messageType);
		
		switch (messageType) {
		case "issueOrder":
			return createIssueOrder(jsonObject);
		default:
			throw new IllegalArgumentException("Unknown message type: " + messageType);
		}
	}

	private static Message createIssueOrder(JSONObject jsonObject) {
		if (!jsonObject.has(ORDER_DATA_KEY)) { throw new IllegalArgumentException("No order data"); }

		return new IssueOrderMessage(jsonObject.getJSONObject(ORDER_DATA_KEY));
	}

}
