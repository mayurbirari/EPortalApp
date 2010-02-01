/**
 * wissen16
 * 23-Jan-2010
 */
package com.wissen.eportal.server;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wissen.eportal.client.data.TaskList;
import com.wissen.eportal.server.domainobjects.Task;
import com.wissen.eportal.server.domainobjects.User;
import com.wissen.eportal.server.domainobjects.UserTask;
import com.wissen.eportal.server.hibernate.HibernateUtil;

/**
 * @author wissen16
 * 
 */
public class TaskListOperation {

	@SuppressWarnings("unchecked")
	public List<Task> getAllTaskList() {
		List<Task> taskList;
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSession();
		taskList = session.createQuery("from Task").list();
		return taskList;
	}

	@SuppressWarnings("unchecked")
	public List<UserTask> getEmpTaskList(String userid) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSession();
		List<UserTask> userTask = null;
		try {
			Query taskValidationQuery = session
					.createQuery("from UserTask where user.id=:uid");// usertask
			// where
			// usertask.user.id="+userid).list();
			taskValidationQuery.setParameter("uid", userid);
			userTask = taskValidationQuery.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return userTask;
	}

	@SuppressWarnings("unchecked")
	public String[][] getEmpOfTask(long taskid) {

		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSession();
		List<UserTask> userTask = null;
		String[][] userlist = new String[10][3];
		try {
			Query taskValidationQuery = session
					.createQuery("from UserTask where task.id=:taskid");// usertask
			// where
			// usertask.user.id="+userid).list();
			taskValidationQuery.setParameter("taskid", taskid);
			userTask = taskValidationQuery.list();
			for (int i = 0; i < userTask.size(); i++) {
				UserTask usert = userTask.get(i);
				User user = usert.getUser();
				userlist[i][0] = user.getId();
				userlist[i][1] = user.getName();
				userlist[i][2] = usert.getStatus();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return userlist;
	}

	public boolean saveNewTaskList(TaskList savetask) {
		try {
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.getSession();
			Transaction addtransaction = session.beginTransaction();
			Task task = new Task();
			task.setId(savetask.getId());
			task.setName(savetask.getName());
			task.setDescr(savetask.getDescr());
			// task.setCreatedDTTM(Timestamp.valueOf("2010-01-25 20-18-40"));
			session.save(task);
			savetask.setId(task.getId());
			addtransaction.commit();
			session.close();
		} catch (Exception e) {
			return false;
		}
		return saveUserTask(savetask.getId(), savetask.getUserList());
	}

	public boolean saveUserTask(long taskid, String[][] userList) {
		for (int i = 0; i < userList.length; i++) {
			if (userList[i][0] == null)
				break;
			try {
				HibernateUtil hibernateUtil = new HibernateUtil();
				Session session = hibernateUtil.getSession();
				Transaction addtransaction = session.beginTransaction();
				UserTask utask = new UserTask();
				Task task = (Task) session.get(Task.class, taskid);
				utask.setTask(task);
				User user = (User) session.get(User.class, userList[i][0]);
				utask.setUser(user);
				utask.setStatus("Starting");
				session.save(utask);
				addtransaction.commit();
				session.close();
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	public boolean updateTaskList(TaskList updatetask) {
		try {
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.getSession();
			Transaction addtransaction = session.beginTransaction();
			Task task = (Task) session.get(Task.class, updatetask.getId());
			task.setName(updatetask.getName());
			task.setDescr(updatetask.getDescr());
			// task.setCreatedDTTM(Timestamp.valueOf("2010-01-25 20-18-40"));
			session.update(task);
			updatetask.setId(task.getId());
			addtransaction.commit();
			session.close();
		} catch (Exception e) {
			return false;
		}
		if (deleteusertask(updatetask.getId()) == 0)
			return false;
		return saveUserTask(updatetask.getId(), updatetask.getUserList());
	}

	public int deleteusertask(long taskid) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSession();
		try {
			Query taskValidationQuery = session
					.createQuery("delete from UserTask where task.id=:taskid");
			taskValidationQuery.setParameter("taskid", taskid);
			return taskValidationQuery.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public boolean deleteTask(long taskid) {
		if (deleteusertask(taskid) == 0)
			return false;
		try {
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.getSession();
			Transaction addtransaction = session.beginTransaction();
			Task task = (Task) session.get(Task.class, taskid);
			session.delete(task);
			addtransaction.commit();
			session.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean saveTaskstatus(long taskid, String empid, String status) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSession();
		try {
			Query taskValidationQuery = session
					.createQuery("Update UserTask set status=:status where task.id=:taskid and user.id=:userid");
			taskValidationQuery.setParameter("taskid", taskid);
			taskValidationQuery.setParameter("userid", empid);
			taskValidationQuery.setParameter("status", status);
			taskValidationQuery.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
