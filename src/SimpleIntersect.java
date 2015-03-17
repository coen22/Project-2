import java.util.ArrayList;

public class SimpleIntersect implements IntersectAlgorithm {
	public ArrayList<Vertex> getIntersections(ArrayList<Segment> lines) {
		ArrayList<Vertex> points = new ArrayList<Vertex>();
		
		ArrayList<Segment> tmpLines = new ArrayList<Segment>();
		tmpLines.addAll(lines);
		
		for (Segment line : lines) {
			
			for (Segment line2 : tmpLines) {
				if (line.equals(line2))
					continue;
				
				Vertex p = findCollision(line, line2);
				
				if (p != null)
					points.add(p);
			}
			
			tmpLines.remove(line);
		}
		
		return points;
	}
	
	Vertex findCollision(Segment l, Segment l2) {
		if ((l2.getA().getX() < l.getA().getX() && l2.getB().getX() < l.getA().getX()) || (l2.getA().getX() > l.getB().getX() && l2.getB().getX() > l.getB().getX()))
			return null;
		
		if ((l2.getA().getY() < l.getA().getY() && l2.getB().getY() < l.getA().getY()) || (l2.getA().getY() > l.getB().getY() && l2.getB().getY() > l.getB().getY()))
			return null;
		
		double de = (l.getA().getX() - l.getB().getX()) * (l2.getA().getY() - l2.getB().getY()) - (l.getA().getY() - l.getB().getY()) * (l2.getA().getX() - l2.getB().getX());

		if (de == 0)
			return null;

		double nuX = (l2.getA().getX() - l2.getB().getX()) * (l.getA().getX() * l.getB().getY() - l.getB().getX() * l.getA().getY()) - (l.getA().getX() - l.getB().getX()) * (l2.getA().getX() * l2.getB().getY() - l2.getB().getX() * l2.getA().getY());
		double nuY = (l2.getA().getY() - l2.getB().getY()) * (l.getA().getX() * l.getB().getY() - l.getB().getX() * l.getA().getY()) - (l.getA().getY() - l.getB().getY()) * (l2.getA().getX() * l2.getB().getY() - l2.getB().getX() * l2.getA().getY());
		
		Vertex p = new Vertex(nuX / de, nuY / de);
		
		if (p.getX() > l.right() || p.getX() < l.left() || p.getX() > l2.right() || p.getX() < l2.left())
			return null;
		
		if (p.getY() > l.bottom() || p.getY() < l.top() || p.getY() > l2.bottom() || p.getY() < l2.top())
			return null;
		
		return p;
	}
}
