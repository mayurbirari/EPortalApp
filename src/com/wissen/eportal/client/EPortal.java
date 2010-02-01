package com.wissen.eportal.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.wissen.eportal.client.constants.HistoryConstants;
import com.wissen.eportal.client.controllers.LoginController;
import com.wissen.eportal.client.observers.LoginObserver;
import com.wissen.eportal.client.widgets.AdminWidget;
import com.wissen.eportal.client.widgets.EmpWidget;
import com.wissen.eportal.client.widgets.LoginWidget;
import com.wissen.eportal.client.widgets.ManagerWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EPortal implements EntryPoint, LoginObserver,
		ValueChangeHandler<String> {

	private VerticalPanel containerPanel;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		containerPanel = new VerticalPanel();
		RootPanel.get("pageContent").add(containerPanel);
		containerPanel.add(new LoginWidget());
		History.addValueChangeHandler(this);
		LoginController.getInstance().addLoginObserver(this);
		History.newItem(HistoryConstants.LOGIN_HISTORY_TOKEN);
	}

	@Override
	public void notifyLoginFailed(String errorMessage) {
		Window.alert("Login fail: " + errorMessage);

	}

	@Override
	public void notifyLoginSucceeded(String role) {
		Window.alert("Login Succeed :" + role);
		if (role.equals("admin"))
			History.newItem(HistoryConstants.HOME_PAGE_HISTORY_TOKEN);
		else if (role.equals("manager"))
			History.newItem(HistoryConstants.MANAGER_PAGE_HISTORY_TOKEN);
		else if (role.equals("employee"))
			History.newItem(HistoryConstants.EMPLOYEE_PAGE_HISTORY_TOKEN);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		if (event.getValue().equals(HistoryConstants.HOME_PAGE_HISTORY_TOKEN)) {
			displayHomeWidget();
		} else if (event.getValue().equals(
				HistoryConstants.MANAGER_PAGE_HISTORY_TOKEN)) {
			displayManagerWidget();
		} else if (event.getValue().equals(
				HistoryConstants.EMPLOYEE_PAGE_HISTORY_TOKEN))
			displayEmployeeWidget();

	}

	/**
	 * to display manager widget
	 */
	private void displayManagerWidget() {
		containerPanel.clear();
		containerPanel.add(new ManagerWidget());

	}

	/**
	 * to display login widget
	 */
	public void displayLoginWidget() {
		containerPanel.clear();
		containerPanel.add(new LoginWidget());
	}

	/**
	 * to display admin widget
	 */
	public void displayHomeWidget() {
		containerPanel.clear();
		containerPanel.add(new AdminWidget());
	}

	/**
	 * to display employee widget
	 */
	public void displayEmployeeWidget() {
		containerPanel.clear();
		containerPanel.add(new EmpWidget());
	}
}
