
public class Testing {

	public static void main(String[] args) {
//		PolyLine polyLine = new PolyLine();
//		polyLine.insertLast(new Vertex(0, 0));
//		polyLine.insertLast(new Vertex(6, 0));
//		polyLine.insertLast(new Vertex(3, 3));
//		polyLine.closeLine();

		LineSegmentList list = new LineSegmentList();
		
		LineSegment lineseg1 = new LineSegment(new Vertex(1,1), new Vertex(7,5));
		LineSegment lineseg2 = new LineSegment(new Vertex(4,2), new Vertex(8,3));
		LineSegment lineseg3 = new LineSegment(new Vertex(5,0), new Vertex(7.5,4));
		LineSegment lineseg4 = new LineSegment(new Vertex(2,4), new Vertex(9,5));
		
		LineSegment lineseg5 = new LineSegment(new Vertex(4,8), new Vertex(7,8));
		LineSegment lineseg6 = new LineSegment(new Vertex(5,10), new Vertex(9,6));
		LineSegment lineseg7 = new LineSegment(new Vertex(6,5), new Vertex(10,9));
		
//		list.insertLast(lineseg1);
//		list.insertLast(lineseg2);
//		list.insertLast(lineseg3);

//		list.insertLast(lineseg4);
//		list.insertLast(lineseg5);
//		list.insertLast(lineseg6);
//		list.insertLast(lineseg7);
		
//		System.out.println(list);
		
//		BentleyOttmann.findIntersects(null, list);
//		BentleyOttmann.findIntersects(polyLine, null);
		
		//----------------------------------------------------------------------------------
		//below here for Tree Testing
		
		//new tree
		SweepLineRB RB = new SweepLineRB();
		
		//here the comparisonXValue is set manually, this corresponds with the x-value at which it is entered into the SweepLine
		lineseg1.setComparisonXValue(1);
		//inserted into the SL
		RB.insertSorted(lineseg1);
		
		
		lineseg4.setComparisonXValue(2);
		RB.insertSorted(lineseg4);
		lineseg2.setComparisonXValue(4);
		RB.insertSorted(lineseg2);
		lineseg3.setComparisonXValue(5);
		RB.insertSorted(lineseg3);
		
		
		//these lines are swaps which are simulated to be in an event queue, this corresponds to the picture in the FB chat
		RB.swap(lineseg4, lineseg1, 6.5);
		RB.swap(lineseg2, lineseg3, 7);
		
//		RB.delete(lineseg1);
		
	}

}
