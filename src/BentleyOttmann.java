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
				LineSegment[] AB = SL.insertSorted(currentSegment);
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
				//grab seg from currentPoint
				//grab segAbove and segBelow seg from SL
				//delete seg from SL
				//check for intersect (which hasn't been found before) and possibly add to SL
			}
			else{ // must be intersection point
				//add intersection point to intersectionPointList
				//retrieve I1 and I2, as the two intersecting lines. Find in SL such that I1 is above I2
				//swap I1 and I2 so that I2 is now ontop
				//get segA and segB where segA is above I2 and segB is below I1
				//check for possible intersection points of the new neighbours, insert into EQ if necessary
			}
			currentNode = currentNode.getAfter();
		}
		
		return intersectionPointList;
	}
}
