package model.line;

import java.util.Collections;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import model.Clearable;
import view.Clearer;
import view.Drawer;

// would belong to a turtle
public class LineList implements Line, Clearable, ChangeListener<SingleLine>,
		ObservableValue<LineList> {

	private ObservableList<SingleLine> myList;

	public LineList() {
		myList = FXCollections.observableArrayList();
	}

	@Override
	public void beDrawn(Drawer drawer) {
		myList.forEach(line -> line.beDrawn(drawer));
	}

	@Override
	public void beCleared(Clearer clearer) {
		myList.clear();
		clearer.clearLines();

	}

	public void add(SingleLine line) {
		line.addListener(this);
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

	@Override
	public void changed(ObservableValue<? extends SingleLine> observable,
			SingleLine oldValue, SingleLine newValue) {
		// TODO Auto-generated method stub
		if (myList.contains(oldValue))
			myList.remove(oldValue);
		myList.add(newValue);
	}

	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListener(ChangeListener<? super LineList> listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(ChangeListener<? super LineList> listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public LineList getValue() {
		// TODO Auto-generated method stub
		return this;
	}

}
