
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
/**
 * This class implements the Engine needed to run the program.
 * @author Group 1: Kareem, Marciano, Brian, David, Coen
 * @version 2.0
 */
public class Engine extends Observable {

    private ArrayList<PolyLine> listOfPolyLine = new ArrayList<PolyLine>();
    private int last;
    private PolyLine line;
    /**
     * Empty constructor
     */
    public Engine() {

    }
    /**
     * Constructor used to import PolyLines from file
     * @param fileName	The desired fileName + ".txt"
     */
    public Engine(String fileName) {
        last = 0; // last is the index at which the last null value was found
        FileHandler handler = new FileHandler(fileName);
        Double[][] values = handler.getValuesFromFile();
        for (int i = 1; i < values[0].length; i++) {
            if (values[0][i] == null) {
                createFromFile(values, i);
            }
        }
    }
    /**
     * Method used to import polylines from file to be drawn into the interface.
     * @param file
     */
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
    
    /**
     * Creates the PolyLines from the Double 2 x m matrix received from the file.
     * @param data	The Double matrix received from reading the file.
     * @param index	The current index in the double matrix: [2][index]
     */
    private void createFromFile(Double[][] data, int index) {
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


 

}
