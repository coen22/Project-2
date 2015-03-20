/**
 * 
 * @author Group 1: Kareem, Marciano, Brian, David, Coen
 *
 * @param <E> Type of List Nodes
 */
public interface DoublyLinkedListADT<E> {
	public int size();
	public boolean isEmpty();
	public E elementAt(int r) throws EmptySequenceException;
	public void insertLast(E e);
	public Node<E> getHeader();
	public Node<E> getTrailer();
	
}