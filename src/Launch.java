
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
        
    	PolyLine line = new PolyLine(new Vertex(0,0));
    	
    	FileHandler handler = new FileHandler("Coordinates.txt");
    	Double[][] values = handler.getValuesFromFile();
    	
    	Vertex a = new Vertex(0,0);
		for (int y = 0; y<values[0].length; y++){
			if (values[0][y] != null && values[1][y] != null){
				a = new Vertex(values[0][y], values[1][y]);
				line.insertLast(a);
			}
		}
		System.out.println(line);
		
		LineSegmentList<LineSegment> segList = new LineSegmentList<LineSegment>(line);
      	System.out.println(segList);

    }

}
