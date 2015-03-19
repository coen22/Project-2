/**
 * An implementation of the winding number algorithm to decide whether a given point is in- or outside
 * of the polygon. The algorithm checks the edges the point would pass through if it was a ray and 
 * increases and decreases the winding number depending on its orientation. If the winding number is 
 * equal to zero, the point is outside of the polygon.
 * @author Brian
 */
public class WNAlgorithm {

    private PolyLine line;
    private Vertex point;
    private int wn;
    private boolean pointCrossesEdge;
    /**
     * Constructor to initialize the Algorithm.
     * @param line	the given Polyline
     * @param point	the given Point
     */
    public WNAlgorithm(PolyLine line, Vertex point) {
        this.line = line;
        this.point = point;
    }
    /**
     * Checks whether the given point is outside.
     * @return	true if the point is outside.
     */
    public boolean isOutside() {
        boolean done = false;
        Node<Vertex> current = line.getHeader().getAfter();
        while (!done) {
            if (movesDownward(current) && pointCrossesEdge(current)) {
                if (!isLeft(current, point)) {
                    wn--;
                }
            }
            if (movesUpward(current) && pointCrossesEdge(current)) {
                if (isLeft(current, point)) {
                    wn++;
                }
            }
            if (current.getAfter() == line.getTrailer().getBefore()) {
                done = true;
            }
            current = current.getAfter();
        }
        return wn == 0;
    }
    /**
     * Decides whether the position of the point is to the left of the edge.
     * @param current	the current node of the PolyLine
     * @param point		the given Point that has to be checked
     * @return			true if the point is left to the edge
     */
    private boolean isLeft(Node<Vertex> current, Vertex point) {
        double calc = ((current.getAfter().getElement().getX() - current.getElement().getX())
                * (point.getY() - current.getElement().getY()) - (point.getX() - current.getElement().getX())
                * (current.getAfter().getElement().getY() - current.getElement().getY()));
        return calc > 0;
    }
    /**
     * Checks whether the edge moves upwards relative to the point.
     * @param current
     * @return
     */
    private boolean movesUpward(Node<Vertex> current) {
        return current.getElement().getY() < current.getAfter().getElement().getY();
    }

    private boolean movesDownward(Node<Vertex> current) {
        return current.getElement().getY() > current.getAfter().getElement().getY();
    }

    private boolean pointCrossesEdge(Node<Vertex> current) {
        pointCrossesEdge = false;
        if (movesUpward(current)) {
            if ((point.getY() > current.getElement().getY()) && (point.getY() < current.getAfter().getElement().getY())) {
                pointCrossesEdge = true;
            }
        }
        if (movesDownward(current)) {
            if ((point.getY() < current.getElement().getY()) && (point.getY() > current.getAfter().getElement().getY())) {
                pointCrossesEdge = true;
            }
        }
        return pointCrossesEdge;
    }
}
