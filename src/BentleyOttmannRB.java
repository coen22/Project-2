import java.util.ArrayList;

/**
 * 
 * @author David
 * @version 5.0
 *
 */
public class BentleyOttmannRB {
	
	private final static boolean DEBUG = false;
	private final static boolean RESULT = true;

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
			if (DEBUG) System.out.println("current Point checking: " + currentPoint);
			if (currentPoint.isLeftPoint() == true){ //the eventpoint is a left-point
				LineSegment currentSegment = currentPoint.getLineSegment1();
				LineSegment[] AB = SL.insertSorted(currentSegment);
				LineSegment above = AB[0];
				LineSegment below = AB[1];
				if (DEBUG) System.out.println("just inserted, above: " + above + ", below: " + below);
				if (above != null){
					if (MatrixVectorFunctions.doesIntersect(above, currentSegment) && !(MatrixVectorFunctions.intersectionPoint(above, currentSegment).getX() < currentPoint.getX()-Math.pow(10, -8))){
						if (DEBUG) System.out.println("new intersect found. " + MatrixVectorFunctions.intersectionPoint(above, currentSegment));
						if (DEBUG) System.out.println("intersect is behind sweep line?: " + (MatrixVectorFunctions.intersectionPoint(above, currentSegment).getX() < currentPoint.getX()));
						eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(above, currentSegment), above, currentSegment, true, false));
					}
					else {
						if (DEBUG) System.out.println("Intersection not accepted");
					}
				}
				if (below != null){
					if (MatrixVectorFunctions.doesIntersect(below, currentSegment) && !(MatrixVectorFunctions.intersectionPoint(below, currentSegment).getX() < currentPoint.getX()-Math.pow(10, -8))){
						if (DEBUG) System.out.println("new intersect found: " + MatrixVectorFunctions.intersectionPoint(below, currentSegment));
						if (DEBUG) System.out.println("intersect is behind sweep line?: " + (MatrixVectorFunctions.intersectionPoint(below, currentSegment).getX() < currentPoint.getX()));
						eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(below, currentSegment), currentSegment, below, true, false));
					}
					else {
						if (DEBUG) System.out.println("Intersection not accepted");
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
						if (DEBUG) System.out.println("new intersect found: " + MatrixVectorFunctions.intersectionPoint(above, below));
						if (DEBUG) System.out.println("intersect is behind sweep line?: " + (MatrixVectorFunctions.intersectionPoint(above, below).getX() < currentPoint.getX()));
						eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(above, below), above, below, true, false));
					}
					else {
						if (DEBUG) System.out.println("Intersection not accepted");
					}
				}
			}
			else{ // the eventpoint must be intersection point
				if (DEBUG) System.out.println("intersection Point reached: (" + currentPoint.getX() + ","+currentPoint.getY()+")");
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
							if (DEBUG) System.out.println("new intersect found: " + MatrixVectorFunctions.intersectionPoint(aboveA, intersectB));
							if (DEBUG) System.out.println("intersect is behind sweep line?: " + (MatrixVectorFunctions.intersectionPoint(aboveA, intersectB).getX() < currentPoint.getX()));
							eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(aboveA, intersectB), aboveA, intersectB, true, false));
						}
						else {
							if (DEBUG) System.out.println("Intersection not accepted");
						}
					}
					if (belowB != null){
						//check for intersect between belowB and intersectB
						if (MatrixVectorFunctions.doesIntersect(intersectA, belowB) && !(MatrixVectorFunctions.intersectionPoint(intersectA, belowB).getX() < currentPoint.getX()-Math.pow(10, -8))){
							if (DEBUG) System.out.println("new intersect found: " + MatrixVectorFunctions.intersectionPoint(intersectA, belowB));
							if (DEBUG) System.out.println("intersect is behind sweep line?: " + (MatrixVectorFunctions.intersectionPoint(intersectA, belowB).getX() < currentPoint.getX()));
							eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(intersectA, belowB), intersectA, belowB, true, false));
						}
						else {
							if (DEBUG) System.out.println("Intersection not accepted");
						}
					}
				}
			}
			if (DEBUG) System.out.println("End of current point. EventList: " + eventList + "\n");
			if (DEBUG) System.out.println("Sweepline: " + SL + "\n");
			currentPoint = eventList.deQueue();
		}
		if (RESULT) System.out.println("number of intersection points: " + intersectionPointList.size());
		if (RESULT) System.out.println(intersectionPointList);
		
		SL = null;
		eventList = null;
		return intersectionPointList;
	}
}
