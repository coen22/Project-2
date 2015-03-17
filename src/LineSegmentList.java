/*
 * Segments of lines created by polyline used by intersection search
 */
public class LineSegmentList implements DoublyLinkedListADT<LineSegment>{
	private Node<LineSegment> header; 
	private Node<LineSegment> trailer;
	private int size;
	
	
	public LineSegmentList(){
		header = new Node<LineSegment>();
		trailer = new Node<LineSegment>();
		header.setAfter(trailer);
		trailer.setBefore(header);
		size = 0;
	}
	
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
	
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LineSegment elementAt(int r) throws EmptySequenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertLast(LineSegment e) {
		Node<LineSegment> x = new Node<LineSegment>(e);
		x.setAfter(trailer);
		x.setBefore(trailer.getBefore());
		trailer.getBefore().setAfter(x);
		trailer.setBefore(x);
		size++;
	}
	
	public String toString(){
		String string = "";
		Node<LineSegment> currentNode = header.getAfter();
		while (currentNode != trailer){
			string = string + "[" + currentNode.getElement().toString() + "]";
			currentNode = currentNode.getAfter();
		}
		return string;
	}
	
	public Node<LineSegment> getHeader(){
		return header;
	}
	
	public Node<LineSegment> getTrailer(){
		return trailer;
	}

}
