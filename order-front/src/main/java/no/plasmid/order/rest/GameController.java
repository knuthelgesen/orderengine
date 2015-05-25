package no.plasmid.order.rest;

import static no.plasmid.order.rest.users.UserUtils.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.exception.AccessDeniedException;
import no.plasmid.order.gamemanagement.GameManagementException;
import no.plasmid.order.gamemanagement.GameManagementService;
import no.plasmid.order.gamemanagement.GameNotFoundException;
import no.plasmid.order.gamemanagement.model.Game;
import no.plasmid.order.gamemanagement.model.GameJson;

@Path("games")
public class GameController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
	
	@Inject
	GameManagementService gameManagementService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<GameJson> getGames(@Context HttpServletRequest request) throws GameManagementException, AccessDeniedException {
		LOGGER.debug("Get games");
		ensureLoggedInUser(request);
		
		List<Game> games = gameManagementService.getGames(getLoggedInUser(request));
		List<GameJson> rc = new ArrayList<GameJson>(games.size());
		for (Game game : games) {
			rc.add(game.toJson());
		}
		
		return rc;
	}
	
	@GET
	@Path("{gameId}")
	@Produces(MediaType.APPLICATION_JSON)
	public GameJson getGame(@Context HttpServletRequest request, @PathParam("gameId") Integer gameId) throws GameManagementException, AccessDeniedException, GameNotFoundException {
		LOGGER.debug("Get game");
		ensureLoggedInUser(request);
		
		Game game = gameManagementService.getGame(gameId, getLoggedInUser(request));
		
		return game.toJson();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public GameJson createGame(@Context HttpServletRequest request, CreateGameJson createGameJson)
			throws GameManagementException, AccessDeniedException {
		LOGGER.debug("Put createGame");
		ensureLoggedInUser(request);
		
		Game createdGame = gameManagementService.createGame(createGameJson.getGameType(), createGameJson.getGameData(), getLoggedInUser(request));
		
		return createdGame.toJson();
	}
	
}
