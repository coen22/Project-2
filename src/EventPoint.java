
public class EventPoint extends Vertex implements Comparable<EventPoint> {
	private LineSegment lineSegment;
	private boolean isIntersection;
	private boolean leftPoint;

	public EventPoint(double x, double y, LineSegment lineSegment, boolean isIntersection, boolean leftPoint) {
		super(x, y);
		this.lineSegment = lineSegment;
		this.isIntersection = isIntersection;
		this.leftPoint = leftPoint;
	}
	
	public void setLineSegment(LineSegment lineSegment){
		this.lineSegment = lineSegment;
	}
	
	public LineSegment getLineSegment(){
		return this.lineSegment;
	}
	
	public boolean isIntersectionPoint(){
		return isIntersection;
	}
	
	public boolean isLeftPoint(){
		return leftPoint;
	}
	
	public String toString(){
		return super.toString();
	}

	//need to finish implementation
	@Override
	public int compareTo(EventPoint b) {
		if (this.getX() > b.getX()){
			return 1;
		}
		else if (this.getX() < b.getX()){
			return -1;
		}
		else{
			if (this.getY() < b.getY()){
				return -1;
			}
			else if (this.getY() > b.getY()){
				return 1;
			}
			else{
				if ((this.leftPoint == true) || (this.leftPoint == false && b.isIntersectionPoint() == true)){ //occurs if the current point to be tested is left of / before other point
					return -1;
				}
				else if
			}
		}
	}

}
