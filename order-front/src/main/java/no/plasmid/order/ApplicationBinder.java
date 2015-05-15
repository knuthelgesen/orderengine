package no.plasmid.order;

import javax.sql.DataSource;

import no.plasmid.order.gamemanagement.GameManagementService;
import no.plasmid.order.usermanagement.UserManagementService;
import no.plasmid.order.usermanagement.dao.UserDAO;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bind(UserManagementService.class).to(UserManagementService.class);
		bind(UserDAO.class).to(UserDAO.class);
		
		bind(GameManagementService.class).to(GameManagementService.class);

		bindFactory(DataSourceFactory.class).to(DataSource.class);
	}

}
