package com.ubs.gtp.data.security.resource;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ubs.gtp.data.domain.client.AdvisorDto;
import com.ubs.gtp.data.domain.dao.AdvisorDao;
import com.ubs.gtp.security.AuthenticationManager;
import com.ubs.gtp.security.Hash;
import com.ubs.gtp.security.UserRepository;
import com.ubs.gtp.security.objects.AdvisorLogin;
import com.ubs.gtp.security.wrapper.FailedAttempts;
import com.ubs.gtp.security.wrapper.OldPasswordNewPassword;
import com.ubs.gtp.security.wrapper.UsernamePasswordSamaccountname;
import com.ubs.gtp.security.wrapper.UsernameWithId;

/**
 * Controller for managing advisor logins.
 * 
 * @author Willy Lai
 * @author Felix.Pflanzl
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminAdvisorController {
	
	private static final transient Logger LOG = LoggerFactory.getLogger(AdminAdvisorController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private AdvisorDao advisors;
	
	/**
	 * Creates a new advisor and returns the ID of the new created advisor.
	 * 
	 * @return UsernameWithId.
	 */
	@RequestMapping(value = "/advisor", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public UsernameWithId createAdvisor(@RequestBody UsernamePasswordSamaccountname usernamePasswordSamaccountname, HttpServletResponse response) {
		AdvisorLogin advisor = new AdvisorLogin();
		UsernameWithId usernameWithId = new UsernameWithId();
				
        AdvisorDto advisorData = new AdvisorDto();
        advisorData.setSamAccountName(usernamePasswordSamaccountname.getSamaccountname());
        advisors.create(advisorData);

        advisor.setId(advisorData.getAdvisorId());
		advisor.setUsername(usernamePasswordSamaccountname.getUsername());
		advisor.setPasswordHash(Hash.hash(usernamePasswordSamaccountname.getPassword(), usernamePasswordSamaccountname.getUsername()));
		advisor.setFailedAttempts(0);
		try {
			advisor = userRepository.createAdvisor(advisor);
			usernameWithId.setId(advisor.getId());
			usernameWithId.setUsername(advisor.getUsername());
			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return usernameWithId;
	}

	/**
	 * Delete an existing advisor.
	 * 
	 * @return UsernameWithId.
	 */
	@RequestMapping(value = "/advisor/{username}", method = RequestMethod.DELETE)
	public void deleteAdvisor(@PathVariable String username, HttpServletResponse response) {
		/*AdvisorLogin advisor;
		try {
			advisor = userRepository.getAdvisor(username);
			advisors.deleteById(advisor.getId());
			if(userRepository.deleteAdvisor(advisor) == false) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			else {
				response.setStatus(HttpServletResponse.SC_GONE);
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}*/
		response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		return;
	}
	
	/**
	 * Change the password of an advisor.
	 * 
	 */
	@RequestMapping(value = "/advisor/{username}/password", method = RequestMethod.PUT)
	public void updateAdvisorLoginPassword(final @PathVariable String username, @RequestBody OldPasswordNewPassword oldPwNewPw, final HttpServletResponse response) {
		/*AdvisorLogin advisor;
		try {
			advisor = userRepository.getAdvisor(username);
			if(authenticationManager.valueAndValueHashMatches(oldPwNewPw.getOld_pw(), advisor) == true) {
				advisor.setPasswordHash(Hash.hash(oldPwNewPw.getNew_pw(), advisor.getUsername()));
				userRepository.updateAdvisor(advisor);
				response.setStatus(HttpServletResponse.SC_OK);
			}
			else {
				response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}*/
		response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		return;
	}
	
	/**
	 * Unlock an advisor account by setting failed_attempts counter back to 0.
	 * 
	 */
	@RequestMapping(value = "/advisor/{username}/failed_attempts", method = RequestMethod.PUT)
	public void updateAdvisorLoginFailedAttempts(final @PathVariable String username, @RequestBody FailedAttempts failedAttempts, final HttpServletResponse response) {
		/*AdvisorLogin advisor;
		try {
			advisor = userRepository.getAdvisor(username);
			if(failedAttempts.getFailedAttempts() == 0) {
				advisor.setFailedAttempts(0);
				userRepository.updateAdvisor(advisor);
				response.setStatus(HttpServletResponse.SC_OK);
			}
			else {
				response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}*/
		response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		return;
	}

}
