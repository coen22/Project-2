
public class PolyLine<E> implements DoublyLinkedListADT<E> {
	private Node<E> header = new Node(); 
	private Node<E> trailer = new Node();
	private int size;

	
	public PolyLine(Node<E> a){
		header.setAfter(a);
		a.setBefore(header);
		a.setAfter(trailer);
		trailer.setBefore(a);
		size = 1;
	}
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
		System.out.println(current.getElement());
		while(current.getAfter().getElement() != trailer.getElement()){
			Node<E> nextNode = current.getAfter();
			System.out.println(current.getElement() + " " + nextNode.getElement() + " Hello");
			Vertex a = (Vertex)current.getElement();
			Vertex b = (Vertex)(nextNode.getElement());
			double l1 = Math.abs(a.getX() - b.getX());
			double l2 = Math.abs(a.getY() - b.getY());
			length = length + Math.sqrt((Math.pow(l1, 2))+(Math.pow(l2,2)));
			current = current.getAfter();
		}
		return length;
	}
	
	public double area(){
		double area = 0;
		boolean done = false;
		Node<E> current = header.getAfter();
		Node<E> next = null;
		if(isClosed() && isSimple()){
			while(!done){
				Vertex a = (Vertex)current.getElement();
				Vertex b = (Vertex)current.getAfter().getElement();
				area += a.getX()*b.getY() - b.getX()*a.getY();
				current = current.getAfter();
				if(current.getAfter() == trailer){
					next = header.getAfter();
					a = (Vertex)current.getElement();
					b = (Vertex)next.getElement();
					area += a.getX()*b.getY() - b.getX()*a.getY();
					done = true;
				}
			}
		}
		return 0.5*area;
	}
	
	public boolean isClosed() {
		return (header.getAfter().getElement() == trailer.getBefore().getElement());
	}
	
	
	/**
	 * Checks if a polyline has points in it	
	 * @return true if the polyline does not contain points. false in if the polyline does contain points
	 */
		
		@Override
		public boolean isEmpty() {
			if(header.getAfter() == trailer){
			return true;
		}
			return false;
		}
	/**
	 * The amount of points a polyline contains
	 * @return integer of amount of points
	 */
	@Override
	public int size() {
		return size;
	}

	@Override
	public E elementAt(int r) throws EmptySequenceException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @param e
	 */
		@Override
		public void insertLast(E e) {
			Node<E> x = new Node(e);
			x.setAfter(trailer);
			x.setBefore(trailer.getBefore());
			trailer.getBefore().setAfter(x);
			trailer.setBefore(x);
			size++;
		}
		}
