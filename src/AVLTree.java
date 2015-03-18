import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class AVLTree {
	AVLNode root;
	
	public void insert(AVLNode n) {
		insert(root, n);
	}

	public void insert(AVLNode parent, AVLNode n) {
		if (parent == null) {
			root = n;
		} else {
			if (n.getValue().getX() < parent.getValue().getX()) {
				if (parent.left == null) {
					parent.left = n;
					n.parent = parent;

					fixBalance(parent);
				} else {
					insert(parent.left, n);
				}
			} else if (n.getValue().getX() > parent.getValue().getX()) {
				if (parent.right == null) {
					parent.right = n;
					n.parent = parent;

					fixBalance(parent);
				} else {
					insert(parent.right, n);
				}
			}
		}
	}

	public void fixBalance(AVLNode n) {
		int balance = getHeight(n.right) - getHeight(n.left);

		if (balance == -2) {
			// Left
			int subbalance = getHeight(n.left.right) - getHeight(n.left.left);

			if (subbalance == -1) {
				// Left-left
				// System.out.println("Left-left");
				n = rotateRight(n);
			} else if (subbalance == 1) {
				// Left-right
				// System.out.println("Left-right");
				n = doubleRotateLeftRight(n);
			}
		} else if (balance == 2) {
			// Right
			int subbalance = getHeight(n.right.right) - getHeight(n.right.left);

			if (subbalance == -1) {
				// Right-left
				// System.out.println("Right-left");
				n = doubleRotateRightLeft(n);
			} else if (subbalance == 1) {
				// Right-right
				// System.out.println("Right-right");
				n = rotateLeft(n);
			}
		}

		if (n.parent != null) {
			fixBalance(n.parent);
		} else {
			root = n;
		}
	}

	public AVLNode rotateRight(AVLNode n) {
		AVLNode v = n.left;
		v.parent = n.parent;

		n.left = v.right;

		if (n.left != null) {
			n.left.parent = n;
		}

		v.right = n;
		n.parent = v;

		if (v.parent != null) {
			if (v.parent.right == n) {
				v.parent.right = v;
			} else if (v.parent.left == n) {
				v.parent.left = v;
			}
		}

		return v;
	}

	public AVLNode rotateLeft(AVLNode n) {
		AVLNode v = n.right;
		v.parent = n.parent;

		n.right = v.left;

		if(n.right != null) {
			n.right.parent = n;
		}

		v.left = n;
		n.parent = v;

		if (v.parent != null) {
			if (v.parent.right == n) {
				v.parent.right = v;
			} else if (v.parent.left == n) {
				v.parent.left = v;
			}
		}

		return v;
	}
	
	public AVLNode doubleRotateLeftRight(AVLNode n) {
		n.left = rotateLeft(n.left);
		return rotateRight(n);
	}
	
	public AVLNode doubleRotateRightLeft(AVLNode n) {
		n.right = rotateRight(n.right);
		return rotateLeft(n);
	}

	public int getHeight(AVLNode n) {
		if (n == null) {
			return 0;
		} else if (n.left == null && n.right == null) {
			return 1;
		} else if (n.left == null) {
			return 1+getHeight(n.right);
		} else if (n.right == null) {
			return 1+getHeight(n.left);
		} else {
			return 1+Math.max(getHeight(n.left), getHeight(n.right));
		}
	}

	public ArrayList<Double> getLayer(int i) {
		ArrayList<Double> list = new ArrayList<Double>();
		getLayer(i, 0, root, list);
		return list;
	} 

	public void getLayer(int i, int current, AVLNode n, ArrayList<Double> list) {
		if (i == current) {
			list.add(n.getValue().getX());
		} else {
			if (n.left != null) getLayer(i, current+1, n.left, list);
			if (n.right != null) getLayer(i, current+1, n.right, list);
		}
	}
	
	public static String repString(String s, int n) {
		String ns = "";

		for (int i=0; i<n; i++) {
			ns += s;
		}

		return ns;
	}

	public static void main(String[] args) {
		AVLTree t = new AVLTree();
		
		Random r = new Random();
		
		t.insert(new AVLNode(new EventPoint(new Vertex(15, 10), null, null, false, false)));		
		for (int i = 0; i < 20; i++) {
			AVLNode node = new AVLNode(new EventPoint(new Vertex(r.nextInt(100), r.nextInt(100)), null, null, false, false));
			t.insert(node);
		}
		
		t.remove(15);
		
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setVisible(true);
		AVLSurface s = new AVLSurface(t);
		frame.setContentPane(s);
	}
	
	public void remove(int k) {
		remove(this.root,k);
	}
	
	private void remove(AVLNode p, int q) {
		if(p == null)
			return;
		else {
			if(p.getValue().getX() > q)
			   remove(p.left, q);
		   else if(p.getValue().getX() < q)
			   remove(p.right, q);
		   else if(p.getValue().getX() == q)
			   removeNode(p);
		}
	}
	
	public void removeNode(AVLNode removedNode) {
		AVLNode r;
		
		if (removedNode.left == null || removedNode.right == null) {
			if(removedNode.parent == null) {
		    	this.root = null;
		    	removedNode = null;
		    	return;
			}
			
			r = removedNode;
		} else {
			r = successor(removedNode);
			System.out.println(r.getValue());
			removedNode.setValue((r.getValue()));
		}
		  
		AVLNode p;
		if(r.left != null) {
			p = r.left;
		} else {
			p = r.right;
		}
		  
		if(p != null) {
			p.parent = r.parent;
		}
		  
		if(r.parent == null) {
			this.root = p;
		} else {
		    if(r == r.parent.left) {
		        r.parent.left = p;
		    } else {
		    	r.parent.right = p;
		    }
		    
		    fixBalance(r.parent);
		}
	}
	
	public AVLNode successor(AVLNode q) {
		AVLNode upNode;
		
		if(q.right != null) {
			upNode = q.right;
			while(upNode.left != null) {
				upNode = upNode.left;
			}
			
			return upNode;
		} else {
			upNode = q.parent;
			while(upNode != null && q == upNode.right) {
				q = upNode;
				upNode = q.parent;
			}
			
			return upNode;
		}
	}
}
