
public class Node<E> {
	private E element;
	private Node<E> before;
	private Node<E> after;
	
	public Node(){
		
	}
	
	public Node(E element){
		this.element = element;
	}
	
	public void setElement(E element){
		this.element = element;
	}
	
	public E getElement(){
		return null;
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
