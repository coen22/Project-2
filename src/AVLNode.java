
public class AVLNode {
	private Valuable data;
	public AVLNode parent, left, right;
	
	public AVLNode(Valuable data) {
		this.data = data;
	}
	
	public int getValue() {
		return this.data.getValue();
	}
	
	public void setValue(Valuable data) {
		this.data = data;
	}
	
	public Valuable getData() {
		return this.data;
	}
	
	public void setData(Valuable data) {
		this.data = data;
	}
}
