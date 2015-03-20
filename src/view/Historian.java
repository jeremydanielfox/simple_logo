package view;

import java.util.Map;

import model.writable.Writable;

/**
 * Allows for objects to record histories that are given to them in the form of
 * Map<String,Writable>. This class allows front end components to keep track of
 * back end data specific to their purpose.
 * 
 * @author Jeremy
 *
 */
public interface Historian {
	/**
	 * Records a given history
	 * 
	 * @param history
	 */
	public void record(Map<String, Writable> history);
}
