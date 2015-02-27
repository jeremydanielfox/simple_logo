package view;

import java.util.ResourceBundle;

import Exceptions.SlogoException;
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
	private Receiver myReceiver;
	private HBox myObjects;
	private Button add;
	private Button enter;
	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/feed");
	private static TextArea prompter;
	private static final int ADD_WIDTH = Integer.parseInt(myValues
			.getString("Add_Width"));
	private static final int ADD_HEIGHT = Integer.parseInt(myValues
			.getString("Add_Height"));
	private static final String PROMPT_TEXT = myValues.getString("Prompt_Text");
	private static final String ADD_TEXT = myValues.getString("Add_Text");
	private static final String ENTER_TEXT = myValues.getString("Enter_Text");

	protected Feed(Receiver receiver) {
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
					 //CommandSender.send(prompter.getText());
					try {
						myReceiver.giveText(prompter.getText());
					} catch (SlogoException ex) {
						ErrorDisplay.getInstance().displayError(ex);
					}
				//myReceiver.giveText(prompter.getText());
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
		add.setOnAction(e -> {
			Database myData = Database.getInstance();
			Stage myStage = new Stage();
			myStage.setHeight(ADD_WIDTH);
			myStage.setWidth(ADD_HEIGHT);
			
			VBox myRoot = new VBox();
			HBox myTitleBox = new HBox();
			Label myTitle = new Label("Commands");
			Button myAddButton = new Button("Add");
			ObservableMap<String, String> myMap = myData.getCmdsHistory();
			ObservableList<String> myList = FXCollections
					.observableArrayList(myMap.keySet());
			ListView<String> myListView = new ListView<String>(myList);
			myListView.setPrefHeight(0);
			VBox.setVgrow(myListView, Priority.ALWAYS);
			myAddButton.setOnMouseClicked(e2 -> {
				Feed.addText(myListView.getSelectionModel().getSelectedItem());
				myStage.close();
			});
			myTitleBox.getChildren().addAll(myTitle, myAddButton);
			myRoot.getChildren().addAll(myTitleBox, myListView);
			Scene myScene = new Scene(myRoot);
			myStage.setScene(myScene);
			myStage.show();
		});
	}

	/**
	 * 
	 */
	public void setupPrompter() {
		prompter = new TextArea();
		prompter.setPromptText(PROMPT_TEXT);
		HBox.setHgrow(prompter, Priority.ALWAYS);
	}

	protected HBox getFeed() {
		return myObjects;
	}

	public static void addText(String text) {
		prompter.insertText(prompter.getCaretPosition(), text);
	}

}
