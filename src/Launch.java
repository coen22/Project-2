
import java.util.ArrayList;
import java.util.Observable;

public class Launch extends Observable {

	private ArrayList<PolyLine> listOfPolyLine = new ArrayList<PolyLine>();

	public Launch() {
		PolyLine a = new PolyLine(new Vertex(300, 300));
		Vertex b = new Vertex(300, 600);
		Vertex c = new Vertex(600, 600);
		Vertex d = new Vertex(573, 462);

		Vertex e = new Vertex(23, 98);

		a.insertLast(b);
		a.insertLast(c);
		a.insertLast(d);
		a.insertLast(e);
		a.closeLine();
		System.out.println(a);

		listOfPolyLine.add(a);
	}

	//single line
	public Launch(String fileName) {
		FileHandler handler = new FileHandler(fileName);
		Double[][] values = handler.getValuesFromFile();    	 	
		PolyLine line = new PolyLine(new Vertex(values[0][0], values[1][0])); 	
		Vertex a = new Vertex(0,0);
		for (int i = 1; i<values[0].length; i++){
			if (values[0][i] != null && values[1][i] != null){
				a = new Vertex(values[0][i], values[1][i]);
				line.insertLast(a);
			}
		} 	
	}

	public ArrayList<PolyLine> getListOfPolyLine() {
		return listOfPolyLine;
	}

	public void setListOfPolyLine(ArrayList<PolyLine> listOfPolyLine) {
		this.listOfPolyLine = listOfPolyLine;
	}

	public static void main(String[] args) {
		PolyLine line = new PolyLine(new Vertex(0, 0));
		Vertex b = new Vertex(10, 0);
		Vertex c = new Vertex(10, 10);
		Vertex d = new Vertex(0, 10);
		Vertex e = new Vertex(0, 0);
		line.insertLast(b);
		line.insertLast(c);
		line.insertLast(d);
		line.insertLast(e);

		LineSegment lineseg1 = new LineSegment(new Vertex(0, 0), new Vertex(5, 0));
		LineSegment lineseg2 = new LineSegment(new Vertex(0, 10), new Vertex(5, 10));
		LineSegment lineseg3 = new LineSegment(new Vertex(0, 20), new Vertex(5, 20));
		LineSegment lineseg4 = new LineSegment(new Vertex(0, 30), new Vertex(5, 30));
		LineSegment lineseg5 = new LineSegment(new Vertex(0, 40), new Vertex(5, 40));

		SweepLine SL = new SweepLine();
		SL.insertSorted(lineseg3);
		System.out.println(SL);
		SL.insertSorted(lineseg1);
		System.out.println(SL);
		SL.insertSorted(lineseg2);
		System.out.println(SL);
		SL.insertSorted(lineseg5);
		System.out.println(SL);
		SL.insertSorted(lineseg4);
		System.out.println(SL);

	}

}
