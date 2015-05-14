package no.plasmid.order.rest.users;

import static no.plasmid.order.rest.UserUtils.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import no.plasmid.order.exception.AccessDeniedException;
import no.plasmid.order.usermanagement.UserManagementException;
import no.plasmid.order.usermanagement.UserManagementService;
import no.plasmid.order.usermanagement.im.User;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("users")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private static final String	DEFAULT_ROLE_NAME	= "user";

	@Inject
	UserManagementService userManagementService;
	
	@GET
	@Path("loggedIn")
	@Produces(MediaType.TEXT_PLAIN)
  public Response loggedInUser(@Context HttpServletRequest request) throws AccessDeniedException {
		LOGGER.debug("Get loggedIn");

		//Check if user is already logged in
		User user = getLoggedInUser(request);
		if (null != user) {
			return Response.ok(user.getUserName()).build();
		} else {
			throw new AccessDeniedException();
		}
	}

	@POST
	@Path("logIn")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response logIn(@Context HttpServletRequest request, @Context HttpHeaders headers, LogInJSON logInJSON)
			throws UserManagementException, AccessDeniedException {
		LOGGER.debug("Post logIn");

		//Check if user is already logged in
		ensureNoLoggedInUser(request);
		
		//Check values
		if (StringUtils.isEmpty(logInJSON.getUserName())) {
			throw new IllegalArgumentException("User name missing");
		}
		if (StringUtils.isEmpty(logInJSON.getPassword())) {
			throw new IllegalArgumentException("Password missing");
		}

		//Try to log on
		User user = userManagementService.login(logInJSON.getUserName(), logInJSON.getPassword());
		if (null != user) {
			//Log in successful
			setLoggedInUser(request, user);
			return Response.ok().build();
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
	
	@POST
	@Path("logOut")
	@Produces(MediaType.TEXT_PLAIN)
	public Response logOut(@Context HttpServletRequest request, @Context HttpHeaders headers) throws AccessDeniedException {
		LOGGER.debug("Post logOut");
		
		//Check that user is logged in
		ensureLoggedInUser(request);

		//Log out by setting user to null and invalidating session
		setLoggedInUser(request, null);
		return Response.ok().build();
	}
	
	@PUT
	@Path("")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(@Context HttpServletRequest request, @Context HttpHeaders headers, CreateUserJSON createUserJSON)
			throws UserManagementException, AccessDeniedException {
		LOGGER.debug("Post createUser");
		
		//Check if user is already logged in
		ensureNoLoggedInUser(request);
		
		//Check values
		if (StringUtils.isEmpty(createUserJSON.getUserName())) {
			throw new IllegalArgumentException("User name missing");
		}
		if (StringUtils.isEmpty(createUserJSON.getPassword())) {
			throw new IllegalArgumentException("Password missing");
		}
		if (StringUtils.isEmpty(createUserJSON.getPasswordRepeat())) {
			throw new IllegalArgumentException("Password missing");
		}
		if (!createUserJSON.getPassword().equals(createUserJSON.getPasswordRepeat())) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		User user = userManagementService.createUser(createUserJSON.getUserName(), createUserJSON.getPassword(), DEFAULT_ROLE_NAME);
		if (null != user) {
			return Response.ok().build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changePassword(@Context HttpServletRequest request, @Context HttpHeaders headers, ChangePasswordJSON changePasswordJSON)
			throws UserManagementException, AccessDeniedException {
		LOGGER.debug("Post changePassword");
		
		//Check that user is logged in
		ensureLoggedInUser(request);

		//Check values
		if (StringUtils.isEmpty(changePasswordJSON.getPassword())) {
			throw new IllegalArgumentException("Password missing");
		}
		if (StringUtils.isEmpty(changePasswordJSON.getPasswordRepeat())) {
			throw new IllegalArgumentException("Password missing");
		}
		if (!changePasswordJSON.getPassword().equals(changePasswordJSON.getPasswordRepeat())) {
			throw new IllegalArgumentException("Passwords do not match");
		}

		User user = getLoggedInUser(request);
		userManagementService.changeUser(user.getUserName(), changePasswordJSON.getPassword(), user.getRoleName());
		return Response.ok().build();
	}
	
	@DELETE
	@Path("")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteUser(@Context HttpServletRequest request, @Context HttpHeaders headers)
			throws UserManagementException, AccessDeniedException {
		LOGGER.debug("Post changePassword");
		
		//Check that user is logged in
		ensureLoggedInUser(request);

		userManagementService.deleteUser(getLoggedInUser(request).getUserName());
		setLoggedInUser(request, null);

		return Response.ok().build();
	}
}
