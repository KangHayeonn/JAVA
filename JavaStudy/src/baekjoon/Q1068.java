// 트리 (백준 1068번)

/* [ 알고리즘 ]
 * 
 * 1. 트리를 먼저 만든다.
 * 2. 트리에서 삭제할 노드를 빼고 DFS 를 돈다.
 * 3. 만약 삭제된 노드 부분을 만나면 카운트를 그대로 유지한다.
 * 4. 삭제된 노드를 거치지 않고 끝 노드까지 갈 경우 count+1을 해준다.
 */

// 문제점 : 트리모양이 일직선일 경우 문제 발생 (0->1->2->3 트리에서 2를 삭제할경우 1을 카운트 해줘야함)
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q1068 {
	static int count =0;
	static boolean[] lastCheck; // 리프노드인지 체크 (false: 리프노드)
	static boolean[] isVisited;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 노드의 개수
		
		ArrayList<Integer>[] tree = new ArrayList[N];
		for(int i=0; i<N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] parent = new int[N]; // 각 인덱스(노드) 별로 부모노드가 무엇인지 나타내기 위한 배열
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
	    lastCheck = new boolean[N]; // 리프노드인지 체크 (false: 리프노드)
	    isVisited = new boolean[N];
		
		/*
		for(int i=0; i<N; i++) {
			System.out.println("노드 "+i + ": ");
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
		
		if(rootLeafCheck(tree, parent[deleteNode], parent, deleteNode)) { // 삭제할 노드의 루트 노드가 삭제된 뒤 리프노드가 되는지 체크
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
					lastCheck[x] = true; // 자식 노드 있음 (리프노드 X)
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
