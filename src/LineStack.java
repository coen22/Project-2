import java.util.ArrayList;

public class LineStack {
	private ArrayList<LineSegment> list;
	
	public Integer size() {
		return list.size();
	}

	public boolean isEmpty() {
		return (list.size() == 0);
	}
	
	private void bubbleSortPush(LineSegment lineSegment){
		boolean continueSearch = true;
		int counter = 0;
		while(continueSearch){
			if (lineSegment.getPointA().getY() > list.get(counter).getPointA().getY()){
				counter++;
			}
			else {
				continueSearch = false;
				list.add(counter, lineSegment);
			}
		}
	}
	
	public String toString(){
		String string = "[";
		for (int i = 0; i < list.size(); i++){
			string = string + list.get(i) + "][";
		}
		return string;
	}

}
