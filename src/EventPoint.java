/**
 * 
 * @author David
 * @version 1.0
 *
 */
public class EventPoint extends Vertex implements Comparable<EventPoint> {
	private LineSegment lineSegment1;
	private LineSegment lineSegment2;
	private boolean isIntersection;
	private boolean leftPoint;

	/**
	 * Constructor for an Event point
	 * 
	 * @param x x-location of event-point
	 * @param y y-location of event-point
	 * @param lineSegment if the event-point either a right or left endpoint of a segment, it is linked here
	 * @param isIntersection if the event-point is an intersection point it will not receive an 
	 * @param leftPoint if the event-point is a left-endpoint then this is true, being false indicates being a right-endpoint
	 */
	public EventPoint(double x, double y, LineSegment lineSegment1, LineSegment lineSegment2, boolean isIntersection, boolean leftPoint) {
		super(x, y);
		this.lineSegment1 = lineSegment1;
		this.lineSegment2 = lineSegment2;
		this.isIntersection = isIntersection;
		this.leftPoint = leftPoint;
	}

	/**
	 * Constructor for an Event point
	 * 
	 * @param vertex the vertex of a new Event-Point
	 * @param lineSegment if the event-point either a right or left endpoint of a segment, it is linked here
	 * @param isIntersection if the event-point is an intersection point it will not receive an 
	 * @param leftPoint if the event-point is a left-endpoint then this is true, being false indicates being a right-endpoint
	 */
	public EventPoint(Vertex vertex, LineSegment lineSegment1, LineSegment lineSegment2, boolean isIntersection, boolean leftPoint) {
		super(vertex);
		this.lineSegment1 = lineSegment1;
		this.lineSegment2 = lineSegment2;
		this.isIntersection = isIntersection;
		this.leftPoint = leftPoint;
	}

	/**
	 * 
	 * @param lineSegment the segment of which the event-point is an end-point of. 
	 */
	public void setLineSegment(LineSegment lineSegment){
		this.lineSegment1 = lineSegment;
	}

	/**
	 * 
	 * @return returns the attached line-segment, segment 1 if it is an intersection point
	 */
	public LineSegment getLineSegment1(){
		return this.lineSegment1;
	}

	/**
	 * 
	 * @return returns the attached line-segment, segment 2 it is an intersection point
	 */
	public LineSegment getLineSegment2(){
		return this.lineSegment2;
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

	public int compareTo(EventPoint b) {
		if (this.getX() > b.getX()){
			return 1;
		}
		else if (this.getX() < b.getX()){
			return -1;
		}
		else{ //x must be the same value
			if (this.getY() < b.getY()){
				return -1;
			}
			else if (this.getY() > b.getY()){
				return 1;
			}
			else{ //x and y must have the same value
				if (this.isIntersection == true && b.isIntersectionPoint() == false){
					return 1;
				}
				else if (b.isIntersectionPoint() == true && this.isIntersectionPoint() == false){
					return -1;
				}
				else if (this.leftPoint == true && b.isLeftPoint() == false){
					return -1;
				}
				else if (this.leftPoint == false && b.isLeftPoint() == true){
					return 1;
				}
				else{ // they are identical, might be linked to different line segment
					return 0;
				}
			}
		}
	}

}
