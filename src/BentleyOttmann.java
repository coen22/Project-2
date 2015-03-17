import java.util.ArrayList;

/**
 * 
 * @author David
 *
 */
public class BentleyOttmann {
	public static ArrayList<Vertex> findIntersects(PolyLine polyLine){
		LineSegmentList segmentList = new LineSegmentList(polyLine);
		SweepLine sweepLineStack = new SweepLine();
		EventList eventList = new EventList(segmentList);
		
		Node<EventPoint> currentEventPointNode = eventList.getHeader().getAfter();
		ArrayList<Vertex> intersectionPointList = new ArrayList<Vertex>();
		
		while (currentEventPointNode != eventList.getTrailer()){
			EventPoint currentPoint = currentEventPointNode.getElement();
			
			if (currentPoint.isLeftPoint() == true){
				//grab seg from currentPoint
				//add segment to sweepline
				//grab segAbove and segBelow
				//check for intersects with currentSegment and segAbove and segBelow
			}
			else if (currentPoint.isLeftPoint() == false && currentPoint.isIntersectionPoint() == false){
				//grab seg from currentPoint
				//grab segAbove and segBelow seg from SL
				//delete seg from SL
				//check for intersect (which hasn't been found before) and possibly add to SL
			}
			else{ // must be intersection point
				//add intersection point to intersectionPointList
			}
		}
		
		return intersectionPointList;
	}
}
