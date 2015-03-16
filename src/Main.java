
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Collision");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		
		Board board = new Board(3, new SimpleIntersect());
		board.addLine(new Segment(new Vertex(10,10), new Vertex(200,100)));
		board.addLine(new Segment(new Vertex(100,40), new Vertex(100,150)));
		board.addLine(new Segment(new Vertex(80,80), new Vertex(180,80)));
		
		frame.setContentPane(board);
		
		frame.setVisible(true);
	}
}
