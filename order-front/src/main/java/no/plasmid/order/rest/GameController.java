package no.plasmid.order.rest;

import static no.plasmid.order.rest.UserUtils.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.exception.AccessDeniedException;
import no.plasmid.order.gamemanagement.GameManagementException;
import no.plasmid.order.gamemanagement.GameManagementService;

@Path("games")
public class GameController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
	
	@Inject
	GameManagementService gameManagementService;
	
	@GET
	@Path("")
	public Response getGames() {
		
		return null;
	}
	
	public Response getGame() {
		return null;
	}

	@PUT
	@Path("")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createGame(@Context HttpServletRequest request, CreateGameJson createGameJson)
			throws GameManagementException, AccessDeniedException {
		LOGGER.debug("Put createGame");
		ensureLoggedInUser(request);
		
		gameManagementService.createGame(createGameJson.getGameType(), createGameJson.getGameData(), getLoggedInUser(request));
		return Response.ok().build();
	}
	
}
