package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Receiver;
import model.database.Database;

public class Feed {
	private static Receiver myReceiver;
	private static Feed instance;
	private static HBox myObjects;
	private static Button add;
	private static Button enter;
	private static TextArea prompter;
	private static final String PROMPT_TEXT = "Input command here";
	private static final String ADD_TEXT = "Add";
	private static final String ENTER_TEXT = "Return";
	private static final double PROMPT_WIDTH = Double.MAX_VALUE;
	private Stage myStage;

	private Feed(Receiver receiver) {
		myReceiver = receiver;
		myObjects = new HBox();
		setupPrompter();
		setupAdd();
		setupEnter();
		myObjects.getChildren().addAll(add, prompter, enter);
	}

	/**
	 * 
	 */
	public void setupEnter() {
		enter = new Button(ENTER_TEXT);
		enter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (prompter.getText() != null)
					CommandSender.send(prompter.getText());
				// myReceiver.giveText(prompter.getText());
				System.out.println(prompter.getText().toString());
				prompter.clear();
			}
		});
	}

	/**
	 * 
	 */
	public void setupAdd() {
		add = new Button(ADD_TEXT);
		add.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Database myData = Database.getInstance();
				myStage= new Stage();
				myStage.setHeight(200);
				myStage.setWidth(300);
				VBox myRoot = new VBox();
				Label myVarName = new Label("Commands");
				ObservableMap<String, String> myMap = myData.getVarsHistory();
				ObservableList<String> myList = FXCollections.observableArrayList(myMap.keySet());
				ListView<String> myListView = new ListView<String>(myList);
				myListView.setPrefHeight(0);
				VBox.setVgrow(myListView, Priority.ALWAYS);
				myRoot.getChildren().addAll(myVarName, myListView);
				Scene myScene = new Scene(myRoot);
				myStage.setScene(myScene);
				myStage.show();
			}
		});
	}

	/**
	 * 
	 */
	public void setupPrompter() {
		prompter = new TextArea();
		prompter.setPromptText(PROMPT_TEXT);
//		prompter.setPrefWidth(0); 
		HBox.setHgrow(prompter, Priority.ALWAYS);
	}

	protected static Feed getInstance(Receiver receiver) {
		if (instance == null) {
			instance = new Feed(receiver);
		}
		return instance;
	}

	//
	// private static void setupFeed() {
	// instance.getChildren().addAll(myObjects);
	// }

	protected HBox getFeed() {
		return myObjects;
	}
	
	public static void addText(String text) {
		prompter.insertText(prompter.getCaretPosition(), text);
	}

}
