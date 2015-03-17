
public class Segment implements Comparable<Segment> {
	private Vertex a;
	private Vertex b;
	
	public Segment(Vertex a, Vertex b) {
		this.a = a;
		this.b = b;
	}
	
	public Segment(double xa, double ya, double xb, double yb) {
		b = new Vertex(xa, ya);
		a = new Vertex(xb, yb);
	}
	
	public Vertex getA() {
		return a;
	}
	
	public Vertex getB() {
		return b;
	}
	
	public void setA(Vertex p) {
		a = p;
	}
	
	public void setB(Vertex p) {
		b = p;
	}
	
	public double left() {
		return Math.min(a.getX(), b.getX());
	}
	
	public double right() {
		return Math.max(a.getX(), b.getX());
	}
	
	public double top() {
		return Math.min(a.getY(), b.getY());
	}
	
	public double bottom() {
		return Math.max(a.getY(), b.getY());
	}
	
	public int compareTo(Segment o) {
		return (int) left();
	}
}
