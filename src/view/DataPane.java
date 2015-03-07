package view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public abstract class DataPane {

	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/panes");
	private VBox myRoot;
	private ObservableList<String> myList;
	private DataPane myInstance;
	private ObservableMap<String, String> myMap = FXCollections
			.observableHashMap();
	private ListView<String> myListView;

	public Node init(String type) {

		myRoot = new VBox();
		myList = FXCollections.observableArrayList(myMap.keySet());
		HBox titleBox = new HBox();
		Label title = new Label(type);
		title.setFont(new Font(
				Integer.parseInt(myValues.getString("FONT_SIZE"))));
		titleBox.getChildren().add(title);
		String[] buttons = myValues.getString(type).split(", ");
		for (String button : buttons) {
			Button toAdd = makeButton(button);
			titleBox.getChildren().add(toAdd);
		}
		myListView = new ListView<String>(myList);
		myListView.setPrefHeight(0);
		VBox.setVgrow(myListView, Priority.ALWAYS);
		myRoot.getChildren().addAll(titleBox, myListView);
		return myRoot;

	}

	private Button makeButton(String name) {
		Button button = new Button(name);
		try {
			Method method = getClass().getDeclaredMethod("handle" + name,
					String.class);
			button.setOnMouseClicked(e -> buttonMethod(method));
		} catch (SecurityException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		return button;
	}

	private void buttonMethod(Method method) {
		if (myListView.getSelectionModel().getSelectedItem() != null) {
			try {
				method.invoke(myInstance, myListView.getSelectionModel()
						.getSelectedItem());
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setInstance(DataPane dp) {
		this.myInstance = dp;
	}

	public ObservableMap<String, String> getMap() {
		return myMap;
	}
	
	public ObservableList<String> getList() {
		return myList;
	}
	
	public void setList(Collection<String> col) {
		for (String s : col) {
			if (!myList.contains(s)) {
				myList.add(s);
			}
		}
		for (String s : myList) {
			if (!col.contains(s)) {
				myList.remove(s);
			}
		}
	}
	
	public ListView<String> getListView() {
		return myListView;
	}

}
