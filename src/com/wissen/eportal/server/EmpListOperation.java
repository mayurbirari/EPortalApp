/**
 * wissen16
 * 23-Jan-2010
 */
package com.wissen.eportal.server;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wissen.eportal.client.data.EmpList;
import com.wissen.eportal.server.domainobjects.Dept;
import com.wissen.eportal.server.domainobjects.User;
import com.wissen.eportal.server.hibernate.HibernateUtil;

/**
 * @author wissen16
 * 
 */
public class EmpListOperation {

	@SuppressWarnings("unchecked")
	public List<User> getAllEmpList() {
		List<User> empList;
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSession();
		empList = session.createQuery("from User").list();
		return empList;
	}

	public List<User> getDeptEmpList(long dept_id) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSession();
		Dept dept = (Dept) session.load(Dept.class, dept_id);
		return dept.getUserList();

	}

	public boolean saveNewEmpList(EmpList save) {
		try {
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.getSession();
			Transaction addtransaction = session.beginTransaction();
			User emp = new User();
			emp.setId(save.getId());
			emp.setName(save.getName());
			emp.setAddr(save.getAddr());
			emp.setPassword(save.getPwd());
			emp.setRole(save.getRole());
			emp.setEmail(save.getEmailid());
			Dept dept = new Dept();
			dept = (Dept) session.get(Dept.class, save.getDeptid());
			emp.setDept(dept);

			// emp.setCreatedDTTM(Timestamp.valueOf("2010-01-25 20-18-40"));
			session.save(emp);
			addtransaction.commit();
			session.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean updateEmpList(EmpList updateEmp) {
		try {
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.getSession();
			Transaction addtransaction = session.beginTransaction();
			User emp = (User) session.get(User.class, updateEmp.getId());
			emp.setName(updateEmp.getName());
			emp.setAddr(updateEmp.getAddr());
			emp.setPassword(updateEmp.getPwd());
			emp.setRole(updateEmp.getRole());
			emp.setEmail(updateEmp.getEmailid());
			Dept dept = new Dept();
			dept = (Dept) session.get(Dept.class, updateEmp.getDeptid());
			emp.setDept(dept);
			// emp.setCreatedDTTM(Timestamp.valueOf("2010-01-25 20-18-40"));
			session.update(emp);
			addtransaction.commit();
			session.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean deleteEmp(String empid) {
		if (deleteusertask(empid) == 0)
			return false;
		try {
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.getSession();
			Transaction addtransaction = session.beginTransaction();
			User emp = (User) session.get(User.class, empid);
			session.delete(emp);
			addtransaction.commit();
			session.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public int deleteusertask(String empid) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSession();
		try {
			Query taskValidationQuery = session
					.createQuery("delete from UserTask where user.id=:userid");
			taskValidationQuery.setParameter("userid", empid);
			return taskValidationQuery.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
}
