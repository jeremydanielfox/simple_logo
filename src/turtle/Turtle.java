package turtle;

import javafx.geometry.Point2D;
import view.Drawer;

public interface Turtle {

    public void beDrawn(Drawer drawer);
    
    public void translate (double distance);
    
    public double rotate (double angle);
    
    public double towards (Point2D target);
    
    public double goHome ();
    
    public double clearScreen ();
    
    
}
