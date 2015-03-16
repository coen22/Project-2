import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;


public class FileHandler{
	
	private Scanner scan;
	private Formatter form;
	private String data;

	public void openFile(){
		try{
			scan = new Scanner(new File("File.txt"));
			form = new Formatter(new File("File.txt"));
		} catch(Exception e){
			System.out.println("File not found - is it in the right directory?");
		}
	}
	
	public void writeFile(String data){
		form.format("%s%n", data);
	}
	
	public String readFile(){
		if(scan.hasNextLine()){
			data = scan.next();
		}
		return data;
	}
	
	public void closeFile(){
		scan.close();
		form.close();
	}
}
