import java.util.ArrayList;

/**
 * 
 * @author Group 1: Kareem, Marciano, Brian, David, Coen
 * @version 3.0
 *
 */
public class BentleyOttmannRB {
	
	private final static boolean RESULT = false;

	/**
	 * Method returns whether two independent polylines intersect each other.
	 * @param line1 polyline 1
	 * @param line2 polyline 2
	 * @return true if there is an intersection
	 */
	public static boolean polyLinesIntersecting(PolyLine line1, PolyLine line2){
		int line1Intersects = (findIntersects(line1, null)).size();
		int line2Intersects = (findIntersects(line2, null)).size();
		LineSegmentList segmentCollection = new LineSegmentList(line1, line2);
		int collectionIntersects = (findIntersects(null, segmentCollection)).size();
		return (!((line1Intersects + line2Intersects) == collectionIntersects));
	}

	/**
	 * Method that determines all the intersection points of either a polyline or a list of line segments. Only one input is accepted, otherwise the polyline is chosen. 
	 * @param polyLine The poly-line of which all intersects should be determined
	 * @param inputSegments The list of segments of which the intersects should be found
	 * @return an ArrayList of all the intersection points
	 */
	public static ArrayList<Vertex> findIntersects(PolyLine polyLine, LineSegmentList inputSegments){
		LineSegmentList segmentList;
		if (polyLine == null && inputSegments != null){
			segmentList = inputSegments;
		}
		else{
			segmentList = new LineSegmentList(polyLine);
		}
		
		SweepLineRB SL = new SweepLineRB();
		EventListRB eventList = new EventListRB(segmentList);
		ArrayList<Vertex> intersectionPointList = new ArrayList<Vertex>();
		
		EventPoint currentPoint = eventList.deQueue();
		while (!eventList.isEmpty()){
			if (currentPoint.isLeftPoint() == true){ //the eventpoint is a left-point
				LineSegment currentSegment = currentPoint.getLineSegment1();
				LineSegment[] AB = SL.insertSorted(currentSegment);
				LineSegment above = AB[0];
				LineSegment below = AB[1];
				if (above != null){
					if (MatrixVectorFunctions.doesIntersect(above, currentSegment) && !(MatrixVectorFunctions.intersectionPoint(above, currentSegment).getX() < currentPoint.getX()-Math.pow(10, -8))){
						eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(above, currentSegment), above, currentSegment, true, false));
					}
				}
				if (below != null){
					if (MatrixVectorFunctions.doesIntersect(below, currentSegment) && !(MatrixVectorFunctions.intersectionPoint(below, currentSegment).getX() < currentPoint.getX()-Math.pow(10, -8))){
						eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(below, currentSegment), currentSegment, below, true, false));
					}
				}
			}
			else if (currentPoint.isLeftPoint() == false && currentPoint.isIntersectionPoint() == false){ //the eventpoint is a right-endpoint
				LineSegment currentSegment = currentPoint.getLineSegment1();
				LineSegment[] AB = SL.delete(currentSegment);
				LineSegment above = AB[0];
				LineSegment below = AB[1];
				if (above != null && below != null){
					if (MatrixVectorFunctions.doesIntersect(above, below) && !(MatrixVectorFunctions.intersectionPoint(above, below).getX() < currentPoint.getX()-Math.pow(10, -8))){
						eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(above, below), above, below, true, false));
					}
				}
			}
			else{ // the eventpoint must be intersection point
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
						if (MatrixVectorFunctions.doesIntersect(aboveA, intersectB) && !(MatrixVectorFunctions.intersectionPoint(aboveA, intersectB).getX() < currentPoint.getX()-Math.pow(10, -8))){
							eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(aboveA, intersectB), aboveA, intersectB, true, false));
						}
					}
					if (belowB != null){
						//check for intersect between belowB and intersectB
						if (MatrixVectorFunctions.doesIntersect(intersectA, belowB) && !(MatrixVectorFunctions.intersectionPoint(intersectA, belowB).getX() < currentPoint.getX()-Math.pow(10, -8))){
							eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(intersectA, belowB), intersectA, belowB, true, false));
						}
					}
				}
			}
			currentPoint = eventList.deQueue();
		}
		if (RESULT) System.out.println("number of intersection points: " + intersectionPointList.size());
		if (RESULT) System.out.println(intersectionPointList);
		
		SL = null;
		eventList = null;
		return intersectionPointList;
	}
}
