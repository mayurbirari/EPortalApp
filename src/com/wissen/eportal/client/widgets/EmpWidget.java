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
public class EmpWidget extends Composite implements DeptObserver, EmpObserver,
		TaskObserver {

	private HorizontalPanel panel;

	private FlexTable empTable;

	private ListWidget taskListWidget;

	private EmpSaveWidget empSaveWidget;

	private TaskSaveWidget taskSaveWidget;

	private DeptList deptForManager;

	private List<TaskList> taskListforUpdate;

	public EmpWidget() {
		init();

		empTable.setWidget(0, 0, new Label("WELCOME....Manager "));
		empTable.setWidget(1, 0, taskListWidget);
		empTable.setWidget(1, 3, empSaveWidget);
		empTable.setWidget(1, 4, taskSaveWidget);
		empSaveWidget.setVisible(false);
		taskSaveWidget.setVisible(false);
		panel.add(empTable);
		LoginController.getInstance().addDeptObserver(this);
		LoginController.getInstance().getDeptList();
		EmpController.getInstance().addEmpObserver(this);
		// EmpController.getInstance().getEmpList(deptForManager.getId());
		TaskController.getInstance().addTaskObserver(this);
		TaskController.getInstance().getTaskList("emp");

		taskListWidget.getAddButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				taskSaveWidget.clearField();
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
				empSaveWidget.setVisible(false);
				taskSaveWidget.getSelectedUserList().setVisible(true);
				taskSaveWidget.getSelectedUserStatusList().setVisible(true);
				taskSaveWidget.getAllUserList().setVisible(false);
				taskSaveWidget.getSaveButton().setVisible(false);
				taskSaveWidget.getSelectedUserList().setEnabled(false);
				taskSaveWidget.getFrameTable().setWidget(4, 1,
						taskSaveWidget.getSelectedUserList());
				taskSaveWidget.getFrameTable().setWidget(4, 2,
						taskSaveWidget.getSelectedUserStatusList());
				taskSaveWidget.getTaskStatusLabel().setVisible(false);
				taskSaveWidget.getTaskStatusTextBox().setVisible(false);
			}
		});
		this.taskSaveWidget.getSaveButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Window.alert("emp me arahe");
				saveTaskStatus();
			}
		});

//		this.taskListWidget.getBox().addClickListener(new ClickListener(){
//
//			public void onClick(ClickEvent event) {
//				Window.alert("emp me arahe");
//				saveTaskStatus();
//			}
//		});
		
		
		initWidget(panel);
	}

	void init() {
		panel = new HorizontalPanel();
		empTable = new FlexTable();
		taskListWidget = new ListWidget("task");
		taskListWidget.getAddButton().setVisible(false);
		taskListWidget.getDelButton().setVisible(false);
		taskListWidget.getUpdateButton().setText("Update Task_status");
		empSaveWidget = new EmpSaveWidget();
		taskSaveWidget = new TaskSaveWidget();
		deptForManager = new DeptList();

	}

	void saveTaskStatus() {
		Window.alert(taskSaveWidget.getTaskIdTextBox().getText() + ":"
				+ empSaveWidget.getEmpIdTextBox().getText() + ":"
				+ taskSaveWidget.getTaskStatusTextBox().getText());
		TaskController.getInstance().saveTaskstatus(
				taskSaveWidget.getTaskIdTextBox().getText(),
				empSaveWidget.getEmpIdTextBox().getText(),
				taskSaveWidget.getTaskStatusTextBox().getText());
		this.taskSaveWidget.visibleFalse();
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
		taskSaveWidget.getAllUserList().setVisible(false);
		taskSaveWidget.getSelectedUserList().setVisible(false);
		taskSaveWidget.getSaveButton().setVisible(true);
		taskSaveWidget.getFrameTable().setWidget(4, 1,
				taskSaveWidget.getAllUserList());
		taskSaveWidget.getFrameTable().setWidget(4, 2,
				taskSaveWidget.getSelectedUserList());
		taskSaveWidget.getTaskStatusLabel().setVisible(true);
		taskSaveWidget.getTaskStatusTextBox().setVisible(true);

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
		EmpList emp = empList.get(0);
		empSaveWidget.fillWidget(emp);
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
