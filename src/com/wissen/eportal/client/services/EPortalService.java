package com.wissen.eportal.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.wissen.eportal.client.data.DeptList;
import com.wissen.eportal.client.data.EmpList;
import com.wissen.eportal.client.data.TaskList;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface EPortalService extends RemoteService {
	String validateUser(String name, String pwd);

	List<DeptList> getDeptList();

	List<DeptList> saveNewDept(DeptList dept);

	List<DeptList> updateDept(DeptList dept);

	List<DeptList> delDept(long deptid);

	List<EmpList> getEmpList(long dept_id);

	List<EmpList> saveNewEmp(EmpList emp);

	List<EmpList> updateEmp(EmpList emp);

	List<EmpList> delEmp(String empid);

	List<TaskList> getTaskList(String emp_id);

	List<TaskList> saveNewTask(TaskList list);

	List<TaskList> updateTask(TaskList list);

	List<TaskList> delTask(long taskid);

	List<TaskList> saveTaskstatus(long parseLong, String empid, String status);
	
	Boolean sendmail(String to, String sub, String msg);
}
