package Exceptions;

/**
 * This is the basic model for any exception specific to Slogo
 * 
 * @author Jeremy
 *
 */
public abstract class SlogoException extends RuntimeException {
	protected String myMessage;

	/**
	 * This is the message that will be printed when the error is displayed to
	 * the user
	 */
	@Override
	public String toString() {
		return myMessage;
	}

	/**
	 * Sets the user message to the input message
	 * 
	 * @param message
	 */
	protected void setMessage(String message) {
		this.myMessage = message;
	}
}
