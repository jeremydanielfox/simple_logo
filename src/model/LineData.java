package model;

import javafx.geometry.Point2D;

public class LineData {
	private Point2D start;
	private Point2D finish;

	protected LineData(Point2D s, Point2D f) {
		start = s;
		finish = f;
	}

	protected Point2D getStart() {
		return start;

	}

	protected Point2D getFinish() {
		return finish;
	}
}
