package searchanalysis;
//Binary search tree implementation
//Main Source: AlgorithmTutor

public class BST {
	private Node root;

	public BST() {
		root = null;
	}

        //searches for the key in the BST, returns the frequency or -1 (if not found).
	private int searchTreeHelper(Node node, int key) {
            // Place your code here
		return 0;        
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
	public int searchTree(int k) {
		return searchTreeHelper(root, k);
	}
	
		
	// insert the key to the tree in its appropriate position 
        // if it is non-existent
        //Otherwise, the key/value (frequency) is updated/incremented.
        public void insert(int key) {
            // Place your code here 
        }
	
}