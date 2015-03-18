import java.util.ArrayList;

/**
 * 
 * @author David
 *
 */
public class BentleyOttmann {
	
	private final static boolean DEBUG = true;

	public static ArrayList<Vertex> findIntersects(PolyLine polyLine, LineSegmentList inputSegments){
		LineSegmentList segmentList;
		if (polyLine == null && inputSegments != null){
			segmentList = inputSegments;
		}
		else{
			segmentList = new LineSegmentList(polyLine);
		}
		SweepLine SL = new SweepLine();
		EventList eventList = new EventList(segmentList);

		ArrayList<Vertex> intersectionPointList = new ArrayList<Vertex>();
		
		if (DEBUG) System.out.println("EventList at Start:              " + eventList);
		
		EventPoint currentPoint = eventList.getHeader().getAfter().getElement();
		while (eventList.size() > 0){
			
			if (currentPoint.isLeftPoint() == true){
				LineSegment currentSegment = currentPoint.getLineSegment1();
				LineSegment[] AB = SL.insertSorted(currentSegment, currentPoint.getX());
				LineSegment above = AB[0];
				LineSegment below = AB[1];
				if (above != null){
					//check for intersect between above and currentSegment
				}
				if (below != null){
					//check for intersect between below and currentSegment
				}
			}
			else if (currentPoint.isLeftPoint() == false && currentPoint.isIntersectionPoint() == false){
				LineSegment currentSegment = currentPoint.getLineSegment1();
				LineSegment[] AB = SL.delete(currentSegment, currentPoint.getX());
				LineSegment above = AB[0];
				LineSegment below = AB[1];
				if (above != null && below != null){
					//check for intersect, only add if not before, between above and below
				}
			}
			else{ // must be intersection point
				intersectionPointList.add(new Vertex(currentPoint.getX(),currentPoint.getY()));
				LineSegment intersectA = currentPoint.getLineSegment1();
				LineSegment intersectB = currentPoint.getLineSegment2();
				LineSegment[] AB = SL.swap(intersectA, intersectB, currentPoint.getX());
				LineSegment aboveA = AB[0];
				LineSegment belowB = AB[1];
				
				if (aboveA != null){
					//check for intersect between aboveA and intersectA
				}
				if (belowB != null){
					//check for intersect between belowB and intersectB
				}
			}
			currentPoint = eventList.deQueue();
			if (DEBUG) System.out.println("End of current point. EventList: " + eventList);
		}

		return intersectionPointList;
	}
}
