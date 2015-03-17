
import java.util.ArrayList;
import java.util.Observable;

public class Launch extends Observable {

    private ArrayList<PolyLine> listOfPolyLine = new ArrayList();

    public Launch() {
        PolyLine a = new PolyLine(new Vertex(0, 0));
        Vertex b = new Vertex(100, 0);
        Vertex c = new Vertex(100, 100);
        Vertex d = new Vertex(0, 100);
        Vertex e = new Vertex(594, 38);
        Vertex f = new Vertex(433, 394);
        
        a.insertLast(b);
        a.insertLast(c);
        a.insertLast(d);
        a.insertLast(e);
        a.insertLast(f);
        
        
        listOfPolyLine.add(a);
    }

    public ArrayList<PolyLine> getListOfPolyLine() {
        return listOfPolyLine;
    }

    public void setListOfPolyLine(ArrayList<PolyLine> listOfPolyLine) {
        this.listOfPolyLine = listOfPolyLine;
    }

    
    
    public static void main(String[] args) {

        PolyLine a = new PolyLine(new Vertex(0, 0));
        Vertex b = new Vertex(10, 0);
        Vertex c = new Vertex(10, 10);
        Vertex d = new Vertex(0, 10);
        a.insertLast(b);
        a.insertLast(c);
        a.insertLast(d);
        System.out.println(a.isClosed());
        a.closeLine();
        System.out.println(a.isClosed());
//		System.out.println(a.length());
//      System.out.println(a.area());
        System.out.println(a);
        
        LineSegmentList<LineSegment> segList = new LineSegmentList<LineSegment>(a);
        System.out.println(segList);
    }

}
