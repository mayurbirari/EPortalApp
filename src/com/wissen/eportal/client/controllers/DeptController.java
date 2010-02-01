package com.wissen.eportal.client.controllers;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.wissen.eportal.client.data.DeptList;
import com.wissen.eportal.client.observers.DeptObserver;
import com.wissen.eportal.client.services.EPortalService;
import com.wissen.eportal.client.services.EPortalServiceAsync;

/**
 * Class represents a controller part of MVC. Its acts as a Mediator to
 * application.
 * 
 * @author wissen16
 */
public class DeptController {

	/**
	 * Create a remote service proxy to talk to the server-side Department service.
	 */
	private final EPortalServiceAsync deptService = GWT
			.create(EPortalService.class);
	private static DeptController _instance;

	private DeptController() {
	}

	public static synchronized DeptController getInstance() {
		if (_instance == null) {
			_instance = new DeptController();
		}
		return _instance;
	}

	public EPortalServiceAsync getdeptService() {
		return deptService;
	}

	// ---------------------- Observers -------------------------------
	private List<DeptObserver> deptObservers = new ArrayList<DeptObserver>();

	// --------------- Registration/DeRegitration methods --------------

	public void addDeptObserver(DeptObserver observer) {
		deptObservers.add(observer);
	}

	public void removeDeptObserver(DeptObserver observer) {
		deptObservers.remove(observer);
	}

	public void getDeptList() {
		deptService.getDeptList(deptCallback);
	}

	public void saveNewDept(long id, String name, String descr) {
		DeptList dept = new DeptList();
		dept.setId(id);
		dept.setName(name);
		dept.setDescr(descr);
		deptService.saveNewDept(dept, deptCallback);
	}

	public void updateDept(long id, String name, String descr) {
		DeptList dept = new DeptList();
		dept.setId(id);
		dept.setName(name);
		dept.setDescr(descr);
		deptService.updateDept(dept, deptCallback);

	}

	public void delDept(long deptid) {
		deptService.delDept(deptid, deptCallback);
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
}
