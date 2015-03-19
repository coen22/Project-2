
public class Node<E> {
	private E element;
	private Node<E> before;
	private Node<E> after;
	
	public Node(E element, Node<E> before, Node<E> after) {
		this.element = element;
		this.before = before;
		this.after = after;
	}
	
	public Node() {
		element = null;
		before = null;
		after = null;
	}
	
	public Node(E element){
		this.element = element;
	}
	
	public void setElement(E element){
		this.element = element;
	}
	
	public E getElement(){
		return element;
	}
	
	public Node<E> getBefore(){
		return before;
	}
	
	public Node<E> getAfter(){
		return after;
	}
	
	public void setBefore(Node<E> before){
		this.before = before;
	}
	
	public void setAfter(Node<E> after){
		this.after = after;
	}
}
