import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
	
	String fileName = "File.txt";
	FileWriter writer;
	BufferedWriter writerB;
	FileReader reader;
	BufferedReader readerB;
	
	public void writeFile(String data){
		try {
			writer = new FileWriter(fileName);
		    writerB = new BufferedWriter(writer);
            writerB.write(data);
            writerB.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
	}
	
	public void readFile(){
		String line = null;

        try {
            reader = new FileReader(fileName);
            readerB = new BufferedReader(reader);
            while((line = readerB.readLine()) != null) {
                System.out.println(line);
            }    
            readerB.close();            
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                   
        }
	}
}
