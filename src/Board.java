import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {
	private ArrayList<Segment> lines = new ArrayList<Segment>();
	private ArrayList<Vertex> collisionData = new ArrayList<Vertex>();
	private IntersectAlgorithm intersect;
	private int brushSize;
	
	public Board(int brush, IntersectAlgorithm s) {
		brushSize = brush;
		intersect = s;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setStroke(new BasicStroke(2, 1, 1));
		
		for (Segment line : lines) {
			g2.drawLine((int) line.getA().getX(), (int) line.getA().getY(), (int) line.getB().getX(), (int) line.getB().getY());
		}
		
		g2.setColor(Color.blue);
		
		for (Vertex p : collisionData)
			g2.fillOval((int) p.getX() - brushSize, (int) p.getY() - brushSize, brushSize*2+1, brushSize*2+1);
	}
	
	public void addLine(Segment l) {
		lines.add(l);
		collisionData = intersect.getIntersections(lines);
	}
	
	public void removelineAt(int i) {
		lines.remove(i);
		collisionData = intersect.getIntersections(lines);
	}
	
	public void removeline(Segment l) {
		lines.remove(l);
		collisionData = intersect.getIntersections(lines);
	}
	
	public void changeLineAt(int i, Segment l) {
		lines.set(i, l);
		collisionData = intersect.getIntersections(lines);
	}
}