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
public interface EmailObserver {

	/**
	 * Notify the observer login successful
	 */
	void notifyEmailSucceeded(boolean flag);
	/**
	 * Notify the observer login failed  with error message
	 * 
	 * @param errorMessage
	 * */
	void notifyEmailFailed(String err);
}
