package Exceptions;

public class IncorrectSyntaxException extends SlogoException {
    
    public IncorrectSyntaxException() {
        this.setMessage("Syntax is incorrect");
        // or missing arguments
}
}
