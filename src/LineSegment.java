
public class LineSegment implements Comparable<LineSegment> {
	private Vertex endPointA;
	private Vertex endPointB;
	private double m;
	private double c;
	
	public LineSegment(Vertex a, Vertex b){
		if (a.getX() < b.getX()){
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
		
		m = (endPointB.getY()-endPointA.getY())/(endPointB.getX()-endPointA.getX());
		c = endPointA.getY() - (m * endPointA.getX());
		
	}
	
	public double getM(){
		return m;
	}
	
	public double getC(){
		return c;
	}
	
	public double calculateY(double x){
		return ((m*x)+c);
	}
	
	public double calculateX(double y){
		return ((y-c)/m);
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
		return "("+endPointA + "->"+ endPointB + ")";
	} 

	public int compareSLHeight(LineSegment seg2, double x) {
		if (this.calculateY(x) > seg2.calculateY(x)){
			return 1;
		}
		else if (this.calculateY(x) < seg2.calculateY(x)){
			return -1;
		}
		else{
			return 0;
		}
	}

	@Override
	public int compareTo(LineSegment o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
