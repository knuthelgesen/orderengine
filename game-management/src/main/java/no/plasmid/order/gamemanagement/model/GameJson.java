package no.plasmid.order.gamemanagement.model;

public abstract class GameJson {

	private Integer gameId;
	
	private String type;
	
	private Integer creatorId;

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
		
}
