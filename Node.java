package searchanalysis;
// data structure that represents a node in the tree
class Node {
	String key; // holds the key
	int frequency; // Frequency for the word stored in key;
	Node parent; // pointer to the parent
	Node left; // pointer to left child
	Node right; // pointer to right child
    int bf; // balance factor of the node

	public Node(String k) {
		key = k;
        frequency = 0; 
		parent = null;
		left = null;
		right = null;
	}
}