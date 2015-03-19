
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
		
		SweepLineRB RB = new SweepLineRB();
		
//		RB.insertSorted(lineseg1);
//		RB.insertSorted(lineseg2);
//		RB.insertSorted(lineseg3);
		
		lineseg1.setComparisonXValue(1);
		RB.insertSorted(lineseg1);
		lineseg4.setComparisonXValue(2);
		RB.insertSorted(lineseg4);
		lineseg2.setComparisonXValue(4);
		RB.insertSorted(lineseg2);
		lineseg3.setComparisonXValue(5);
		RB.insertSorted(lineseg3);
		
		
		RB.swap(lineseg4, lineseg1);
		RB.swap(lineseg2, lineseg3);
		
		
	}

}
