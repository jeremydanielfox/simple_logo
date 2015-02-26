package Exceptions;

public class VariableNotFoundException extends SlogoException {

	public VariableNotFoundException() {
		this.setMessage("Variable not found. Please define the variable and try again.");
	}
}
