package Exceptions;

public class CommandNotFoundException extends SlogoException {

	public CommandNotFoundException() {
		this.setMessage("Command was not found");
	}
}
