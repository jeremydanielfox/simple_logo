package model;

import javafx.geometry.Point2D;

public class LineData implements Drawable {
	private Point2D start;
	private Point2D finish;

	protected LineData(Point2D s, Point2D f) {
		start = s;
		finish = f;
	}

	public Point2D getStart() {
		return start;

	}

	public Point2D getFinish() {
		return finish;
	}
	
	public String toString(){
		return "Start = " + start.toString() + ". Finish =  " + finish.toString();
	}
}
