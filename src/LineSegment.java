import java.util.Random;

public class LineSegment implements Comparable<LineSegment> {
	private Vertex endPointA;
	private Vertex endPointB;
	private double slope;
	private double offset;
	private static Random random = new Random();
	
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
		
		slope = (endPointB.getY()-endPointA.getY())/(endPointB.getX()-endPointA.getX());
		offset = endPointA.getY() - (slope * endPointA.getX());
		
		endPointA.setX(endPointA.getX() - random.nextDouble() * Math.pow(10, -10));
		endPointB.setX(endPointB.getX() + random.nextDouble() * Math.pow(10, -8));
	}
	
	public double getM(){
		return slope;
	}
	
	public double getC(){
		return offset;
	}
	
	public double calculateY(double x){
		return ((slope*x)+offset);
	}
	
	public double calculateX(double y){
		return ((y-offset)/slope);
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
	
	public boolean equals(LineSegment seg2){
		return (this.endPointA.equals(seg2.getA()) && this.endPointB.equals(seg2.getB()));
	}
	
}
