
/**
 * Basic Vector and Matrix functions
 *
 * @author Group 1: Kareem, Marciano, Brian, David, Coen
 * @version 1.0
 */
public class MatrixVectorFunctions {
	
	private final static boolean DEBUG = false;
	
	/**
	 * Checks an Intersect through the use of orientation between the endpoints of the lines
	 * @param 	line1 Line segment 1
	 * @param 	line2 Line segment 2
	 * @return 	Boolean return. Returns true when an intersect is found on the linesegments
	 * 			Returns false when there is no intersect on the line segments
	 */
	public static boolean doesIntersect(LineSegment line1, LineSegment line2){
		if (DEBUG) System.out.println("checking for intersect");
		Vertex p1 = line1.getA();
		Vertex p2 = line1.getB();
		Vertex q1 = line2.getA();
		Vertex q2 = line2.getB();
		
		int o1 = orientation(p1, p2, q1);
		int o2 = orientation(p1, p2, q2);
		int o3 = orientation(q1, q2, p1);
		int o4 = orientation(q1, q2, p2);
		
		
		//General case
		if(o1 != o2 && o3 != o4){
			return true;
		}
		//Special cases
		//ie one line extending another, two segments 'in' eachother
		
		if(o1 == 0 && onSegment(p1, q1, p2)){
			return true;
		}
		if(o2 == 0 && onSegment(p1, q2, p2)){
			return true;
		}
		if(o3 == 0 && onSegment(q1, p1, q2)){
			return true;
		}
		if(o4 == 0 && onSegment(q1, p2, q2)){
			return true;
		}
		if (DEBUG) System.out.println("failed to find intersect...");
		return false;
	}
	/**
	 * Checks if two segments are overlapping only needed in special cases
	 * @param p Points to be checked
	 * @param q
	 * @param r
	 * @return 	a boolean which states true if it does lay on the segment
	 */
	public static boolean onSegment(Vertex p, Vertex q, Vertex r){
		if(	q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
			q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY())){
			return true;
		}
		return false;
	}
	/**
	 * Checks if the orientation is clockwise, counterclockwise or colinear
	 * @param p Point 1
	 * @param q Point 2
	 * @param r Point 3
	 * @return The value which can either be -1, 0, 1
	 */
	public static int orientation(Vertex p, Vertex q, Vertex r){
		int value = (int) ((q.getY() - p.getY())*(r.getX() - q.getX()) -
					(q.getX() - p.getX())*(r.getY() - q.getY()));
		if(value == 0){
			return 0;
		}
		else if(value > 0){
			return 1;
		}
		else{
			return -1;
		}
	}
	
	public static Vertex intersectionPoint(LineSegment segA, LineSegment segB){
		if (DEBUG)System.out.println("segA.getA: " + segA.getA());
		if (DEBUG)System.out.println("segA.getB: " + segA.getB());
		if (DEBUG)System.out.println("segB.getA: " + segB.getA());
		if (DEBUG)System.out.println("segB.getB: " + segB.getB());
		
		if (DEBUG)System.out.println("will do math");
		double x = (segB.getConstantOffset() - segA.getConstantOffset())/(segA.getSlope()-segB.getSlope());
		if (DEBUG)System.out.println(x);
		double y = segA.getSlope()*x + segA.getConstantOffset();
		if (DEBUG)System.out.println(y);
		
		if (Double.isNaN(x) || Double.isNaN(y)){
			if (segA.getA().equals(segB.getA()) || segA.getA().equals(segB.getB())){ //segA point A matches an end-point of segB
				if (DEBUG)System.out.println("will return SegA.getA");
				return segA.getA();
			}
			else if (segA.getB().equals(segB.getA()) || segA.getB().equals(segB.getB())){ //segA point B matches an end-point of segB
				if (DEBUG)System.out.println("will return SegA.getB");
				return segA.getB();
			}
			else if (segA.getSlope() == 0 && Double.isInfinite(segB.getSlope())){ //segA is horizontal and segB is vertical
				if (DEBUG) System.out.println("B is vertical, A is horizontal");
				return (new Vertex(segB.getB().getX(), segA.getB().getY()));
			}
			else if (segB.getSlope() == 0 && Double.isInfinite(segA.getSlope())){ //segA is vertical and segB is horizontal
				if (DEBUG) System.out.println("A is vertical, B is horizontal");
				return (new Vertex(segA.getB().getX(), segB.getB().getY()));
			}
			else if (Double.isInfinite(segA.getSlope())){ //segA is vertical
				if (DEBUG) System.out.println("A is vertical");
				double xOfA = segA.getB().getX();
				double verticalY = ((segB.getSlope()*xOfA) + segB.getConstantOffset());
				return (new Vertex(xOfA, verticalY));
			}
			else if (Double.isInfinite(segB.getSlope())){ //segB is vertical
				if (DEBUG) System.out.println("B is vertical");
				double xOfB = segB.getB().getX();
				double verticalY = ((segA.getSlope()*xOfB) + segA.getConstantOffset());
				return (new Vertex(xOfB, verticalY));
			}
		}
		if (DEBUG) System.out.println("failed to catch anything, returning calculations.................................................................");
		return new Vertex(x,y);
	}
}
