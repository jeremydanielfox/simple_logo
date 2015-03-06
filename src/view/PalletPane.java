package view;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import model.writable.Writable;

public class PalletPane implements DataPane, Historian {
	
	VBox myRoot;
	private ObservableList<String> myList;

	@Override
	public Node init() {
		myRoot = new VBox();
		myList = FXCollections.observableArrayList(myMap.keySet());
		return myRoot;
	}

	@Override
	public void record(Map<String, Writable> history) {
		history.forEach((k, v) -> myMap.put(k, v.getValue()));

	}

}
