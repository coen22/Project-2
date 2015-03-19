
public class Testing {

	public static void main(String[] args) {
		PolyLine polyLine = new PolyLine();
		LineSegmentList list = new LineSegmentList();
		
//		polyLine.insertLast(new Vertex(0, 0));
//		polyLine.insertLast(new Vertex(6, 0.5));
//		polyLine.insertLast(new Vertex(3, 3));
//		polyLine.closeLine();

		
		
//		LineSegment lineseg1 = new LineSegment(new Vertex(1,1), new Vertex(7,5));
//		LineSegment lineseg2 = new LineSegment(new Vertex(4,2), new Vertex(8.5,3));
//		LineSegment lineseg3 = new LineSegment(new Vertex(5,0), new Vertex(7.5,4));
//		LineSegment lineseg4 = new LineSegment(new Vertex(2,4), new Vertex(9,5));
//		LineSegment lineseg5 = new LineSegment(new Vertex(4,6), new Vertex(10,1));
		
		LineSegment lineseg1 = new LineSegment(new Vertex(0,0), new Vertex(7,0));
		LineSegment lineseg2 = new LineSegment(new Vertex(5,0), new Vertex(7,0));
		LineSegment lineseg3 = new LineSegment(new Vertex(2.5,0), new Vertex(14,10));
		LineSegment lineseg4 = new LineSegment(new Vertex(6,0), new Vertex(5,5));
		LineSegment lineseg5 = new LineSegment(new Vertex(3,5), new Vertex(8,11));
		
		list.insertLast(lineseg1);
//		list.insertLast(lineseg2);
		list.insertLast(lineseg3);
		list.insertLast(lineseg4);
		list.insertLast(lineseg5);
		
//		System.out.println(list);
		
		BentleyOttmann.findIntersects(null, list);
		BentleyOttmannRB.findIntersects(null, list);
		
//		BentleyOttmann.findIntersects(polyLine, null);
//		BentleyOttmannRB.findIntersects(polyLine, null);
		
//		//----------------------------------------------------------------------------------
//		//below here for Tree Testing
//		
//		//new tree
//		SweepLineRB RB = new SweepLineRB();
//		
//		//inserted into the SL
//		RB.insertSorted(lineseg1);
//		
//		
//		RB.insertSorted(lineseg4);
//		RB.insertSorted(lineseg2);
//		RB.insertSorted(lineseg3);
//		
//		
//		//these lines are swaps which are simulated to be in an event queue, this corresponds to the picture in the FB chat
//		RB.swap(lineseg4, lineseg1, 6.5);
//		RB.swap(lineseg2, lineseg3, 7);
//		
//		RB.insertSorted(lineseg5);
//		
//		RB.delete(lineseg1);
//		
//		RB.swap(lineseg5, lineseg2, 8);
//		
//		RB.delete(lineseg3);
//		RB.delete(lineseg2);
//		RB.delete(lineseg4);
//		RB.delete(lineseg5);
		
	}

}
