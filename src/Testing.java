
public class Testing {

	public static void main(String[] args) {
		PolyLine polyLine = new PolyLine();
		polyLine.insertLast(new Vertex(0, 0));
		polyLine.insertLast(new Vertex(6, 0));
		polyLine.insertLast(new Vertex(3, 3));
		polyLine.closeLine();
		
//		System.out.println(polyLine);
		
		LineSegmentList list = new LineSegmentList();
		
		LineSegment lineseg1 = new LineSegment(new Vertex(3,0), new Vertex(3,5));
		LineSegment lineseg2 = new LineSegment(new Vertex(3,5), new Vertex(3,10));
		LineSegment lineseg3 = new LineSegment(new Vertex(2,-1), new Vertex(1,-2));
		LineSegment lineseg4 = new LineSegment(new Vertex(1,-2), new Vertex(-1,2));
		LineSegment lineseg5 = new LineSegment(new Vertex(-1,-1), new Vertex(4,1));
		
		list.insertLast(lineseg1);
		list.insertLast(lineseg2);
//		list.insertLast(lineseg3);
//		list.insertLast(lineseg4);
//		list.insertLast(lineseg5);
		
		BentleyOttmann.findIntersects(null, list);
//		BentleyOttmann.findIntersects(polyLine, null);
		
	}

}
