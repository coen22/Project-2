import java.util.ArrayList;


public class LineStack {
	private ArrayList<LineSegment> list;
	
	public void push(LineSegment lineSegment) {
		list.add(0, lineSegment);
	}

	public Integer size() {
		return list.size();
	}

	public boolean isEmpty() {
		return (list.size() == 0);
	}
	
	private void bubbleSortPush(LineSegment lineSegment){
		boolean continueSearch = true;
		while(continueSearch){
			
		}
	}

}
