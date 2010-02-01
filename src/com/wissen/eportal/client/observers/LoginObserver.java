/**
 * 
 */
package com.wissen.eportal.client.observers;

/**
 * Classes who want to observe login result must implement this interface.
 * 
 * Interface notify about login success and failure.
 * 
 * @author wissen16
 * 
 */
public interface LoginObserver {

	/**
	 * Notify the observer login successful
	 */
	void notifyLoginSucceeded(String isvalid);

	/**
	 * Notify the observer login failed  with error message
	 * 
	 * @param errorMessage
	 * */
	void notifyLoginFailed(String errorMessage);
}
