/**
 * 
 * @author David
 * @version 1.0
 *
 */
public class EventPoint extends Vertex implements Valuable, Comparable<EventPoint> {
	private LineSegment lineSegment1;
	private LineSegment lineSegment2;
	private boolean isIntersection;
	private boolean leftPoint;

	/**
	 * Constructor for an Event point
	 * 
	 * @param x x-location of event-point
	 * @param y y-location of event-point
	 * @param lineSegment1 if the event-point either a right or left endpoint of a segment, it is linked here. Upper line for an intersection point
	 * @param lineSegment2 bottom line in case of intersection point
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
	 * @param lineSegment1 if the event-point either a right or left endpoint of a segment, it is linked here. Upper line for an intersection point
	 * @param lineSegment2 bottom line in case of intersection point
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

	/**
	 * 
	 * @return	Returns if a specific point is an intersection. With True of False
	 */
	public boolean isIntersectionPoint(){
		return isIntersection;
	}

	/**
	 * 
	 * @return	Returns if a specific point is the left point of a linesegment
	 */
	public boolean isLeftPoint(){
		return leftPoint;
	}

	/**
	 * To string method
	 */
	public String toString(){
		return super.toString();
	}
	
	/**
	 * Compares one event point to the other  Functions like a comparator but adjusted for our own purposes
	 * @param b		The other eventpoint to be compared
	 * @return		Returns an int that can either be 1 for b being more to the left or below, -1 for be being more to the right or above
	 * 				and 0 if two eventpoints are identical
	 */
	public int compareTo(EventPoint b) {
		if (this.getX() > b.getX()){
			return 1;
		}
		else if (this.getX() < b.getX()){
			return -1;
		}
		else{ //x must be the same value
//			if (this.getY() < b.getY()){
//				return -1;
//			}
//			else if (this.getY() > b.getY()){
//				return 1;
//			}
//			else{ //x and y must have the same value
//				if (this.isIntersection == true && b.isIntersectionPoint() == false){
//					return 1;
//				}
//				else if (b.isIntersectionPoint() == true && this.isIntersectionPoint() == false){
//					return -1;
//				}
//				else if (this.leftPoint == true && b.isLeftPoint() == false){
//					return -1;
//				}
//				else if (this.leftPoint == false && b.isLeftPoint() == true){
//					return 1;
//				}
//				else{// they are identical, might be linked to different line segment
//					if ((this.lineSegment1.equals(b.getLineSegment1()) && this.lineSegment2.equals(b.getLineSegment2()))){
//						return 0;
//					}
//					else {
//						return 1;
//					}
//				}
//			}
			return 0;
		}
	}

	/**
	 * @return 	Returns the value
	 */
	public int getValue() {
		return (int) x;
	}

}
