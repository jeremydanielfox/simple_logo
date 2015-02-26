package Exceptions;

public abstract class SlogoException extends RuntimeException {
	protected String myMessage;

	@Override
	public String toString() {
		return myMessage;
	}
}
