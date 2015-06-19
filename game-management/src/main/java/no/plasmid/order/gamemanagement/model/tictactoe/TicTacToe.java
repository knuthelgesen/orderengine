package no.plasmid.order.gamemanagement.model.tictactoe;

import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.GameJson;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.gamemanagement.model.View;
import no.plasmid.order.gamemanagement.order.Order;
import no.plasmid.order.usermanagement.im.User;

public class TicTacToe extends Game {
	
	private static final long serialVersionUID = 1L;

	private Player bluePlayer;
	private Player redPlayer;
	
	private Player playerUp;	//The player to make a move

	private PlayerColor[] board;
	
	public TicTacToe(Integer gameId, Integer creatorId, Player bluePlayer, Player redPlayer) {
		super(gameId, creatorId);
		this.bluePlayer = bluePlayer;
		this.redPlayer = redPlayer;
		this.playerUp = bluePlayer;	//Blue always starts
		
		this.board = new PlayerColor[9];
	}

	public Player getOPlayer() {
		return bluePlayer;
	}

	public Player getXPlayer() {
		return redPlayer;
	}
	
	public Player getCurrentTurn() {
		return playerUp;
	}

	@Override
	public void handleOrder(Order order) {
		// TODO Auto-generated method stub
	}

	@Override
	public GameJson toJson() {
		TicTacToeJson rc = new TicTacToeJson();
		
		rc.setGameId(getGameId());
		rc.setCreatorId(getCreatorId());
		
		return rc;
	}

	@Override
	public Player getPlayer(User user) {
		Player rc = null;
		if (bluePlayer instanceof HumanPlayer && ((HumanPlayer) bluePlayer).getUserId().equals(user.getUserId())) {
			rc = bluePlayer;
		}
		if (redPlayer instanceof HumanPlayer && ((HumanPlayer) redPlayer).getUserId().equals(user.getUserId())) {
			rc = redPlayer;
		}
		return rc;
	}

	@Override
	public View<TicTacToe> generateViewForPlayer(Player player) {
		String playerColor;
		if (this.bluePlayer.equals(player)) {
			playerColor = "blue";
		} else {
			playerColor = "red";
		}

		String playerUp;
		if (this.playerUp.equals(bluePlayer)) {
			playerUp = "blue";
		} else {
			playerUp = "red";
		}
				
		return new TicTacToeView(playerColor, playerUp, board);
	}
	
	public enum PlayerColor {
		BLUE("blue"), RED("red");
		
		private final String value;
		
		public static PlayerColor get(String value) {
			switch (value) {
			case "blue":
				return PlayerColor.BLUE;
			case "red":
				return PlayerColor.RED;
			default:
				throw new IllegalArgumentException("Unknown value");
			}
		}
		
		private PlayerColor(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
		
	}
	
}
