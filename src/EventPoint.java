
public class EventPoint extends Vertex {
	private LineSegment lineSegment;
	private boolean intersect;
	private boolean leftPoint;

	public EventPoint(double x, double y, LineSegment lineSegment, boolean intersect, boolean leftPoint) {
		super(x, y);
		this.lineSegment = lineSegment;
		this.intersect = intersect;
		this.leftPoint = leftPoint;
	}
	
	public void setLineSegment(LineSegment lineSegment){
		this.lineSegment = lineSegment;
	}
	
	public LineSegment getLineSegment(){
		return this.lineSegment;
	}
	
	public boolean isIntersect(){
		return intersect;
	}
	
	public boolean isLeftPoint(){
		return leftPoint;
	}
	
	public String toString(){
		return super.toString();
	}
	
	public int compareTo(EventPoint b){
		if (this.getX() > b.getX()){
			return 1;
		}
		else if (this.getX() < b.getX()){
			return -1;
		}
		else{
			return 0;
		}
	}

}
