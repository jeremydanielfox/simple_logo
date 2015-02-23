package model;

import java.util.Collection;

import javafx.collections.FXCollections;

public class ScreenData {
	private static ScreenData instance;
	private static Collection<LineData> myLines;
	private static Collection<TurtleData> myTurtles;

	private ScreenData() {
		myLines = FXCollections.observableArrayList();
		myTurtles = FXCollections.observableArrayList();
	}

	protected static ScreenData getInstance() {
		if (instance == null)
			instance = new ScreenData();
		return instance;
	}

}
