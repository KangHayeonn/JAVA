// DFS와 BFS (백준 1260번)

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q1260 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수
		int start = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호
		boolean[] isVisited = new boolean[N+1];
		
		// 인접행렬로 구현
		int[][] arr = new int[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 양방향
			arr[from][to] = 1;
			arr[to][from] = 1; // 단방향일 경우 이부분 삭제
		}
		DFS_Stack_Array(arr, isVisited, N, M, start);
		System.out.println("");
		isVisited = new boolean[N+1];
		BFS_Queue_Array(arr, isVisited, N, M, start);
		
		// 인접리스트로 구현
		/*
		LinkedList<Integer>[] list = new LinkedList[N+1]; // 정점 수 만큼 배열 생성(연결리스트 갯수)
		
		for(int i=0; i<=N; i++) {
			list[i] = new LinkedList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 양방향
			list[from].add(to);
			list[to].add(from); // 단방향일 경우 이부분 삭제
		}
		
		for (int i = 1; i <= N; i++) { // 방문 순서를 위해 오름차순 정렬 
			Collections.sort(list[i]);
		}
		
		DFS_Recursion_LL(list, isVisited, N, M, start);
		System.out.println("");
		isVisited = new boolean[N+1];
		BFS_Queue_LL(list, isVisited, N, M, start);
		*/
	}
	
	// DFS 구현 : 인접리스트 & 재귀
	
	public static void DFS_Recursion_LL(LinkedList<Integer>[] list, boolean[] isVisited, int N, int M, int V) {
		isVisited[V] = true;
		System.out.print(V + " ");
		
		for(Integer j : list[V]) {
			if(!isVisited[j]) DFS_Recursion_LL(list, isVisited, N, M, j);
		}
	}
	
	// DFS 구현 : 인접행렬 & 스택
	public static void DFS_Stack_Array(int[][] arr, boolean[] isVisited, int N, int M, int V) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean check;
		isVisited[V] = true;
		System.out.print(V + " ");
		stack.push(V);
		
		while(!stack.isEmpty()) {
			int x = stack.peek();
			check = false;
			
			for(int i=1; i<N+1; i++) {
				if(arr[x][i] == 1 && !isVisited[i]) {
					stack.push(i);
					System.out.print(i + " ");
					check = true;
					isVisited[i] = true;
					break; // 필수
				}
			}
			
			if(!check) stack.pop();
		}
	}
	
	// BFS 구현 : 인접행렬 & 큐
	public static void BFS_Queue_Array(int[][] arr, boolean[] isVisited, int N, int M, int V) {
		Queue<Integer> queue = new LinkedList<Integer>();
		isVisited[V] = true;
		queue.add(V);
		
		while(!queue.isEmpty()) {
			int x = queue.poll();
			System.out.print(x + " ");
			
			for(int i=1; i<N+1; i++) {
				if(arr[x][i] == 1 && !isVisited[i]) {
					queue.add(i);
					isVisited[i] = true;
				}
			}
		}
	}
	
	// BFS 구현 : 인접리스트 & 큐
	public static void BFS_Queue_LL(LinkedList<Integer>[] list, boolean[] isVisited, int N, int M, int V) {
		Queue<Integer> queue = new LinkedList<Integer>();
		isVisited[V] = true;
		queue.add(V);
		//System.out.print(V + " ");
		
		while(!queue.isEmpty()) {
			int x = queue.poll();
			System.out.print(x + " ");
			
			Iterator<Integer> iter = list[x].listIterator();
			
			while(iter.hasNext()) {
				int y = iter.next();
				if(!isVisited[y]) {
					queue.add(y);
					isVisited[y] = true;
					//System.out.print(y + " ");
				}
			}
		}
	}
}
