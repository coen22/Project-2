/**
 * 
 * @author Group 1: Kareem, Marciano, Brian, David, Coen
 * @ version 1.0
 *
 */
public class Vertex {
	private static final double EPSILON = 0.0000001;
	protected double x;
	protected double y;
	/**
	 * Constructor which accepts two doubles
	 * @param x	x coordinate
	 * @param y	y coordinate
	 */
	public Vertex(double x, double y){
		this.x = x;
		this.y = y;
	}
	/**
	 * Constructor which accepts another Vertex
	 * @param vertex
	 */
	public Vertex(Vertex vertex){
		this.x = vertex.getX();
		this.y = vertex.getY();
	}
	
	/**
	 * Checks if two Vertexes are the same point
	 * @param v2	Vertex to be checked
	 * @return		Returns boolean true if it is the same vertex, returns false if it isn't 
	 */
	public boolean equals(Vertex v2){
		return ((Math.abs(v2.getX()-this.x)<EPSILON) && (Math.abs(v2.getY()-this.y)<EPSILON));
	}
	/**
	 * 
	 * @return	Returns x coordinate
	 */
	public double getX(){
		return x;
	}
	/**
	 * 
	 * @return	Returns y coordinate
	 */
	public double getY(){
		return y;
	}
	/**
	 * Can change the x coordinate of a specific vertex
	 * @param x	Replacement x coordinate
	 */
	public void setX(double x){
		this.x = x;
	}
	/**
	 * Can change the y coordinate of a specific vertex
	 * @param x	Replacement y coordinate
	 */
	public void setY(double y){
		this.y = y;
	}
	/**
	 * Returns a string of with the x and y coordinates
	 */
	public String toString(){
		return "("+x+","+y+")";
	}
	
	public Vertex copy(){
		return (new Vertex(x, y));
	}
}
