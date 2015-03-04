package model.line;

import java.util.Collections;
import java.util.List;

import model.Clearable;
import model.Drawable;
import view.Clearer;
import view.Drawer;
import view.Renderable;

// would belong to a workspace
public class LineListCollection implements Line, Clearable {
	private static int ourId = 0;

	private int myId;
	private List<LineList> myLineLists;

	public LineListCollection(int id, List<LineList> linelists) {
		myId = id;
		myLineLists = linelists;
	}

	@Override
	public void beDrawn(Drawer drawer) {
		myLineLists.forEach(linelist -> linelist.beDrawn(drawer));
	}

	public List<LineList> getLineLists() {
		return Collections.unmodifiableList(myLineLists);
	}

	public int getId() {
		return myId;
	}

	public String toString() {
		return myLineLists.toString();
	}

	@Override
	public void beCleared(Clearer clearer) {
		myLineLists.forEach(linelist -> linelist.beCleared(clearer));

	}
}
