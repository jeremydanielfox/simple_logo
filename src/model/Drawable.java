package model;

import view.TurtleView;

/**
 * 
 * @author Jeremy
 *
 */
public interface Drawable {

	public abstract void Draw(TurtleView tv);
	public abstract void Clear(TurtleView tv);
}
