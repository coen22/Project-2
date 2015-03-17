
public class EventList<EventPoint> implements DoublyLinkedListADT<EventPoint>{
	 private Node<EventPoint> header;
	 private Node<EventPoint> trailer;
	 private int size;
	 
	 @Override
	 public int size(){
		 return size;
	 }
	    
	public EventPoint deQueue(){
		if (size() != 0){
			return header.getAfter().getElement();
		}
		return null;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EventPoint elementAt(int r) throws EmptySequenceException {
		if (size == 0){
			throw new EmptySequenceException();
		}
		else if (r < size/2){
			Node<EventPoint> current = header.getAfter();
			for (int i = 0; i < r; i++){
				current = current.getAfter();
			}
			return current.getElement();
		}
		else{
			Node<EventPoint> current = trailer.getBefore();
			for (int i = size; i > r; i--){
				current = current.getBefore();
			}
			return current.getElement();
		}
	}

	@Override
	public void insertLast(EventPoint e) {
		// TODO Auto-generated method stub
		
	}

	public Node<EventPoint> getTrailer() {
		return trailer;
	}

	public Node<EventPoint> getHeader() {
		return header;
	}

}
