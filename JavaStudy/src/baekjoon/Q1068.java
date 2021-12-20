// Ʈ�� (���� 1068��)

/* [ �˰��� ]
 * 
 * 1. Ʈ���� ���� �����.
 * 2. Ʈ������ ������ ��带 ���� DFS �� ����.
 * 3. ���� ������ ��� �κ��� ������ ī��Ʈ�� �״�� �����Ѵ�.
 * 4. ������ ��带 ��ġ�� �ʰ� �� ������ �� ��� count+1�� ���ش�.
 */

// ������ : Ʈ������� �������� ��� ���� �߻� (0->1->2->3 Ʈ������ 2�� �����Ұ�� 1�� ī��Ʈ �������)
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q1068 {
	static int count =0;
	static boolean[] lastCheck; // ����������� üũ (false: �������)
	static boolean[] isVisited;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // ����� ����
		
		ArrayList<Integer>[] tree = new ArrayList[N];
		for(int i=0; i<N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] parent = new int[N]; // �� �ε���(���) ���� �θ��尡 �������� ��Ÿ���� ���� �迭
		int rootNode = 0;
		for(int i=0; i<N; i++) {
			int from = i;
			int to = Integer.parseInt(st.nextToken());
			
			parent[i] = to;
			
			if(to==-1) {
				rootNode = i;
				continue;
			}
			else {
				tree[from].add(to);
				tree[to].add(from);
			}
		}

		int deleteNode = Integer.parseInt(br.readLine());
	    lastCheck = new boolean[N]; // ����������� üũ (false: �������)
	    isVisited = new boolean[N];
		
		/*
		for(int i=0; i<N; i++) {
			System.out.println("��� "+i + ": ");
			for(Integer j : tree[i]) {
				System.out.print(j + " ");
				
			}
			System.out.println();
		}*/
	    
	    DFS(tree, rootNode, deleteNode);
	    deleteDFS(tree, deleteNode);

		for(int i=0; i<N; i++) {
			if(!lastCheck[i]) {
				count++;
			}
		}
		
		if(rootLeafCheck(tree, parent[deleteNode], parent, deleteNode)) { // ������ ����� ��Ʈ ��尡 ������ �� ������尡 �Ǵ��� üũ
			System.out.print(count+1);
		} else{
			System.out.print(count);
		}
	}
	public static void DFS(ArrayList<Integer>[] tree, int rootNode, int deleteNode) {
		Stack<Integer> stack = new Stack<>();
		stack.add(rootNode);
		isVisited[rootNode] = true;

		boolean check;
		
		while(!stack.isEmpty()) {
			int x = stack.peek();
			check = false;

			if(x==deleteNode) {
				stack.pop();
				continue;
			}

			for(Integer i : tree[x]) {
				if(!isVisited[i]) {
					stack.push(i);
					check = true;
					isVisited[i] = true;
					lastCheck[x] = true; // �ڽ� ��� ���� (������� X)
					break;
				}
			}
			if(!check) {
				stack.pop();
			}
		}
	}
	public static void deleteDFS(ArrayList<Integer>[] tree, int rootNode) {
		Stack<Integer> stack1 = new Stack<>();
		stack1.add(rootNode);
		isVisited[rootNode] = true;

		boolean check;
		
		while(!stack1.isEmpty()) {
			int x = stack1.peek();
			check = false;

			for(Integer i : tree[x]) {
				if(!isVisited[i]) {
					stack1.push(i);
					check = true;
					isVisited[i] = true;
					break;
				}
			}
			if(!check) {
				lastCheck[x] = true;
				stack1.pop();
			}
		}
	}
	public static boolean rootLeafCheck(ArrayList<Integer>[] tree, int rootNode, int[] parent, int deleteNode) {
		
		if(rootNode < 0) return false;
		
		for(Integer i : tree[rootNode]) {
			if(i!= parent[rootNode] && i!=deleteNode) {
				return false;
			}
		}	
		
		return true;
	}
}
