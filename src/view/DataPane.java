package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Node;

public interface DataPane {

	ObservableMap<String, String> myMap = FXCollections.observableHashMap();

	public Node init();

	public default ObservableMap<String, String> getMap() {
		return myMap;
	}
	
}
