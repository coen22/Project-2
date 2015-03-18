
public class Vertex {
	private final double EPSILON = 0.0000001;
	private double x;
	private double y;
	
	public Vertex(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Vertex(Vertex vertex){
		this.x = vertex.getX();
		this.y = vertex.getY();
	}
	
	public boolean equals(Vertex v2){
		return ((Math.abs(v2.getX()-this.x)<EPSILON) && (Math.abs(v2.getY()-this.y)<EPSILON));
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public String toString(){
		return "("+x+","+y+")";
	}
}
