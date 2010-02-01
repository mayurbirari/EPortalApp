/**
 * 
 */
package com.wissen.eportal.client.observers;

import java.util.List;

import com.wissen.eportal.client.data.DeptList;

/**
 * Classes who want to observe login result must implement this interface.
 * 
 * Interface notify about login success and failure.
 * 
 * @author Yogesh Bhave
 * 
 */
public interface DeptObserver {

	/**
	 * Notify the observer login successful
	 */
	void notifyDeptListSucceeded(List<DeptList> deptList);
	/**
	 * Notify the observer login failed  with error message
	 * 
	 * @param errorMessage
	 * */
	void notifyDeptListFailed(String errorMessage);
}
