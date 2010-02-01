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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author wissen16
 *
 */
@Entity
@Table(name="userrole")
public class UserRole {

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "role")
	private String role;
	
	@SuppressWarnings("unused")
	@OneToOne(mappedBy="userrole")
	private User user;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the Role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param id the id to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
