package no.plasmid.order.rest.users;

import javax.servlet.http.HttpServletRequest;

import no.plasmid.order.exception.AccessDeniedException;
import no.plasmid.order.usermanagement.im.User;

public class UserUtils {

	private static final String USER_SESSION_ATTRIBUTE = "user";
	
	public static User getLoggedInUser(HttpServletRequest request) {
		return (User)request.getSession().getAttribute(USER_SESSION_ATTRIBUTE);
	}

	public static void setLoggedInUser(final HttpServletRequest request, User user) {
		request.getSession().invalidate();
		request.getSession().setAttribute(USER_SESSION_ATTRIBUTE, user);
	}
	
	public static void ensureLoggedInUser(HttpServletRequest request) throws AccessDeniedException {
		if (null == request.getSession().getAttribute(USER_SESSION_ATTRIBUTE)) {
			throw new AccessDeniedException();
		}
	}

	public static void ensureNoLoggedInUser(HttpServletRequest request) throws AccessDeniedException {
		if (null != request.getSession().getAttribute(USER_SESSION_ATTRIBUTE)) {
			throw new AccessDeniedException();
		}
	}

}
