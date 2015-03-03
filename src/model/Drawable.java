package model;

import view.TurtleView;

/**
 * 
 * @author Jeremy
 *
 */
public interface Drawable {

	public abstract void draw(TurtleView tv);
	public abstract void clear(TurtleView tv);
}
