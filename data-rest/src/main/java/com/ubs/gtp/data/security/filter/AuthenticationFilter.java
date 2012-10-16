package com.ubs.gtp.data.security.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.ubs.gtp.data.domain.dao.AdvisorDao;
import com.ubs.gtp.data.domain.security.Login;
import com.ubs.gtp.security.SessionManager;
import com.ubs.gtp.security.util.ApiPathUtil;

/**
 * Authentication Filter that intercepts the REST requests to Data Services and
 * validates the token.
 * 
 * @author Willy Lai
 * @since 0.1
 */
@Component
public class AuthenticationFilter implements Filter {

	private static final transient Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Autowired
	private SessionManager sessionManager;

	@Autowired
	private AdvisorDao advisors;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * Filter method implementation, that checks whether a token exist, and if
	 * it exists, whether it is valid.
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequestWrapper request = new HttpServletRequestWrapper((HttpServletRequest) servletRequest);
		HttpServletResponseWrapper response = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
		List<String> paths = ApiPathUtil.getApiPath(request.getRequestURL().toString());
		String sessionToken = request.getHeader("Authorization");
		// magic session token for testing
		// TODO: remove this in final version !!!
		if ("magic".equals(sessionToken)) {
			filterChain.doFilter(servletRequest, servletResponse);
		}
		// check whether session token is in header
		if (sessionToken == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		} else {
			LOG.info("Session Token: " + sessionToken);
			// check whether session is valid
			boolean isValidSession = sessionManager.validateSession(sessionToken);
			if (isValidSession) {
				LOG.info("Session is valid.");
				if (paths.size() > 1) {
					Integer id = Integer.parseInt(paths.get(1));
					Login login = sessionManager.getLogin(sessionToken);
					if ("Client".equals(login.getType())) {
						LOG.error("CLIENT");
						if ("client".equals(paths.get(0)) || "portfolio".equals(paths.get(0))) {
							LOG.error("SECURED API");
							if (id.equals(login.getId())) {
								LOG.error("VALID");
								filterChain.doFilter(servletRequest, servletResponse);
							} else {
								response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
							}
						} else {
							filterChain.doFilter(servletRequest, servletResponse);
						}
					} else if ("Advisor".equals(login.getType())) {
						LOG.error("ADVISOR");
						if ("client".equals(paths.get(0)) || "faClient".equals(paths.get(0)) || "portfolio".equals(paths.get(0))) {
							Optional<List<Integer>> clientIds = advisors.getClientIdsByAdvisorId(login.getId());
							LOG.error("SECURED API");
							if (clientIds.isPresent() && clientIds.get().contains(id)) {
								LOG.error("VALID");
								filterChain.doFilter(servletRequest, servletResponse);
							} else if (id.equals(login.getId())) {
								filterChain.doFilter(servletRequest, servletResponse);
							} else {
								response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
							}
						} else {
							filterChain.doFilter(servletRequest, servletResponse);
						}
					} else {
						filterChain.doFilter(servletRequest, servletResponse);
					}
				} else {
					filterChain.doFilter(servletRequest, servletResponse);
				}
			} else {
				LOG.info("Session is invalid.");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
