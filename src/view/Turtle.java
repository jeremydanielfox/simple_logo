package view;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle extends ImageView {
	private static final ResourceBundle myValues = ResourceBundle.getBundle(
			"view.resources.Values_turtle", new Locale("en", "US"));
	private static final int WIDTH = Integer.parseInt(myValues
			.getString("Width"));
	private static int ID = 0;
	private int myID;
	private boolean penDown;
	private boolean hidden;

	public Turtle(double xpos, double ypos, Image img) {
		this.setTranslateX(xpos);
		this.setTranslateY(ypos);
		this.setImage(img);
		this.setFitWidth(WIDTH);
		this.setPreserveRatio(true);
		this.setSmooth(true);
		this.setCache(true);
		penDown = true;
		hidden = false;
		myID = ID;
		ID++;
	}

	public void move(double xpos, double ypos) {
		this.setTranslateX(xpos);
		this.setTranslateY(ypos);
	}

	public void toggleHidden() {
		hidden = !this.hidden;
	}

	public void togglePenDown() {
		penDown = !this.penDown;
	}

	public boolean isHidden() {
		return this.hidden;
	}

	public boolean isPenDown() {
		return this.penDown;
	}

}
