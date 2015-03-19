import java.util.TreeMap;


public class SweepLineRB {
	
	private final boolean DEBUG = false;
	private TreeMap<LineSegment, LineSegment> treeList;

	public SweepLineRB(){
		treeList = new TreeMap<LineSegment, LineSegment>();
	}
	
	public LineSegment[] swap(LineSegment intersectA, LineSegment intersectB, double x) {
		if (DEBUG) System.out.println("swapping line " + intersectA + " with line " + intersectB);
		x = x + Math.pow(10, -8);
		
		//array that provides information to the algorithm which new line-segments meet or must be checked for intersects. Index 0 is the higher segment, 1 the new lower element.
		LineSegment[] returnArray = {null,null};
		
		treeList.remove(intersectA);
		intersectA.setComparisonXValue(x);
		intersectB.setComparisonXValue(x);
		treeList.put(intersectA, intersectA);
		
		returnArray[0] = treeList.higherKey(intersectB);
		returnArray[1] = treeList.lowerKey(intersectA);
		
		if (DEBUG) System.out.println(treeList);
		return returnArray;
	}
	
	public LineSegment[] delete(LineSegment lineSegment){
		if (DEBUG) System.out.println("\nDeleting: " + lineSegment);
		
		//array that provides information to the algorithm which new line-segments meet or must be checked for intersects. Index 0 is the higher segment, 1 the new lower element.
		LineSegment[] returnArray = {null,null};

		returnArray[0] = treeList.higherKey(lineSegment);
		returnArray[1] = treeList.lowerKey(lineSegment);
		
		treeList.remove(lineSegment);
		
		if (DEBUG) System.out.println(treeList);
		return returnArray;
	}
	
	/**
	 * inserts a new line-segment into the sweep-line. automatically determines the x-value for which to compare the segment to others
	 * @param lineSegment the segment to be inserted
	 * @return 
	 */
	public LineSegment[] insertSorted(LineSegment lineSegment){
		if (DEBUG) System.out.println("\nEntering: " + lineSegment);
		LineSegment[] returnArray = {null,null};
		
		lineSegment.setComparisonXValue(lineSegment.getA().getX());
		treeList.put(lineSegment, lineSegment);
		
		returnArray[0] = treeList.higherKey(lineSegment);
		returnArray[1] = treeList.lowerKey(lineSegment);
		
		if (DEBUG)System.out.println(treeList);
		return returnArray;
	}



	private boolean isEmpty() {
		return (treeList.size() == 0);
	}
}
