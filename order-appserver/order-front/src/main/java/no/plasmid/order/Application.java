package no.plasmid.order;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationPath("/rest")
public class Application extends ResourceConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	public Application() {
		LOGGER.debug("Start application config");
		packages(true, "no.plasmid.order");
	}
	
}
