package model;

import view.Drawer;

/**
 * Something to be drawn by a Drawer.
 * 
 * @author Jeremy
 *
 */

public interface Drawable {
	/**
	 * Takes in a Drawer that will later be used to draw itself
	 * 
	 * @param drawer
	 */
	public void beDrawn(Drawer drawer);

}
