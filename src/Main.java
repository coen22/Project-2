import java.awt.Point;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Collision");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		
		Board board = new Board();
		board.addLine(new LineSegment(new Point(10,10), new Point(200,100)));
		board.addLine(new LineSegment(new Point(100,60), new Point(100,150)));
		
		frame.setContentPane(board);
		
		frame.setVisible(true);
	}
}
