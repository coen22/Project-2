
public class AVLNode {
	private EventPoint value;
	public AVLNode parent, left, right;
	
	public AVLNode(EventPoint value) {
		this.value = value;
	}
	
	public EventPoint getValue() {
		return this.value;
	}
	
	public void setValue(EventPoint value) {
		this.value = value;
	}
	
	public int compareTo(AVLNode node) {
		return value.compareTo(node.value);
	}
}
