
public class Testing {

	public static void main(String[] args) {
		PolyLine polyLine = new PolyLine();
		LineSegmentList list = new LineSegmentList();
		
		polyLine.insertLast(new Vertex(2, -3));
		polyLine.insertLast(new Vertex(3, -1));
		polyLine.insertLast(new Vertex(0, 1));
		polyLine.insertLast(new Vertex(3, 3));
		polyLine.insertLast(new Vertex(5, 6));
//		polyLine.closeLine();
		
		LineSegment lineseg1 = new LineSegment(new Vertex(2, -3), new Vertex(3, -1));
		LineSegment lineseg2 = new LineSegment(new Vertex(3,-1), new Vertex(0,1));
		LineSegment lineseg3 = new LineSegment(new Vertex(0,1), new Vertex(3,3));
		LineSegment lineseg4 = new LineSegment(new Vertex(3,3), new Vertex(5,6));


//		
		list.insertLast(lineseg1);
		list.insertLast(lineseg2);
		list.insertLast(lineseg3);
		list.insertLast(lineseg4);
		
		LineSegmentList polyList = new LineSegmentList(polyLine);
		System.out.println("Polyline  " + polyList);
		System.out.println("list      " + list);
		
//		BentleyOttmannRB.findIntersects(null, list);
		
		System.out.println("using the polyline segments");
		BentleyOttmannRB.findIntersects(null, polyList);
		
		System.out.println("using the segments");
		BentleyOttmannRB.findIntersects(null, list);
		
	}

}
