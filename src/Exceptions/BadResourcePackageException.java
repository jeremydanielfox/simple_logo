package Exceptions;

public class BadResourcePackageException extends SlogoException {

	public BadResourcePackageException() {
		this.setMessage("Resource Package is null.");
	}
}
