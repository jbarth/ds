package com.ubs.gtp.data.security.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ubs.gtp.security.AuthenticationException;
import com.ubs.gtp.security.AuthenticationManager;
import com.ubs.gtp.security.SessionManager;
import com.ubs.gtp.security.wrapper.TokenWithId;
import com.ubs.gtp.security.wrapper.UsernamePassword;
import com.ubs.gtp.security.wrapper.UsernamePasswordPin;

/**
 * Controller for authentication.
 * 
 * @author Willy Lai
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthenticationController {
	
	private static final transient Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SessionManager sessionManager;

	
	/**
	 * Validates the passed credentials and returns a token when credentials are
	 * valid. Otherwise returns a authentication failed object.
	 * 
	 * @return sessionToken.
	 */
	@RequestMapping(value = "/login/client", method = RequestMethod.GET)
//	@ResponseBody
	public ModelAndView authenticateClientDebug(@RequestParam String username, @RequestParam String password, @RequestParam String pin) {
		TokenWithId token = null;
		try {
			token = authenticationManager.authenticateClient(username, password, pin);
		} catch (AuthenticationException e) {
			token = new TokenWithId("Authentication failed.", 0);
		}
//		return token;
		return new ModelAndView("session", "session", token);
	}
	
	/**
	 * Validates the passed credentials and returns a token when credentials are
	 * valid. Otherwise returns a authentication failed object.
	 * 
	 * @return sessionToken.
	 */
	@RequestMapping(value = "/login/advisor", method = RequestMethod.GET)
//	@ResponseBody
	public ModelAndView authenticateAdvisorDebug(@RequestParam String username, @RequestParam String password) {
		TokenWithId token = null;
		try {
			token = authenticationManager.authenticateAdvisor(username, password);
		} catch (AuthenticationException e) {
			token = new TokenWithId("Authentication failed.", 0);
		}
//		return token;
		return new ModelAndView("session","session", token);
	}
	
	/**
	 * Removes the current session.
	 * Used for logout.
	 * 
	 * @return sessionToken.
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public String logout(@RequestParam String sessionToken) {
		if (sessionManager.removeSession(sessionToken)) {
			return "Sucessfully logged out";
		} else {
			return "";
		}
	}
	
	/**
	 * Validates the passed credentials and returns a token when credentials are
	 * valid. Otherwise returns a authentication failed object.
	 * 
	 * @return sessionToken.
	 */
	@RequestMapping(value = "/login/client", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public TokenWithId authenticateClient(@RequestBody UsernamePasswordPin usernamePasswordPin) {
		TokenWithId token;
		try {
			token = authenticationManager.authenticateClient(usernamePasswordPin.getUsername(), usernamePasswordPin.getPassword(), usernamePasswordPin.getPin());
		} catch (AuthenticationException e) {
			token = new TokenWithId("Authentication failed.", 0);
		}
		return token;
	}
	
	/**
	 * Validates the passed credentials and returns a token when credentials are
	 * valid. Otherwise returns a authentication failed object.
	 * 
	 * @return sessionToken.
	 */
	@RequestMapping(value = "/login/advisor", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public TokenWithId authenticateAdvisor(@RequestBody UsernamePassword usernamePassword) {
		TokenWithId token;
		try {
			token = authenticationManager.authenticateAdvisor(usernamePassword.getUsername(), usernamePassword.getPassword());
		} catch (AuthenticationException e) {
			token = new TokenWithId("Authentication failed.", 0);
		}
		return token;
	}
	
}
