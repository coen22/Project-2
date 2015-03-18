import java.util.ArrayList;


/**
 * 
 * @author David
 *
 */
public class SweepLine {
	
	private final boolean DEBUG = true;
	
	private AVLTree tree = new AVLTree();

	public Integer size() {
		return tree.size();
	}

	public boolean isEmpty() {
		return (tree.size() == 0);
	}
	
	public LineSegment[] swap(LineSegment intersectA, LineSegment intersectB, double x) {
		if (DEBUG) System.out.println("\nSwapping elements: " + intersectA + ", " + intersectB);
		LineSegment[] returnArray = {null,null};
		int counter = 0;
		while ( counter < tree.size() && intersectA.compareSLHeight((LineSegment) tree.get(counter), x) < 0){
			counter++;
		}
		if (counter + 1 < tree.size() && tree.get(counter+1) == intersectB){ // A is above B
			if (DEBUG) System.out.println("intersect B is below A"); 
			tree.set(counter, intersectB);
			tree.set(counter + 1, intersectA);
			if (counter-1>= 0){
				returnArray[0] = (LineSegment) tree.get(counter - 1);
				if (DEBUG) System.out.println("returning new above: " + returnArray[0]);
			}
			if (counter + 2 < tree.size()){
				returnArray[1] = (LineSegment) tree.get(counter + 2);
				if (DEBUG) System.out.println("returning new below: " + returnArray[1]);
			}
		}
		else if (counter - 1 >= 0 && tree.get(counter-1) == intersectB){ // B is above A, this case should not happen
			if (DEBUG) System.out.println("intersect B is above A");
			tree.set(counter, intersectB);
			tree.set(counter-1, intersectA);
			if (counter-2>= 0){
				returnArray[0] = (LineSegment) tree.get(counter - 2);
				if (DEBUG) System.out.println("returning new above: " + returnArray[0]);
			}
			if (counter + 1 < tree.size()){
				returnArray[1] = (LineSegment) tree.get(counter + 1);
				if (DEBUG) System.out.println("returning new below: " + returnArray[1]);
			}
		}		
		return returnArray;
	}

	public LineSegment[] delete(LineSegment lineSegment, double x){
		if (DEBUG) System.out.println("\nDeleting: " + lineSegment);
		LineSegment[] returnArray = {null,null};
		int counter = 0;
		
		while ( counter < tree.size() && lineSegment.compareSLHeight((LineSegment) tree.get(counter), x) < 0) {
			counter++;
		}
		
		if (counter - 1 >= 0){
			if (DEBUG) System.out.println("returning new above: " + tree.get(counter-1));
			returnArray[0] = (LineSegment) tree.get(counter - 1);
		}
		if (counter + 1 < tree.size()){
			if (DEBUG) System.out.println("returning new below: " + tree.get(counter+1));
			returnArray[1] = (LineSegment) tree.get(counter + 1);
		}
		tree.remove(counter);
		return returnArray;
	}

	public LineSegment[] insertSorted(LineSegment lineSegment, double x){
		if (DEBUG) System.out.println("\nEntering: " + lineSegment);
		LineSegment[] returnArray = {null,null};
		if (isEmpty()){
			tree.insert(new AVLNode(lineSegment));
			return returnArray;
		}
		else{
			int counter = 0;
			while ( counter < tree.size() && lineSegment.compareSLHeight((LineSegment) tree.get(counter), x) < 0){
				counter++;
			}
			tree.insert(new AVLNode(lineSegment));
			if (counter - 1 >= 0){
				if (DEBUG) System.out.println("returning above: " + tree.get(counter-1));
				returnArray[0] = (LineSegment) tree.get(counter-1);
			}
			if (counter + 1 < tree.size()){
				if (DEBUG) System.out.println("returning below: " + tree.get(counter+1));
				returnArray[1] = (LineSegment) tree.get(counter+1);
			}
			return returnArray;
		}
	}

	public String toString(){
		String string = "";
		for (int i = 0; i < tree.size(); i++){
			string = string +"["+ tree.get(i) + "]";
		}
		return string;
	}

}
