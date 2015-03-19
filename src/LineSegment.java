import java.util.Random;

public class LineSegment implements Comparable<LineSegment>{
	private Vertex endPointA;
	private Vertex endPointB;
	private double slope;
	private double offset;
	private double comparisonXValue;
	private static Random random = new Random();
	
	/**
	 * Constructor to create a linesegment between two points
	 * @param a		Set of coordinates. First endpoint
	 * @param b		Set of coordinates. Second endpoint
	 */
	public LineSegment(Vertex a, Vertex b){
		if (a.getX() < b.getX()){
			this.endPointA = a.copy();
			this.endPointB = b.copy();
		}
		else if (Math.abs(a.getX()-b.getX()) < 0.0000001){
			if (a.getY() < b.getY()){
				this.endPointA = a.copy();
				this.endPointB = b.copy();
			}
			else{
				this.endPointA = b.copy();
				this.endPointB = a.copy();
			}
		}
		else{
			this.endPointA = b.copy();
			this.endPointB = a.copy();
		}
		
		slope = (endPointB.getY()-endPointA.getY())/(endPointB.getX()-endPointA.getX());
		offset = endPointA.getY() - (slope * endPointA.getX());
		
		endPointA.setX(endPointA.getX() - random.nextDouble() * Math.pow(10, -10) - Math.pow(10, -8));
		endPointB.setX(endPointB.getX() + random.nextDouble() * Math.pow(10, -10));
	}
	
	/**
	 * @return	Returns the slope of a specific linesegment
	 */
	public double getSlope(){
		return slope;
	}
	
	/**
	 * @return Returns the offset that has happened to an endpoint
	 */
	public double getConstantOffset(){
		return offset;
	}
	
	/**
	 * Calculates y coordinate
	 * @param x		Takes in the x coordinate
	 * @return		Returns the y
	 */
	public double calculateY(double x){
		return ((slope*x)+offset);
	}
	
	/**
	 * Calculates x coordinate
	 * @param y		Takes in the y coordinate
	 * @return		Returns the x
	 */
	public double calculateX(double y){
		return ((y-offset)/slope);
	}
	
	/**
	 * @return	Returns the endPointA of a specific linesegment
	 */
	public Vertex getA(){
		return endPointA;
	}
	
	/**
	 * @return	Returns the endPointB of a specific linesegment
	 */
	public Vertex getB(){
		return endPointB;
	}
	
	/**
	 * Can manually set a point for a linesegment
	 * @param a		The new Vertex which replaces the previous endPointA
	 */
	public void setPointA(Vertex a){
		this.endPointA = a;
	}
	
	/**
	 * Can manually set a point for a linesegment
	 * @param b		The new Vertex which replaces the previous endPointB
	 */
	public void setPointB(Vertex b){
		this.endPointB = b;
	}
	
	/**
	 * Returns the length of the linesegment that called this method
	 * @return		Returns the calculated length
	 */
	public double getLength(){
		return Math.abs(Math.sqrt(Math.pow(endPointA.getX()-endPointB.getX(), 2) + Math.pow(endPointA.getY()-endPointB.getY(), 2)));
	}
	
	/**
	 * A to string method
	 */
	public String toString(){
		return "("+endPointA + "->"+ endPointB + ")";
	} 

	/**
	 * Compares the function of both lines at a given x (Sweepline implementation)
	 * @param seg2		The to be compared line
	 * @param x			The x to be compared to
	 * @return			Returns an int. If seg2 is smaller it returns 1, if seg2 is greater it returns -1 else it will return 0
	 */
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
	
	/**
	 * Compares two linesegments to see if they are equal
	 * @param seg2	The segment to be compared
	 * @return		Returns a boolean. True if the lines are equal, false if they are not.
	 */
	public boolean equals(LineSegment seg2){
		return (this.endPointA.equals(seg2.getA()) && this.endPointB.equals(seg2.getB()));
	}

	public double getComparisonXValue() {
		return comparisonXValue;
	}

	public void setComparisonXValue(double comparisonXValue) {
		this.comparisonXValue = comparisonXValue;
	}

	@Override
	public int compareTo(LineSegment seg2) {
		if (this.calculateY(comparisonXValue) > seg2.calculateY(comparisonXValue)){
			return 1;
		}
		else if (this.calculateY(comparisonXValue) < seg2.calculateY(comparisonXValue)){
			return -1;
		}
		else{
			return 0;
		}
	}
	
}
