package Exceptions;

public class IncorrectNumberOfBracketsException extends SlogoException{
	
	public IncorrectNumberOfBracketsException() {
		this.setMessage("Number of open and closed brackets does not match");
	}
}
