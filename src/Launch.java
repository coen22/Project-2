import java.util.Observable;

public class Launch extends Observable{

	public static void main(String[] args){
		PolyLine<Vertex> test = new PolyLine<Vertex>();
		
		System.out.println(test);
		
		test.insertLast(new Vertex(10, 10));
		
		System.out.println(test);
	}

}