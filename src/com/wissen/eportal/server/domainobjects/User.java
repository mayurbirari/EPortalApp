/**
 * wissen16
 * 21-Jan-2010
 */
package com.wissen.eportal.server.domainobjects;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;



/**
 * @author wissen16
 *
 */
@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name = "user_id")//login name
	private String id;
	
	@Column(name = "user_name")
	private String name;
	
	@Column(name = "user_pwd")
	private String password;
	
	@Column(name = "user_addr")
	private String addr;
	
	
	@Column(name="user_role")
	private String userrole;
	
	
	@Column(name="email_id")
	private String email;
	
	@Version
	@Column(name="created_dttm")
	private Timestamp createdDTTM;
	
	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Dept dept;
	
	@OneToMany(mappedBy = "task")
	List<UserTask> taskList;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * @return the taskList
	 */
	public List<UserTask> getTaskList() {
		return taskList;
	}

	/**
	 * @param taskList the taskList to set
	 */
	public void settaskList(List<UserTask> taskList) {
		this.taskList = taskList;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the createdDTTM
	 */
	public Timestamp getCreatedDTTM() {
		return createdDTTM;
	}

	/**
	 * @param createdDTTM the createdDTTM to set
	 */
	public void setCreatedDTTM(Timestamp createdDTTM) {
		this.createdDTTM = createdDTTM;
	}
	
	/**
	 * @return the deptid
	 */
	public Dept getDept() {
		return dept;
	}

	/**
	 * @param dept 
	 *            the dept to set
	 */
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return userrole;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.userrole = role;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}
