import java.util.TreeMap;

/**
 * 
 * @author Group 1: Kareem, Marciano, Brian, David, Coen 
 * @version 3.0
 *
 */
public class SweepLineRB {
	
	private TreeMap<LineSegment, LineSegment> sweepLine;

	/**
	 * Constructor for an empty SweepLine
	 */
	public SweepLineRB(){
		sweepLine = new TreeMap<LineSegment, LineSegment>();
	}
	
	/**
	 * Method used when two line segments intersect and thus must be swapped
	 * @param intersectA the segment above prior to the intersection
	 * @param intersectB the segment below prior to the intersection
	 * @param x an x-variable immediately (10^-8) behind the intersection point, data used to update the x in the line-segments so they can be re-ordered correctly
	 * @return the line segments above the flipped segments. Hence the segment above the now, upper intersecting segment and the segment below the now, lower intersecting segment
	 */
	public LineSegment[] swap(LineSegment intersectA, LineSegment intersectB, double x) {
		x = x + Math.pow(10, -8);
		
		//array that provides information to the algorithm which new line-segments meet or must be checked for intersects. Index 0 is the higher segment, 1 the new lower element.
		LineSegment[] returnArray = {null,null};
		
		sweepLine.remove(intersectA);
		intersectA.setComparisonXValue(x);
		intersectB.setComparisonXValue(x);
		sweepLine.put(intersectA, intersectA);
		
		returnArray[0] = sweepLine.higherKey(intersectB);
		returnArray[1] = sweepLine.lowerKey(intersectA);
		
		return returnArray;
	}
	
	/**
	 * Deletes the given segment and returns new neighbours
	 * @param lineSegment segment to be deleted
	 * @return 	the segments above and below the deleted lines, now neighbours
	 */
	public LineSegment[] delete(LineSegment lineSegment){
		//array that provides information to the algorithm which new line-segments meet or must be checked for intersects. Index 0 is the higher segment, 1 the new lower element.
		LineSegment[] returnArray = {null,null};

		returnArray[0] = sweepLine.higherKey(lineSegment);
		returnArray[1] = sweepLine.lowerKey(lineSegment);
		
		sweepLine.remove(lineSegment);
		
		return returnArray;
	}
	
	/**
	 * inserts a new line-segment into the sweep-line. automatically determines the x-value for which to compare the segment to others
	 * @param lineSegment the segment to be inserted
	 * @return the segments immediately above and below the newly inserted line.
	 */
	public LineSegment[] insertSorted(LineSegment lineSegment){
		//array that provides information to the algorithm which new line-segments meet or must be checked for intersects. Index 0 is the higher segment, 1 the new lower element.
		LineSegment[] returnArray = {null,null};
		
		lineSegment.setComparisonXValue(lineSegment.getA().getX());
		sweepLine.put(lineSegment, lineSegment);
		
		returnArray[0] = sweepLine.higherKey(lineSegment);
		returnArray[1] = sweepLine.lowerKey(lineSegment);
		
		return returnArray;
	}
	
	/**
	 * toString method for SweepLine
	 */
	public String toString(){
		return sweepLine.toString();
	}
}
