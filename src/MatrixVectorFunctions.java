
/**
 * Basic Vector and Matrix functions
 *
 * @author Coen, Marciano & David
 * @version 1.0
 */
public class MatrixVectorFunctions {
	
	public static boolean doesIntersect(Vertex p1, Vertex p2, Vertex q1, Vertex q2){
		int o1 = orientation(p1, p2, q1);
		int o2 = orientation(p1, p2, q2);
		int o3 = orientation(q1, q2, p1);
		int o4 = orientation(q1, q2, p2);
		
		System.out.println("o1 = " + o1 + " o2 = " + o2 + " o3 = " + o3 + " o4 = " + o4);
		//General case
		if(o1 != o2 && o3 != o4){
			System.out.println("Enters General case");
			return true;
		}
		//Special cases
		//ie one line extending another, two segments 'in' eachother
		
		if(o1 == 0 && onSegment(p1, q1, p2)){
			System.out.println("Special Case 1 ");
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
		return false;
	}
	public static boolean onSegment(Vertex p, Vertex q, Vertex r){
		if(	q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
			q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY())){
			return true;
		}
		return false;
	}
	public static int orientation(Vertex p, Vertex q, Vertex r){
		int value = (int) ((q.getY() - p.getY())*(r.getX() - q.getX()) -
					(q.getX() - p.getX())*(r.getY() - q.getY()));
		if(value == 0){
			return 0;
		}
		return value;
	}
	public static boolean intersectExists(LineSegment l, LineSegment l2){
		double denominator = (l.getA().getX() - l.getB().getX()) * (l2.getA().getY() - l2.getB().getY()) - (l.getA().getY() - l.getB().getY()) * (l2.getA().getX() - l2.getB().getX());

		return (denominator != 0);
	}
	
	public static Vertex intersect(LineSegment l, LineSegment l2) {
		double denominator = (l.getA().getX() - l.getB().getX()) * (l2.getA().getY() - l2.getB().getY()) - (l.getA().getY() - l.getB().getY()) * (l2.getA().getX() - l2.getB().getX());

		if (denominator == 0)
			return null;
		
		double numeratorX = (l2.getA().getX() - l2.getB().getX()) * (l.getA().getX() * l.getB().getY() - l.getB().getX() * l.getA().getY()) - (l.getA().getX() - l.getB().getX()) * (l2.getA().getX() * l2.getB().getY() - l2.getB().getX() * l2.getA().getY());
		double numeratorY = (l2.getA().getY() - l2.getB().getY()) * (l.getA().getX() * l.getB().getY() - l.getB().getX() * l.getA().getY()) - (l.getA().getY() - l.getB().getY()) * (l2.getA().getX() * l2.getB().getY() - l2.getB().getX() * l2.getA().getY());
		
		return new Vertex(numeratorX / denominator, numeratorY / denominator);
	}
}
