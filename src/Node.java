
public class Node<E> {
	private E element;
	private Node before;
	private Node after;
	
	public Node(){
		
	}
	
	public Node(E element){
		this.element = element;
	}
	
	public E getElement(){
		return null;
	}
	
	public Node getBefore(){
		return before;
	}
	
	public Node getAfter(){
		return after;
	}
	
	public void setBefore(Node before){
		this.before = before;
	}
	
	public void setAfter(Node after){
		this.after = after;
	}
	
}
