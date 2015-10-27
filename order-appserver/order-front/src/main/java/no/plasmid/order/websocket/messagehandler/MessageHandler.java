package no.plasmid.order.websocket.messagehandler;

import no.plasmid.order.Adapter;
import no.plasmid.order.message.Message;

public interface MessageHandler {

	public void handleMessage(Message message, Adapter adapter);
	
}
