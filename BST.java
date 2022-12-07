package searchanalysis;
//Binary search tree implementation

//Main Source: AlgorithmTutor

public class BST {
	private Node root;

	public BST() {
		root = null;
	}

	// searches for the key in the BST, returns the frequency or -1 (if not found).
	private int searchTreeHelper(Node node, String key) {
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
			System.out.print(node.key + " " + node.frequency + "\n");
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
	// Node->Left Subtree->Right Subtree
	public void preorder() {
		preOrderHelper(root);
	}

	// In-Order traversal
	// Left Subtree -> Node -> Right Subtree
	public void inorder() {
		inOrderHelper(root);
	}

	// Post-Order traversal
	// Left Subtree -> Right Subtree -> Node
	public void postorder() {
		postOrderHelper(root);
	}

	// search the tree for the key k
	// and return the frequency of the key
	public int searchTree(String k) {
		return searchTreeHelper(root, k);
	}

	private Node insertHelper(Node node, String key) {
		if (node == null) {
			Node _node = new Node(key);
			_node.frequency++;
			return _node;
		} else if (key.compareTo(node.key) < 0) {
			node.left = insertHelper(node.left, key);
		} else if (key.compareTo(node.key) > 0) {
			node.right = insertHelper(node.right, key);
		} else if (key.compareTo(node.key) == 0) {
			node.frequency++;
		}
		return node;
	}

	// insert the key to the tree in its appropriate position
	// if it is non-existent
	// Otherwise, the key/value (frequency) is updated/incremented.
	public void insert(String key) {
		this.root = insertHelper(root, key);
	}

}