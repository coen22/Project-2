import java.util.ArrayList;

public class SHIntersect implements IntersectAlgorithm {

	public ArrayList<Vertex> getIntersections(ArrayList<Segment> lines) {
		LinkedList<Vertex> points = getAllPoints(lines);
		points.sort();
		ArrayList<Vertex> out = new ArrayList<Vertex>();
		
		for (int i = 0; i < 5; i++) {
			
		}
		
		return out;
	}
	
	public LinkedList<Vertex> getAllPoints(ArrayList<Segment> lines) {
		LinkedList<Vertex> points = new LinkedList<Vertex>();
		
		for (Segment segment : lines) {
			points.add(segment.getA());
			points.add(segment.getB());
		}
		
		return points;
	}
	
	/*
	Initialize event queue EQ = all segment endpoints;
    Sort EQ by increasing x and y;
    Initialize sweep line SL to be empty;
    Initialize output intersection list IL to be empty;

    While (EQ is nonempty) {
        Let E = the next event from EQ;
        If (E is a left endpoint) {
            Let segE = E's segment;
            Add segE to SL;
            Let segA = the segment Above segE in SL;
            Let segB = the segment Below segE in SL;
            If (I = Intersect( segE with segA) exists) 
                Insert I into EQ;
            If (I = Intersect( segE with segB) exists) 
                Insert I into EQ;
        }
        Else If (E is a right endpoint) {
            Let segE = E's segment;
            Let segA = the segment Above segE in SL;
            Let segB = the segment Below segE in SL;
            Delete segE from SL;
            If (I = Intersect( segA with segB) exists) 
                If (I is not in EQ already) 
                    Insert I into EQ;
        }
        Else {  // E is an intersection event
            Add E’s intersect point to the output list IL;
            Let segE1 above segE2 be E's intersecting segments in SL;
            Swap their positions so that segE2 is now above segE1;
            Let segA = the segment above segE2 in SL;
            Let segB = the segment below segE1 in SL;
            If (I = Intersect(segE2 with segA) exists)
                If (I is not in EQ already) 
                    Insert I into EQ;
            If (I = Intersect(segE1 with segB) exists)
                If (I is not in EQ already) 
                    Insert I into EQ;
        }
        remove E from EQ;
    }
    return IL;
	 */
}
