package no.plasmid.order.websocket.message;

public class EnterGameMessage extends Message {

	private final Integer gameId;
	
	public EnterGameMessage(Integer gameId) {
		super("enterGame");
		
		this.gameId = gameId;
	}

	public Integer getGameId() {
		return gameId;
	}

}
