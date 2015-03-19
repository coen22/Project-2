import java.util.TreeMap;


public class SweepLineRB {
	
	private final boolean DEBUG = true;
	private TreeMap<LineSegment, LineSegment> treeList;

	public SweepLineRB(){
		treeList = new TreeMap<LineSegment, LineSegment>();
	}
	
	public void swap(LineSegment intersectA, LineSegment intersectB) {
		if (DEBUG) System.out.println("\n-----------------------------------------------------------\nSwapping elements: " + intersectA + ", " + intersectB);
		LineSegment[] returnArray = {null,null};
		int counter = 0;
		
		treeList.put(intersectA, intersectB);
		treeList.put(intersectB, intersectA);
		
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
	
	public void delete(LineSegment lineSegment){
		if (DEBUG) System.out.println("\nDeleting: " + lineSegment);
		LineSegment[] returnArray = {null,null};

		treeList.remove(lineSegment);
		
//		if (counter - 1 >= 0){
//			if (DEBUG) System.out.println("returning new above: " + list.get(counter-1));
//			returnArray[0] = list.get(counter-1);
//		}
//		if (counter + 1 < list.size()){
//			if (DEBUG) System.out.println("returning new below: " + list.get(counter+1));
//			returnArray[1] = list.get(counter+1);
//		}
//		
//		list.remove(counter);
//		return returnArray;
		
		System.out.println(treeList);
	}
	
	public void insertSorted(LineSegment lineSegment){
		if (DEBUG) System.out.println("\nEntering: " + lineSegment);
		double x = lineSegment.getA().getX();
		LineSegment[] returnArray = {null,null};
		if (isEmpty()){
			treeList.put(lineSegment, lineSegment);
//			return returnArray;
		}
		else{
			treeList.put(lineSegment, lineSegment);
			
//			if (counter - 1 >= 0){
//				if (DEBUG) System.out.println("returning above: " + list.get(counter-1));
//				returnArray[0] = list.get(counter-1);
//			}
//			if (counter + 1 < list.size()){
//				if (DEBUG) System.out.println("returning below: " + list.get(counter+1));
//				returnArray[1] = list.get(counter+1);
//			}
//			return returnArray;
		}
		
		System.out.println(treeList);
	}



	private boolean isEmpty() {
		return (treeList.size() == 0);
	}
}
