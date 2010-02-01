package com.wissen.eportal.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.wissen.eportal.client.controllers.LoginController;

/**
 * Class represents a login widget. Provides login UI for user.
 * 
 * @author wissen16
 * 
 */
public class LoginWidget extends Composite {

	private HorizontalPanel loginPanel;
	private TextBox userNameTextBox;
	private PasswordTextBox passwordTextBox;
	private FlexTable loginTable;
	private Button loginButton;

	public LoginWidget() {
		init();

		loginPanel.add(loginTable);

		loginButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				validateUser();
			}
		});

		initWidget(loginPanel);
	}

	private void init() {
		loginPanel = new HorizontalPanel();
		userNameTextBox = new TextBox();
		passwordTextBox = new PasswordTextBox();
		loginButton = new Button("Login");

		// creating table
		loginTable = new FlexTable();
		loginTable.setText(1, 0, "User Login Name: ");
		loginTable.setWidget(1, 1, userNameTextBox);
		loginTable.setText(2, 0, "User Password: ");
		loginTable.setWidget(2, 1, passwordTextBox);
		loginTable.setWidget(3, 0, loginButton);
		loginTable.getFlexCellFormatter().setColSpan(3, 0, 2);
		loginTable.getFlexCellFormatter().setAlignment(3, 0,
				HasHorizontalAlignment.ALIGN_CENTER,
				HasVerticalAlignment.ALIGN_MIDDLE);
	}

	/**
	 * Method validates user based on userName and password against database.
	 * 
	 * */
	private void validateUser() {
		LoginController.getInstance().validateUser(userNameTextBox.getText(),
				passwordTextBox.getText());
	}
}
