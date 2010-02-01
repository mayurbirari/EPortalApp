package com.wissen.eportal.client.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.wissen.eportal.client.observers.EmailObserver;
import com.wissen.eportal.client.services.EPortalService;
import com.wissen.eportal.client.services.EPortalServiceAsync;

/**
 * Class represents a controller part of MVC. Its acts as a Mediator to
 * application.
 * 
 * @author wissen16
 */
public class EmailController {

	/**
	 * Create a remote service proxy to talk to the server-side Task service.
	 */
	private final EPortalServiceAsync emailService = GWT
			.create(EPortalService.class);
	private static EmailController _instance;

	private EmailController() {
	}

	public static synchronized EmailController getInstance() {
		if (_instance == null) {
			_instance = new EmailController();
		}
		return _instance;
	}

	public EPortalServiceAsync getemailService() {
		return emailService;
	}

	// ---------------------- Observers -------------------------------
	private List<EmailObserver> EmailObservers = new ArrayList<EmailObserver>();

	// --------------- Registration/DeRegitration methods --------------
	public void addEmailObserver(EmailObserver observer) {
		EmailObservers.add(observer);
	}

	public void removeEmpObserver(EmailObserver observer) {
		EmailObservers.remove(observer);
	}


	AsyncCallback<Boolean> emailCallback = new AsyncCallback<Boolean>() {
		public void onFailure(Throwable caught) {
			GWT.log("Error in email validation", caught);
			for (EmailObserver observer : EmailObservers) {
				observer.notifyEmailFailed("email retrive failed: "
						+ caught);
			}
		}

		public void onSuccess(Boolean flag) {
			for (EmailObserver observer : EmailObservers) {
				if(flag==true)
				Window.alert("News send successfully");
				observer.notifyEmailSucceeded(flag);
			}
		}
	};

	public void sendemail(String to, String sub, String msg) {
		emailService.sendmail(to,sub,msg,emailCallback);
		
	}
}
