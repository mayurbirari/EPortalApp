package com.wissen.eportal.client.controllers;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.wissen.eportal.client.data.DeptList;
import com.wissen.eportal.client.observers.DeptObserver;
import com.wissen.eportal.client.observers.LoginObserver;
import com.wissen.eportal.client.services.EPortalService;
import com.wissen.eportal.client.services.EPortalServiceAsync;

/**
 * Class represents a controller part of MVC. Its acts as a Mediator to
 * application.
 * 
 * @author wissen16
 */
public class LoginController {

	/**
	 * Create a remote service proxy to talk to the server-side Login service.
	 */
	private final EPortalServiceAsync loginService = GWT
			.create(EPortalService.class);
	private static LoginController _instance;

	private LoginController() {
	}

	public static synchronized LoginController getInstance() {
		if (_instance == null) {
			_instance = new LoginController();
		}
		return _instance;
	}

	public EPortalServiceAsync getLoginService() {
		return loginService;
	}

	// ---------------------- Observers -------------------------------
	private List<LoginObserver> loginObservers = new ArrayList<LoginObserver>();
	private List<DeptObserver> deptObservers = new ArrayList<DeptObserver>();

	// --------------- Registration/DeRegitration methods --------------
	public void addLoginObserver(LoginObserver observer) {
		loginObservers.add(observer);
	}

	public void removeLoginObserver(LoginObserver observer) {
		loginObservers.remove(observer);
	}

	public void addDeptObserver(DeptObserver observer) {
		deptObservers.add(observer);
	}

	public void removeDeptObserver(DeptObserver observer) {
		deptObservers.remove(observer);
	}

	// ---------------------- LoginController's Methods ---------------------
	public void validateUser(String userName, String password) {
		loginService.validateUser(userName, password, loginCallback);
	}

	// -------------------- Call Back classes ---------------------------
	AsyncCallback<String> loginCallback = new AsyncCallback<String>() {
		@Override
		public void onFailure(Throwable caught) {
			GWT.log("Error in user validation", caught);
			for (LoginObserver observer : loginObservers) {
				observer.notifyLoginFailed("Login failed: " + caught);
			}
		}

		@Override
		public void onSuccess(String isValid) {
			if (!isValid.equals("false")) {
				for (LoginObserver observer : loginObservers) {
					observer.notifyLoginSucceeded(isValid);
				}
			} else {
				for (LoginObserver observer : loginObservers) {
					observer.notifyLoginFailed("Invald user name or password");
				}
			}
		}
	};

	public void getDeptList() {
		loginService.getDeptList(deptCallback);
	}

	public void saveNewDept(long id, String name, String descr) {
		DeptList dept = new DeptList();
		dept.setId(id);
		dept.setName(name);
		dept.setDescr(descr);
		loginService.saveNewDept(dept, deptCallback);
	}

	AsyncCallback<List<DeptList>> deptCallback = new AsyncCallback<List<DeptList>>() {
		@Override
		public void onFailure(Throwable caught) {
			GWT.log("Error in dept validation", caught);
			for (DeptObserver observer : deptObservers) {
				observer.notifyDeptListFailed("dept save failed: " + caught);
			}
		}

		@Override
		public void onSuccess(List<DeptList> deptList) {
			for (DeptObserver observer : deptObservers) {
				observer.notifyDeptListSucceeded(deptList);
			}
		}
	};

	public void updateDept(long id, String name, String descr) {
		DeptList dept = new DeptList();
		dept.setId(id);
		dept.setName(name);
		dept.setDescr(descr);
		loginService.updateDept(dept, deptCallback);

	}

	public void delDept(long deptid) {
		loginService.delDept(deptid, deptCallback);
	}

}
