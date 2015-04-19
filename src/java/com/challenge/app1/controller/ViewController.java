package com.challenge.app1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.ParameterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.challenge.app1.dao.AccountDao;
import com.challenge.app1.dao.LogDao;

@Controller
public class ViewController {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private LogDao logDao;
	
	@Autowired
	private HttpServletRequest request;

	public ConsumerManager manager_;

	public ViewController() throws ConsumerException {
		manager_ = new ConsumerManager();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(
			@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "accountIdentifier", required = true) String accountIdentifier) {

		try {
			// perform discovery on the user-supplied identifier
			@SuppressWarnings("rawtypes")
			List discoveries = manager_.discover(openid);

			// attempt to associate with the OpenID provider
			// and retrieve one service endpoint for authentication
			DiscoveryInformation discovered = manager_.associate(discoveries);

			// store the discovery information in the user's session for later
			// use
			// leave out for stateless operation / if there is no session
			// session.setAttribute("discovered", discovered);

			// obtain a AuthRequest message to be sent to the OpenID provider
			AuthRequest authReq = manager_.authenticate(discovered,
					getURLWithContextPath(request) +  "/return");

			return "redirect:" + authReq.getDestinationUrl(true);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/return", method = RequestMethod.GET)
	public String returnPage() {

		// extract the parameters from the authentication response
		// (which comes in as a HTTP request from the OpenID provider)
		ParameterList openidResp = new ParameterList(request.getParameterMap());

		// retrieve the previously stored discovery information
		// DiscoveryInformation discovered = (DiscoveryInformation)
		// session.getAttribute("discovered");

		// extract the receiving URL from the HTTP request
		StringBuffer receivingURL = request.getRequestURL();
		String queryString = request.getQueryString();
		if (queryString != null && queryString.length() > 0)
			receivingURL.append("?").append(request.getQueryString());

		try {
			// verify the response
			VerificationResult verification = manager_.verify(
					receivingURL.toString(), openidResp, null);

			// examine the verification result and extract the verified
			// identifier
			Identifier verified = verification.getVerifiedId();
			if (verified != null) {
				// success, use the verified identifier to identify the user
				return "redirect:" + "/history";
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// OpenID authentication failed
		return "redirect:" + "/failed";
	}

	@RequestMapping("/history")
	public ModelAndView showHistory() {

		ModelAndView mv = new ModelAndView("history");
		mv.addObject("log", logDao.getEvents());
		return mv;
	}
	
	@RequestMapping("/users")
	public ModelAndView showUsers() {

		ModelAndView mv = new ModelAndView("users");
		mv.addObject("users", accountDao.getAccountCollection());
		return mv;
	}

	@RequestMapping("/failed")
	public ModelAndView failed() {
		ModelAndView mv = new ModelAndView("failed");
		return mv;
	}

	private static String getURLWithContextPath(HttpServletRequest request) {
		//removing port
		//SEVERE Return_To URL verification failed
	   return request.getScheme() + "://" + request.getServerName() /*+ ":" + request.getServerPort()*/ + request.getContextPath();
	}
}
