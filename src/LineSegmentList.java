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
	
	public LineSegmentList(E e){
		header = new Node<E>();
		trailer = new Node<E>();
		Node<E> firstNode = new Node<E>(e);
		header.setAfter(firstNode);
		firstNode.setBefore(header);
		firstNode.setAfter(trailer);
		trailer.setBefore(firstNode);
		size = 1;
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
		// TODO Auto-generated method stub
		
	}

}
