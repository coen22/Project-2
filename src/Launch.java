
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

    public Launch(String fileName) {
    	FileHandler handler = new FileHandler(fileName);
    	Double[][] values = handler.getValuesFromFile();
    	
    	PolyLine line = new PolyLine(new Vertex(values[0][0], values[1][0]));
    	Vertex a = new Vertex(0,0);
    	
    	listOfPolyLine.add(line);
    	
		System.out.println(listOfPolyLine);
    }
    
    public ArrayList<PolyLine> getListOfPolyLine() {
        return listOfPolyLine;
    }

    public void setListOfPolyLine(ArrayList<PolyLine> listOfPolyLine) {
        this.listOfPolyLine = listOfPolyLine;
    }
    
    public static void main(String[] args) {

//        PolyLine a = new PolyLine(new Vertex(0, 0));
//        Vertex b = new Vertex(10, 0);
//        Vertex c = new Vertex(10, 10);
//        Vertex d = new Vertex(0, 10);
//        Vertex e = new Vertex(0, 0);
//        a.insertLast(b);
//        a.insertLast(c);
//        a.insertLast(d);
//        a.insertLast(e);
////		System.out.println(a.length());
////	    System.out.println(a.area());
//        System.out.println(a);
//        LineSegmentList<LineSegment> segList = new LineSegmentList<LineSegment>(a);
//        System.out.println(segList);
    	
    	new Launch("Coordinates.txt");


    }

}
