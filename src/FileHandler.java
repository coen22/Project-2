import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {
	
	private String fileName;
	private String[] fileIn;
	private Double[][] doubleIn, values;
	private FileWriter writer;
	private BufferedWriter writerB;
	private FileReader reader;
	private BufferedReader readerB;
	
	public FileHandler(String fileName) {
		this.fileName = fileName;
	}
	
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
	
	public String[] readFile() {
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
	
	public Double[][] readFileToDouble() {
		String[] str = readFile();
		Double[][] temp = new Double[1000][1000];
		int x, y = 0;
		for (int i = 0; i<str.length; i++) {
			x = 0;
			y++;
			if (str[i] != null) {
				Matcher m = Pattern.compile("(?!=\\d\\.\\d\\.)([\\d.]+)").matcher(str[i]);
				while(m.find()) {
					double d = Double.parseDouble(m.group(1));
					temp[x++][i] = d;
				}
				doubleIn = new Double[x][y];
				for(int a=0; a<x; a++) {
					for(int b=0; b<y; b++) {
						doubleIn[a][b] = temp[a][b];
					}
				}
			}
		}
		return doubleIn;
	}
	
	public Double[][] getValuesFromFile() {
		Double[][] dob = readFileToDouble();
		Double[][] temp = new Double[2][1000];
		int sizeX = 0, sizeY = 0;
			for(int y=0; y<dob[0].length; y++){
				for(int x=0; x<dob.length; x++){
					if (x%2 == 0){
						temp[0][sizeX++] = dob[x][y];
					}
					if (x%2 != 0){
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
