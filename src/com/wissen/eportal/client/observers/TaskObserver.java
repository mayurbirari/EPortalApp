/**
 * 
 */
package com.wissen.eportal.client.observers;

import java.util.List;
import com.wissen.eportal.client.data.TaskList;

/**
 * Classes who want to observe login result must implement this interface.
 * 
 * Interface notify about login success and failure.
 * 
 * @author wissen16
 * 
 */
public interface TaskObserver {

	/**
	 * Notify the observer login successful
	 */
	void notifyTaskListSucceeded(List<TaskList> taskList);
	/**
	 * Notify the observer login failed  with error message
	 * 
	 * @param errorMessage
	 * */
	void notifyTaskListFailed(String errorMessage);
}
