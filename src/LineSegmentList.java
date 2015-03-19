/*
 * Segments of lines created by polyline used by intersection search
 */
public class LineSegmentList implements DoublyLinkedListADT<LineSegment>{
	private Node<LineSegment> header; 
	private Node<LineSegment> trailer;
	private int size;
	
	/**
	 * Constructor for an empty LineSegmentList
	 */
	public LineSegmentList(){
		header = new Node<LineSegment>();
		trailer = new Node<LineSegment>();
		header.setAfter(trailer);
		trailer.setBefore(header);
		size = 0;
	}
	
	/**
	 * Constructor for a LineSegmentList that requires a polyline to get started
	 * @param polyLine	needed to create it in LineSegments
	 */
	public LineSegmentList(PolyLine polyLine){
		header = new Node<LineSegment>();
		trailer = new Node<LineSegment>();
		header.setAfter(trailer);
		trailer.setBefore(header);
		Node<Vertex> currentNode = polyLine.getHeader().getAfter();
		
		while (currentNode != polyLine.getTrailer().getBefore()){
			insertLast((LineSegment)(new LineSegment(currentNode.getElement(), currentNode.getAfter().getElement())));
			size++;
			currentNode = currentNode.getAfter();
		}
	
	}
	
	/**
	 * Constructor for a LineSegmentList that requires two polylines to create
	 * @param polyLine1	Needed to create the linesegments
	 * @param polyLine2	Needed to create the linesegments
	 */
	public LineSegmentList(PolyLine polyLine1, PolyLine polyLine2){
		header = new Node<LineSegment>();
		trailer = new Node<LineSegment>();
		header.setAfter(trailer);
		trailer.setBefore(header);
		
		Node<Vertex> currentNode = polyLine1.getHeader().getAfter();
		while (currentNode != polyLine1.getTrailer().getBefore()){
			insertLast((LineSegment)(new LineSegment(currentNode.getElement(), currentNode.getAfter().getElement())));
			size++;
			currentNode = currentNode.getAfter();
		}
		
		Node<Vertex> currentNode2 = polyLine2.getHeader().getAfter();
		while (currentNode2 != polyLine2.getTrailer().getBefore()){
			insertLast((LineSegment)(new LineSegment(currentNode2.getElement(), currentNode2.getAfter().getElement())));
			size++;
			currentNode2 = currentNode2.getAfter();
		}
	
	}
	
	/**
	 * @return	It returns an int that is the size of the lineSegmentList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return	It returns a boolean, true if the lineSegmentList is empty, false if it isn't 
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * returns the element at a rank (Unimplemented and Unused in this class)
	 * @param r	The rank where it should find the element
	 * @return	Returns the element at a rank
	 */
	@Override
	public LineSegment elementAt(int r) throws EmptySequenceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Adds a LineSegment to a LineSegmentList at the last spot
	 * @param e	The element to be added to the LineSegmentList
	 */
	@Override
	public void insertLast(LineSegment e) {
		Node<LineSegment> x = new Node<LineSegment>(e);
		x.setAfter(trailer);
		x.setBefore(trailer.getBefore());
		trailer.getBefore().setAfter(x);
		trailer.setBefore(x);
		size++;
	}
	
	/**
	 * Basic toString method
	 * @return	Returns string containing all elements of a LineSegmentList
	 */
	public String toString(){
		String string = "";
		Node<LineSegment> currentNode = header.getAfter();
		while (currentNode != trailer){
			string = string + "[" + currentNode.getElement().toString() + "]  ";
			currentNode = currentNode.getAfter();
		}
		return string;
	}
	
	/**
	 * @return	Returns the header of the LineSegmentList
	 */
	public Node<LineSegment> getHeader(){
		return header;
	}
	
	/**
	 * @return	Returns the trailer of the LineSegmentList
	 */
	public Node<LineSegment> getTrailer(){
		return trailer;
	}

}
