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
	private SortingAlgorithm sorter;
	private int brushSize = 5;
	private boolean debug = false;
	
	
	public Board(int brush, SortingAlgorithm s) {
		brushSize = brush;
		sorter = s;
	}
	
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
			g2.fillOval(p.x - brushSize, p.y - brushSize, brushSize*2+1, brushSize*2+1);
	}
	
	public void addLine(LineSegment l) {
		lines.add(l);
		collisionData = sorter.makeCollisionData(lines);
	}
	
	public void removelineAt(int i) {
		lines.remove(i);
		collisionData = sorter.makeCollisionData(lines);
	}
	
	public void removeline(LineSegment l) {
		lines.remove(l);
		collisionData = sorter.makeCollisionData(lines);
	}
	
	public void changeLineAt(int i, LineSegment l) {
		lines.set(i, l);
		collisionData = sorter.makeCollisionData(lines);
	}
}