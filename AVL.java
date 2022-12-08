package searchanalysis;
//AVL Binary search tree implementation in Java
//Main Source: AlgorithmTutor
public class AVL {
	private Node root;
	public AVL() {
		root = null;
	}
        
        //searches for the key in the AVL, returns the frequency or -1 (if not found).
	private int searchTreeHelper(Node node, String key) {
	// Place your code here 
		if (node.key.compareTo(key) < 0) {
			if (node.right != null) {
				return searchTreeHelper(node.right, key);
			}
		} else if (node.key.compareTo(key) == 0) {
			return node.frequency;
		} else {
			if (node.left != null) {
				return searchTreeHelper(node.left, key);
			}
		}
		return -1;
	}
        
	// update the balance factor the node
	private void updateBalance(Node node) {
		if (node.bf < -1 || node.bf > 1) {
			rebalance(node);
			return;
		}

		if (node.parent != null) {
			if (node == node.parent.left) {
				node.parent.bf -= 1;
			} 

			if (node == node.parent.right) {
				node.parent.bf += 1;
			}

			if (node.parent.bf != 0) {
				updateBalance(node.parent);
			}
		}
	}

	// rebalance the tree
	void rebalance(Node node) {
		if (node.bf > 0) {
			if (node.right.bf < 0) {
				rightRotate(node.right);
				leftRotate(node);
			} else {
				leftRotate(node);
			}
		} else if (node.bf < 0) {
			if (node.left.bf > 0) {
				leftRotate(node.left);
				rightRotate(node);
			} else {
				rightRotate(node);
			}
		}
	}


	private void preOrderHelper(Node node) {
		if (node != null) {
			System.out.print(node.key + " ");
			preOrderHelper(node.left);
			preOrderHelper(node.right);
		} 
	}

	private void inOrderHelper(Node node) {
		if (node != null) {
			inOrderHelper(node.left);
			System.out.print(node.key + " ");
			inOrderHelper(node.right);
		} 
	}

	private void postOrderHelper(Node node) {
		if (node != null) {
			postOrderHelper(node.left);
			postOrderHelper(node.right);
			System.out.print(node.key + " ");
		} 
	}

	// Pre-Order traversal
	// Node.Left Subtree.Right Subtree
	public void preorder() {
		preOrderHelper(this.root);
	}

	// In-Order traversal
	// Left Subtree . Node . Right Subtree
	public void inorder() {
		inOrderHelper(this.root);
	}

	// Post-Order traversal
	// Left Subtree . Right Subtree . Node
	public void postorder() {
		postOrderHelper(this.root);
	}

	// search the tree for the key k
	// and return the frequency of the key
	public int searchTree(String k) {
		return searchTreeHelper(this.root, k);
	}

	// rotate left at node x
	void leftRotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;

		// update the balance factor
		x.bf = x.bf - 1 - Math.max(0, y.bf);
		y.bf = y.bf - 1 + Math.min(0, x.bf);
	}

	// rotate right at node x
	void rightRotate(Node x) {
		Node y = x.left;
		x.left = y.right;
		if (y.right != null) {
			y.right.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.right) {
			x.parent.right = y;
		} else {
			x.parent.left = y;
		}
		y.right = x;
		x.parent = y;

		// update the balance factor
		x.bf = x.bf + 1 - Math.min(0, y.bf);
		y.bf = y.bf + 1 + Math.max(0, x.bf);
	}


        // insert the key to the tree in its appropriate position 
        // if it is non-existent
        //Otherwise, the key/value (frequency) is updated/incremented.
	public void insert(String key) {
		// PART 1: Ordinary BST insert
                // Place your code here 
                // Uncomment PART2 after you are done with PART1
		
		root = insertHelper(this.root, null, key);
		// PART 2: re-balance the node if necessary
		//updateBalance(root);
	}

	int getBF(Node n){
		if(n == null) return 0;
		else return n.bf;
	}
	public Node insertHelper(Node root, Node parent, String key){
		if(root == null){
			root = new Node(key);
			root.bf = 1;
			root.frequency = 1;
			root.parent = parent;
		}
		else if(root.key.compareTo(key) > 0){
			root.left = insertHelper(root.left, root, key);	
		}
		else if(root.key.compareTo(key) < 0){
			root.right = insertHelper(root.right, root, key);
		}
		else{
			root.frequency++;
		}

		/// Balance tree if current node is not balanced
		if(Math.abs(getBF(root.left) - getBF(root.right)) == 2)
			updateBalance(root);			
		return root;
	}	
}