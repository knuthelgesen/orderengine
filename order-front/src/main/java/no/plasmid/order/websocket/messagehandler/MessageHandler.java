package no.plasmid.order.websocket.messagehandler;

import no.plasmid.order.websocket.WebsocketAdapter;
import no.plasmid.order.websocket.message.Message;

public interface MessageHandler {

	public void handleMessage(Message message, WebsocketAdapter adapter);
	
}
