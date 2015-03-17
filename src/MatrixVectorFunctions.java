
/**
 * Basic Vector and Matrix functions
 *
 * @author Coen, Marciano & David
 * @version 1.0
 */
public class MatrixVectorFunctions {
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
