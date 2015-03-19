/**
 * 
 * @author Marciano
 *
 */
public class PolyLine implements DoublyLinkedListADT<Vertex> {

    private Node<Vertex> header;
    private Node<Vertex> trailer;
    private int size;

    /**
     * Constructor to create an empty polyline
     */
    public PolyLine() {
        header = new Node<Vertex>();
        trailer = new Node<Vertex>();
        header.setAfter(trailer);
        trailer.setBefore(header);
        size = 0;
    }

    /**
     * Constructor to create a polyline with a start point
     * @param e	The vertex that is the startpoint of the to be created polyline
     */
    public PolyLine(Vertex e) {
        header = new Node<Vertex>();
        trailer = new Node<Vertex>();
        Node<Vertex> firstNode = new Node<Vertex>(e);
        header.setAfter(firstNode);
        firstNode.setBefore(header);
        firstNode.setAfter(trailer);
        trailer.setBefore(firstNode);
        size = 1;
    }

    /**
     * @return	Returns the header of a polyline
     */
    public Node<Vertex> getHeader() {
        return header;
    }

    /**
     * @return	Returns the trailer of a polyline
     */
    public Node<Vertex> getTrailer() {
        return trailer;
    }

    /**
     * Checks if a polyline has any self intersections if it doesn't it means it is a simple polyline
     * @return	Returns true if a polyline is simple, false if it has self intersections
     */
    public boolean isSimple() {
    	boolean isClosed = isClosed();
    	if (isClosed && ((BentleyOttmann.findIntersects(this, null)).size() == (size-1))){
    		return true;
    	}
    	else if(!isClosed && (BentleyOttmann.findIntersects(this, null).size()) == this.size()-2){
    		return true;
    	}
    	return false;
    }
/**
 * Checks if a point is inside a closed polyline (Buggy)
 * @param x  Point to be checked
 * @return True for inside. False for outside.
 */
    public boolean pointInside(Vertex x){
    	//If the Polyline is closed and has even amount of sides
		Node<Vertex> current = header.getAfter();
		int rot = 0;
		System.out.println("Enters method");
		System.out.println(isClosed());
		System.out.println((size()-1) % 2 == 0);
		if(isClosed() && ((size()-1) % 2 == 0)){	
			System.out.println("Even");
			for(int i = 0; i < size() - 1; i++){
				rot += MatrixVectorFunctions.orientation((Vertex) current.getElement(), (Vertex) current.getAfter().getElement(), x);
				current = current.getAfter();
			}
			System.out.println("Amount of rotations " + rot);
			if( rot == 0){
				return false;
			}
			else{
				return true;
			}
		}
		else if(isClosed()){
			System.out.println("Uneven");
			for(int i = 0; i < (size() - 1); i++){
				rot+= MatrixVectorFunctions.orientation((Vertex) current.getElement(), (Vertex) current.getAfter().getElement(), x);
				current = current.getAfter();
				System.out.println("Number of rotations " + rot);
			}
			System.out.println("Total amount of rotations " + rot);
			if( rot <= 1 && rot >= -1){
				return false;
			}
			else{
				return true;
			}
		}
		System.out.println("This cannot be executed");
		return false;
    }
/**
 * Calculates the length of a polyline
 * @return Returns the length of a polyline
 */
    public double length() {
        double length = 0;
        Node<Vertex> current = header.getAfter();
        System.out.println(current.getElement());
        while (current.getAfter().getElement() != trailer.getElement()) {
            Node<Vertex> nextNode = current.getAfter();
            Vertex a = (Vertex) current.getElement();
            Vertex b = (Vertex) (nextNode.getElement());
            double l1 = Math.abs(a.getX() - b.getX());
            double l2 = Math.abs(a.getY() - b.getY());
            length = length + Math.sqrt((Math.pow(l1, 2)) + (Math.pow(l2, 2)));
            current = current.getAfter();
        }
        return length;
    }
/**
 * Calculates the area of an arbitrary closed polyline
 * @return returns a double of the area 
 */
    public double area() {
        double area = 0;
        boolean done = false;
        Node<Vertex> current = header.getAfter();
        Node<Vertex> next = null;
        if (isClosed()) {
            while (!done) {
                Vertex a = (Vertex) current.getElement();
                Vertex b = (Vertex) current.getAfter().getElement();
                area += a.getX() * b.getY() - b.getX() * a.getY();
                current = current.getAfter();
                if (current.getAfter() == trailer) {
                    next = header.getAfter();
                    a = (Vertex) current.getElement();
                    b = (Vertex) next.getElement();
                    area += a.getX() * b.getY() - b.getX() * a.getY();
                    done = true;
                }
            }
        }
        return 0.5 * area;
    }
/**
 * Checks if the size is greater or equal to three otherwise it is just a line or point
 * then it will see if the point after the header and before the trailer are the same
 * @return Boolean if it is closed or not
 */
    public boolean isClosed() {
    	if(size >= 3){
    		if(((Vertex) header.getAfter().getElement()).getX() == ((Vertex) trailer.getBefore().getElement()).getX() && ((Vertex) header.getAfter().getElement()).getY() == ((Vertex) trailer.getBefore().getElement()).getY()){
    			return true;
    		}
    	}
    	
    	return false;
    }

    /**
     * Checks if a polyline has points in it
     *
     * @return true if the polyline does not contain points. false in if the
     * polyline does contain points
     */
    @Override
    public boolean isEmpty() {
        if (header.getAfter() == trailer) {
            return true;
        }
        return false;
    }

    /**
     * The amount of points a polyline contains
     *
     * @return integer of amount of points
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Will close the polyline if it contains 3 or more points
     */
    public void closeLine() {
        if (!isClosed() && size >= 3) {
        	insertLast(new Vertex(header.getAfter().getElement().getX(), header.getAfter().getElement().getY()));
//            Node<Vertex> firstNode = header.getAfter();
//            Vertex doubleE = (Vertex)(firstNode.getElement());
//            insertLast(doubleE);
        }
    }
/**
 * returns the element at a specific rank
 * @param 	rank of element to be shown
 * @return element at rank
 */
    @Override
    public Vertex elementAt(int r) throws EmptySequenceException {
        Node<Vertex> current = header.getAfter();
        for (int i = 0; i < r; i++) {
            current = current.getAfter();
        }

        return current.getElement();
    }
/**
 * Changes a specific point on a polyline
 * @param r The rank of the to be changed element
 * @param e The element that has to be changed
 * @throws EmptySequenceException
 */
    public void changeElementAt(int r, Vertex e) throws EmptySequenceException {
        Node<Vertex> current = header.getAfter();
        for (int i = 0; i < r; i++) {
            current = current.getAfter();
        }
        current.setElement(e);
    }

/**
* Main method used to increase the size of a polyline
* @param e The point in the line that needs to be added
*/
    @Override
    public void insertLast(Vertex e) {
        Node<Vertex> x = new Node<Vertex>(e);
        x.setAfter(trailer);
        x.setBefore(trailer.getBefore());
        trailer.getBefore().setAfter(x);
        trailer.setBefore(x);
        size++;
    }
/**
 * Basic toString method
 */
    public String toString() {
        String string = "";
        Node<Vertex> currentNode = header.getAfter();
        for (int i = 0; i < size; i++) {
            string = string + "[" + currentNode.getElement() + "]";
            currentNode = currentNode.getAfter();
        }
        return string;
    }
}
