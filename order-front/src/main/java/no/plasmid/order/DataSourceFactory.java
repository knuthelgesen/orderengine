package no.plasmid.order;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.glassfish.hk2.api.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceFactory implements Factory<DataSource> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceFactory.class);
	
	@Override
	public void dispose(DataSource arg0) {
	}

	@Override
	public DataSource provide() {
		LOGGER.debug("Provide datasource");
		try {
			return InitialContext.doLookup("jdbc/order");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

}
