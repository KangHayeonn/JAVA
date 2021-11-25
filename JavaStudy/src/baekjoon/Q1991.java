// 트리 순회 (백준 1991번)

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import baekjoon.Q1991.Tree.Node;

public class Q1991 {
	public static class Tree {
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
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		Tree tree = new Tree();
		
		for(int i=0; i<count; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				String str = st.nextToken();
				Q1991.Tree.Node n = new Node('A');
				if(str.charAt(0) != '.' ) { // 대문자 알파벳일 때
					if(j==0) {
						if(str.charAt(0) != 'A') {
							n = tree.addNode(str.charAt(0));
						}
					}
					else if(j==1) {
						n.addLeft(new Node(str.charAt(0)));
					}
					else {
						n.addRight(new Node(str.charAt(0)));
					}
					
				} else { //. 일 때
					continue;
				}
			}
		}
		
		tree.preOrder(new Node('A'));
		System.out.println();
		tree.inOrder(new Node('A'));
		System.out.println();
		tree.postOrder(new Node('A'));
		System.out.println();
	}
}
