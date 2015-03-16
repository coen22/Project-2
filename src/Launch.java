import java.util.Observable;

public class Launch extends Observable{

	public static void main(String[] args){
		
		
		PolyLine a = new PolyLine(new Node(new Vertex(0,0)));
		Vertex b = new Vertex(10,0);
		Vertex c = new Vertex(10,10);
		Vertex d = new Vertex(0,10);
		Vertex e = new Vertex(0,0);
		a.insertLast(b);
		a.insertLast(c);
		a.insertLast(d);
		a.insertLast(e);
		System.out.println(a.length());
		System.out.println(a.area());
	}

}