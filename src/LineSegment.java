import java.awt.Point;

public class LineSegment {
	public Point a;
	public Point b;
	
	public LineSegment(Point a, Point b) {
		if (a.x < b.x) {
			this.a = a;
			this.b = b;
		} else {
			this.a = b;
			this.b = a;
		}
	}
	
	public LineSegment(int xa, int ya, int xb, int yb) {
		if (xa < xb) {
			a = new Point(xa, ya);
			b = new Point(xb, yb);
		} else {
			b = new Point(xa, ya);
			a = new Point(xb, yb);
		}
	}
	
	public int left() {
		return Math.min(a.x, b.x);
	}
	
	public int right() {
		return Math.max(a.x, b.x);
	}
	
	public int top() {
		return Math.min(a.y, b.y);
	}
	
	public int bottom() {
		return Math.max(a.y, b.y);
	}
}
