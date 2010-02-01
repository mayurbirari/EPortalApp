package com.wissen.eportal.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.wissen.eportal.client.data.DeptList;
import com.wissen.eportal.client.data.EmpList;
import com.wissen.eportal.client.data.TaskList;

/**
 * The async counterpart of <code>EPortalService</code>.
 */
public interface EPortalServiceAsync {
	void validateUser(String name, String pwd, AsyncCallback<String> callback);

	void getDeptList(AsyncCallback<List<DeptList>> deptCallback);

	void getEmpList(long dept_id, AsyncCallback<List<EmpList>> empCallback);

	void getTaskList(String emp_id, AsyncCallback<List<TaskList>> taskCallback);

	void saveNewDept(DeptList dept, AsyncCallback<List<DeptList>> deptCallback);

	void updateDept(DeptList dept, AsyncCallback<List<DeptList>> deptCallback);

	void delDept(long deptid, AsyncCallback<List<DeptList>> deptCallback);

	void saveNewEmp(EmpList list, AsyncCallback<List<EmpList>> empCallback);

	void updateEmp(EmpList list, AsyncCallback<List<EmpList>> empCallback);

	void delEmp(String emaiid, AsyncCallback<List<EmpList>> empCallback);

	void saveNewTask(TaskList list, AsyncCallback<List<TaskList>> taskCallback);

	void updateTask(TaskList list, AsyncCallback<List<TaskList>> taskCallback);

	void delTask(long taskid, AsyncCallback<List<TaskList>> taskCallback);

	void saveTaskstatus(long parseLong, String empid, String status,
			AsyncCallback<List<TaskList>> taskCallback);

	void sendmail(String to, String sub, String msg,
			AsyncCallback<Boolean> emailCallback);

}
