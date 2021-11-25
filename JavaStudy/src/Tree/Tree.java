package Tree;

public class Tree {
	private int count;
	
	public Tree() {
		count =0;
	}
	public class Node {
		Object data;
		Node left;
		Node right;
		
		public Node(Object data) {
			this.data =data;
			left = null;
			right = null;
		}
		
		public void addLeft(Node node) {
			left = node;
			count++;
		}
		
		public void addRight(Node node) {
			right = node;
			count++;
		}
		
		public void deleteLeft() {
			left = null;
			count--;
		}
		
		public void deleteRight() {
			right = null;
			count--;
		}
	}
	
	public Node addNode(Object data) {
		Node n = new Node(data);
		return n;
	}
	
	//전위 순회 (루트 -> 좌 -> 우)
	public void preOrder(Node node) {
		if(node==null) {
			return;
		}
		System.out.print(node.data+" ");
		preOrder(node.left);
		preOrder(node.right);
	}
	
	//중위 순회 (좌 -> 루트 -> 우)
	public void inOrder(Node node) {
		if(node==null) {
			return;
		}
		inOrder(node.left);
		System.out.print(node.data+" ");
		inOrder(node.right);
	}
	
	//후위 순회 (좌 -> 우 -> 루트)
	public void postOrder(Node node) {
		if(node==null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data+" ");
	}
}
