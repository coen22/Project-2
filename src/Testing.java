
public class Testing {

	public static void main(String[] args) {
		PolyLine polyLine = new PolyLine();
		polyLine.insertLast(new Vertex(0, 0));
		polyLine.insertLast(new Vertex(6, 0));
		polyLine.insertLast(new Vertex(3, 3));
		polyLine.closeLine();

		LineSegmentList list = new LineSegmentList();
		
		LineSegment lineseg1 = new LineSegment(new Vertex(-2,-1), new Vertex(2,1));
		LineSegment lineseg2 = new LineSegment(new Vertex(-1,-2), new Vertex(1,2));
		LineSegment lineseg3 = new LineSegment(new Vertex(1,9), new Vertex(4,9));
		LineSegment lineseg4 = new LineSegment(new Vertex(2,10), new Vertex(6,7));
		LineSegment lineseg5 = new LineSegment(new Vertex(4,8), new Vertex(7,8));
		LineSegment lineseg6 = new LineSegment(new Vertex(5,10), new Vertex(9,6));
		LineSegment lineseg7 = new LineSegment(new Vertex(6,5), new Vertex(10,9));
		
		list.insertLast(lineseg1);
		list.insertLast(lineseg2);
//		list.insertLast(lineseg3);

//		list.insertLast(lineseg4);
//		list.insertLast(lineseg5);
//		list.insertLast(lineseg6);
//		list.insertLast(lineseg7);
		
		
//		System.out.println(list);
		
		BentleyOttmann.findIntersects(null, list);
//		BentleyOttmann.findIntersects(polyLine, null);
		
	}

}
