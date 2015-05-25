package no.plasmid.order.rest;

import static no.plasmid.order.rest.users.UserUtils.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.exception.AccessDeniedException;
import no.plasmid.order.rest.users.UserUtils;
import no.plasmid.order.utils.CSRFUtils;
import no.plasmid.order.utils.WSTokenUtils;

@Path("tokens")
public class TokenController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);

	@GET
	@Path("csrf")
	@Produces("text/plain")
	public Response getCSRFToken(@Context HttpServletRequest request) {
		LOGGER.debug("Get CSRF token");
		
		final String csrfToken = CSRFUtils.getCSRFToken(request.getSession());
		return Response.ok(csrfToken).build();
	}
	
	@GET
	@Path("ws")
	@Produces("text/plain")
	public Response getWSToken(@Context HttpServletRequest request) throws AccessDeniedException {
		LOGGER.debug("Get WS token");

		ensureLoggedInUser(request);

		final String wsToken = WSTokenUtils.getWSToken(UserUtils.getLoggedInUser(request));
		return Response.ok(wsToken).build();
	}
	
}
