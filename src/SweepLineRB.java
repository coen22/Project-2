import java.util.TreeMap;


public class SweepLineRB {
	
	private final boolean DEBUG = false;
	private TreeMap<LineSegment, LineSegment> treeList;

	public SweepLineRB(){
		treeList = new TreeMap<LineSegment, LineSegment>();
	}
	
	public void swap(LineSegment intersectA, LineSegment intersectB, double x) {
		LineSegment[] returnArray = {null,null};
		
		treeList.remove(intersectA);
		intersectA.setComparisonXValue(x);
		intersectB.setComparisonXValue(x);
		treeList.put(intersectA, intersectA);
		
		System.out.println("\n-------------------------------------");
		System.out.println(treeList);
		System.out.println("intersectA: " + intersectA + ". intersectB: " + intersectB);
		System.out.println("higherKey: " + treeList.higherKey(intersectB));
		System.out.println("lowerKey: " + treeList.lowerKey(intersectA));
		System.out.println(treeList);
		System.out.println("-------------------------------------\n");
		
//		while (counter < list.size() && intersectA.equals(list.get(counter)) != true){
//			counter++;
//		}
//		if (DEBUG) System.out.println("counter: " + counter);
//		if (counter >= list.size()){
//			return returnArray;
//		}
//		if (counter + 1 < list.size() && list.get(counter+1) == intersectB){ // A is above B
//			if (DEBUG) System.out.println("intersect B is below A"); 
//			list.set(counter, intersectB);
//			list.set(counter+1, intersectA);
//			if (counter-1>= 0){
//				returnArray[0] = list.get(counter-1);
//				if (DEBUG) System.out.println("returning new above: " + returnArray[0]);
//			}
//			if (counter + 2 < list.size()){
//				returnArray[1] = list.get(counter+2);
//				if (DEBUG) System.out.println("returning new below: " + returnArray[1]);
//			}
//		}
//		else if (counter - 1 >= 0 && list.get(counter-1) == intersectB){ // B is above A, this case should not happen
//			if (DEBUG) System.out.println("intersect B is above A");
//			list.set(counter, intersectB);
//			list.set(counter-1, intersectA);
//			if (counter-2>= 0){
//				returnArray[0] = list.get(counter-2);
//				if (DEBUG) System.out.println("returning new above: " + returnArray[0]);
//			}
//			if (counter + 1 < list.size()){
//				returnArray[1] = list.get(counter+1);
//				if (DEBUG) System.out.println("returning new below: " + returnArray[1]);
//			}
//		}		
//		return returnArray;
		
		System.out.println(treeList);
	}
	
	public LineSegment[] delete(LineSegment lineSegment){
		if (DEBUG) System.out.println("\nDeleting: " + lineSegment);
		LineSegment[] returnArray = {null,null};

		returnArray[0] = treeList.higherKey(lineSegment);
		returnArray[1] = treeList.lowerKey(lineSegment);
		
		treeList.remove(lineSegment);
		
		System.out.println(treeList);
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
		
		System.out.println(treeList);
		
		return returnArray;
	}



	private boolean isEmpty() {
		return (treeList.size() == 0);
	}
}
