package model.line;

import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import view.Drawer;

// would belong to a turtle
public class LineList implements Line {

	private ObservableList<SingleLine> myList;

	public LineList() {
		myList = FXCollections.observableArrayList();
	}

	@Override
	public void beDrawn(Drawer drawer) {
		myList.forEach(line -> line.beDrawn(drawer));
	}


	public void add(SingleLine line) {
		myList.add(line);
	}

	public void clear() {
		myList.clear();
	}

	// not sure if useful at all...
	public Point2D getStart() {
		return myList.get(0).getStart();
	}

	public Point2D getFinish() {
		return myList.get(myList.size() - 1).getFinish();
	}

	// unmodifable list... make immutable?
	// observable will be ObservableList<LineList>?
	public List<SingleLine> getList() {
		return Collections.unmodifiableList(myList);
	}

	public String toString() {
		return myList.toString();
	}

	public void addListener(ListChangeListener<? super Line> listener) {
		myList.addListener(listener);
	}
}
