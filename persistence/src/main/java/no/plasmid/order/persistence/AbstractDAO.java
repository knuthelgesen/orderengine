package no.plasmid.order.persistence;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDAO.class);

	protected DataSource getDataSource() {
		LOGGER.debug("Provide datasource");
		try {
			return InitialContext.doLookup("jdbc/order");
		} catch (NamingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
}
