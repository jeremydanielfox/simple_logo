package Exceptions;

public class UnclosedListException extends SlogoException{
    
    public UnclosedListException() {
            this.setMessage("List was not closed");
    }
}
