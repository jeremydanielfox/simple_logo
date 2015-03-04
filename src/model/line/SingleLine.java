package model.line;

import view.Drawer;
import javafx.geometry.Point2D;


public class SingleLine implements Line {
    private Point2D start;
    private Point2D finish;

    public SingleLine (Point2D s, Point2D f) {
        start = s;
        finish = f;
    }

    @Override
    public void beDrawn (Drawer drawer) {
       drawer.drawLine(start, finish);
    }
    
    public Point2D getStart () {
        return start;
    }

    public Point2D getFinish () {
        return finish;
    }

    public String toString () {
        return "Start = " + start.toString() + ". Finish =  "
               + finish.toString();
    }
    
    
    //
    // @Override
    // public void Draw(TurtleView tv) {
    // tv.getLineGraphicsContext().strokeLine(this.getStart().getX(), this.getStart()
    // .getY(), this.getFinish().getX(), this.getFinish().getY());
    // }
    //
    // @Override
    // public void Clear(TurtleView tv) {
    // tv.getLineGraphicsContext().clearRect(0, 0, tv.getTurtleCanvas().getWidth(),
    // tv.getTurtleCanvas().getHeight());
    //
    // }

}
