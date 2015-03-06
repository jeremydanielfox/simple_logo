package model.writable;

public class ConsoleWritable extends Writable {

    private String myValue;

    public ConsoleWritable(String value){
            myValue = value;
    }

    @Override
    public String getName() {
            return "";
    }

    @Override
    public String getValue() {
            return myValue;
    }

}
