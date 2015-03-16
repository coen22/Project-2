
public interface DoublyLinkedListADT<E> {
	public int size();
	public boolean isEmpty();
	public E elementAt(int r) throws EmptySequenceException;
	public void insertLast(E e);

}
