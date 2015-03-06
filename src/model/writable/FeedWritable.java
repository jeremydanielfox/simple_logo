package model.writable;

public class FeedWritable extends Writable {
	
	private String myValue;

	public FeedWritable(String value){
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
