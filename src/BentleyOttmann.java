import java.util.ArrayList;

/**
 * 
 * @author David
 *
 */
public class BentleyOttmann {
	
	private final static boolean DEBUG = true;
	
	public boolean polyLinesIntersecting(PolyLine line1, PolyLine line2){
		return false;
	}

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
					if (MatrixVectorFunctions.doesIntersect(above, currentSegment) && !(MatrixVectorFunctions.intersectionPoint(above, currentSegment).getX() < currentPoint.getX()-Math.pow(10, -8))){
						if (DEBUG) System.out.println("new intersect found. " + MatrixVectorFunctions.intersectionPoint(above, currentSegment));
						if (DEBUG) System.out.println("intersect is behind sweep line?: " + (MatrixVectorFunctions.intersectionPoint(above, currentSegment).getX() < currentPoint.getX()));
						eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(above, currentSegment), above, currentSegment, true, false));
					}
					else {
						System.out.println("Intersection not accepted");
					}
				}
				if (below != null){
					if (MatrixVectorFunctions.doesIntersect(below, currentSegment) && !(MatrixVectorFunctions.intersectionPoint(below, currentSegment).getX() < currentPoint.getX()-Math.pow(10, -8))){
						if (DEBUG) System.out.println("new intersect found: " + MatrixVectorFunctions.intersectionPoint(below, currentSegment));
						if (DEBUG) System.out.println("intersect is behind sweep line?: " + (MatrixVectorFunctions.intersectionPoint(below, currentSegment).getX() < currentPoint.getX()));
						eventList.insertSorted(new EventPoint(MatrixVectorFunctions.intersectionPoint(below, currentSegment), currentSegment, below, true, false));
					}
					else {
						System.out.println("Intersection not accepted");
					}
				}
			}
			else if (currentPoint.isLeftPoint() == false && currentPoint.isIntersectionPoint() == false){
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
						System.out.println("Intersection not accepted");
					}
				}
			}
			else{ // must be intersection point
				if (DEBUG) System.out.println("intersection Point reached: (" + currentPoint.getX() + ","+currentPoint.getY()+")");
				intersectionPointList.add(new Vertex(currentPoint.getX(),currentPoint.getY()));
				LineSegment intersectA = currentPoint.getLineSegment1();
				LineSegment intersectB = currentPoint.getLineSegment2();
				LineSegment[] AB = SL.swap(intersectA, intersectB);
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
							System.out.println("Intersection not accepted");
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
							System.out.println("Intersection not accepted");
						}
					}
				}
			}
			if (DEBUG) System.out.println("End of current point. EventList: " + eventList + "\n");
			if (DEBUG) System.out.println("Sweepline: " + SL + "\n");
			currentPoint = eventList.deQueue();
		}
		
		System.out.println("number of intersection points: " + intersectionPointList.size());
		System.out.println(intersectionPointList);
		return intersectionPointList;
	}
}
