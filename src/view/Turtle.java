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

	protected Turtle(double xpos, double ypos, Image img) {
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

	protected void move(double xpos, double ypos) {
		this.setTranslateX(xpos);
		this.setTranslateY(ypos);
	}

	protected void toggleHidden() {
		hidden = !this.hidden;
	}

	protected void togglePenDown() {
		penDown = !this.penDown;
	}

	protected boolean isHidden() {
		return this.hidden;
	}

	protected boolean isPenDown() {
		return this.penDown;
	}

	protected int getID() {
		return myID;
	}

}
