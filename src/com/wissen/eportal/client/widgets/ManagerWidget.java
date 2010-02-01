/**
 * 
 */
package com.wissen.eportal.client.widgets;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
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
public class ManagerWidget extends Composite implements DeptObserver,
		EmpObserver, TaskObserver {

	private HorizontalPanel panel;

	private FlexTable managerTable;

	private ListWidget empListWidget;

	private ListWidget taskListWidget;

	private DeptSaveWidget deptSaveWidget;

	private EmpSaveWidget empSaveWidget;

	private TaskSaveWidget taskSaveWidget;

	private DeptList deptForManager;

	private List<EmpList> empListforUpdate;

	private List<TaskList> taskListforUpdate;

	public ManagerWidget() {
		init();

		managerTable.setWidget(0, 0, new Label("WELCOME....Manager "));
		managerTable.setWidget(1, 0, empListWidget);
		managerTable.setWidget(1, 2, taskListWidget);
		managerTable.setWidget(1, 4, deptSaveWidget);
		managerTable.setWidget(1, 5, empSaveWidget);
		managerTable.setWidget(1, 6, taskSaveWidget);

		deptSaveWidget.setVisible(false);
		empSaveWidget.setVisible(false);
		taskSaveWidget.setVisible(false);
		panel.add(managerTable);
		LoginController.getInstance().addDeptObserver(this);
		LoginController.getInstance().getDeptList();
		// Window.alert(deptForManager.getId()+":"+deptForManager.getName()+":"+deptForManager.getDescr());
		EmpController.getInstance().addEmpObserver(this);
		// EmpController.getInstance().getEmpList(deptForManager.getId());
		TaskController.getInstance().addTaskObserver(this);
		TaskController.getInstance().getTaskList("emp");

		empListWidget.getAddButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				empSaveWidget.clearField();
				deptSaveWidget.fillWidget(deptForManager);
				deptSaveWidget.setVisible(true); // this is temperary use of
													// emplist add button to
													// view dept for manager
				deptSaveWidget.getSaveButton().setVisible(false);
				taskSaveWidget.setVisible(false);
				empSaveWidget.setVisible(false);
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
				empSaveWidget.getSaveButton().setVisible(false);
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

		initWidget(panel);
	}

	void init() {
		panel = new HorizontalPanel();
		managerTable = new FlexTable();
		empListWidget = new ListWidget("emp");
		empListWidget.getAddButton().setText("View Dept");
		empListWidget.getDelButton().setVisible(false);
		empListWidget.getUpdateButton().setText("View Employee");
		taskListWidget = new ListWidget("task");
		taskListWidget.getAddButton().setVisible(false);
		taskListWidget.getDelButton().setVisible(false);
		taskListWidget.getUpdateButton().setText("Assign Task");
		deptSaveWidget = new DeptSaveWidget();
		empSaveWidget = new EmpSaveWidget();
		taskSaveWidget = new TaskSaveWidget();
		deptForManager = new DeptList();

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

	@Override
	public void notifyDeptListFailed(String errorMessage) {
		Window.alert(errorMessage);
	}

	@Override
	public void notifyDeptListSucceeded(List<DeptList> deptList) {
		this.deptForManager = deptList.get(0);
		this.deptForManager.setId(deptList.get(0).getId());
		this.deptForManager.setName(deptList.get(0).getName());
		this.deptForManager.setDescr(deptList.get(0).getDescr());
		EmpController.getInstance().getEmpList(deptForManager.getId());
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
