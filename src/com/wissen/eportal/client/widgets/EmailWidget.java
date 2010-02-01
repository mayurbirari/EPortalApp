package com.wissen.eportal.client.widgets;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.wissen.eportal.client.controllers.EmailController;
import com.wissen.eportal.client.observers.EmailObserver;

/**
 * Class represents a login widget. Provides login UI for user.
 * 
 * @author wissen16
 * 
 */
public class EmailWidget extends Composite implements EmailObserver {

	private HorizontalPanel emailPanel;
	private TextBox toTextBox;
	private TextBox subjectTextBox;
	private TextArea msgTextArea;
	private FlexTable emailTable;
	private Button sendButton;
	private Button cancelButton;

	public EmailWidget() {
		init();

		emailPanel.add(emailTable);
		EmailController.getInstance().addEmailObserver(this);

		sendButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				sendemail();
			}
		});
		
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setVisible(false);
			}
		});
		initWidget(emailPanel);
	}

	private void init() {
		emailPanel = new HorizontalPanel();
		toTextBox = new TextBox();
		subjectTextBox = new TextBox();
		msgTextArea = new TextArea();
		msgTextArea.setSize("300","100");
		sendButton = new Button("send");
		cancelButton = new Button("Discard");

		// creating table
		emailTable = new FlexTable();
		emailTable.setText(1, 0, "Sender Email id: ");
		emailTable.setWidget(1, 1, toTextBox);
		emailTable.setText(2, 0, "subject : ");
		emailTable.setWidget(2, 1, subjectTextBox);
		emailTable.setText(3, 0, "News/Message : ");
		emailTable.setWidget(3, 1,msgTextArea);
		emailTable.setWidget(4, 0, sendButton);
		emailTable.setWidget(4, 1, cancelButton);
		emailTable.getFlexCellFormatter().setAlignment(4, 0,
				HasHorizontalAlignment.ALIGN_CENTER,
				HasVerticalAlignment.ALIGN_MIDDLE);
		emailTable.setBorderWidth(1);
	}

	/**
	 * Method validates user based on userName and password against database.
	 * 
	 * */
	private void sendemail() {
		EmailController.getInstance().sendemail(toTextBox.getText(),
				subjectTextBox.getText(),msgTextArea.getText());
	}


	@Override
	public void notifyEmailFailed(String err) {
		
		
	}

	@Override
	public void notifyEmailSucceeded(boolean flag) {
		if(flag==true){
			this.setVisible(false);
			
		}
		
	}
}
