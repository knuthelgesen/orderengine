package no.plasmid.order.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThrowableExceptionMapper.class);

	@Override
	public Response toResponse(Throwable e) {
		LOGGER.error(e.getMessage(), e);
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

}
