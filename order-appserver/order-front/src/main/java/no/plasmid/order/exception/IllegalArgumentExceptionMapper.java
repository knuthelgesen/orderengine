package no.plasmid.order.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

	private static final Logger LOGGER = LoggerFactory.getLogger(IllegalArgumentExceptionMapper.class);
	
	@Override
	public Response toResponse(IllegalArgumentException e) {
		LOGGER.debug(e.getMessage(), e);
		return Response.status(Status.BAD_REQUEST).build();
	}

}
