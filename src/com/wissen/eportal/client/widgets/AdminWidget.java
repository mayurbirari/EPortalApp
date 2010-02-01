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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.wissen.eportal.client.controllers.EmpController;
import com.wissen.eportal.client.controllers.LoginController;
import com.wissen.eportal.client.controllers.TaskController;
import com.wissen.eportal.client.data.DeptList;
import com.wissen.eportal.client.data.EmpList;
import com.wissen.eportal.client.data.TaskList;
import com.wissen.eportal.client.observers.DeptObserver;
import com.wissen.eportal.client.observers.EmpObserver;
import com.wissen.eportal.client.observers.TaskObserver;

/**
 * Class represents home widget.
 * 
 * @author wissen16
 * 
 */
public class AdminWidget extends Composite implements DeptObserver,
		EmpObserver, TaskObserver {

	private HorizontalPanel panel;

	private FlexTable adminTable;

	private ListWidget deptListWidget;

	private ListWidget empListWidget;

	private ListWidget taskListWidget;

	private DeptSaveWidget deptSaveWidget;

	private EmpSaveWidget empSaveWidget;

	private TaskSaveWidget taskSaveWidget;

	private List<DeptList> deptListforUpdate;

	private List<EmpList> empListforUpdate;

	private List<TaskList> taskListforUpdate;

	private Button emailButton;

	public AdminWidget() {
		init();

		adminTable.setWidget(0, 0, new Label("WELCOME.... Administrator "));
		adminTable.setWidget(1, 1, deptListWidget);
		adminTable.setWidget(1, 3, empListWidget);
		adminTable.setWidget(1, 5, taskListWidget);
		adminTable.setWidget(1, 7, deptSaveWidget);
		adminTable.setWidget(1, 8, empSaveWidget);
		adminTable.setWidget(1, 9, taskSaveWidget);
		adminTable.setWidget(3, 3, emailButton);
		deptSaveWidget.setVisible(false);
		empSaveWidget.setVisible(false);
		taskSaveWidget.setVisible(false);
		panel.add(adminTable);
		LoginController.getInstance().addDeptObserver(this);
		LoginController.getInstance().getDeptList();
		EmpController.getInstance().addEmpObserver(this);
		EmpController.getInstance().getEmpList(Long.parseLong("0"));
		TaskController.getInstance().addTaskObserver(this);
		TaskController.getInstance().getTaskList("");

		deptListWidget.getAddButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				deptSaveWidget.clearField();
				deptSaveWidget.setVisible(true);
				empSaveWidget.setVisible(false);
				taskSaveWidget.setVisible(false);

			}
		});

		deptListWidget.getUpdateButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				fillDeptWidget(deptListWidget.getFrameListBox().getValue(
						deptListWidget.getFrameListBox().getSelectedIndex()));
				empSaveWidget.setVisible(false);
				taskSaveWidget.setVisible(false);
				deptSaveWidget.setVisible(true);
			}
		});

		empListWidget.getAddButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				empSaveWidget.clearField();
				deptSaveWidget.setVisible(false);
				taskSaveWidget.setVisible(false);
				empSaveWidget.setVisible(true);
			}
		});

		empListWidget.getUpdateButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				fillEmpWidget(empListWidget.getFrameListBox().getValue(
						empListWidget.getFrameListBox().getSelectedIndex()));
				taskSaveWidget.setVisible(false);
				deptSaveWidget.setVisible(false);
				empSaveWidget.setVisible(true);
			}
		});

		taskListWidget.getAddButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				taskSaveWidget.clearField();
				deptSaveWidget.setVisible(false);
				empSaveWidget.setVisible(false);
				taskSaveWidget.setVisible(true);
				addUpdateElementofTask();
			}
		});

		taskListWidget.getUpdateButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				fillTaskWidget(taskListWidget.getFrameListBox().getValue(
						taskListWidget.getFrameListBox().getSelectedIndex()));
				taskSaveWidget.setVisible(true);
				deptSaveWidget.setVisible(false);
				empSaveWidget.setVisible(false);
				addUpdateElementofTask();
			}
		});

		taskListWidget.getFrameListBox().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				fillTaskWidget(taskListWidget.getFrameListBox().getValue(
						taskListWidget.getFrameListBox().getSelectedIndex()));
				taskSaveWidget.setVisible(true);
				deptSaveWidget.setVisible(false);
				empSaveWidget.setVisible(false);
				taskSaveWidget.getSelectedUserStatusList().setVisible(true);
				taskSaveWidget.getAllUserList().setVisible(false);
				taskSaveWidget.getSaveButton().setVisible(false);
				taskSaveWidget.getSelectedUserList().setEnabled(false);
				taskSaveWidget.getFrameTable().setWidget(4, 1,
						taskSaveWidget.getSelectedUserList());
				taskSaveWidget.getFrameTable().setWidget(4, 2,
						taskSaveWidget.getSelectedUserStatusList());
			}
		});

		emailButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				adminTable.setWidget(4, 1, new EmailWidget());
				adminTable.getFlexCellFormatter().setColSpan(4, 1, 4);
			}

		});

		initWidget(panel);
	}

	void init() {
		panel = new HorizontalPanel();
		adminTable = new FlexTable();
		deptListWidget = new ListWidget("dept");
		empListWidget = new ListWidget("emp");
		taskListWidget = new ListWidget("task");
		deptSaveWidget = new DeptSaveWidget();
		empSaveWidget = new EmpSaveWidget();
		taskSaveWidget = new TaskSaveWidget();
		emailButton = new Button("Publish News");
	}

	/**
	 * to get selected emp
	 * 
	 * @param dept_name
	 *            to retrived perticular emp
	 */

	void addUpdateElementofTask() {
		taskSaveWidget.getSelectedUserStatusList().setVisible(false);
		taskSaveWidget.getSelectedUserList().setEnabled(true);
		taskSaveWidget.getAllUserList().setVisible(true);
		taskSaveWidget.getSaveButton().setVisible(true);
		taskSaveWidget.getFrameTable().setWidget(4, 1,
				taskSaveWidget.getAllUserList());
		taskSaveWidget.getFrameTable().setWidget(4, 2,
				taskSaveWidget.getSelectedUserList());
	}

	void fillTaskWidget(String Taskid) {
		for (int i = 0; i < taskListforUpdate.size(); i++) {
			TaskList tasklist = taskListforUpdate.get(i);
			if (tasklist.getId() == Long.parseLong(Taskid)) {
				this.taskSaveWidget.fillWidget(tasklist);
				break;
			} else
				continue;
		}

	}

	void fillEmpWidget(String empid) {
		for (int i = 0; i < empListforUpdate.size(); i++) {
			EmpList emplist = empListforUpdate.get(i);
			if (emplist.getId().equals(empid)) {
				empSaveWidget.fillWidget(emplist);
				break;
			} else
				continue;
		}

	}

	void fillDeptWidget(String Deptid) {
		for (int i = 0; i < deptListforUpdate.size(); i++) {
			DeptList deptlist = deptListforUpdate.get(i);
			if (deptlist.getId() == Long.parseLong(Deptid)) {
				deptSaveWidget.fillWidget(deptlist);
				break;
			} else
				continue;
		}
	}

	@Override
	public void notifyDeptListFailed(String errorMessage) {
		Window.alert(errorMessage);
	}

	@Override
	public void notifyDeptListSucceeded(List<DeptList> deptList) {
		deptListforUpdate = deptList;
		deptListWidget.getFrameListBox().clear();
		for (int i = 0; i < deptList.size(); i++) {
			DeptList list = deptList.get(i);
			deptListWidget.getOracle().add(list.getName());
			deptListWidget.getFrameListBox().addItem(list.getName(),
					String.valueOf(list.getId()));
		}
	}

	@Override
	public void notifyEmpListFailed(String errorMessage) {
		Window.alert(errorMessage);

	}

	@Override
	public void notifyEmpListSucceeded(List<EmpList> empList) {
		empListforUpdate = empList;
		empListWidget.getFrameListBox().clear();
		for (int i = 0; i < empList.size(); i++) {
			EmpList list = empList.get(i);
			empListWidget.getOracle().add(list.getName());
			empListWidget.getFrameListBox().addItem(list.getName(),
					list.getId());
		}

	}

	@Override
	public void notifyTaskListFailed(String errorMessage) {
		Window.alert(errorMessage);

	}

	@Override
	public void notifyTaskListSucceeded(List<TaskList> taskList) {
		taskListforUpdate = taskList;
		taskListWidget.getFrameListBox().clear();
		for (int i = 0; i < taskList.size(); i++) {
			TaskList list = taskList.get(i);
			taskListWidget.getOracle().add(list.getName());
			taskListWidget.getFrameListBox().addItem(list.getName(),
					String.valueOf(list.getId()));
		}
	}
}
