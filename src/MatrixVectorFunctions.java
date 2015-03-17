
/**
 * Basic Vector and Matrix functions
 *
 * @author Coen, Marciano & David
 * @version 1.0
 */
public class MatrixVectorFunctions {
	
	public static boolean onSegment(Vertex p, Vertex q, Vertex r){
		if(	q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())
			q.getX() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY()))
		
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
