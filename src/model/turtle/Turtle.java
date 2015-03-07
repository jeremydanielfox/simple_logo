package model.turtle;

import model.Drawable;
import javafx.geometry.Point2D;
import view.Drawer;

/**
 * Interface for all turtles. They are drawable
 * @author Nate
 *
 */
public interface Turtle extends Drawable{

    public void beDrawn(Drawer drawer);
    
    public void translate (double distance);
    
    public double rotate (double angle);
    
    public double towards (Point2D target);
    
    public double goHome ();
    
    public double clearScreen ();

    public double show ();
    
    public double hide ();

    public double setPenUp ();

    public double setPenDown ();
    
    public double setHeading (double arg);

    public Point2D getPosition ();
    
    public double getHeading ();
}
