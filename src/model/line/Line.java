package model.line;

import model.Drawable;
import view.Drawer;

/**
 * Represents all lines in the simulation that could be drawn by a Drawer.
 * @author Nathan Prabhu
 *
 */
public interface Line extends Drawable {

	public void beDrawn(Drawer drawer);

}
