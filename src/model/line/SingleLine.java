package model.line;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import view.Drawer;

/**
 * Represents one line drawn by a SingleTurtle
 * @author Nathan Prabhu
 *
 */
public class SingleLine implements Line,ObservableValue<SingleLine> {
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

	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(ChangeListener<? super SingleLine> listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(ChangeListener<? super SingleLine> listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SingleLine getValue() {
		// TODO Auto-generated method stub
		return this;
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
