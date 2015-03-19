/**
 * 
 * @author David
 *
 */
public class EventList implements DoublyLinkedListADT<EventPoint>{
	private Node<EventPoint> header;
	private Node<EventPoint> trailer;
	private int size;

	public EventList() {
		header = new Node<EventPoint>();
		trailer = new Node<EventPoint>();
		header.setAfter(trailer);
		trailer.setBefore(header);
		size = 0;
	}

	public EventList(LineSegmentList segList){
		header = new Node<EventPoint>();
		trailer = new Node<EventPoint>();
		header.setAfter(trailer);
		trailer.setBefore(header);
		size = 0;

		Node<LineSegment> currentNode = segList.getHeader().getAfter();
		while (currentNode != segList.getTrailer()){
			insertSorted(new EventPoint(currentNode.getElement().getA(), currentNode.getElement(), null, false, true));
			insertSorted(new EventPoint(currentNode.getElement().getB(), currentNode.getElement(), null, false, false));
			currentNode = currentNode.getAfter();
		}
	}

	@Override
	public int size(){
		return size;
	}

	/**
	 * adding event-points in the correct location. If the event-point is an intersection point and it already exists in the list, it is not added a second time.
	 * @param newPoint the EventPoint to be added. 
	 */
	public void insertSorted(EventPoint newPoint){
		if (isEmpty()){
			insertLast(newPoint);
			size++;
		}
		else{
			Node<EventPoint> currentNode = header.getAfter();
			Node<EventPoint> newNode = new Node<EventPoint>(newPoint);
			while(currentNode != trailer && newPoint.compareTo(currentNode.getElement()) > 0){
				currentNode = currentNode.getAfter();
			}
			if (currentNode != trailer && newPoint.compareTo(currentNode.getElement()) == 0){ //the node to be inserted is a equal to the one already there and it is an intersection point
				//do nothing
			}
			else {
				newNode.setAfter(currentNode);
				newNode.setBefore(currentNode.getBefore());
				currentNode.getBefore().setAfter(newNode);
				currentNode.setBefore(newNode);
				size++;
			}
		}
	}

	public EventPoint deQueue(){
		size--;
		if (isEmpty()){
			return null;
		}
		else{
			Node<EventPoint> returnNode = header.getAfter();
			EventPoint returnElement = returnNode.getElement();
			header.setAfter(returnNode.getAfter());
			returnNode.getAfter().setBefore(header);
			returnNode = null;
			return returnElement;
		}
	}

	@Override
	public boolean isEmpty() {
		return (size==0);
	}

	@Override
	public EventPoint elementAt(int r) throws EmptySequenceException {
		if (isEmpty()){
			throw new EmptySequenceException();
		}
		else if (r <= size/2){
			Node<EventPoint> current = header.getAfter();
			for (int i = 0; i < r; i++){
				current = current.getAfter();
			}
			return current.getElement();
		}
		else{
			Node<EventPoint> current = trailer.getBefore();
			for (int i = size-1; i > r; i--){
				current = current.getBefore();
			}
			return current.getElement();
		}
	}

	@Override
	public void insertLast(EventPoint e) {
		Node<EventPoint> x = new Node<EventPoint>(e);
		x.setAfter(trailer);
		x.setBefore(trailer.getBefore());
		trailer.getBefore().setAfter(x);
		trailer.setBefore(x);
		size++;
	}

	public Node<EventPoint> getTrailer() {
		return trailer;
	}

	public Node<EventPoint> getHeader() {
		return header;
	}

	public String toString() {
		String string = "";
		Node<EventPoint> currentNode = header.getAfter();
		while (currentNode != trailer) {
			string = string + "[" + currentNode.getElement() + ", intersect:"+currentNode.getElement().isIntersectionPoint()+", leftPoint:"+currentNode.getElement().isLeftPoint()+"]";
			currentNode = currentNode.getAfter();
		}
		return string;
	}

}
