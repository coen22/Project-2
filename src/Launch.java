import java.util.Observable;

public class Launch extends Observable{

	public static void main(String[] args){
		
		LineSegment testsegment1 = new LineSegment(new Vertex(0, 0), new Vertex(100, 100));
		LineSegment testsegment2 = new LineSegment(new Vertex(100, 100), new Vertex(0, 0));
		LineSegment testsegment3 = new LineSegment(new Vertex(10, 10), new Vertex(50, 50));
		
		LineStack stack1 = new LineStack();
		
		stack1.bubbleSortPush(testsegment2);
		System.out.println(stack1);
		stack1.bubbleSortPush(testsegment1);
		System.out.println(stack1);
	}

}