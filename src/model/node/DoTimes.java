package model.node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DoTimes extends Repeat{

	@Override
	public Queue<String> getTokenTracker() {
		Queue<String> queue = new LinkedList<String>(Arrays.asList("ListStart", "Variable", "Other", "ListEnd"));
		return queue;
	}
}
