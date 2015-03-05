package model.line;

import javafx.beans.value.ObservableValue;
import model.Drawable;
import view.Drawer;

public interface Line extends Drawable {

	public void beDrawn(Drawer drawer);

}
