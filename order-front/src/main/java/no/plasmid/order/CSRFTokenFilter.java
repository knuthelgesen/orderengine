package no.plasmid.order;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.plasmid.order.utils.CSRFUtils;

@WebFilter("/*")
public class CSRFTokenFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if (!httpServletRequest.getMethod().toUpperCase().equals("GET")) {
			//Not GET, so check CSRF token
			if (!CSRFUtils.checkCSRFToken(httpServletRequest)) {
				throw new IllegalArgumentException("CSRF token not valid");
			}
		}
		
		Cookie cookie = new Cookie("XSRF-TOKEN", CSRFUtils.getCSRFToken(httpServletRequest.getSession()));
		cookie.setPath("/");
		((HttpServletResponse)response).addCookie(cookie);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
