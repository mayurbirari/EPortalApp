/**
 * wissen16
 * 23-Jan-2010
 */
package com.wissen.eportal.client.data;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author wissen16
 *
 */
public class EmpList  implements IsSerializable{
	private String id;
	private String name;
	private String pwd;
	private String addr;
	private String role;
	private String emailid;
	private long deptid;
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
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	 * @return the emailid
	 */
	public String getEmailid() {
		return emailid;
	}
	/**
	 * @param emailid the emailid to set
	 */
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	/**
	 * @return the deptid
	 */
	public long getDeptid() {
		return deptid;
	}
	/**
	 * @param deptid the deptid to set
	 */
	public void setDeptid(long deptid) {
		this.deptid = deptid;
	}	
}
