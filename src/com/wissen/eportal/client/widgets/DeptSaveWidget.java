/**
 * 
 */
package com.wissen.eportal.client.widgets;

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
import com.google.gwt.user.client.ui.TextBox;
import com.wissen.eportal.client.controllers.LoginController;
import com.wissen.eportal.client.data.DeptList;

/**
 * Class represents home widget.
 * 
 * @author wissen16
 * 
 */
public class DeptSaveWidget extends Composite {
	private HorizontalPanel panel;

	private FlexTable frameTable;

	private TextBox deptIdTextBox;

	private TextBox deptNameTextBox;

	private TextBox deptDescrTextBox;

	private Button saveButton;

	private Button cancelButton;

	private boolean updateFlag = false;

	public DeptSaveWidget() {
		init();
		frameTable.setWidget(0, 0, new Label("DEPARTMENT INFORMATION :"));
		frameTable.getFlexCellFormatter().setColSpan(0, 0, 3);
		frameTable.getFlexCellFormatter().setAlignment(3, 0,
				HasHorizontalAlignment.ALIGN_CENTER,
				HasVerticalAlignment.ALIGN_MIDDLE);
		frameTable.setWidget(1, 1, new Label("DEPT ID :"));
		frameTable.setWidget(1, 2, deptIdTextBox);
		frameTable.setWidget(2, 1, new Label("DEPT NAME :"));
		frameTable.setWidget(2, 2, deptNameTextBox);
		frameTable.setWidget(3, 1, new Label("DEPT DESCRIPTION :"));
		frameTable.setWidget(3, 2, deptDescrTextBox);
		frameTable.setWidget(4, 1, saveButton);
		frameTable.setWidget(4, 2, cancelButton);
		frameTable.setBorderWidth(2);

		panel.add(frameTable);

		this.saveButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				saveNewDept();
			}
		});

		this.cancelButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				visibleFalse();
			}
		});

		initWidget(panel);
	}

	void visibleFalse() {
		this.setVisible(false);
	}

	void init() {
		panel = new HorizontalPanel();
		frameTable = new FlexTable();
		deptIdTextBox = new TextBox();
		deptNameTextBox = new TextBox();
		deptDescrTextBox = new TextBox();
		saveButton = new Button("SAVE");
		cancelButton = new Button("CANCEL");
	}

	void saveNewDept() {
		if (deptIdTextBox.getText().equals("")
				|| deptNameTextBox.getText().equals("")
				|| deptDescrTextBox.getText().equals(""))
			Window.alert("Id or Dept_Name or Description  is empty ");
		else {
			if (this.updateFlag == false)
				LoginController.getInstance().saveNewDept(
						Long.parseLong(deptIdTextBox.getText()),
						deptNameTextBox.getText(), deptDescrTextBox.getText());
			else
				LoginController.getInstance().updateDept(
						Long.parseLong(deptIdTextBox.getText()),
						deptNameTextBox.getText(), deptDescrTextBox.getText());
		}
		this.visibleFalse();
	}

	/**
	 * @return the deptIdTextBox
	 */
	public TextBox getDeptIdTextBox() {
		return deptIdTextBox;
	}

	/**
	 * @param deptIdTextBox
	 *            the deptIdTextBox to set
	 */
	public void setDeptIdTextBox(TextBox deptIdTextBox) {
		this.deptIdTextBox = deptIdTextBox;
	}

	/**
	 * @return the deptNameTextBox
	 */
	public TextBox getDeptNameTextBox() {
		return deptNameTextBox;
	}

	/**
	 * @param deptNameTextBox
	 *            the deptNameTextBox to set
	 */
	public void setDeptNameTextBox(TextBox deptNameTextBox) {
		this.deptNameTextBox = deptNameTextBox;
	}

	/**
	 * @return the deptDescrTextBox
	 */
	public TextBox getDeptDescrTextBox() {
		return deptDescrTextBox;
	}

	/**
	 * @param deptDescrTextBox
	 *            the deptDescrTextBox to set
	 */
	public void setDeptDescrTextBox(TextBox deptDescrTextBox) {
		this.deptDescrTextBox = deptDescrTextBox;
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

	public void fillWidget(DeptList deptlist) {

		this.updateFlag = true;
		this.getDeptIdTextBox().setEnabled(false);
		this.getDeptIdTextBox().setText(String.valueOf(deptlist.getId()));
		this.getDeptNameTextBox().setText(deptlist.getName());
		this.getDeptDescrTextBox().setText(deptlist.getDescr());
	}

	public void clearField() {

		this.updateFlag = false;
		this.getDeptIdTextBox().setEnabled(true);
		this.getDeptIdTextBox().setText(null);
		this.getDeptNameTextBox().setText(null);
		this.getDeptDescrTextBox().setText(null);
	}
}
