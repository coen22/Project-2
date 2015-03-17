/*
 * Segments of lines created by polyline used by intersection search
 */
public class LineSegmentList<E> implements DoublyLinkedListADT<E>{
	private Node<E> header; 
	private Node<E> trailer;
	private int size;
	
	
	public LineSegmentList(){
		header = new Node<E>();
		trailer = new Node<E>();
		header.setAfter(trailer);
		trailer.setBefore(header);
		size = 0;
	}
	
	public LineSegmentList(PolyLine polyLine){
		header = new Node<E>();
		trailer = new Node<E>();
		header.setAfter(trailer);
		trailer.setBefore(header);
		Node<Vertex> currentNode = polyLine.getHeader().getAfter();
		
		while (currentNode != polyLine.getTrailer().getBefore()){
			insertLast((E)(new LineSegment(currentNode.getElement(), currentNode.getAfter().getElement())));
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
	public E elementAt(int r) throws EmptySequenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertLast(E e) {
		Node<E> x = new Node<E>(e);
		x.setAfter(trailer);
		x.setBefore(trailer.getBefore());
		trailer.getBefore().setAfter(x);
		trailer.setBefore(x);
		size++;
	}
	
	public String toString(){
		String string = "";
		Node<E> currentNode = header.getAfter();
		while (currentNode != trailer){
			string = string + "[" + currentNode.getElement().toString() + "]";
			currentNode = currentNode.getAfter();
		}
		return string;
	}

}
