import java.util.TreeMap;


/**
 * 
 * @author David
 *
 */
public class EventListRB {
	
	private TreeMap<EventPoint, EventPoint> eventList;

	/**
	 * Constructor for an empty eventlist
	 */
	public EventListRB() {
		eventList = new TreeMap<EventPoint, EventPoint>();
		
	}

	/**
	 * Constructor for an eventlist with one node
	 * @param segList	The node to be added into the eventlist
	 */
	public EventListRB(LineSegmentList segList){
		eventList = new TreeMap<EventPoint, EventPoint>();

		Node<LineSegment> currentNode = segList.getHeader().getAfter();
		while (currentNode != segList.getTrailer()){
			insertSorted(new EventPoint(currentNode.getElement().getA(), currentNode.getElement(), null, false, true));
			insertSorted(new EventPoint(currentNode.getElement().getB(), currentNode.getElement(), null, false, false));
			currentNode = currentNode.getAfter();
		}
	}

	/**
	 * @return	Returns the size of the eventlist 
	 */
	public int size(){
		return eventList.size();
	}

	/**
	 * adding event-points in the correct location. If the event-point is an intersection point and it already exists in the list, it is not added a second time.
	 * @param newPoint the EventPoint to be added. 
	 */
	public void insertSorted(EventPoint newPoint){
		eventList.put(newPoint, newPoint);
	}

	/**
	 * Dequeues the EventList
	 * @return	Returns the deleted element
	 */
	public EventPoint deQueue(){
		EventPoint returnPoint = eventList.firstKey();
		eventList.remove(returnPoint);
		return returnPoint;
	}
}
