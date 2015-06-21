package no.plasmid.order.websocket.message;

import org.json.JSONObject;

public class IssueOrderMessage extends Message {

	private final JSONObject orderData;
	
	public IssueOrderMessage(JSONObject orderData) {
		super("issueOrder");
		
		this.orderData = orderData;
	}

	public JSONObject getOrderData() {
		return orderData;
	}

}
