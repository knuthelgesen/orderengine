package no.plasmid.order.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.plasmid.order.gamemanagement.GameNotFoundException;

public class GameNotFoundExceptionMapper implements ExceptionMapper<GameNotFoundException> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameNotFoundExceptionMapper.class);
	
	@Override
	public Response toResponse(GameNotFoundException e) {
		LOGGER.debug(e.getMessage(), e);
		return Response.status(Status.NOT_FOUND).build();
	}

}
