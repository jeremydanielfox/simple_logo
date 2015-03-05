package Exceptions;

public class UnexpectedEndOfInstructionsException extends SlogoException {
    
    public UnexpectedEndOfInstructionsException () {
        this.setMessage("Unexpected End Of Instructions. Please add remaining instructions.");
    }
}
