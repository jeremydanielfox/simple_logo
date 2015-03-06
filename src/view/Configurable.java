package view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface Configurable {
	public void setPenColor(Color color);

	public void setPenWidth(double width);

	public void setBackgroundColor(Color color);

	public Node getView();

	public void setTurtleImage(Image img);
}
