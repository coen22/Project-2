
public class AVLTree {
	
	private class TreeNode {
		private TreeNode lChild;
		private TreeNode rChild;
		private TreeNode parent;
		private EventPoint event;
		
		public TreeNode(EventPoint event, TreeNode left, TreeNode right, TreeNode par) {
			lChild = left;
			rChild = right;
			parent = par;
		}
	}
	
	public TreeNode root;
	
	public boolean exists(EventPoint point) {
		return false;
	}
	
	public void deleteFirst(){
		
	}
	
	public void sortInsert(EventPoint newPoint) {
		sortedInsert(newPoint, root);
	}
	
	private void sortedInsert(EventPoint newPoint, TreeNode node){
		
	}
}
