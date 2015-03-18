public class WNAlgorithm {
	private PolyLine line;
	private Vertex point;
	private LineSegment segment;
	private Node<Vertex> header, trailer;
	private int wn;
	private boolean pointCrossesEdge;
	
	public WNAlgorithm(PolyLine line, Vertex point){
		this.line = line;
		this.point = point;
	}		
	private boolean isOutside(){
		boolean done = false;
		Node<Vertex> current = header.getAfter();
		while (!done){
			if(movesDownward(current)){
				if(!isLeft(current, point)){
					wn--;
				}
			}
			if(movesUpward(current)){
				if(isLeft(current, point)){
					wn++;
				}
			}
			if(current.getAfter() == trailer){
				done = true;
			}
		}
		return wn != 0;
	}	
	public boolean isLeft(Node<Vertex> current, Vertex point){
		double calc = ( (current.getAfter().getElement().getX() - current.getElement().getX())
							*(point.getY() - current.getElement().getY()) - (point.getX() - current.getElement().getX())
								*(current.getAfter().getElement().getY() - current.getElement().getY()) );
		return calc > 0;
	}	
	private boolean movesUpward(Node<Vertex> current){
		return current.getElement().getY() < current.getAfter().getElement().getY();
	}	
	private boolean movesDownward(Node<Vertex> current){
		return current.getElement().getY() > current.getAfter().getElement().getY();
	}	
	private boolean pointCrossesEdge(Node<Vertex> current){
		pointCrossesEdge = false;
		if(movesUpward(current)){
			if((point.getY() > current.getElement().getY())&&(point.getY() < current.getAfter().getElement().getY())){
				pointCrossesEdge = true;
			}
		}
		if(movesDownward(current)){
			if((point.getY() < current.getElement().getY())&&(point.getY() > current.getAfter().getElement().getY())){
				pointCrossesEdge = true;
			}
		}
		return pointCrossesEdge;
	}
}
