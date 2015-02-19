package turtle_view;

import javafx.scene.image.ImageView;

public class Turtle extends ImageView {
	
	boolean penDown;
	boolean hidden;
	
	public Turtle (double xpos, double ypos) {
		this.setTranslateX(xpos);
		this.setTranslateY(ypos);
		penDown = true;
		hidden = false;
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
