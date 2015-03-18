import java.util.ArrayList;


/**
 * 
 * @author David
 *
 */
public class SweepLine {
	
	private final boolean DEBUG = true;
	
	private ArrayList<LineSegment> list;

	public SweepLine(){
		list = new ArrayList<LineSegment>();
	}

	public Integer size() {
		return list.size();
	}

	public boolean isEmpty() {
		return (list.size() == 0);
	}
	
	public LineSegment[] swap(LineSegment intersectA, LineSegment intersectB, double x) {
		if (DEBUG) System.out.println("\nSwapping elements: " + intersectA + ", " + intersectB);
		LineSegment[] returnArray = {null,null};
		int counter = 0;
		while ( counter < list.size() && intersectA.compareSLHeight(list.get(counter), x) > 0){
			counter++;
		}
		if (counter + 1 < list.size() && list.get(counter+1) == intersectB){ // A is above B
			if (DEBUG) System.out.println("intersect B is below A"); 
			list.set(counter, intersectB);
			list.set(counter+1, intersectA);
			if (counter-1>= 0){
				returnArray[0] = list.get(counter-1);
				if (DEBUG) System.out.println("returning new above: " + returnArray[0]);
			}
			if (counter + 2 < list.size()){
				returnArray[1] = list.get(counter+2);
				if (DEBUG) System.out.println("returning new below: " + returnArray[1]);
			}
		}
		else if (counter - 1 >= 0 && list.get(counter-1) == intersectB){ // B is above A, this case should not happen
			if (DEBUG) System.out.println("intersect B is above A");
			list.set(counter, intersectB);
			list.set(counter-1, intersectA);
			if (counter-2>= 0){
				returnArray[0] = list.get(counter-2);
				if (DEBUG) System.out.println("returning new above: " + returnArray[0]);
			}
			if (counter + 1 < list.size()){
				returnArray[1] = list.get(counter+1);
				if (DEBUG) System.out.println("returning new below: " + returnArray[1]);
			}
		}		
		return returnArray;
	}

	public LineSegment[] delete(LineSegment lineSegment, double x){
		if (DEBUG) System.out.println("\nDeleting: " + lineSegment);
		LineSegment[] returnArray = {null,null};
		int counter = 0;
		
		while ( counter < list.size() && lineSegment.compareSLHeight(list.get(counter), x) > 0){
			counter++;
		}
		if (DEBUG) System.out.println("counter: " + counter);
		
		if (counter - 1 >= 0){
			if (DEBUG) System.out.println("returning new above: " + list.get(counter-1));
			returnArray[0] = list.get(counter-1);
		}
		if (counter + 1 < list.size()){
			if (DEBUG) System.out.println("returning new below: " + list.get(counter+1));
			returnArray[1] = list.get(counter+1);
		}
		
		list.remove(counter);
		return returnArray;
	}

	public LineSegment[] insertSorted(LineSegment lineSegment, double x){
		if (DEBUG) System.out.println("\nEntering: " + lineSegment);
		LineSegment[] returnArray = {null,null};
		if (isEmpty()){
			list.add(lineSegment);
			return returnArray;
		}
		else{
			int counter = 0;
			while ( counter < list.size()-1 && lineSegment.compareSLHeight(list.get(counter), x) > 0){
				counter++;
			}
			list.add(counter, lineSegment);
			if (counter - 1 >= 0){
				if (DEBUG) System.out.println("returning above: " + list.get(counter-1));
				returnArray[0] = list.get(counter-1);
			}
			if (counter + 1 < list.size()){
				if (DEBUG) System.out.println("returning below: " + list.get(counter+1));
				returnArray[1] = list.get(counter+1);
			}
			return returnArray;
		}
	}

	public String toString(){
		String string = "";
		for (int i = 0; i < list.size(); i++){
			string = string +"["+ list.get(i) + "]";
		}
		return string;
	}

}
