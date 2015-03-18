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
		
		EventPoint currentPoint = eventList.deQueue();
		while (eventList.size() > 0){
			if (DEBUG) System.out.println("current Point checking: " + currentPoint);
			if (currentPoint.isLeftPoint() == true){
				LineSegment currentSegment = currentPoint.getLineSegment1();
				LineSegment[] AB = SL.insertSorted(currentSegment, currentPoint.getX());
				LineSegment above = AB[0];
				LineSegment below = AB[1];
				if (above != null){
					if (MatrixVectorFunctions.doesIntersect(above, currentSegment)){
						if (DEBUG) System.out.println("new intersect found!");
						eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(above, currentSegment), above, currentSegment, true, false));
					}
				}
				if (below != null){
					if (MatrixVectorFunctions.doesIntersect(below, currentSegment)){
						if (DEBUG) System.out.println("new intersect found!");
						eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(below, currentSegment), currentSegment, below, true, false));
					}
				}
			}
			else if (currentPoint.isLeftPoint() == false && currentPoint.isIntersectionPoint() == false){
				LineSegment currentSegment = currentPoint.getLineSegment1();
				LineSegment[] AB = SL.delete(currentSegment, currentPoint.getX());
				LineSegment above = AB[0];
				LineSegment below = AB[1];
				if (above != null && below != null){
					if (MatrixVectorFunctions.doesIntersect(above, below)){
						if (DEBUG) System.out.println("new intersect found!");
						eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(above, below), above, below, true, false));
					}
				}
			}
			else{ // must be intersection point
				if (DEBUG) System.out.println("intersection Point found: (" + currentPoint.getX() + ","+currentPoint.getY()+")");
				intersectionPointList.add(new Vertex(currentPoint.getX(),currentPoint.getY()));
				LineSegment intersectA = currentPoint.getLineSegment1();
				LineSegment intersectB = currentPoint.getLineSegment2();
				LineSegment[] AB = SL.swap(intersectA, intersectB, currentPoint.getX());
				LineSegment aboveA = AB[0];
				LineSegment belowB = AB[1];
				
				if (aboveA == null && belowB == null){
					//do nothing
				}
				else{
					if (aboveA != null){
						//check for intersect between aboveA and intersectA
						if (MatrixVectorFunctions.doesIntersect(aboveA, intersectA)){
							if (DEBUG) System.out.println("new intersect found!");
							eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(aboveA, intersectA), aboveA, intersectA, true, false));
						}
					}
					if (belowB != null){
						//check for intersect between belowB and intersectB
						if (MatrixVectorFunctions.doesIntersect(intersectB, belowB)){
							if (DEBUG) System.out.println("new intersect found!");
							eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(intersectB, belowB), intersectB, belowB, true, false));
						}
					}
				}
			}
			if (DEBUG) System.out.println("End of current point. EventList: " + eventList + "\n");
			currentPoint = eventList.deQueue();
		}
		
		System.out.println("number of intersection points: " + intersectionPointList.size());
		System.out.println(intersectionPointList);
		return intersectionPointList;
	}
}
