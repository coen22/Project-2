/**
 * 
 * @author Marciano
 *
 */
public class PolyLine implements DoublyLinkedListADT<Vertex> {

    private Node<Vertex> header;
    private Node<Vertex> trailer;
    private int size;

    public PolyLine() {
        header = new Node<Vertex>();
        trailer = new Node<Vertex>();
        header.setAfter(trailer);
        trailer.setBefore(header);
        size = 0;
    }

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

    public Node<Vertex> getHeader() {
        return header;
    }

    public Node<Vertex> getTrailer() {
        return trailer;
    }

    /*
     * To be implemented
     */
    public boolean isSimple() {
        // TODO Auto-generated method stub
        return false;
    }

    public double length() {
        double length = 0;
        Node<Vertex> current = header.getAfter();
        System.out.println(current.getElement());
        while (current.getAfter().getElement() != trailer.getElement()) {
            Node<Vertex> nextNode = current.getAfter();
            System.out.println(current.getElement() + " " + nextNode.getElement() + " Hello");
            Vertex a = (Vertex) current.getElement();
            Vertex b = (Vertex) (nextNode.getElement());
            double l1 = Math.abs(a.getX() - b.getX());
            double l2 = Math.abs(a.getY() - b.getY());
            length = length + Math.sqrt((Math.pow(l1, 2)) + (Math.pow(l2, 2)));
            current = current.getAfter();
        }
        return length;
    }

    public double area() {
        double area = 0;
        boolean done = false;
        Node<Vertex> current = header.getAfter();
        Node<Vertex> next = null;
        if (isClosed() && isSimple()) {
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
            Node<Vertex> firstNode = header.getAfter();
            Vertex doubleE = (Vertex)(firstNode.getElement());
            insertLast(doubleE);
        }
    }

    @Override
    public Vertex elementAt(int r) throws EmptySequenceException {
        Node<Vertex> current = header.getAfter();
        for (int i = 0; i < r; i++) {
            current = current.getAfter();
        }

        return current.getElement();
    }

    public void changeElementAt(int r, Vertex e) throws EmptySequenceException {
        Node<Vertex> current = header.getAfter();
        for (int i = 0; i < r; i++) {
            current = current.getAfter();
        }
        current.setElement(e);
    }

    /**
     *
     * @param e
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
