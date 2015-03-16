
public class Vertex {
	private final double EPSILON = 0.0000000001;
	private double x;
	private double y;
	
	public Vertex(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Vertex v2){
		return (((v2.getX()-this.x)<EPSILON) && ((v2.getY()-this.y)<EPSILON));
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
}
