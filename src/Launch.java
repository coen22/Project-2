
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
    	
//    	new Launch("Coordinates.txt");
    	
    	LineSegment seg1 = new LineSegment(new Vertex(0, 0), new Vertex(10, 0));
    	LineSegment seg2 = new LineSegment(new Vertex(0, 0), new Vertex(0, 10));

    	
    	System.out.println("intersect exists: " + MatrixVectorFunctions.intersectExists(seg1, seg2));


    }

}
