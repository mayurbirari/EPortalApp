/**
 * wissen16
 * 21-Jan-2010
 */
package com.wissen.eportal.server.domainobjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author wissen16
 *
 */

@Entity
@Table(name="user_task")
public class UserTask {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;
	
	@Column(name = "task_status")
	private String status;
	/**
	 * @return the userid
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param userid  
	 *            the user to set
	 */
	public void setUser(User userid) {
		this.user = userid;
	}
	
	/**
	 * @return the taskid
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param taskid  
	 *            the task to set
	 */
	public void setTask(Task taskid) {
		this.task = taskid;
	}

	/**
	 * @param status  
	 *            the task status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the task status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @param id  
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the task status
	 */
	public long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTask other = (UserTask) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
