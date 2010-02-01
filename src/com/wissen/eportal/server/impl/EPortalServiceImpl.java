package com.wissen.eportal.server.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.wissen.eportal.client.data.DeptList;
import com.wissen.eportal.client.data.EmpList;
import com.wissen.eportal.client.data.TaskList;
import com.wissen.eportal.client.services.EPortalService;
import com.wissen.eportal.server.constants.HTTPSessionConstants;
import com.wissen.eportal.server.domainobjects.Dept;
import com.wissen.eportal.server.domainobjects.Task;
import com.wissen.eportal.server.domainobjects.User;
import com.wissen.eportal.server.domainobjects.UserTask;
import com.wissen.eportal.server.emailxml.SendFileEmail;
import com.wissen.eportalTest.server.DeptListOperationTest;
import com.wissen.eportalTest.server.EmpListOperationTest;
import com.wissen.eportalTest.server.TaskListOperationTest;
import com.wissen.eportalTest.server.UserValidationTest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class EPortalServiceImpl extends RemoteServiceServlet implements
		EPortalService {

	public String validateUser(String userName, String password) {
		String isValid = "false";
		HttpServletRequest request = this.getThreadLocalRequest();
		UserValidationTest userValidationTest = new UserValidationTest();
		User user = userValidationTest.validateUser(userName, password);
		if (user != null) {
			isValid = user.getRole();
			HttpSession httpSession = request.getSession();
			httpSession
					.setAttribute(HTTPSessionConstants.USER_ID, user.getId());
			httpSession.setAttribute(HTTPSessionConstants.USER_ROLE, user
					.getRole());
			Dept dept = user.getDept();
			httpSession.setAttribute(HTTPSessionConstants.USER_DEPT_ID, String
					.valueOf(dept.getId()));
		}
		return isValid;
	}

	@Override
	public List<DeptList> getDeptList() {
		List<DeptList> deptListinfo = null;
		List<Dept> deptList = null;
		String userrole;
		DeptListOperationTest list;
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession httpSession = request.getSession();
		if (httpSession != null) {
			list = new DeptListOperationTest();
			userrole = (String) httpSession
					.getAttribute(HTTPSessionConstants.USER_ROLE);
			deptList = list.getDeptList();
			deptListinfo = getDeptList(deptList);
			if (userrole.equals("manager") || userrole.equals("employee")) {
				long deptid = Long.parseLong((String) httpSession
						.getAttribute(HTTPSessionConstants.USER_DEPT_ID));
				DeptList dept = new DeptList();
				for (int i = 0; i < deptListinfo.size(); i++) {
					dept = deptListinfo.get(i);
					if (dept.getId() == deptid) {
						break;
					}
				}
				deptListinfo.clear();
				deptListinfo.add(dept);
			}
		}
		return deptListinfo;
	}

	private List<DeptList> getDeptList(List<Dept> deptList) {
		List<DeptList> deptListinfo = new ArrayList<DeptList>();
		for (Dept dept : deptList) {
			DeptList list = new DeptList();
			list.setId(dept.getId());
			list.setName(dept.getName());
			list.setDescr(dept.getDescr());
			deptListinfo.add(list);
		}
		return deptListinfo;
	}

	@Override
	public List<EmpList> getEmpList(long dept_id) {
		List<EmpList> empListinfo = null;
		List<User> empList = null;
		EmpListOperationTest list;
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession httpSession = request.getSession();
		if (httpSession != null) {
			String userrole = (String) httpSession
					.getAttribute(HTTPSessionConstants.USER_ROLE);
			list = new EmpListOperationTest();
			if (dept_id == 0 || dept_id == 1) {
				empList = list.getAllEmpList();
			} else
				empList = list.getDeptEmpList(dept_id);
			empListinfo = getEmpList(dept_id, empList);
			if (userrole.equals("employee")) {
				String userid = (String) httpSession
						.getAttribute(HTTPSessionConstants.USER_ID);
				EmpList emp = new EmpList();
				for (int i = 0; i < empListinfo.size(); i++) {
					emp = empListinfo.get(i);
					if (emp.getId().equals(userid)) {
						break;
					}
				}
				empListinfo.clear();
				empListinfo.add(emp);
			}
		}
		return empListinfo;

	}

	private List<EmpList> getEmpList(long deptid, List<User> empList) {
		List<EmpList> empListinfo = new ArrayList<EmpList>();
		for (User user : empList) {
			EmpList list = new EmpList();
			list.setId(user.getId());
			list.setName(user.getName());
			list.setRole(user.getRole());
			list.setPwd(user.getPassword());
			list.setEmailid(user.getEmail());
			list.setAddr(user.getAddr());
			list.setDeptid(deptid);
			empListinfo.add(list);
		}
		return empListinfo;

	}

	@Override
	public List<TaskList> getTaskList(String emp_id) {
		List<TaskList> taskListinfo = null;
		List<Task> taskList = null;
		List<UserTask> usertaskList = null;
		TaskListOperationTest list;
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession httpSession = request.getSession();
		if (httpSession != null) {
			list = new TaskListOperationTest();

			if (emp_id.equals("")) {
				taskList = list.getAllTaskList();
				taskListinfo = getAlltaskList(taskList);
			} else {
				if (emp_id.equals("emp"))
					emp_id = (String) httpSession
							.getAttribute(HTTPSessionConstants.USER_ID);
				usertaskList = list.getEmpTaskList(emp_id);
				taskList = list.getAllTaskList();
				taskListinfo = getAlltaskList(taskList);
				taskListinfo.clear();
				for (int i = 0; i < usertaskList.size(); i++) {
					Task task = usertaskList.get(i).getTask();
					TaskList taskinfo = new TaskList();
					taskinfo.setId(task.getId());
					taskinfo.setName(task.getName());
					taskinfo.setDescr(task.getDescr());
					taskinfo.setUserList(list.getEmpOfTask(task.getId()));
					taskListinfo.add(taskinfo);
				}
			}
		}
		return taskListinfo;
	}

	private List<TaskList> getAlltaskList(List<Task> taskList) {
		List<TaskList> taskListinfo = new ArrayList<TaskList>();
		for (Task task : taskList) {
			TaskList list = new TaskList();
			list.setId(task.getId());
			list.setName(task.getName());
			list.setDescr(task.getDescr());
			TaskListOperationTest tlist = new TaskListOperationTest();
			list.setUserList(tlist.getEmpOfTask(task.getId()));
			taskListinfo.add(list);
		}
		return taskListinfo;

	}

	@Override
	public List<DeptList> saveNewDept(DeptList saveDept) {
		DeptListOperationTest save = new DeptListOperationTest();
		if (save.saveNewDeptList(saveDept)) {
			return getDeptList();
		} else
			return null;
	}

	@Override
	public List<DeptList> updateDept(DeptList updateDept) {
		DeptListOperationTest update = new DeptListOperationTest();
		if (update.updateDeptList(updateDept)) {
			return getDeptList();
		}
		return null;
	}

	@Override
	public List<DeptList> delDept(long deptid){
		DeptListOperationTest delete = new DeptListOperationTest();
		if (delete.deleteDept(deptid)) {
			//y
			return getDeptList();
		}
		return null;
	}

	@Override
	public List<EmpList> delEmp(String empid) {
		EmpListOperationTest delete = new EmpListOperationTest();
		if (delete.deleteEmp(empid)) {
			return getEmpList(0);
		}
		return null;
	}

	@Override
	public List<EmpList> saveNewEmp(EmpList saveEmp) {
		EmpListOperationTest save = new EmpListOperationTest();
		if (save.saveNewEmpList(saveEmp)) {
			return getEmpList(0);
		} else
			return null;
	}

	@Override
	public List<EmpList> updateEmp(EmpList updateEmp) {
		EmpListOperationTest update = new EmpListOperationTest();
		if (update.updateEmpList(updateEmp)) {
			return getEmpList(0);
		}
		return null;
	}

	@Override
	public List<TaskList> saveNewTask(TaskList savetask) {
		TaskListOperationTest save = new TaskListOperationTest();
		if (save.saveNewTaskList(savetask)) {
			return getTaskList("");
		} else
			return null;
	}

	@Override
	public List<TaskList> updateTask(TaskList updatetask) {
		TaskListOperationTest save = new TaskListOperationTest();
		if (save.updateTaskList(updatetask)) {
			return getTaskList("");
		} else
			return null;
	}

	@Override
	public List<TaskList> delTask(long taskid) {
		TaskListOperationTest delete = new TaskListOperationTest();
		if (delete.deleteTask(taskid)) {
			return getTaskList("");
		}
		return null;
	}

	@Override
	public List<TaskList> saveTaskstatus(long taskid, String empid,
			String status) {
		TaskListOperationTest updatestatus = new TaskListOperationTest();
		if (updatestatus.saveTaskstatus(taskid, empid, status)) {
			return getTaskList("emp");
		}
		return null;
	}

	@Override
	public Boolean sendmail(String to, String sub, String msg) {
//		EmailManagerUtil mail=new EmailManagerUtil();
//		return mail.sendSimpleMail();
		SendFileEmail mail=new SendFileEmail();
		return mail.sendmail(to, sub, msg);
	}
}
