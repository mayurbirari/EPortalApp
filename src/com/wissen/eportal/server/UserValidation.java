/**
 * 
 */
package com.wissen.eportal.server;

import org.hibernate.Query;
import org.hibernate.Session;

import com.wissen.eportal.server.domainobjects.User;
import com.wissen.eportal.server.hibernate.HibernateUtil;

/**
 * @author wissen16
 * 
 */
public class UserValidation {

	/**
	 * Method validates user against database
	 * 
	 * @return true if user is valid else false
	 * 
	 * */
	public User validateUser(String userName, String password) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSession();
		Query userValidationQuery = session
				.createQuery("from User user where user.id = :userName and user.password = :password");
		userValidationQuery.setParameter("userName", userName);
		userValidationQuery.setParameter("password", password);
		User user = (User) userValidationQuery.uniqueResult();
		return user;
	}

}
