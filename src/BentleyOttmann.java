
public class BentleyOttmann {
	public Vertex intersect(LineSegment l, LineSegment l2) {
		double de = (l.getA().getX() - l.getB().getX()) * (l2.getA().getY() - l2.getB().getY()) - (l.getA().getY() - l.getB().getY()) * (l2.getA().getX() - l2.getB().getX());

		double nu1 = (l2.getA().getX() - l2.getB().getX()) * (l.getA().getX() * l.getB().getY() - l.getB().getX() * l.getA().getY()) - (l.getA().getX() - l.getB().getX()) * (l2.getA().getX() * l2.getB().getY() - l2.getB().getX() * l2.getA().getY());
		double nu2 = (l2.getA().getY() - l2.getB().getY()) * (l.getA().getX() * l.getB().getY() - l.getB().getX() * l.getA().getY()) - (l.getA().getY() - l.getB().getY()) * (l2.getA().getX() * l2.getB().getY() - l2.getB().getX() * l2.getA().getY());
		
		return new Vertex(nu1 / de, nu2 / de);
	}
}
