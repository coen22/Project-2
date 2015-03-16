
public class LineSegment {
	private Vertex endPointA;
	private Vertex endPointB;
	
	public LineSegment(Vertex a, Vertex b){
		
		this.endPointA = a;
		this.endPointB = b;
		//make sure that point a is left of point b!
	}
	
	public Vertex getA(){
		return endPointA;
	}
	
	public Vertex getB(){
		return endPointB;
	}
	
	public void setPointA(Vertex a){
		this.endPointA = a;
	}
	
	public void setPointB(Vertex b){
		this.endPointB = b;
	}
	
	public double getLength(){
		return Math.abs(Math.sqrt(Math.pow(endPointA.getX()-endPointB.getX(), 2) + Math.pow(endPointA.getY()-endPointB.getY(), 2)));
	}
	
	public String toString(){
		return "("+endPointA + ","+ endPointB + ")";
	}
}
