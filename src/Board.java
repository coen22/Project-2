import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {
	private ArrayList<Line> lines = new ArrayList<Line>();
	private ArrayList<Point> collisionData = new ArrayList<Point>();
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setStroke(new BasicStroke(2, 1, 1));
		
		for (Line line : lines)
			g2.drawLine(line.a.x, line.a.y, line.b.x, line.b.y);
		
		g2.setColor(Color.blue);
		
		for (Point p : collisionData)
			g2.drawOval(p.x - 2, p.y - 2, 5, 5);
	}
	
	public void addLine(Line l, boolean rebuild) {
		lines.add(l);
		
		if (rebuild)
			makeCollisionData();
	}
	
	public void makeCollisionData() {
		ArrayList<Point> points = new ArrayList<Point>();
		
		ArrayList<Line> tmpLines = new ArrayList<Line>();
		tmpLines.addAll(lines);
		
		for (Line line : lines) {
			
			for (Line line2 : tmpLines) {
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
	
	Point findCollision(Line l, Line l2) {
		int de = (l.a.x - l.b.x) * (l2.a.y - l2.b.y) - (l.a.y - l.b.y) * (l2.a.x - l2.b.x);
		int nu1 = (l2.a.x - l2.b.x) * (l.a.x * l.b.y - l.b.x * l.a.y) - (l.a.x - l.b.x) * (l2.a.x * l2.b.y - l2.b.x * l2.a.y);
		int nu2 = (l2.a.y - l2.b.y) * (l.a.x * l.b.y - l.b.x * l.a.y) - (l.a.y - l.b.y) * (l2.a.x * l2.b.y - l2.b.x * l2.a.y);
		
		System.out.println("de = " + de);
		
		if (de == 0)
			return null;
		
		Point p = new Point(0,0);
		p.x = (int) ((double) nu1 / (double) de);
		p.y = (int) ((double) nu2 / (double) de);
		
		System.out.println("x = " + p.x + " y = " + p.y);
		
		return p;
	}
}