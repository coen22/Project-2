public class WNAlgorithm {
	private PolyLine line;
	private Vertex point;
	private Node<Vertex> header, trailer;
	private int wn;
	private boolean movesUpward, movesDownward, pointCrossesEdge;
	
	public WNAlgorithm(PolyLine line, Vertex point){
		this.line = line;
		this.point = point;
	}
	
	public void algoritm(){
		
	}
	
	private boolean isOutside(){
		return wn != 0;
	}
	
	private boolean movesUpward(){
		
		return movesUpward;
	}
	
	private boolean movesDownward(){
		return movesDownward;
	}
	
	private boolean pointCrossesEdge(){
		return pointCrossesEdge;
	}
}
