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
	
	public void insertSorted(LineSegment lineSegment){
		if (isEmpty()){
			list.add(lineSegment);
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
					list.add(counter, lineSegment);
				}
			}
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
