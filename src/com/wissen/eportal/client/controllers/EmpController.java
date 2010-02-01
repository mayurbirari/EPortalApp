package com.wissen.eportal.client.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.wissen.eportal.client.data.EmpList;
import com.wissen.eportal.client.observers.EmpObserver;
import com.wissen.eportal.client.services.EPortalService;
import com.wissen.eportal.client.services.EPortalServiceAsync;

/**
 * Class represents a controller part of MVC. Its acts as a Mediator to
 * application.
 * 
 * @author wissen16
 */
public class EmpController {

	/**
	 * Create a remote service proxy to talk to the server-side Employee
	 * service.
	 */
	private final EPortalServiceAsync empService = GWT
			.create(EPortalService.class);
	private static EmpController _instance;

	private EmpController() {
	}

	public static synchronized EmpController getInstance() {
		if (_instance == null) {
			_instance = new EmpController();
		}
		return _instance;
	}

	public EPortalServiceAsync getempService() {
		return empService;
	}

	// ---------------------- Observers -------------------------------
	private List<EmpObserver> empObservers = new ArrayList<EmpObserver>();

	// --------------- Registration/DeRegitration methods --------------
	public void addEmpObserver(EmpObserver observer) {
		empObservers.add(observer);
	}

	public void removeEmpObserver(EmpObserver observer) {
		empObservers.remove(observer);
	}

	public void getEmpList(Long dept_id) {
		empService.getEmpList(dept_id, empCallback);
	}

	AsyncCallback<List<EmpList>> empCallback = new AsyncCallback<List<EmpList>>() {
		@Override
		public void onFailure(Throwable caught) {
			GWT.log("Error in emp list validation", caught);
			for (EmpObserver observer : empObservers) {
				observer.notifyEmpListFailed("EmpList retrive failed: "
						+ caught);
			}
		}

		@Override
		public void onSuccess(List<EmpList> empList) {
			for (EmpObserver observer : empObservers) {
				observer.notifyEmpListSucceeded(empList);
			}
		}
	};

	public void saveNewEmp(EmpList list) {
		empService.saveNewEmp(list, empCallback);

	}

	public void updateEmp(EmpList list) {
		empService.updateEmp(list, empCallback);

	}

	public void deleteEmp(String empid) {
		empService.delEmp(empid, empCallback);

	}

}
