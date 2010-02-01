/**
 * 
 */
package com.wissen.eportal.client.observers;

import java.util.List;
import com.wissen.eportal.client.data.EmpList;

/**
 * Classes who want to observe login result must implement this interface.
 * 
 * Interface notify about login success and failure.
 * 
 * @author wissen16
 * 
 */
public interface EmpObserver {

	/**
	 * Notify the observer login successful
	 */
	void notifyEmpListSucceeded(List<EmpList> empList);
	/**
	 * Notify the observer login failed  with error message
	 * 
	 * @param errorMessage
	 * */
	void notifyEmpListFailed(String errorMessage);
}
