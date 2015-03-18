import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This program handles every txt file in which the data of the
 * coordinates are stored.
 * @author Brian
 */
public class FileHandler {
	
	private String fileName;
	private String[] fileIn;
	private Double[][] doubleIn, values;
	private FileWriter writer;
	private BufferedWriter writerB;
	private FileReader reader;
	private BufferedReader readerB;
	private int x, y, last;
	/**
	 * Constructor to initialize the name of the .txt file.
	 * @param fileName	A String value containing the name of the text file.
	 */
	public FileHandler(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * Removes the line in the textfile by overwriting every separate line
	 * by nothing (i.e. "").
	 */
	public void deleteFile() {
		try {
			writer = new FileWriter(fileName);
			writerB = new BufferedWriter(writer);
			writerB.write("");
			writerB.close();
		} catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
	}
	/**
	 * Writes the given String value in the text file. Every time this method
	 * is called, a new line will be written.
	 * @param data	The String value that has to be written.
	 */
	public void writeFile(String data) {
		try {
			writer = new FileWriter(fileName, true);
		    writerB = new BufferedWriter(writer);
            writerB.write(data);
            writerB.newLine();
            writerB.close();
        } catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
	}
	/**
	 * Reads the text file and stores the data in a String array.
	 * @return	A String array containing all coordinates data.
	 */
	private String[] readFile() {
		String line = null;
		String[] temp = new String[1000];
		int i = 0;
		try {
            reader = new FileReader(fileName);
            readerB = new BufferedReader(reader);       
            while((line = readerB.readLine()) != null) {
            	temp[i++] = line;
            }
            fileIn = new String[i];
            System.arraycopy(temp, 0, fileIn, 0, fileIn.length);
            readerB.close();      
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                   
        }
        return fileIn;
	}
	/**
	 * Reads the coordinate values (x and y) and stores it in a Double
	 * matrix. The Double matrix is first stored in a temporary array and
	 * then copied to an array of the correct size (the largest size
	 * of one entry of the String array).
	 * @return A Double array containing all data.
	 */
	private Double[][] readFileToDouble() {
		String[] str = readFile();
		Double[][] temp = new Double[1000][1000];
		for (int i = 0; i<str.length; i++) {
			x = 0;
			y++;
			if (str[i] != null) {
				Matcher m = Pattern.compile("(?!=\\d\\.\\d\\.)([\\d.]+)").matcher(str[i]);
				while(m.find()) {
					double d = Double.parseDouble(m.group(1));
					temp[x++][i] = d;
					if (x  > last)
						last = x;
				}
			}
		}
		doubleIn = new Double[last][y];
		for(int a=0; a<last; a++) {
			for(int b=0; b<y; b++) {
				doubleIn[a][b] = temp[a][b];
			}
		}
		return doubleIn;
	}
	/**
	 * Reads the values from the Double matrix obtained from
	 * readFileToDouble() and stores the value in another Double
	 * matrix of two rows, one containing all x-values and one containing
	 * all y-values. Every seperate string from the initial String array is split
	 * by an element containing 'null'.
	 * @return The final double matrix containing all data.
	 */
	public Double[][] getValuesFromFile() {
		Double[][] dob = readFileToDouble();
		Double[][] temp = new Double[2][1000];
		int sizeX = 0, sizeY = 0;
			for(int y=0; y<dob[0].length; y++){
				for(int x=0; x<dob.length; x++){
					if (x%2 == 0 && dob[x][y] != null){
						temp[0][sizeX++] = dob[x][y];
					}
					if (x%2 != 0 && dob[x][y] != null){
						temp[1][sizeY++] = dob[x][y];
					}
				}
				temp[0][sizeX++] = null;
				temp[1][sizeY++] = null;
		}
		values = new Double[2][sizeX];
		for(int a = 0; a<2; a++){
			for (int b = 0; b<sizeX; b++){
				values[a][b] = temp[a][b];
			}
		}
		return values;
	}
}
