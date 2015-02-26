package model;

import java.util.Collection;

import javafx.collections.ObservableList;

public class ScreenData {
	private ObservableList<LineData> myLines;
	private ObservableList<TurtleData> myTurtles;

	public ScreenData(ObservableList<LineData> ld, ObservableList<TurtleData> td) {
		myLines = ld;
		myTurtles = td;
	}

	public void addTurtles(Collection<TurtleData> data) {
		myTurtles.addAll(data);
	}

	public void addLines(Collection<LineData> data) {
		myLines.addAll(data);
	}

	public Collection<LineData> getLines() {
		return myLines;
	}

	public Collection<TurtleData> getTurtles() {
		return myTurtles;
	}

}
