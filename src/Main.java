import java.awt.Point;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Collision");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		
		Board board = new Board();
		board.addLine(new Line(new Point(10,10), new Point(200,100)), false);
		board.addLine(new Line(new Point(200,200), new Point(60,10)), true);
		
		frame.setContentPane(board);
		
		frame.setVisible(true);
	}
}
