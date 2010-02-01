package com.wissen.eportal.client.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.wissen.eportal.client.data.TaskList;
import com.wissen.eportal.client.observers.TaskObserver;
import com.wissen.eportal.client.services.EPortalService;
import com.wissen.eportal.client.services.EPortalServiceAsync;

/**
 * Class represents a controller part of MVC. Its acts as a Mediator to
 * application.
 * 
 * @author wissen16
 */
public class TaskController {

	/**
	 * Create a remote service proxy to talk to the server-side Task service.
	 */
	private final EPortalServiceAsync taskService = GWT
			.create(EPortalService.class);
	private static TaskController _instance;

	private TaskController() {
	}

	public static synchronized TaskController getInstance() {
		if (_instance == null) {
			_instance = new TaskController();
		}
		return _instance;
	}

	public EPortalServiceAsync gettaskService() {
		return taskService;
	}

	// ---------------------- Observers -------------------------------
	private List<TaskObserver> taskObservers = new ArrayList<TaskObserver>();

	// --------------- Registration/DeRegitration methods --------------
	public void addTaskObserver(TaskObserver observer) {
		taskObservers.add(observer);
	}

	public void removeEmpObserver(TaskObserver observer) {
		taskObservers.remove(observer);
	}

	public void getTaskList(String emp_id) {
		taskService.getTaskList(emp_id, taskCallback);
	}

	AsyncCallback<List<TaskList>> taskCallback = new AsyncCallback<List<TaskList>>() {
		@Override
		public void onFailure(Throwable caught) {
			GWT.log("Error in task list validation", caught);
			for (TaskObserver observer : taskObservers) {
				observer.notifyTaskListFailed("task List retrive failed: "
						+ caught);
			}
		}

		@Override
		public void onSuccess(List<TaskList> taskList) {
			// Window.alert(taskList.get(0).getUserList().get(0));
			for (TaskObserver observer : taskObservers) {
				observer.notifyTaskListSucceeded(taskList);
			}
		}
	};

	public void saveNewTask(TaskList list) {
		taskService.saveNewTask(list, taskCallback);
	}

	public void updateTask(TaskList list) {
		taskService.updateTask(list, taskCallback);

	}

	public void delTask(long taskid) {
		taskService.delTask(taskid, taskCallback);

	}

	public void saveTaskstatus(String taskid, String empid, String status) {
		Window.alert("Controller tak thick he:" + taskid);
		taskService.saveTaskstatus(Long.parseLong(taskid), empid, status,
				taskCallback);

	}

}
