package no.plasmid.order.gamemanagement.model.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.GameJson;
import no.plasmid.order.gamemanagement.model.HumanPlayer;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.gamemanagement.model.View;
import no.plasmid.order.gamemanagement.order.Order;
import no.plasmid.order.usermanagement.im.User;

public class TicTacToe extends Game {
	
	private static final String SQUARE_KEY	= "square";

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
		if (!(order instanceof SelectSquareOrder)) { throw new IllegalArgumentException("Unnsuportet order type"); }

		SelectSquareOrder selectSquareOrder = (SelectSquareOrder)order;
		
		board[selectSquareOrder.getSquare()] = selectSquareOrder.getPlayerColor();
		
		if (selectSquareOrder.getPlayerColor() == PlayerColor.BLUE) {
			playerUp = redPlayer;
		} else {
			playerUp = bluePlayer;
		}
	}

	@Override
	public GameJson toJson() {
		TicTacToeJson rc = new TicTacToeJson();
		
		rc.setGameId(getGameId());
		rc.setCreatorId(getCreatorId());
		
		return rc;
	}

	@Override
	public String getType() {
		return "tic_tac_toe";
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
	
	@Override
	public List<Player> getPlayers() {
		List<Player> rc = new ArrayList<Player>();
		rc.add(bluePlayer);
		rc.add(redPlayer);
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
	public Order issueOrder(Player player, JSONObject orderData) {
		if (!player.equals(playerUp)) { throw new IllegalArgumentException("Not the players turn"); }
		if (!orderData.has(SQUARE_KEY)) { throw new IllegalArgumentException("No square specified"); }
		
		int square = orderData.getInt(SQUARE_KEY);
		if (null != board[square]) {throw new IllegalArgumentException("Square already filled in"); }
		
		PlayerColor playerColor;
		if (player.equals(bluePlayer)) {
			playerColor = PlayerColor.BLUE;
		} else {
			playerColor = PlayerColor.RED;
		}
		
		
		SelectSquareOrder rc = new SelectSquareOrder(square, playerColor);
		handleOrder(rc);
		
		return rc;
	}
	
	@Override
	public Map<Player, View<? extends Game>> generateViewsForOrder(Order order) {
		if (!(order instanceof SelectSquareOrder)) { throw new IllegalArgumentException("Unnsuportet order type"); }

		SelectSquareOrder selectSquareOrder = (SelectSquareOrder)order;
		
		Map<Player, View<? extends Game>> rc = new HashMap<Player, View<? extends Game>>();
	
		PlayerColor playerColorUp;
		if (selectSquareOrder.getPlayerColor() == PlayerColor.BLUE) {
			playerColorUp = PlayerColor.RED;
		} else {
			playerColorUp = PlayerColor.BLUE;
		}
		
		TicTacToeDeltaView deltaView = new TicTacToeDeltaView(selectSquareOrder.getPlayerColor(), playerColorUp, selectSquareOrder.getSquare());
		rc.put(bluePlayer, deltaView);
		rc.put(redPlayer, deltaView);
		
		return rc;
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
