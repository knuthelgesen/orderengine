package no.plasmid.order.gamemanagement.ai;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import no.plasmid.order.gamemanagement.GameManagementException;
import no.plasmid.order.gamemanagement.GameManagementService;
import no.plasmid.order.gamemanagement.GameNotFoundException;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.Player;
import no.plasmid.order.gamemanagement.model.View;

public class AIExecutor {

	private static AIExecutor instance = new AIExecutor();
	
	public static AIExecutor getInstance() {
		return instance;
	}
	
	private ExecutorService executorService;
	
	private Map<Player, AI<? extends Game>> aiContainter;
	
	private GameManagementService gameManagementService;
	
	private AIExecutor() {
		executorService = Executors.newFixedThreadPool(5);
	
		aiContainter = new HashMap<Player, AI<? extends Game>>();
		gameManagementService = new GameManagementService();
	}

	public void scheduleEnteredGame(Player player, Integer gameId, View<? extends Game> view) throws AIException {
		final AI<? extends Game> ai = resolveAI(player, gameId);
		
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				ai.enteredGame(gameId, player, view);
			}
		});
	}
	
	public void scheduleViewChanged(Player player, Integer gameId, View<? extends Game> deltaView) {
		// TODO Auto-generated method stub
		
	}

	private AI<? extends Game> resolveAI(Player player, Integer gameId) throws AIException {
		AI<? extends Game> ai = aiContainter.get(player);
		if (null == ai) {
			try {
				ai = AIFactory.create(gameManagementService.getGame(gameId));
				aiContainter.put(player, ai);
			} catch (GameManagementException | GameNotFoundException e) {
				throw new AIException(e);
			}
		}

		return ai;
	}
	
}
