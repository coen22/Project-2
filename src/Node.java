
public class Node<E> {
	private E element;
	private Node<E> before;
	private Node<E> after;
	
	/**
	 * Constructor to make a new node
	 * @param element	Node will contain this element
	 * @param before	The node that comes before the created node
	 * @param after		The node that comes after the created node
	 */
	public Node(E element, Node<E> before, Node<E> after) {
		this.element = element;
		this.before = before;
		this.after = after;
	}
	
	/**
	 * Creates an empty node
	 * (For header-trailer use)
	 */
	public Node() {
		element = null;
		before = null;
		after = null;
	}
	
	/**
	 * Creates a node with only the element 
	 * @param element	Node will contain this element
	 */
	public Node(E element){
		this.element = element;
	}
	
	/**
	 * Will change the element in the node that called this method
	 * @param element	The element to be entered into the node
	 */
	public void setElement(E element){
		this.element = element;
	}
	
	/**
	 * 
	 * @return	Returns the element which the node contains
	 */
	public E getElement(){
		return element;
	}
	
	/**
	 * 
	 * @return	Returns the node that comes before the node that called this method
	 */
	public Node<E> getBefore(){
		return before;
	}
	
	/**
	 * 
	 * @return	Returns the node that comes after the node that called this method
	 */
	public Node<E> getAfter(){
		return after;
	}
	
	/**
	 * Sets the before partner for the node that called this method
	 * @param before	The node that needs to be before the node that called the method
	 */
	public void setBefore(Node<E> before){
		this.before = before;
	}
	
	/**
	 * Sets the after partner for the node that called this method
	 * @param after	The node that needs to be after the node that called the method
	 */
	public void setAfter(Node<E> after){
		this.after = after;
	}
}
