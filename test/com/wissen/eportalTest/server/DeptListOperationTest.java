/**
 * wissen16
 * 23-Jan-2010
 */
package com.wissen.eportalTest.server;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wissen.eportal.client.data.DeptList;
import com.wissen.eportal.server.domainobjects.Dept;
import com.wissen.eportal.server.hibernate.HibernateUtil;

/**
 * @author wissen16
 *
 */
public class DeptListOperationTest {

	@SuppressWarnings("unchecked")
	public List<Dept> getDeptList() {
		List<Dept> deptList;
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSession();
		deptList = session.createQuery("from Dept").list();
		return deptList;
	}
	
	public boolean saveNewDeptList(DeptList save) {
			
		save.setId(23);
		save.setName("Intern");
		save.setDescr("trainee programmer");
	
		
		
		try{
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSession();
		Transaction addtransaction=session.beginTransaction();
		Dept dept=new Dept();
		dept.setId(save.getId());
		dept.setName(save.getName());
		dept.setDescr(save.getDescr());
		session.save(dept);
		addtransaction.commit();
		session.close();
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

	public boolean updateDeptList(DeptList updateDept) {
		
		updateDept.setId(23);
		updateDept.setName("Intern");
		updateDept.setDescr("trainee programmer");
	
		
		
		try{
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.getSession();
			Transaction addtransaction=session.beginTransaction();
			Dept dept=(Dept) session.get(Dept.class,updateDept.getId());
			dept.setName(updateDept.getName());
			dept.setDescr(updateDept.getDescr());
			//dept.setCreatedDTTM(Timestamp.valueOf("2010-01-25 20-18-40"));
			session.update(dept);
			addtransaction.commit();
			session.close();
			}
			catch(Exception e){
				return false;
			}
			return true;
	}

	public boolean deleteDept(long deptid) {
		
		deptid=10;
		try{
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.getSession();
			Transaction addtransaction=session.beginTransaction();
			Dept dept=(Dept) session.get(Dept.class,deptid);
			session.delete(dept);
			addtransaction.commit();
			session.close();
			}
			catch(Exception e){
				return false;
			}
			return true;
	}

}
