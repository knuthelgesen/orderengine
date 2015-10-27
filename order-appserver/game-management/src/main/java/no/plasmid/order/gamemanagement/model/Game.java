package no.plasmid.order.gamemanagement.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import no.plasmid.order.gamemanagement.order.Order;
import no.plasmid.order.gamemanagement.order.OrderListener;
import no.plasmid.order.usermanagement.im.User;

public abstract class Game implements Serializable, OrderListener {

	private static final long serialVersionUID = 1L;
	
	private final Integer gameId;
	
	private final Integer creatorId;
	
	public Game(Integer gameId, Integer creatorId) {
		this.gameId = gameId;
		this.creatorId = creatorId;
	}
	
	public Integer getGameId() {
		return gameId;
	}
	
	public Integer getCreatorId() {
		return creatorId;
	}
	
	/**
	 * Get the type of game.
	 * 
	 * @return
	 */
	public abstract String getType();

	public abstract GameJson toJson();

	/**
	 * Will generate a {@link View} representing the current game state, so a {@link Player} can enter a game. The view
	 * is specific for each player.
	 * 
	 * @param player The player to generate the view for.
	 * @return
	 */
	public abstract View<?> generateViewForPlayer(Player player);
	
	/**
	 * Return a list of all players in the game
	 * @return
	 */
	public abstract List<Player> getPlayers();
	
	/**
	 * Find the {@link Player} for a {@link User} 
	 * @param user The user to find player from.
	 * @return
	 */
	public abstract Player getPlayer(User user);
	
	/**
	 * Issue a new order on the game, from the specified {@link Player}. The order data is specific for the game.
	 * 
	 * @param player The player issuing the order.
	 * @param orderData Data used to issue the order.
	 * @return
	 */
	public abstract Order issueOrder(Player player, JSONObject orderData);

	/**
	 * Generate a map of {@link View} representing the changes made to the game state by the {@link Order}. One view will
	 * be generated for each {@link Player} in the game.
	 * 
	 * @param order
	 * @return
	 */
	public abstract Map<Player, View<? extends Game>> generateViewsForOrder(Order order);

}
