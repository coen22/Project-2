import java.awt.geom.Point2D;

@SuppressWarnings("serial")
public class Vertex extends Point2D.Double implements Comparable<Vertex> {
	private Segment segment1 = null;
	private Segment segment2 = null;
	
	public Vertex(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Segment getSegment1() {
		return segment1;
	}
	
	public Segment getSegment2() {
		return segment2;
	}

	public int compareTo(Vertex v) {
		return (int) (v.x - x);
	}
}
