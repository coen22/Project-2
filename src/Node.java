public class Node<T> {
	public T data;
	public Node<T> prev;
	public Node<T> next;
	
	public Node(T d, Node<T> p, Node<T> n) {
		data = d;
		prev = p;
		next = n;
	}
	
	public String toString() {
		return data.toString();
	}
}