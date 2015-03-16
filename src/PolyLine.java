
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
