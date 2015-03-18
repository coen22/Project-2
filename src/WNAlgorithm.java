public class WNAlgorithm {
	private PolyLine line;
	private Vertex point;
	private LineSegment segment;
	private Node<Vertex> header, trailer, current;
	private int wn;
	private boolean pointCrossesEdge;
	
	public WNAlgorithm(PolyLine line, Vertex point){
		this.line = line;
		this.point = point;
	}
	
	public boolean algoritm(){
		return isOutside();
	}
	
	private boolean isOutside(){
		return wn != 0;
	}
	
	private boolean movesUpward(){
		return current.getElement().getY() < current.getAfter().getElement().getY();
	}
	
	private boolean movesDownward(){
		return current.getElement().getY() > current.getAfter().getElement().getY();
	}
	
	private boolean pointCrossesEdge(){
		pointCrossesEdge = false;
		if(movesUpward()){
			if((point.getX() > current.getElement().getX())&&(point.getX() < current.getAfter().getElement().getX())){
				pointCrossesEdge = true;
			}
		}
		if(movesDownward()){
			if((point.getX() < current.getElement().getX())&&(point.getX() > current.getAfter().getElement().getX())){
				pointCrossesEdge = true;
			}
		}
		return pointCrossesEdge;
	}
}
