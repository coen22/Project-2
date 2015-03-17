
public class LinkedList<T> {
	private Node<T> header =new Node<T>(null, null, null);
	private Node<T> current;
	
	private int size = 0;
	
	public LinkedList() {
		header.next = header;
		header.prev = header;
	}
	
	public void add(T data) {
		Node<T> node = new Node<T>(data, header.prev, header);
		header.prev.next = node;
		header.prev = node;
		
		if (size == 0)
			current = header.next;
		
		size++;
	}
	
	public void addAll(T[] data) {
		for (T element : data)
			add(element);
	}
	
	public void removeCurrent() throws Exception {
		if (size == 0)
			throw new Exception("List is empty");
		
		Node<T> after = current.next;
		
		current.prev.next = current.next;
		current.next.prev = current.prev;
		
		current.prev = null;
		current.next = null;
		current.data = null;
		
		current = after;
		
		if (current == header)
			current = header.next;
		
		size--;
	}
	
	public Node<T> current() {
		return current;
	}
	
	public Node<T> prev() {
		current = current.prev;
		
		if (current == header)
			current = current.prev;
		
		return current;
	}
	
	public Node<T> next() {
		current = current.next;
		
		if (current == header)
			current = current.next;
		
		return current;
	}
	
	public Node<T> first() {
		current = header.next;
		return current;
	}
	
	public String toString() {
		String s = "[ ";
		
		Node<T> node = header.next;
		
		while (node != header) {
			s += node.toString() + " ";
			node = node.next;
		}
		
		s += "]";
		
		return s;
	}

	public int size() {
		return size;
	}
	
	public void sort() {
		for (Node<Comparable> node = (Node<Comparable>) header.next; node != header; node = node.next) {
			if (node.data.compareTo(node.next.data) > 0) {
				Comparable tmp = node.data;
				node.data = node.next.data;
				node.next.data = node.data;
			}
		}
	}
}
