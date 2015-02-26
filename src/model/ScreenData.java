package model;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ScreenData {
	private ObservableList<LineData> myLines;
	private ObservableList<TurtleData> myTurtles;

	public ScreenData(Collection<LineData> ld, Collection<TurtleData> td) {
		myLines = FXCollections.observableArrayList(ld);
		myLines.addListener(new ChangeListener<LineData>() {public void changed(ObservableValue));
		myTurtles = FXCollections.observableArrayList(td);
	}

	public void addTurtles(Collection<TurtleData> data) {
		myTurtles.addAll(data);
	}

	public void addLines(Collection<LineData> data) {
		myLines.addAll(data);
	}

	// public void addTurtles(List<Turtle> turtles){
	// myTurtles.addAll(turtles);
	// }

	public Collection<LineData> getLines() {
		return myLines;
	}

	public Collection<TurtleData> getTurtles() {
		return myTurtles;
	}

}
