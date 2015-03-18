import java.util.ArrayList;

/**
 * 
 * @author David
 *
 */
public class BentleyOttmann {

	public static ArrayList<Vertex> findIntersects(PolyLine polyLine){
		LineSegmentList segmentList = new LineSegmentList(polyLine);
		SweepLine SL = new SweepLine();
		EventList eventList = new EventList(segmentList);

		Node<EventPoint> currentNode = eventList.getHeader().getAfter();
		ArrayList<Vertex> intersectionPointList = new ArrayList<Vertex>();

		while (currentNode != eventList.getTrailer()){
			EventPoint currentPoint = currentNode.getElement();

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
				LineSegment[] AB = SL.delete(currentSegment);
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
				
				//swap still has to be implemented and maybe everything re-structured slightly...
				LineSegment[] AB = SL.swap(intersectA, intersectB);
				LineSegment above = AB[0];
				LineSegment below = AB[1];
				//Find in SL such that intersectA is above intersectB
				//swap I1 and I2 so that I2 is now ontop
				//get segA and segB where segA is above I2 and segB is below I1
				
				//check for possible intersection points of the new neighbours, insert into EQ if necessary
			}
			currentNode = currentNode.getAfter();
		}

		return intersectionPointList;
	}
}
