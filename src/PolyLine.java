
public class PolyLine<E> implements DoublyLinkedListADT<E> {
	private Node<E> header; 
	private Node<E> trailer;

	/*
	 * To be implemented
	 */
	public boolean isSimple() {
		// TODO Auto-generated method stub
		return false;
	}

	public double length(){
		double length = 0;
		Node<E> current = header.getAfter();
		while(current.getAfter() != trailer){
			Vertex a = (Vertex)current.getElement();
			Vertex b = (Vertex)current.getAfter().getElement();
			double l1 = Math.abs(a.getX() - b.getX());
			double l2 = Math.abs(a.getY() - b.getY());
			length = length + Math.sqrt((Math.pow(l1, 2))+(Math.pow(l2,2)));
		}
		return length;
	}
	
	public boolean isClosed() {
		return (header.getAfter().getElement() == trailer.getBefore().getElement());
	}
	/*
	 * 
	 */
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
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
