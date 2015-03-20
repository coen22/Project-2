
public class Testing {

	public static void main(String[] args) {
		PolyLine polyLine = new PolyLine();
		LineSegmentList list = new LineSegmentList();
		
		/*
		 * Possible solution to problem with certain lines in the UI...
		 * increase the offset (make it bigger) and change EPSILON to be constant. check manually and in UI, see if x100 makes a difference
		 */
		
		
		/*
		 * Classes which can be deleted:
		 * 
		 * AVLNode
		 * AVLSurface
		 * AVLTree
		 * BentleyOttmann - must re-assign some methods from UI
		 * EventList
		 * Launch
		 * SweepLine
		 * Testing
		 * Valuable
		 */
		
		polyLine.insertLast(new Vertex(0, 0));
		polyLine.insertLast(new Vertex(6, 0.5));
		polyLine.insertLast(new Vertex(3, 3));
		polyLine.closeLine();
		
		LineSegment lineseg1 = new LineSegment(new Vertex(0,0), new Vertex(7,0));
		LineSegment lineseg2 = new LineSegment(new Vertex(5,0), new Vertex(7,1));
		LineSegment lineseg3 = new LineSegment(new Vertex(2.5,0), new Vertex(14,10));
		LineSegment lineseg4 = new LineSegment(new Vertex(6,0), new Vertex(5,5));
		LineSegment lineseg5 = new LineSegment(new Vertex(3,5), new Vertex(8,11));
		
		list.insertLast(lineseg1);
		list.insertLast(lineseg2);
		list.insertLast(lineseg3);
		list.insertLast(lineseg4);
		list.insertLast(lineseg5);
		
		BentleyOttmannRB.findIntersects(null, list);
		
		BentleyOttmannRB.findIntersects(polyLine, null);
		
	}

}
