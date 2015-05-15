package no.plasmid.order.front;

import javax.sql.DataSource;
import javax.ws.rs.core.Application;

import no.plasmid.order.gamemanagement.GameManagementService;
import no.plasmid.order.usermanagement.UserManagementService;
import no.plasmid.order.usermanagement.dao.UserDAO;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;

public class UserControllerTest extends JerseyTest {

	@Before
	public void before() throws Exception {
//		TestDataSourceFactory.createDatabase();
	}
	
	@After
	public void after() throws Exception {
//		TestDataSourceFactory.dropDatabase();
	}
	
  @Override
  protected Application configure() {
    AbstractBinder binder = new AbstractBinder() {
        @Override
        protected void configure() {
      		bind(UserManagementService.class).to(UserManagementService.class);
      		bind(UserDAO.class).to(UserDAO.class);
      		
      		bind(GameManagementService.class).to(GameManagementService.class);
            
      		bindFactory(TestDataSourceFactory.class).to(DataSource.class);
        }
    };
    
//    ResourceConfig config = new ResourceConfig(UserController.class, TokenController.class, GameController.class);
    ResourceConfig config = new ResourceConfig();
    config.packages(true, "no.plasmid.order");
    config.register(binder);
      return config;
  }

//  @Test
//  public void canGetNotLoggedIn() {
//    Response response = target("users/loggedIn").request().get();
//    Assert.assertEquals(401, response.getStatus());
//  }

}
