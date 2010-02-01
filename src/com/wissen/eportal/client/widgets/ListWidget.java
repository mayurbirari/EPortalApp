/**
 * 
 */
package com.wissen.eportal.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;

import com.google.gwt.user.client.ui.ListBox;
import com.wissen.eportal.client.controllers.EmpController;
import com.wissen.eportal.client.controllers.LoginController;
import com.wissen.eportal.client.controllers.TaskController;

/**
 * Class represents home widget.
 * 
 * @author wissen16
 * 
 */
public class ListWidget extends Composite {
	private HorizontalPanel panel;

	private FlexTable frameTable;

	private ListBox frameListBox;

	private Button addButton, delButton, updateButton;
	
	private  MultiWordSuggestOracle oracle;
	
	private SuggestBox box;
	public ListWidget(final String entity) {
		init();
		frameTable.setWidget(1, 1, getFrameListBox());
		frameTable.getFlexCellFormatter().setColSpan(1, 1, 5);
		frameTable.setWidget(3, 1, addButton);
		frameTable.setWidget(3, 2, updateButton);
		frameTable.setWidget(3, 3, delButton);
		frameTable.setWidget(6, 1,new Label("find"));
		frameTable.setWidget(7, 1,box);
		frameTable.getFlexCellFormatter().setColSpan(7, 1, 4);
		panel.add(frameTable);

		getFrameListBox().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (entity.equals("dept"))
					getSelectedDeptEmp(Long.parseLong(getFrameListBox()
							.getValue(getFrameListBox().getSelectedIndex())));
				else if (entity.equals("emp"))
					getSelectedEmpTask(getFrameListBox().getValue(
							getFrameListBox().getSelectedIndex()));
			}
		});
		this.getDelButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (entity.equals("dept"))
					delDept(getFrameListBox().getValue(
							getFrameListBox().getSelectedIndex()));
				else if (entity.equals("emp"))
					delEmp(getFrameListBox().getValue(
							getFrameListBox().getSelectedIndex()));
				else
					delTask(getFrameListBox().getValue(
							getFrameListBox().getSelectedIndex()));
			}
		});

		initWidget(panel);
	}

	void init() {
		panel = new HorizontalPanel();
		frameTable = new FlexTable();
		setFrameListBox(new ListBox());
		getFrameListBox().setVisibleItemCount(4);
		getFrameListBox().setSize("150", "175");

		addButton = new Button("ADD");
		updateButton = new Button("EDIT");
		delButton = new Button("DELETE");
		oracle = new MultiWordSuggestOracle(); 
		box=new SuggestBox(oracle);
	}

	/**
	 * @return the oracle
	 */
	public MultiWordSuggestOracle getOracle() {
		return oracle;
	}

	/**
	 * @param oracle the oracle to set
	 */
	public void setOracle(MultiWordSuggestOracle oracle) {
		this.oracle = oracle;
	}

	/**
	 * @return the box
	 */
	public SuggestBox getBox() {
		return box;
	}

	/**
	 * @param box the box to set
	 */
	public void setBox(SuggestBox box) {
		this.box = box;
	}

	/**
	 * @return the addButton
	 */
	public Button getAddButton() {
		return addButton;
	}

	/**
	 * @param addButton
	 *            the addButton to set
	 */
	public void setAddButton(Button addButton) {
		this.addButton = addButton;
	}

	/**
	 * @return the delButton
	 */
	public Button getDelButton() {
		return delButton;
	}

	/**
	 * @param delButton
	 *            the delButton to set
	 */
	public void setDelButton(Button delButton) {
		this.delButton = delButton;
	}

	/**
	 * @return the updateButton
	 */
	public Button getUpdateButton() {
		return updateButton;
	}

	/**
	 * @param updateButton
	 *            the updateButton to set
	 */
	public void setUpdateButton(Button updateButton) {
		this.updateButton = updateButton;
	}

	public void setFrameListBox(ListBox frameListBox) {
		this.frameListBox = frameListBox;
	}

	public ListBox getFrameListBox() {
		return frameListBox;
	}

	void getSelectedDeptEmp(long dept_id) {
		EmpController.getInstance().getEmpList(dept_id);
	}

	void getSelectedEmpTask(String emp_id) {
		TaskController.getInstance().getTaskList(emp_id);
	}

	void delDept(String deptid) {
		LoginController.getInstance().delDept(Long.parseLong(deptid));
	}

	void delEmp(String empid) {
		EmpController.getInstance().deleteEmp(empid);
	}

	void delTask(String taskid) {
		TaskController.getInstance().delTask(Long.parseLong(taskid));
	}
}
