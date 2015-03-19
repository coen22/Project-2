
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

public class Launch extends Observable {

    private ArrayList<PolyLine> listOfPolyLine = new ArrayList<PolyLine>();
    private int last;
    private PolyLine line;

    public Launch() {
//        PolyLine a = new PolyLine(new Vertex(450, 150));
//        Vertex c = new Vertex(600, 600);
//        Vertex d = new Vertex(573, 462);
//
//        Vertex e = new Vertex(23, 98);
//
//        for (int i = 0; i < 360; i++) {
//            if (((2 * Math.PI) / i) == Math.PI / 2) {
//                a.insertLast(new Vertex(150, 1 * 400 + 150));
//            } else if (((2 * Math.PI) / i) == Math.PI) {
//                a.insertLast(new Vertex(400 * -1 + 150, 0 * 400 + 150));
//            } else if (((2 * Math.PI) / i) == 3 / 2 * Math.PI) {
//                a.insertLast(new Vertex(400 * -1 + 150, -1 * 400 + 150));
//            } else if (((2 * Math.PI) / i) == 2 * Math.PI) {
//                a.insertLast(new Vertex(400 * 0 + 150, 0 * 400 + 150));
//            } else if (((2 * Math.PI) / i) > Math.PI / 2) {
//                a.insertLast(new Vertex(Math.cos((2 * Math.PI) / i) * 400 + 150, Math.sin((2 * Math.PI) / i) * 400 + 150));
//            } else if (((2 * Math.PI) / i) > Math.PI) {
//                a.insertLast(new Vertex(-Math.cos((2 * Math.PI) / i) * 400 + 150, Math.sin((2 * Math.PI) / i) * 400 + 150));
//            } else if (((2 * Math.PI) / i) > 3 / 2 * Math.PI) {
//                a.insertLast(new Vertex(-Math.cos((2 * Math.PI) / i) * 400 + 150, -Math.sin((2 * Math.PI) / i) * 400 + 150));
//            } else if (((2 * Math.PI) / i) > 3 / 2 * Math.PI) {
//                a.insertLast(new Vertex(Math.cos((2 * Math.PI) / i) * 400 + 150, -Math.sin((2 * Math.PI) / i) * 400 + 150));
//            }
//        }
//        a.insertLast(c);
//        a.insertLast(d);
//        a.insertLast(e);
//        a.closeLine();
//        System.out.println(a);

//        listOfPolyLine.add(a);
    }

    //single line - EDIT: now supports multi line!
    public Launch(String fileName) {
        last = 0; // last is the index at which the last null value was found
        FileHandler handler = new FileHandler(fileName);
        Double[][] values = handler.getValuesFromFile();
        for (int i = 1; i < values[0].length; i++) {
            if (values[0][i] == null) {
                createFromFile(values, i);
            }
        }
    }

    public void createPolyLineFromFile(File file) {
        last = 0; // last is the index at which the last null value was found
        FileHandler handler = new FileHandler(file.getPath());
        Double[][] values = handler.getValuesFromFile();
        for (int i = 1; i < values[0].length; i++) {
            if (values[0][i] == null) {
                createFromFile(values, i);
            }
        }
    }

    public void createFromFile(Double[][] data, int index) {
        PolyLine temp = new PolyLine(new Vertex(data[0][last]*100, data[1][last]*100));
        Vertex vertex;
        for (int y = last + 1; y < index; y++) {
            vertex = new Vertex(data[0][y] * 100, data[1][y] * 100);
            temp.insertLast(vertex);
            line = temp;
        }
        last = index + 1;
        System.out.println(line);
        listOfPolyLine.add(line);
    }

    public ArrayList<PolyLine> getListOfPolyLine() {
        return listOfPolyLine;
    }

    public void setListOfPolyLine(ArrayList<PolyLine> listOfPolyLine) {
        this.listOfPolyLine = listOfPolyLine;
    }

    public static void main(String[] args) {
//		PolyLine line = new PolyLine(new Vertex(0, 0));
//		Vertex b = new Vertex(10, 0);
//		Vertex c = new Vertex(10, 10);
//		Vertex d = new Vertex(0, 10);
//		Vertex e = new Vertex(0, 0);
//		line.insertLast(b);
//		line.insertLast(c);
//		line.insertLast(d);
//		line.insertLast(e);
//
//		LineSegment lineseg1 = new LineSegment(new Vertex(0, 0), new Vertex(10, 10));
//		LineSegment lineseg2 = new LineSegment(new Vertex(0, 10), new Vertex(10, 0));
//		LineSegment lineseg3 = new LineSegment(new Vertex(0, 20), new Vertex(5, 20));
//		LineSegment lineseg4 = new LineSegment(new Vertex(0, 30), new Vertex(5, 30));
//		LineSegment lineseg5 = new LineSegment(new Vertex(0, 40), new Vertex(5, 40));
//		
        Launch l = new Launch("Coordinates.txt");
//		SweepLine SL = new SweepLine();
//		SL.insertSorted(lineseg3);
//s		SystemF.out.println(SL);
//		SL.insertSorted(lineseg1);
//		System.out.println(SL);
//		SL.insertSorted(lineseg2);
//		System.out.println(SL);
//		SL.insertSorted(lineseg5);
//		System.out.println(SL);
//		SL.insertSorted(lineseg4);
//		System.out.println(SL);

    }

}
