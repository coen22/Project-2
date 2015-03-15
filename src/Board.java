import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {
	private ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
	private ArrayList<Point> collisionData = new ArrayList<Point>();
	private boolean debug = true;
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setStroke(new BasicStroke(2, 1, 1));
		
		for (LineSegment line : lines) {
			if (debug)
				g2.drawRect(line.left(), line.top(),
							Math.abs(line.b.x - line.a.x),
							Math.abs(line.b.y - line.a.y));
			
			g2.drawLine(line.a.x, line.a.y, line.b.x, line.b.y);
		}
		
		g2.setColor(Color.blue);
		
		for (Point p : collisionData)
			g2.fillOval(p.x - 2, p.y - 2, 5, 5);
	}
	
	public void addLine(LineSegment l) {
		lines.add(l);
		makeCollisionData();
	}
	
	public void removelineAt(int i) {
		lines.remove(i);
		makeCollisionData();
	}
	
	public void removeline(LineSegment l) {
		lines.remove(l);
		makeCollisionData();
	}
	
	public void changeLineAt(int i, LineSegment l) {
		lines.set(i, l);
		makeCollisionData();
	}
	
	public void makeCollisionData() {
		ArrayList<Point> points = new ArrayList<Point>();
		
		ArrayList<LineSegment> tmpLines = new ArrayList<LineSegment>();
		tmpLines.addAll(lines);
		
		for (LineSegment line : lines) {
			
			for (LineSegment line2 : tmpLines) {
				if (line.equals(line2))
					continue;
				
				Point p = findCollision(line, line2);
				
				if (p != null)
					points.add(p);
			}
			
			tmpLines.remove(line);
		}
		
		collisionData = points;
	}
	
	Point findCollision(LineSegment l, LineSegment l2) {
		if ((l2.a.x < l.a.x && l2.b.x < l.a.x) || (l2.a.x > l.b.x && l2.b.x > l.b.x))
			return null;
		
		if ((l2.a.y < l.a.y && l2.b.y < l.a.y) || (l2.a.y > l.b.y && l2.b.y > l.b.y))
			return null;
		
		int de = (l.a.x - l.b.x) * (l2.a.y - l2.b.y) - (l.a.y - l.b.y) * (l2.a.x - l2.b.x);

		if (de == 0)
			return null;

		int nu1 = (l2.a.x - l2.b.x) * (l.a.x * l.b.y - l.b.x * l.a.y) - (l.a.x - l.b.x) * (l2.a.x * l2.b.y - l2.b.x * l2.a.y);
		int nu2 = (l2.a.y - l2.b.y) * (l.a.x * l.b.y - l.b.x * l.a.y) - (l.a.y - l.b.y) * (l2.a.x * l2.b.y - l2.b.x * l2.a.y);
		
		Point p = new Point(0,0);
		p.x = (int) ((double) nu1 / (double) de);
		p.y = (int) ((double) nu2 / (double) de);
		
		if (p.x > l.right() || p.x < l.left() || p.x > l2.right() || p.x < l2.left())
			return null;
		
		if (p.y > l.bottom() || p.y < l.top() || p.y > l2.bottom() || p.y < l2.top())
			return null;
		
		return p;
	}
}