import java.util.ArrayList;

/**
 * 
 * @author David
 *
 */
public class SweepLine {
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
	
	public LineSegment[] delete(LineSegment lineSegment){
		LineSegment[] returnArray = {null,null};
		if (isEmpty()){
			return returnArray;
		}
		else{
			boolean continueSearch = true;
			int counter = 0;
			while(continueSearch){
				if (lineSegment.getA().getY() > list.get(counter).getA().getY()){
					counter++;
				}
				else {
					continueSearch = false;
				}
			}
			if (counter - 1 >= 0){
				returnArray[0] = list.get(counter-1);
			}
			if (counter + 1 < list.size()){
				returnArray[1] = list.get(counter+1);
			}
			list.remove(counter);
			return returnArray;
		}
	}
	
	public LineSegment[] insertSorted(LineSegment lineSegment){
		LineSegment[] returnArray = {null,null};
		if (isEmpty()){
			list.add(lineSegment);
			return returnArray;
		}
		else{
			boolean continueSearch = true;
			int counter = 0;
			while(continueSearch){
				if (counter < list.size() && lineSegment.getA().getY() > list.get(counter).getA().getY()){
					counter++;
				}
				else {
					continueSearch = false;
					list.add(counter, lineSegment);
				}
			}
			if (counter - 1 >= 0){
				returnArray[0] = list.get(counter-1);
			}
			if (counter + 1 < list.size()){
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
