package com.challenge.app1.controller.appdirect;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.app1.controller.appdirect.event.AddonEvent;
import com.challenge.app1.controller.appdirect.event.AssignEvent;
import com.challenge.app1.controller.appdirect.event.BaseEvent;
import com.challenge.app1.controller.appdirect.event.CancelEvent;
import com.challenge.app1.controller.appdirect.event.ChangeEvent;
import com.challenge.app1.controller.appdirect.event.NoticeEvent;
import com.challenge.app1.controller.appdirect.event.OrderEvent;
import com.challenge.app1.controller.appdirect.event.UnAssignEvent;
import com.challenge.app1.controller.appdirect.result.AddonResult;
import com.challenge.app1.controller.appdirect.result.AssignResult;
import com.challenge.app1.controller.appdirect.result.CancelResult;
import com.challenge.app1.controller.appdirect.result.ChangeResult;
import com.challenge.app1.controller.appdirect.result.CreateResult;
import com.challenge.app1.controller.appdirect.result.NoticeResult;
import com.challenge.app1.controller.appdirect.result.UnassignResult;
import com.challenge.app1.dao.AccountDao;
import com.challenge.app1.dao.LogDao;
import com.challenge.app1.model.Account;
import com.challenge.app1.model.Event;

@RestController
public class AppDirectController {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private LogDao logDao;

	@Autowired
	private HttpServletRequest request;

	@Resource(name = "oAuthProps")
	private Properties oAuthProps;

	@RequestMapping(value = "/create", method = RequestMethod.GET, headers = "Accept=application/xml", produces = "application/xml")
	public @ResponseBody CreateResult create(
			@RequestParam(value = "url") String url) {

		CreateResult result = new CreateResult();

		try {
			OrderEvent event = (OrderEvent) getEvent(url, OrderEvent.class);

			if(event.getFlag().equals("STATELESS"))
			{
				result.setSuccess("true");
				result.setErrorCode("0");
				result.setMessage("");
				return result;
			}
			
			logEvent(event);

			Account account = new Account();
			account.setId(new Date().getTime());
			account.setEmail(event.getCreator().getEmail());
			account.setEditionCode(event.getPayload().getOrder()
					.getEditionCode());

			account = accountDao.save(account);

			result.setSuccess("true");
			result.setErrorCode("0");
			result.setMessage("Successfully registered.");
			result.setAccountIdentifier(account.getAccountIdentifier());

			return result;

		} catch (Exception e) {			
			e.printStackTrace();
			
			result.setSuccess("false");
			result.setErrorCode("1");
			result.setMessage("Failed with following message: "
					+ e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/change", method = RequestMethod.GET, headers = "Accept=application/xml", produces = "application/xml")
	public @ResponseBody ChangeResult change(
			@RequestParam(value = "url") String url) {
		ChangeResult result = new ChangeResult();

		try {
			ChangeEvent event = (ChangeEvent) getEvent(url, ChangeEvent.class);

			String id = event.getPayload().getAccount().getAccountIdentifier();
			
			if(id.equals("dummy-account"))
			{
				result.setSuccess("true");
				result.setErrorCode("0");
				result.setMessage("");
				return result;
			}
			
			logEvent(event);	
			
			Account account = accountDao.find(id);
			account.setEditionCode(event.getPayload().getOrder()
					.getEditionCode());

			account = accountDao.save(account);

			result.setSuccess("true");
			result.setErrorCode("0");
			result.setMessage("Changed id:" + id);

			return result;

		} catch (Exception e) {			
			e.printStackTrace();
			
			result.setSuccess("false");
			result.setErrorCode("1");
			result.setMessage("Failed with following message: "
					+ e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET, headers = "Accept=application/xml", produces = "application/xml")
	public @ResponseBody CancelResult cancel(
			@RequestParam(value = "url") String url) {
		CancelResult result = new CancelResult();

		try {
			CancelEvent event = (CancelEvent) getEvent(url, CancelEvent.class);

			String id = event.getPayload().getAccount().getAccountIdentifier();
			
			if(id.equals("dummy-account"))
			{
				result.setSuccess("true");
				result.setErrorCode("0");
				result.setMessage("");
				return result;
			}

			logEvent(event);	
			
			accountDao.delete(id);

			result.setSuccess("true");
			result.setErrorCode("0");
			result.setMessage("Cancelled id:" + id);

			return result;

		} catch (Exception e) {
			e.printStackTrace();

			result.setSuccess("false");
			result.setErrorCode("1");
			result.setMessage("Failed with following message: "
					+ e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/notice", method = RequestMethod.GET, headers = "Accept=application/xml", produces = "application/xml")
	public @ResponseBody NoticeResult notice(
			@RequestParam(value = "url") String url) {
		NoticeResult result = new NoticeResult();

		try {
			NoticeEvent event = (NoticeEvent) getEvent(url, NoticeEvent.class);

			String id = event.getPayload().getAccount().getAccountIdentifier();
			
			if(id.equals("dummy-account"))
			{
				result.setSuccess("true");
				result.setErrorCode("0");
				result.setMessage("");
				return result;
			}
			
			logEvent(event);

			result.setSuccess("true");
			result.setErrorCode("0");
			result.setMessage("Notice id:" + id);

			return result;

		} catch (Exception e) {
			e.printStackTrace();

			result.setSuccess("false");
			result.setErrorCode("1");
			result.setMessage("Failed with following message: "
					+ e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/assign", method = RequestMethod.GET, headers = "Accept=application/xml", produces = "application/xml")
	public @ResponseBody AssignResult assign(
			@RequestParam(value = "url") String url) {
		AssignResult result = new AssignResult();

		try {
			AssignEvent event = (AssignEvent) getEvent(url, AssignEvent.class);

			String id = event.getPayload().getAccount().getAccountIdentifier();
			
			if(id.equals("dummy-account"))
			{
				result.setSuccess("true");
				result.setErrorCode("0");
				result.setMessage("");
				return result;
			}
			
			logEvent(event);

			result.setSuccess("true");
			result.setErrorCode("0");
			result.setMessage("Assign id:" + id);

			return result;

		} catch (Exception e) {			
			e.printStackTrace();

			result.setSuccess("false");
			result.setErrorCode("1");
			result.setMessage("Failed with following message: "
					+ e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/unassign", method = RequestMethod.GET, headers = "Accept=application/xml", produces = "application/xml")
	public @ResponseBody UnassignResult unassign(
			@RequestParam(value = "url") String url) {
		UnassignResult result = new UnassignResult();

		try {
			UnAssignEvent event = (UnAssignEvent) getEvent(url,
					UnAssignEvent.class);
			
			String id = event.getPayload().getAccount().getAccountIdentifier();
			
			if(id.equals("dummy-account"))
			{
				result.setSuccess("true");
				result.setErrorCode("0");
				result.setMessage("");
				return result;
			}
			
			logEvent(event);

			result.setSuccess("true");
			result.setErrorCode("0");
			result.setMessage("Unassign id:" + id);

			return result;

		} catch (Exception e) {			
			e.printStackTrace();
			
			result.setSuccess("false");
			result.setErrorCode("1");
			result.setMessage("Failed with following message: "
					+ e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/addon", method = RequestMethod.GET, headers = "Accept=application/xml", produces = "application/xml")
	public @ResponseBody AddonResult addon(
			@RequestParam(value = "url") String url) {
		AddonResult result = new AddonResult();

		try {
			AddonEvent event = (AddonEvent) getEvent(url,
					AddonEvent.class);
			
			String id = event.getPayload().getAccount().getAccountIdentifier();
			//String code = event.getPayload().getOrder().getAddonOfferingCode();
			
			if(id.equals("dummy-account"))
			{
				result.setSuccess("true");
				result.setErrorCode("0");
				result.setMessage("");
				return result;
			}
			
			logEvent(event);

			result.setSuccess("true");
			result.setErrorCode("0");
			result.setMessage("Addon id:"+id );

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			
			result.setSuccess("false");
			result.setErrorCode("1");
			result.setMessage("Failed with following message: "
					+ e.getMessage());
		}
		return result;
	}
	
	private BaseEvent getEvent(String url, Class<? extends BaseEvent> class1)
			throws MalformedURLException, IOException,
			OAuthMessageSignerException, OAuthExpectationFailedException,
			OAuthCommunicationException, JAXBException {

		URL u = new URL(URLDecoder.decode(url, "UTF-8"));
		HttpURLConnection request = (HttpURLConnection) u.openConnection();

		OAuthConsumer consumer = new DefaultOAuthConsumer((String) oAuthProps.get("consumerKey"),
				(String) oAuthProps.get("consumerSecret"));
		consumer.sign(request);
		request.connect();

		JAXBContext jaxbContext = JAXBContext.newInstance(class1);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		BaseEvent event = (BaseEvent) jaxbUnmarshaller.unmarshal(request
				.getInputStream());

		return event;
	}

	private void logEvent(BaseEvent orderEvent) {
		Event event = new Event();
		event.setType(orderEvent.getType());
		event.setEmail(orderEvent.getCreator().getEmail());

		logDao.save(event);
	}

}
