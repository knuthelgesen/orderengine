package no.plasmid.order.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

public class CSRFUtils {

	private static final String STORED_TOKEN_SESSION_ATTRIBUTE	= "storedCSRFToken";
	private static final String CSRF_TOKEN_HEADER								= "X-XSRF-TOKEN";	
	
	public static String getCSRFToken(final HttpSession session) {
		String csrfToken = (String) session.getAttribute(STORED_TOKEN_SESSION_ATTRIBUTE);
		if (null == csrfToken) {
	    csrfToken = Long.toHexString((long)(Math.random() * Long.MAX_VALUE)).trim();
	    session.setAttribute(STORED_TOKEN_SESSION_ATTRIBUTE, csrfToken);
		}
		
		return csrfToken;
	}

	public static boolean checkCSRFToken(final HttpServletRequest request) {
		return checkCSRFToken(request, request.getHeader(CSRF_TOKEN_HEADER));
	}
	
	public static boolean checkCSRFToken(final HttpServletRequest request, final String recievedToken) {
		boolean accepted = false;
		String storedToken = null;

		final HttpSession session = request.getSession();
		storedToken = (String) session.getAttribute(STORED_TOKEN_SESSION_ATTRIBUTE);
		
		if (!StringUtils.isEmpty(storedToken) && !StringUtils.isEmpty(recievedToken) && storedToken.equals(recievedToken)) {
			accepted = true;
			session.setAttribute(STORED_TOKEN_SESSION_ATTRIBUTE, null);
		}
		
		return accepted;
	}
	
}
