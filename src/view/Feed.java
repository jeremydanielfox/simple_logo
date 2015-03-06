package view;

import java.util.ResourceBundle;

import Exceptions.SlogoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
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
import model.database.OldDatabase;
import model.line.LineListCollection;

public class Feed {

	private Receiver myReceiver;
	private HBox myObjects;
	private Button add;
	private Button enter;
	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/feed");
	private TextArea prompter;
	private int myID;
	private Stage myStage;

	private static final int ADD_WIDTH = Integer.parseInt(myValues
			.getString("Add_Width"));
	private static final int ADD_HEIGHT = Integer.parseInt(myValues
			.getString("Add_Height"));
	private static final String PROMPT_TEXT = myValues.getString("Prompt_Text");
	private static final String ADD_TEXT = myValues.getString("Add_Text");
	private static final String ENTER_TEXT = myValues.getString("Enter_Text");

	protected Feed(Receiver receiver, int id) {
		myID = id;
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
				String text = prompter.getText();
				if (text != null && !text.equals(""))
					try {
						myReceiver.giveText(text, myID);
					} catch (SlogoException ex) {
						ErrorDisplay.getInstance().displayError(ex);
					}
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
			// Database myData = Database.getInstance();
			myStage = new Stage();
			myStage.setHeight(ADD_WIDTH);
			myStage.setWidth(ADD_HEIGHT);
			CommandPane cp = new CommandPane(this);
			Scene myScene = new Scene((Parent) cp.init());
			myStage.setScene(myScene);
			myStage.show();
		});
	}

	public Stage getStage() {
		return this.myStage;
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

	public void addText(String text) {
		prompter.insertText(prompter.getCaretPosition(), text);
	}

}
