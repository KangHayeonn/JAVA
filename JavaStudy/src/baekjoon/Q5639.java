// 이진 검색 트리 (백준 5639번)

// 트리 진짜 너무 어렵다...
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q5639 {
	public static class Node{
		int num;
		Node left;
		Node right;
		
		Node(int num) {
			this.num = num;
		}
		
		Node(int num, Node left, Node right){
			this.num = num;
            this.left = left;
            this.right = right;
		}
		
		void add(int N) {
			if(N < this.num) {
				if(this.left==null) {
					this.left = new Node(N);
				} else this.left.add(N);
			} else {
				if(this.right==null) {
					this.right = new Node(N);
				} else this.right.add(N);
			}
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Node root = new Node(Integer.parseInt(br.readLine()));
		while(true) {
			String str = br.readLine();
			if(str == null || str.equals("")) break; // 이부분 주의
			int num = Integer.parseInt(str);
			if(num > 0) {
				root.add(num);
			}else break;
		}
		
		postOrder(root);
	}
	
	public static void postOrder(Node node) {
		if(node ==null) return;
		
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.num);
	}
}
