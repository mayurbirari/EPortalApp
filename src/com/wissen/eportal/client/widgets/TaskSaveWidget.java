/**
 * 
 */
package com.wissen.eportal.client.widgets;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.wissen.eportal.client.controllers.EmpController;
import com.wissen.eportal.client.controllers.TaskController;
import com.wissen.eportal.client.data.EmpList;
import com.wissen.eportal.client.data.TaskList;
import com.wissen.eportal.client.observers.EmpObserver;

/**
 * Class represents home widget.
 * 
 * @author wissen16
 * 
 */
public class TaskSaveWidget extends Composite implements EmpObserver {
	private HorizontalPanel panel;

	private FlexTable frameTable;

	private TextBox taskIdTextBox;

	private TextBox taskNameTextBox;

	private TextBox taskDescrTextBox;

	private Label taskStatusLabel;

	private TextBox taskStatusTextBox;// for those emp who pertucularly update
	// there status

	private ListBox allUserList;

	private ListBox selectedUserList;

	private ListBox selectedUserStatusList;

	private Button saveButton;

	private Button cancelButton;

	private boolean updateFlag = false;

	public TaskSaveWidget() {
		init();
		EmpController.getInstance().addEmpObserver(this);
		frameTable.setWidget(0, 0, new Label("TASK INFORMATION :"));
		frameTable.getFlexCellFormatter().setColSpan(0, 0, 3);
		frameTable.getFlexCellFormatter().setAlignment(3, 0,
				HasHorizontalAlignment.ALIGN_CENTER,
				HasVerticalAlignment.ALIGN_MIDDLE);
		frameTable.setWidget(1, 1, new Label("TASK ID :"));
		frameTable.setWidget(1, 2, taskIdTextBox);
		frameTable.setWidget(2, 1, new Label("TASK NAME :"));
		frameTable.setWidget(2, 2, taskNameTextBox);
		frameTable.setWidget(3, 1, new Label("TASK DESCRIPTION :"));
		frameTable.setWidget(3, 2, taskDescrTextBox);
		frameTable.setWidget(4, 1, allUserList);
		frameTable.setWidget(4, 2, selectedUserList);
		// frameTable.setWidget(4, 2,selectedUserStatusList);
		frameTable.setWidget(5, 1, taskStatusLabel);
		frameTable.setWidget(5, 2, taskStatusTextBox);
		selectedUserStatusList.setVisible(false);
		selectedUserStatusList.setEnabled(false);
		frameTable.setWidget(8, 1, saveButton);
		frameTable.setWidget(8, 2, cancelButton);
		frameTable.setBorderWidth(2);

		panel.add(frameTable);

		this.saveButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				if (taskStatusLabel.isVisible()
						|| taskStatusTextBox.isVisible()) {
					return;
				}
				saveNewTask();
			}
		});

		this.cancelButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				visibleFalse();
			}
		});

		this.allUserList.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addItemInSelectedList();
			}
		});

		this.selectedUserList.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				removeItemfromSelectedList();
			}
		});

		initWidget(panel);
	}

	void addItemInSelectedList() {
		selectedUserList.addItem(allUserList.getItemText(allUserList
				.getSelectedIndex()), allUserList.getValue(allUserList
				.getSelectedIndex()));
	}

	void removeItemfromSelectedList() {
		selectedUserList.removeItem(selectedUserList.getSelectedIndex());
		selectedUserStatusList.removeItem(selectedUserList.getSelectedIndex());
	}

	void visibleFalse() {
		this.setVisible(false);
	}

	void init() {
		panel = new HorizontalPanel();
		frameTable = new FlexTable();

		taskIdTextBox = new TextBox();
		taskNameTextBox = new TextBox();
		taskDescrTextBox = new TextBox();
		taskStatusTextBox = new TextBox();
		taskStatusTextBox.setVisible(false);
		taskStatusLabel = new Label("Update Status");
		taskStatusLabel.setVisible(false);
		saveButton = new Button("SAVE");
		cancelButton = new Button("CANCEL");
		allUserList = new ListBox();
		selectedUserList = new ListBox();
		selectedUserStatusList = new ListBox();
		selectedUserList.setVisibleItemCount(4);
		selectedUserList.setSize("150", "150");
		allUserList.setVisibleItemCount(4);
		allUserList.setSize("150", "150");
		selectedUserStatusList.setVisibleItemCount(4);
		selectedUserStatusList.setSize("150", "150");

	}

	void saveNewTask() {
		if (taskIdTextBox.getText().equals("")
				|| taskNameTextBox.getText().equals("")
				|| taskDescrTextBox.getText().equals(""))
			Window.alert("Id or Name or Description  is empty ");
		else {
			TaskList list = new TaskList();
			list.setId(Long.parseLong(taskIdTextBox.getText()));
			list.setName(taskNameTextBox.getText());
			list.setDescr(taskDescrTextBox.getText());
			String[][] userlist = new String[10][2];
			for (int i = 0; i < selectedUserList.getItemCount(); i++)
				userlist[i][0] = selectedUserList.getValue(i);
			list.setUserList(userlist);
			if (this.updateFlag == false)
				TaskController.getInstance().saveNewTask(list);
			else
				TaskController.getInstance().updateTask(list);
		}
		this.visibleFalse();
	}

	public void fillWidget(TaskList tasklist) {
		this.getTaskDescrTextBox().setText(tasklist.getDescr());
		this.getTaskIdTextBox().setText(String.valueOf(tasklist.getId()));
		this.getTaskNameTextBox().setText(tasklist.getName());
		this.selectedUserList.clear();
		this.selectedUserStatusList.clear();
		if (tasklist.getUserList() != null)
			for (int i = 0; i < tasklist.getUserList().length; i++) {
				if (tasklist.getUserList()[i][0] == null)
					break;
				this.selectedUserList.addItem(tasklist.getUserList()[i][1],
						tasklist.getUserList()[i][0]);
				this.selectedUserStatusList.addItem(
						tasklist.getUserList()[i][2],
						tasklist.getUserList()[i][0]);
			}
		this.updateFlag = true;
	}

	public void clearField() {
		this.getTaskDescrTextBox().setText(null);
		this.getTaskIdTextBox().setText(null);
		this.getTaskNameTextBox().setText(null);
		this.getSelectedUserList().clear();
		this.updateFlag = false;
	}

	@Override
	public void notifyEmpListFailed(String errorMessage) {
		Window.alert(errorMessage);

	}

	@Override
	public void notifyEmpListSucceeded(List<EmpList> empList) {
		if (empList.size() > this.allUserList.getItemCount())
			for (int i = 0; i < empList.size(); i++) {
				EmpList list = empList.get(i);
				this.allUserList.addItem(list.getName(), list.getId());
			}
	}

	/**
	 * @return the taskIdTextBox
	 */
	public TextBox getTaskIdTextBox() {
		return taskIdTextBox;
	}

	/**
	 * @param taskIdTextBox
	 *            the taskIdTextBox to set
	 */
	public void setTaskIdTextBox(TextBox taskIdTextBox) {
		this.taskIdTextBox = taskIdTextBox;
	}

	/**
	 * @return the taskNameTextBox
	 */
	public TextBox getTaskNameTextBox() {
		return taskNameTextBox;
	}

	/**
	 * @param taskNameTextBox
	 *            the taskNameTextBox to set
	 */
	public void setTaskNameTextBox(TextBox taskNameTextBox) {
		this.taskNameTextBox = taskNameTextBox;
	}

	/**
	 * @return the taskDescrTextBox
	 */
	public TextBox getTaskDescrTextBox() {
		return taskDescrTextBox;
	}

	/**
	 * @param taskDescrTextBox
	 *            the taskDescrTextBox to set
	 */
	public void setTaskDescrTextBox(TextBox taskDescrTextBox) {
		this.taskDescrTextBox = taskDescrTextBox;
	}

	/**
	 * @return the allUserList
	 */
	public ListBox getAllUserList() {
		return allUserList;
	}

	/**
	 * @param allUserList
	 *            the allUserList to set
	 */
	public void setAllUserList(ListBox allUserList) {
		this.allUserList = allUserList;
	}

	/**
	 * @return the selectedUserList
	 */
	public ListBox getSelectedUserList() {
		return selectedUserList;
	}

	/**
	 * @param selectedUserList
	 *            the selectedUserList to set
	 */
	public void setSelectedUserList(ListBox selectedUserList) {
		this.selectedUserList = selectedUserList;
	}

	/**
	 * @return the saveButton
	 */
	public Button getSaveButton() {
		return saveButton;
	}

	/**
	 * @param saveButton
	 *            the saveButton to set
	 */
	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	/**
	 * @return the cancelButton
	 */
	public Button getCancelButton() {
		return cancelButton;
	}

	/**
	 * @param cancelButton
	 *            the cancelButton to set
	 */
	public void setCancelButton(Button cancelButton) {
		this.cancelButton = cancelButton;
	}

	/**
	 * @return the selectedUserStatusList
	 */
	public ListBox getSelectedUserStatusList() {
		return selectedUserStatusList;
	}

	/**
	 * @param selectedUserStatusList
	 *            the selectedUserStatusList to set
	 */
	public void setSelectedUserStatusList(ListBox selectedUserStatusList) {
		this.selectedUserStatusList = selectedUserStatusList;
	}

	/**
	 * @return the frameTable
	 */
	public FlexTable getFrameTable() {
		return frameTable;
	}

	/**
	 * @param frameTable
	 *            the frameTable to set
	 */
	public void setFrameTable(FlexTable frameTable) {
		this.frameTable = frameTable;
	}

	/**
	 * @return the taskStatusLabel
	 */
	public Label getTaskStatusLabel() {
		return taskStatusLabel;
	}

	/**
	 * @param taskStatusLabel
	 *            the taskStatusLabel to set
	 */
	public void setTaskStatusLabel(Label taskStatusLabel) {
		this.taskStatusLabel = taskStatusLabel;
	}

	/**
	 * @return the taskStatusTextBox
	 */
	public TextBox getTaskStatusTextBox() {
		return taskStatusTextBox;
	}

	/**
	 * @param taskStatusTextBox
	 *            the taskStatusTextBox to set
	 */
	public void setTaskStatusTextBox(TextBox taskStatusTextBox) {
		this.taskStatusTextBox = taskStatusTextBox;
	}
}
