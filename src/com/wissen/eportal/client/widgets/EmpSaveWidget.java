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
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.wissen.eportal.client.controllers.EmpController;
import com.wissen.eportal.client.data.EmpList;

/**
 * Class represents home widget.
 * 
 * @author wissen16
 * 
 */
public class EmpSaveWidget extends Composite {
	private HorizontalPanel panel;

	private FlexTable frameTable;

	private TextBox empIdTextBox;

	private TextBox empNameTextBox;

	private PasswordTextBox empPwdTextBox;

	private TextBox empAddrTextBox;

	private TextBox empRoleTextBox;

	private TextBox empEmailIdTextBox;

	private TextBox empDeptIdTextBox;

	private Button saveButton;

	private Button cancelButton;

	private boolean updateFlag = false;

	public EmpSaveWidget() {
		init();
		frameTable.setWidget(0, 0, new Label("EMPLOYEE INFORMATION :"));
		frameTable.getFlexCellFormatter().setColSpan(0, 0, 3);
		frameTable.getFlexCellFormatter().setAlignment(3, 0,
				HasHorizontalAlignment.ALIGN_CENTER,
				HasVerticalAlignment.ALIGN_MIDDLE);
		frameTable.setWidget(1, 1, new Label("EMP ID :"));
		frameTable.setWidget(1, 2, empIdTextBox);
		frameTable.setWidget(2, 1, new Label("EMP NAME :"));
		frameTable.setWidget(2, 2, empNameTextBox);
		frameTable.setWidget(3, 1, new Label("EMP PASSWORD :"));
		frameTable.setWidget(3, 2, empPwdTextBox);
		frameTable.setWidget(4, 1, new Label("EMP ADDRESS :"));
		frameTable.setWidget(4, 2, empAddrTextBox);
		frameTable.setWidget(5, 1, new Label("EMP ROLE :"));
		frameTable.setWidget(5, 2, empRoleTextBox);
		frameTable.setWidget(6, 1, new Label("EMP EMAIL-ID :"));
		frameTable.setWidget(6, 2, empEmailIdTextBox);
		frameTable.setWidget(7, 1, new Label("EMP DEPARTMENT_ID :"));
		frameTable.setWidget(7, 2, empDeptIdTextBox);

		frameTable.setWidget(8, 1, saveButton);
		frameTable.setWidget(8, 2, cancelButton);
		frameTable.setBorderWidth(2);

		panel.add(frameTable);

		this.saveButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				saveNewEmp();
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

		empIdTextBox = new TextBox();
		empNameTextBox = new TextBox();
		empPwdTextBox = new PasswordTextBox();
		empAddrTextBox = new TextBox();
		empRoleTextBox = new TextBox();
		empEmailIdTextBox = new TextBox();
		empDeptIdTextBox = new TextBox();

		saveButton = new Button("SAVE");
		cancelButton = new Button("CANCEL");
	}

	/**
	 * @return the empIdTextBox
	 */
	public TextBox getEmpIdTextBox() {
		return empIdTextBox;
	}

	/**
	 * @param empIdTextBox
	 *            the empIdTextBox to set
	 */
	public void setEmpIdTextBox(TextBox empIdTextBox) {
		this.empIdTextBox = empIdTextBox;
	}

	/**
	 * @return the empNameTextBox
	 */
	public TextBox getEmpNameTextBox() {
		return empNameTextBox;
	}

	/**
	 * @param empNameTextBox
	 *            the empNameTextBox to set
	 */
	public void setEmpNameTextBox(TextBox empNameTextBox) {
		this.empNameTextBox = empNameTextBox;
	}

	/**
	 * @return the empPwdTextBox
	 */
	public TextBox getEmpPwdTextBox() {
		return empPwdTextBox;
	}

	/**
	 * @param empPwdTextBox
	 *            the empPwdTextBox to set
	 */
	public void setEmpPwdTextBox(PasswordTextBox empPwdTextBox) {
		this.empPwdTextBox = empPwdTextBox;
	}

	/**
	 * @return the empAddrTextBox
	 */
	public TextBox getEmpAddrTextBox() {
		return empAddrTextBox;
	}

	/**
	 * @param empAddrTextBox
	 *            the empAddrTextBox to set
	 */
	public void setEmpAddrTextBox(TextBox empAddrTextBox) {
		this.empAddrTextBox = empAddrTextBox;
	}

	/**
	 * @return the empRoleTextBox
	 */
	public TextBox getEmpRoleTextBox() {
		return empRoleTextBox;
	}

	/**
	 * @param empRoleTextBox
	 *            the empRoleTextBox to set
	 */
	public void setEmpRoleTextBox(TextBox empRoleTextBox) {
		this.empRoleTextBox = empRoleTextBox;
	}

	/**
	 * @return the empEmailIdTextBox
	 */
	public TextBox getEmpEmailIdTextBox() {
		return empEmailIdTextBox;
	}

	/**
	 * @param empEmailIdTextBox
	 *            the empEmailIdTextBox to set
	 */
	public void setEmpEmailIdTextBox(TextBox empEmailIdTextBox) {
		this.empEmailIdTextBox = empEmailIdTextBox;
	}

	/**
	 * @return the empDeptIdTextBox
	 */
	public TextBox getEmpDeptIdTextBox() {
		return empDeptIdTextBox;
	}

	/**
	 * @param empDeptIdTextBox
	 *            the empDeptIdTextBox to set
	 */
	public void setEmpDeptIdTextBox(TextBox empDeptIdTextBox) {
		this.empDeptIdTextBox = empDeptIdTextBox;
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

	void saveNewEmp() {
		if (empIdTextBox.getText().equals("")
				|| empNameTextBox.getText().equals("")
				|| empAddrTextBox.getText().equals("")
				|| empRoleTextBox.getText().equals("")
				|| empDeptIdTextBox.getText().equals("")
				|| empEmailIdTextBox.getText().equals("")
				|| empPwdTextBox.getText().equals(""))
			Window.alert("Id or Dept_Name or Description  is empty ");
		else {
			EmpList list = new EmpList();
			list.setId(empIdTextBox.getText());
			list.setName(empNameTextBox.getText());
			list.setPwd(empPwdTextBox.getText());
			list.setAddr(empAddrTextBox.getText());
			list.setRole(empRoleTextBox.getText());
			list.setEmailid(empEmailIdTextBox.getText());
			list.setDeptid(Long.parseLong(empDeptIdTextBox.getText()));
			if (this.updateFlag == false)
				EmpController.getInstance().saveNewEmp(list);
			else
				EmpController.getInstance().updateEmp(list);
		}
		this.visibleFalse();
	}

	public void fillWidget(EmpList emplist) {

		this.updateFlag = true;
		this.getEmpIdTextBox().setEnabled(false);

		this.getEmpIdTextBox().setText(String.valueOf(emplist.getId()));
		this.getEmpNameTextBox().setText(emplist.getName());
		this.getEmpAddrTextBox().setText(emplist.getAddr());
		this.getEmpPwdTextBox().setText(emplist.getPwd());
		this.getEmpRoleTextBox().setText(emplist.getRole());
		this.getEmpEmailIdTextBox().setText(emplist.getEmailid());
		this.getEmpDeptIdTextBox().setText(String.valueOf(emplist.getDeptid()));

	}

	public void clearField() {

		this.updateFlag = false;
		this.getEmpIdTextBox().setEnabled(true);

		this.getEmpIdTextBox().setText(null);
		this.getEmpNameTextBox().setText(null);
		this.getEmpAddrTextBox().setText(null);
		this.getEmpPwdTextBox().setText(null);
		this.getEmpRoleTextBox().setText(null);
		this.getEmpEmailIdTextBox().setText(null);
		this.getEmpDeptIdTextBox().setText(null);
	}
}
