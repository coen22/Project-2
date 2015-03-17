
public class LineSegment {
	private Vertex endPointA;
	private Vertex endPointB;
	
	public LineSegment(Vertex a, Vertex b){
		if (a.getX() < b.getY()){
			this.endPointA = a;
			this.endPointB = b;
		}
		else if (Math.abs(a.getX()-b.getX()) < 0.0000001){
			if (a.getY() < b.getY()){
				this.endPointA = a;
				this.endPointB = b;
			}
			else{
				this.endPointA = b;
				this.endPointB = a;
			}
		}
		else{
			this.endPointA = b;
			this.endPointB = a;
		}
		
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
