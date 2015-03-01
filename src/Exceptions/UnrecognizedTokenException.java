package Exceptions;

public class UnrecognizedTokenException extends SlogoException{
    
    public UnrecognizedTokenException(String token) {
        this.setMessage(String.format("%s token not recognized", token));
}

}
