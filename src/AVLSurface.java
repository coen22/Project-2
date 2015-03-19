
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class AVLSurface extends JPanel {
	private static final long serialVersionUID = 1L;
	private AVLTree tree;
	private Graphics2D g2;
	
	private int SIZE = 75;

	public AVLSurface(AVLTree tree) {
		this.tree = tree;
	}

	public void paint(Graphics g) {
		g2 = (Graphics2D) g;

		drawNode(tree.root, getWidth()/2, 50, SIZE*2.6f, SIZE);
	}

	public void drawNode(AVLNode n, int x, int y, float width, int size) {
		if (n.left != null) g2.drawLine(x, y, x-(int) (width/1.9*1.5), y+size);
		if (n.right != null) g2.drawLine(x, y, x+(int) (width/1.9*1.5), y+size);
		
		g2.setColor(Color.white);
		g2.fillOval(x-size/2, y-size/2, size, size);
		g2.setColor(Color.black);
		g2.drawOval(x-size/2, y-size/2, size, size);

		g2.setColor(Color.black);
		g2.setFont(new Font("Helvetica", Font.PLAIN, (int) (24*(size*1.4)/SIZE)));
		drawCenteredString(String.valueOf(n.getValue()), x-size/2, y-size/2, size, size, g2);
		
		width /= 1.9;
		size /= 1.15;
		
		if (n.left != null) drawNode(n.left, x-(int) (width*1.5), y+size, width, size);
		if (n.right != null) drawNode(n.right, x+(int) (width*1.5), y+size, width, size);
	}

	public void drawCenteredString(String s, int xOff, int yOff, int w, int h, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;
		int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(s, x+xOff, y+yOff);
	}
}
