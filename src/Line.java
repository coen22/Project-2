import java.awt.Point;

public class Line {
	public Point a;
	public Point b;
	
	public Line(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	public Line(int xa, int ya, int xb, int yb) {
		a = new Point(xa, ya);
		b = new Point(xb, yb);
	}
}
