import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
	
	private String fileName;
	private FileWriter writer;
	private BufferedWriter writerB;
	private FileReader reader;
	private BufferedReader readerB;
	
	public FileHandler(String fileName){
		this.fileName = fileName;
	}
	
	public void deleteFile(){
		try{
			writer = new FileWriter(fileName);
			writerB = new BufferedWriter(writer);
			writerB.write("");
			writerB.close();
		} catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
	}
	
	public void writeFile(String data){
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
	
	public void readFile(){
		String line = null;

        try {
            reader = new FileReader(fileName);
            readerB = new BufferedReader(reader);
            while((line = readerB.readLine()) != null) {
                System.out.println(line);
            }    
            readerB.close();            
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                   
        }
	}
}
