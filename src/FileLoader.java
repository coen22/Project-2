import java.io.*;
import java.util.ArrayList;

class FileLoader {
	public ArrayList<LineSegment> segments;
	
	public void loadLineData(String fileName) throws IOException {
		DataInputStream in = new DataInputStream(new FileInputStream(fileName));
		
		segments = new ArrayList<LineSegment>();
		
		while (in.available() > 0) {
			segments.add(new LineSegment(new Vertex(in.readDouble(), in.readDouble()),
										new Vertex(in.readDouble(), in.readDouble())));
		}
		
		in.close();
	}
	
	public void saveLineData(String fileName) throws IOException {
		DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
		
		for (LineSegment l : segments) {
			out.writeDouble(l.getA().getX());
			out.writeDouble(l.getA().getY());
			out.writeDouble(l.getB().getX());
			out.writeDouble(l.getB().getY());
		}
		
		out.close();
	}
}
