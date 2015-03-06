package model.database;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import model.writable.Writable;
import view.Historian;

public class History implements Recordable {

	private String myType;
	private ObservableMap<String, Writable> myMap;

	public History(String type) {
		myType = type;
		myMap = FXCollections.observableHashMap();
	}

	@Override
	public void beRecorded(Historian recorder) {
		// recorder.record(myMap);
	}

	@Override
	public void addListener(MapChangeListener change) {
		// TODO Auto-generated method stub

	}

	public void put(String key, Writable value) {
		myMap.put(key, value);
	}
	
	public Writable get(String key){
		return myMap.get(key);
	}

}
