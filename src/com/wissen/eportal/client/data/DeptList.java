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
public class DeptList  implements IsSerializable{
	private long id;
	private String name;
	private String descr;
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
	 * @return the descr
	 */
	public String getDescr() {
		return descr;
	}
	/**
	 * @param descr the descr to set
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	
}
