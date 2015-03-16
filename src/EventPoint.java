
public class EventPoint extends Vertex {
	private LineSegment lineSegment;

	public EventPoint(double x, double y, LineSegment lineSegment) {
		super(x, y);
		this.lineSegment = lineSegment;
	}
	
	public void setLineSegment(LineSegment lineSegment){
		this.lineSegment = lineSegment;
	}
	
	public LineSegment getLineSegment(){
		return this.lineSegment;
	}

}
